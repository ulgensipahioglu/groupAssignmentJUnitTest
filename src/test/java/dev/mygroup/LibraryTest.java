package dev.mygroup;

import java.util.ArrayList;

import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class LibraryTest {

    @Test
    public void testAdvanceDay() {

    }

    @Test
    public void testUserCanBorrowOneBookPerDay() {
        Library library = new Library();
        ArrayList<Book> borrowedBookList = library.borrowBook("Harry Potter");
        for (int i = 0; i < borrowedBookList.size(); i++) {
            Book book = borrowedBookList.get(i);
            if (book.getDaysBorrowed() == 0) {
                borrowedBookList = library.borrowBook("Hitchhiker's guide to the galaxy");
            }
        }
        assertTrue(borrowedBookList.size() == 1);
    }

    @Test
    public void testBorrowBookAddsToBorrowedList() {
        Library library = new Library();
        ArrayList<Book> borrowed = library.borrowBook("Harry Potter");
        boolean found = false;
        for (int i = 0; i < borrowed.size(); i++) {
            if (borrowed.get(i).getName().equalsIgnoreCase("Harry Potter")) {
                found = true;
                break; // Stop searching once found
            }
        }
        assertTrue(found);
    }

    @Test
    public void testMarkBookAsBorrowed() {
        Library library = new Library();
        ArrayList<Book> borrowBookList = library.borrowBook("Harry Potter");
        boolean marked = false;
        for (int i = 0; i < borrowBookList.size(); i++) {
            Book book = borrowBookList.get(i);
            if (book.getName().equalsIgnoreCase("Harry Potter") && book.isBorrowed()) {
                marked = true;
            }
            assertTrue(marked);
        }
    }

    @Test
    public void testBooksInStockListSouldDecreaseDuringBorrowing() {
        Library library = new Library();
        int actualAvailableBook = 0;
        int expextedAvailableStockBooks = library.listAvailableBooks().size() - 1;
        ArrayList<Book> borrowBookList = library.borrowBook("Harry Potter");
        for (int i = 0; i < borrowBookList.size(); i++) {

            Book book = borrowBookList.get(i);
            if (book.getName().equalsIgnoreCase("Harry Potter") && book.isBorrowed()) {
                actualAvailableBook = library.listAvailableBooks().size();
            }
            Assert.assertEquals(expextedAvailableStockBooks, actualAvailableBook);
        }
    }

    @Test
    public void testUserCannotBorrowSameBookTitleAgain() {
        Library library = new Library();
        ArrayList<Book> borrowBookList = library.borrowBook("Ondskan");
        boolean ableToBorrowBook = true;
        String title = "Ondskan";//"Harry Potter",Ondskan;
        int borrowdBooklistSize = borrowBookList.size();

        for (int i = 0; i < borrowBookList.size(); i++) {
            Book book = borrowBookList.get(i);
            if (book.getName().contains(title)) {
                library.borrowBook(title);
                int countBorrowedBookAfter = borrowBookList.size();
                if (borrowdBooklistSize < countBorrowedBookAfter) {
                    ableToBorrowBook = false;
                    break;
                }
                if (borrowdBooklistSize == countBorrowedBookAfter) {
                    ableToBorrowBook = true;
                    break;
                }
            }
        }
        assertTrue(ableToBorrowBook);
    }

    @Test
    public void testUserCannotBorrowBookMoreThan5Times() {
        Library library = new Library();

        ArrayList<Book> borrowBookList = library.borrowBook("Harry Potter");
        library.borrowBook("Hitchhiker's guide to the galaxy");
        library.borrowBook("It ends with us");
        library.borrowBook("Ondskan");
        library.borrowBook("Tempelriddaren");
        library.borrowBook("The Great Gatsby");
        int borrowdBooklistSize = borrowBookList.size();
        assertTrue(borrowdBooklistSize <= 5);
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
