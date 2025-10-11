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

    public void extendTime() {
        this.daysBorrowed += -7;
    }

    public void advanceDay() {
        this.daysBorrowed++;
    }

    public void borrowBook() {
        this.borrowed = true;
        this.daysBorrowed = 0;
    }

    public void returnBook() {
        this.borrowed = false;
    }

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
