package dev.mygroup;

import org.junit.Test;
import static org.junit.Assert.*; // all methots imported

public class BookTest {

    @Test
    // This test ensures that late fees are applied only after 7 days.
    public void testCheckLateFeeOnlyAfterSevenDays() {
        Book book = new Book("TestBook", "Fiction", "Tester");

        // 5 days borrowed -> should be free
        for (int i = 0; i < 5; i++) book.advanceDay();
        assertEquals(0, book.checkLateFee());

        // 8 days borrowed -> 1 day late -> 20 kr fee
        for (int i = 0; i < 3; i++) book.advanceDay();
        assertEquals(20, book.checkLateFee());

        // 10 days borrowed -> 3 days late -> 60 kr fee
        for (int i = 0; i < 2; i++) book.advanceDay();
        assertEquals(60, book.checkLateFee());
    }

        @Test
    // Checks that a book can be borrowed and cannot be borrowed twice.
    public void testBorrowBook() {
        Book book = new Book("Harry Potter", "Fantasy", "J.K. Rowling");
        book.borrowBook();
        assertTrue(book.isBorrowed());  // after borrow
        book.borrowBook();              // try again
        assertTrue(book.isBorrowed());  // still borrowed (no change)

    }

    @Test
    // Verifies that returning a borrowed book works correctly.
    public void testReturnBook() {
        Book book = new Book("Harry Potter", "Fantasy", "J.K. Rowling");
        book.borrowBook();
        book.borrowBook();
        assertTrue(book.isBorrowed()); // borrow worked

        book.returnBook();
        assertFalse(book.isBorrowed()); // now it's returned

        book.returnBook(); 
        assertFalse(book.isBorrowed()); // still false, cannot return twice
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
    // This test checks the daysBorrowed value of the extendTime() method.
    public void testExtendTimeDecreasesInsteadOfIncreases() {
        Book book = new Book("TestBook", "Fiction", "Tester");
        for (int i = 0; i < 10; i++) book.advanceDay();
        book.extendTime();
        assertNotEquals(17, book.getDaysBorrowed());
        assertEquals(3, book.getDaysBorrowed());
    }
}