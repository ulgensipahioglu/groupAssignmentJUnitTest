package dev.mygroup;

import org.junit.Test;
import static org.junit.Assert.*; // all methots imported

public class BookTest {

    @Test
    // This test ensures that late fees are applied only after 7 days.
    public void testCheckLateFeeOnlyAfterSevenDays() {
        Book book = new Book("TestBook", "Fiction", "Tester");

        for (int i = 0; i < 5; i++) book.advanceDay(); // 5 days borrowed -> should be free
        assertEquals(0, book.checkLateFee());

        for (int i = 0; i < 3; i++) book.advanceDay(); // 8 days borrowed -> 1 day late -> 20 kr fee
        assertEquals(20, book.checkLateFee());

        for (int i = 0; i < 2; i++) book.advanceDay(); // 10 days borrowed -> 3 days late -> 60 kr fee
        assertEquals(60, book.checkLateFee());
    }

        @Test
    // Checks that a book can be borrowed and cannot be borrowed twice.
    public void testBorrowBook() {
        Book book = new Book("Harry Potter", "Fantasy", "J.K. Rowling");
        
        book.borrowBook();
        assertTrue(book.isBorrowed());  // borrow first time
        
        book.borrowBook();              // try again
        assertTrue(book.isBorrowed());  // still borrowed, cannot be borrowed again

    }

    @Test
    // Verifies that returning a borrowed book works correctly.
    public void testReturnBook() {
        Book book = new Book("Harry Potter", "Fantasy", "J.K. Rowling");
        
        book.borrowBook();
        assertTrue(book.isBorrowed()); // borrow first time

        book.returnBook();
        assertFalse(book.isBorrowed()); // now it's returned

        book.returnBook();              // try again
        assertFalse(book.isBorrowed()); // still return, cannot return twice
        }

    @Test
    // Ensures that advancing a day increases the number of borrowed days.
    public void testAdvanceDayIncreasesDaysBorrowed() {
        Book book = new Book("Harry Potter", "Fantasy", "J.K. Rowling");
        
        book.borrowBook();
        int before = book.getDaysBorrowed();
        
        book.advanceDay();
        assertEquals(before + 1, book.getDaysBorrowed());
    }

    @Test
    public void testExtendTimeIncreasesDaysBorrowed() {
        Book book = new Book("TestBook", "Fiction", "Tester");
        for (int i = 0; i < 10; i++) book.advanceDay();     // 10 days borrowed
        
        book.extendTime();                                  // add +7 days
        assertEquals(17, book.getDaysBorrowed());  // now 17 days
    }
}