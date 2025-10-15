package dev.mygroup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class LibraryTest {

    @Test
    public void testAdvanceDay() {

        Library library = new Library();
        library.borrowBook("Harry Potter");
        library.advanceDay();
        int currentDay = library.listBorrowedBooks(false).get(0).getDaysBorrowed();
        assertTrue(currentDay == 1);
    }

    @Test
    public void testUserCanBorrowOneBookPerDay() {
        Library library = new Library();
        library.borrowBook("Harry Potter");
        int totalBorrowedBook = library.listBorrowedBooks(false).size();
        for (int i = 0; i < totalBorrowedBook; i++) {
            Book book = library.listBorrowedBooks(false).get(i);
            if (book.getDaysBorrowed() == 0) {
                library.borrowBook("Hitchhiker's guide to the galaxy");
            }
        }
        assertTrue(totalBorrowedBook == 1);
    }

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

    @Test
    public void testBooksInStockListSouldDecreaseDuringBorrowing() {
        Library library = new Library();
        int availableBookListBeforeBorrow = library.listAvailableBooks().size();
        int availableBookListAfterBorrow = 0;
        library.borrowBook("Harry Potter");
        int totalBorrowedBook = library.listBorrowedBooks(false).size();
        for (int i = 0; i < totalBorrowedBook; i++) {
            Book book = library.listBorrowedBooks(false).get(i);
            if (book.getName().equalsIgnoreCase("Harry Potter") && book.isBorrowed()) {
                availableBookListAfterBorrow = library.listAvailableBooks().size();
            }
            assertTrue(availableBookListAfterBorrow == availableBookListBeforeBorrow - 1);
        }
    }

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

    @Test
    public void testUserShouldBorrowBookFromStockList() {
        Library library = new Library();
        library.borrowBook("Abcde");
        int borrowedBook = library.listBorrowedBooks(false).size();
        assertTrue(borrowedBook == 0);
    }

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
        assertTrue(borrowdBooklistSize <= 5);
    }

    @Test
    public void testUserCanRetrunBookAsManyBooksPerDay() {
        Library library = new Library();
        library.borrowBook("Harry Potter");
        library.advanceDay();
        library.borrowBook("Hitchhiker's guide to the galaxy");
        library.advanceDay();
        library.borrowBook("It ends with us");

        int borrowedbefore = library.listBorrowedBooks(false).size();
        int availableBefore = library.listAvailableBooks().size();

        library.returnBook("Harry Potter");
        library.returnBook("Hitchhiker's guide to the galaxy");
        library.returnBook("It ends with us");

        int borrowedAfter = library.listBorrowedBooks(false).size();
        int availableAfter = library.listAvailableBooks().size();

        assertTrue((borrowedAfter == borrowedbefore - 3)
                && (availableAfter == availableBefore + 3));
    }

    @Test
    public void testFineAppliedAfterSevenDays() {
        Library library = new Library();
        library.borrowBook("Harry Potter");

        for (int i = 0; i < 9; i++) {
            library.advanceDay();
        }
        Book book = library.listBorrowedBooks(false).get(0);
        int lateFee = library.returnBook("Harry Potter");
        int lateDays = book.getDaysBorrowed() - 7;
        assertTrue(lateFee == lateDays * 20);
    }

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
