package dev.mygroup;

public class Book {

    private String name;
    private String genre;
    private String author;
    private boolean borrowed;
    private int daysBorrowed;

    public Book(String name, String genre, String author) {
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.borrowed = false;
        this.daysBorrowed = 0;
    }

    public int checkLateFee() {
        if (daysBorrowed >= 7) {
            int lateDays = daysBorrowed - 6;
            System.out.println("The book is " + daysBorrowed + " days late");
            return lateDays * 20;
        }
        return 0;
    }

    /*
     * public int checkLateFee() {
    // Calculate the number of days the book is late
    
    int lateDays = daysBorrowed - 7; // The first 7 days are free

    if (lateDays > 0) {             // Only apply a late fee if the book is overdue

        // Print how many days the book is late
        System.out.println("The book is " + lateDays + " days late");

        return lateDays * 20;       // Charge 20 kr per late day
    }

    return 0;                       // No fee if the book is borrowed for 7 days or less
}
     */

    public void extendTime() {
        this.daysBorrowed += -7;
    }

    /*
     * public void extendTime() {
        this.daysBorrowed += 7; // add 7 days
}
     */

    public void advanceDay() {
        this.daysBorrowed++;
    }

    public void borrowBook() {
             this.borrowed = true;
             this.daysBorrowed = 0;
    }

    /*
     * public void borrowBook() {
     * if (!this.borrowed) { // If the book is not borrowed, you can borrowed
     *  this.borrowed = true;
        this.daysBorrowed = 0;
            }
     */

    public void returnBook() {
        this.borrowed = false;
    }

    /*
     *     public void returnBook() {
        if (this.borrowed) {  // If the book is borrowed, you can return
            this.borrowed = false;
        }
    }
     */

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public int getDaysBorrowed() {
        return daysBorrowed;
    }
}
