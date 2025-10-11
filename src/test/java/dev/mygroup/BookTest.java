package dev.mygroup;

import org.junit.Test;
import static org.junit.Assert.*; // all methots imported

public class BookTest {

    @Test
    // This test checks that the extendTime() method does not reduce daysBorrowed by mistake.
    public void testExtendTimeDecreasesInsteadOfIncreases() {
        Book book = new Book("TestBook", "Fiction", "Tester");
        for (int i = 0; i < 10; i++) book.advanceDay();
        book.extendTime();
        assertNotEquals(17, book.getDaysBorrowed());
        assertEquals(3, book.getDaysBorrowed());
    }


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
}