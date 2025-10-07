package dev.mygroup;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class LibraryTest {

    @Test
    public void testAdvanceDay() {

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
    public void testBorrowMarksBookAsBorrowed() {
        Library library = new Library();
        ArrayList<Book> borrowed = library.borrowBook("Harry Potter");
        boolean found = false;
        for (Book b : borrowed) {
            if (b.getName().equalsIgnoreCase("Harry Potter") && b.isBorrowed()) {
                found = true;
                break; // Stop searching once found
            }
        }
        assertTrue(found);
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
