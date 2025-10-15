package dev.mygroup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class LibraryTest {

    //1.1:The user caanot borrow a book which is not listed in the stock list
    @Test
    public void testUserShouldBorrowBookFromStockList() {
        Library library = new Library();
        library.borrowBook("Abcde");
        int borrowedBook = library.listBorrowedBooks(false).size();
        assertTrue(borrowedBook == 0);
    }

    // 1.2:The book is marked as borrowed or not
    @Test
    public void testMarkBookAsBorrowed() {
        Library library = new Library();
        library.borrowBook("Harry Potter");
        int totalBorrowedBook = library.listBorrowedBooks(false).size();
        boolean marked = false;
        for (int i = 0; i < totalBorrowedBook; i++) {
            Book book = library.listBorrowedBooks(false).get(i);
            if (book.getName().equalsIgnoreCase("Harry Potter") && book.isBorrowed()) {
                marked = true;
            }
            assertTrue(marked);
        }
    }

    //1.3:The booked is listed in list of borrowed book
    @Test
    public void testBorrowBookAddsToBorrowedList() {
        Library library = new Library();
        library.borrowBook("Harry Potter");
        int totalBorrowedBook = library.listBorrowedBooks(false).size();
        boolean found = false;
        for (int i = 0; i < totalBorrowedBook; i++) {
            if (library.listBorrowedBooks(false).get(i).getName().equalsIgnoreCase("Harry Potter")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    //1.4:Checking borrow list and stock list before and after borrowing a book
    @Test
    public void testBooksInStockListSouldDecreaseDuringBorrowing() {
        Library library = new Library();
        int availableStockListBefore = library.listAvailableBooks().size();
        int borrowedBookListBefore = library.listBorrowedBooks(false).size();
        library.borrowBook("Harry Potter");
        int availableStockListAfter = library.listAvailableBooks().size();
        int borrowedBookListAfter = library.listBorrowedBooks(false).size();
        assertTrue((availableStockListAfter == availableStockListBefore - 1)
                && (borrowedBookListAfter == borrowedBookListBefore + 1));
    }

    //1.5:Updating the current day
    @Test
    public void testAdvanceDay() {
        Library library = new Library();
        library.borrowBook("Harry Potter");
        library.advanceDay();
        int currentDay = library.listBorrowedBooks(false).get(0).getDaysBorrowed();
        assertTrue(currentDay == 1);
    }

    //1.6:The user may only borrow 1 book per day.
    @Test
    public void testUserCanBorrowOneBookPerDay() {
        Library library = new Library();
        library.borrowBook("Harry Potter");
        library.borrowBook("Tempelriddaren");
        int totalBorrowedBook = library.listBorrowedBooks(false).size();
        //assertTrue(totalBorrowedBook == 1);
        assertEquals(1, totalBorrowedBook);
    }

    //1.7:The user may at most have 5 borrowed books at a time.
    @Test
    public void testUserCannotBorrowBookMoreThan5Times() {
        Library library = new Library();

        library.borrowBook("Harry Potter");
        library.advanceDay();
        library.borrowBook("Hitchhiker's guide to the galaxy");
        library.advanceDay();
        library.borrowBook("It ends with us");
        library.advanceDay();
        library.borrowBook("Ondskan");
        library.advanceDay();
        library.borrowBook("Tempelriddaren");
        library.advanceDay();
        library.borrowBook("The Great Gatsby");
        int borrowdBooklistSize = library.listBorrowedBooks(false).size();
        assertEquals(5, borrowdBooklistSize);
    }

    //1.8:The user may only borrow one book per title. .
    @Test
    public void testUserCannotBorrowSameBookTitleAgain() {
        Library library = new Library();
        library.borrowBook("Ondskan");
        int borrowedBefore = library.listBorrowedBooks(false).size();
        library.advanceDay();
        library.borrowBook("Ondskan");
        int borrowedAfter = library.listBorrowedBooks(false).size();
        assertEquals(borrowedAfter, borrowedBefore);
    }

    // 2. The user may return as many books as he/she wishes per day.
    @Test
    public void testUserCanRetrunBookAsManyBooksPerDay() {
        Library library = new Library();
        library.borrowBook("Harry Potter");
        library.advanceDay();
        library.borrowBook("Hitchhiker's guide to the galaxy");
        library.advanceDay();
        library.borrowBook("It ends with us");

        int borrowedbeforeRetrun = library.listBorrowedBooks(false).size();
        int availableBeforeReturn = library.listAvailableBooks().size();

        library.returnBook("Harry Potter");
        library.returnBook("Hitchhiker's guide to the galaxy");
        library.returnBook("It ends with us");

        int borrowedAfterReturn = library.listBorrowedBooks(false).size();
        int availableAfterReturn = library.listAvailableBooks().size();

        assertTrue((borrowedAfterReturn == borrowedbeforeRetrun - 3)
                && (availableAfterReturn == availableBeforeReturn + 3));
    }

    // 3. For every day the book is late, the user has to pay a fine of 20 kr. 
    @Test
    public void testFineAppliedAfterSevenDays() {
        Library library = new Library();
        library.borrowBook("Harry Potter");

        for (int i = 0; i < 10; i++) {
            library.advanceDay();
        }
        Book book = library.listBorrowedBooks(false).get(0);
        int lateFee = library.returnBook("Harry Potter");
        int lateDays = book.getDaysBorrowed() - 7;
        assertTrue(lateFee == lateDays * 20);
    }

    //The user can use the extend function to reset the book back to 0 days borrowed, this counts as borrowing 1 book.
    @Test
    public void testExtendFunctionResetsBorrowedDaysToZero() {
        Library library = new Library();
        library.borrowBook("Harry Potter");

        for (int i = 0; i < 6; i++) {
            library.advanceDay();
        }

        int day = library.extendTime("Harry Potter");
        assertTrue(day == 0);
    }
    //The user should not borrow a book again after extend on same day

    @Test
    public void testUserShouldNotBorrowAgainAfterExtendSameDay() {
        int totalBorrowedBook = 0;
        Library library = new Library();
        library.borrowBook("Harry Potter");

        for (int i = 0; i < 6; i++) {
            library.advanceDay();
        }
        library.extendTime("Harry Potter");
        totalBorrowedBook = library.listBorrowedBooks(false).size();
        library.borrowBook("Ondskan");
        totalBorrowedBook = library.listBorrowedBooks(false).size();

        assertTrue(totalBorrowedBook == 1);
    }

    @Test
    public void testExtendTime() {

    }

    @Test
    public void testListAvailableBooks() {

    }

    @Test
    public void testListBorrowedBooks() {

    }

    @Test
    public void testListBorrowedBooksBy() {

    }

    @Test
    public void testReturnBook() {

    }
}
