package dev.mygroup;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class LibraryTest {
    
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

}
