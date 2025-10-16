package dev.mygroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Library {

    private ArrayList<Book> booksInStockList;
    private ArrayList<Book> borrowedBooksList;
    private Scanner scanner;

    public static final boolean AUTHOR = true;
    public static final boolean GENRE = false;

    public Library() {
        this.booksInStockList = new ArrayList<Book>();
        this.borrowedBooksList = new ArrayList<Book>();
        scanner = new Scanner(System.in);
        stockLibrary();
    }

    public ArrayList<Book> borrowBook(String title) {
        Book book;
        for (int i = 0; i < borrowedBooksList.size(); i++) {
            book = borrowedBooksList.get(i);
            if (book.getDaysBorrowed() == 0) {
                System.out.println("Sorry!You can borrow one book per day.");
                return borrowedBooksList;
            }
            if (book.getName().contains(title)) {
                System.out.println("Sorry!You have already borrowed this book.");
                return borrowedBooksList;
            }
            if (borrowedBooksList.size() >= 5) {
                System.out.println("Sorry!You have already borrowed 5 books.");
                return borrowedBooksList;
            }
        }
        boolean found = false;
        for (int i = 0; i < booksInStockList.size(); i++) {
            book = booksInStockList.get(i);
            if (book.getName().equalsIgnoreCase(title)) {
                found = true;
                book.borrowBook();
                this.borrowedBooksList.add(book);
                this.booksInStockList.remove(i);
                System.out.println("You have successfully borrowed the book " + title + ".");
                break;
            }
        }
        if (!found) {
            System.out.println("Select book from the above list.");
        }
        System.out.println();
        return borrowedBooksList;
    }

    public int returnBook(String title) {

        int totalLateFee = 0;

        for (int i = 0; i < borrowedBooksList.size(); i++) {
            Book book = borrowedBooksList.get(i);
            if (book.getName().equalsIgnoreCase(title)) {
                int fee = book.checkLateFee();
                totalLateFee += fee;
                book.returnBook();
                borrowedBooksList.remove(i);
                booksInStockList.add(book);
                break;
            }
        }
        if (totalLateFee == 0) {
            System.out.println("You have successfully returned the book.");
        }
        if (totalLateFee > 0) {
            System.out.println("You have successfully returned the book and " + "you owe us " + totalLateFee + " kr in late fees.");
        }
        System.out.println();
        return totalLateFee;
    }

    public ArrayList<Book> listBorrowedBooks(boolean includeDaysBorrowed) {
        Collections.sort(borrowedBooksList, Comparator.comparing(Book::getDaysBorrowed).reversed());
        for (int i = 0; i < borrowedBooksList.size(); i++) {
            Book book = borrowedBooksList.get(i);
            System.out.print("Title: " + book.getName());
            if (includeDaysBorrowed) {
                System.out.println(", Days borrowed: " + book.getDaysBorrowed());
            } else {
                System.out.println();
            }
        }
        System.out.println();
        return borrowedBooksList;
    }

    public int extendTime(String title) {

        for (int i = 0; i < borrowedBooksList.size(); i++) {
            Book book = borrowedBooksList.get(i);
            if (book.getDaysBorrowed() == 0) {
                System.out.println("Sorry!You can not extend this book because you have already borrowed a book today.");
                return book.getDaysBorrowed();
            }
        }

        for (int i = 0; i < borrowedBooksList.size(); i++) {
            Book book = borrowedBooksList.get(i);
            if (book.getName().equalsIgnoreCase(title)) {
                book.extendTime();
                System.out.println("You have successfully extended time for this book '" + book.getName() + "' today.");
                return book.getDaysBorrowed();
            }
        }

        return Integer.MIN_VALUE;
    }

    public void listBorrowedBooksBy(boolean author) {
        if (author) {
            System.out.println("Authors: ");
            for (int i = 0; i < borrowedBooksList.size(); i++) {
                Book book = borrowedBooksList.get(i);
                System.out.println(book.getAuthor());
            }
            System.out.print("> ");

            String input = scanner.nextLine();
            for (int i = 0; i < borrowedBooksList.size(); i++) {
                Book book = borrowedBooksList.get(i);
                if (book.getAuthor() == input) {
                    System.out.println("Title: " + book.getName());
                }
            }

        } else {
            System.out.println("Genres: ");
            for (int i = 0; i < borrowedBooksList.size(); i++) {
                Book book = borrowedBooksList.get(i);
                System.out.println(book.getGenre());
            }
            System.out.print("> ");

            String input = scanner.nextLine();
            for (int i = 0; i < borrowedBooksList.size(); i++) {
                Book book = borrowedBooksList.get(i);
                if (book.getGenre() == input) {
                    System.out.println("Title: " + book.getName());
                }
            }
        }
    }

    public void advanceDay() {
        for (int i = 0; i < borrowedBooksList.size(); i++) {
            //Book book = borrowedBooksList.get(i);
            borrowedBooksList.get(i).advanceDay();
        }
        System.out.println("Date has been updated successfully.");
    }

    public ArrayList<Book> listAvailableBooks() {
        Collections.sort(booksInStockList, Comparator.comparing(Book::getName));
        for (int i = 0; i < booksInStockList.size(); i++) {
            System.out.println("Title: " + booksInStockList.get(i).getName());
        }

        return booksInStockList;
    }

    private void stockLibrary() {
        for (int i = 0; i < 2; i++) {
            Book book = new Book("Harry Potter", "Fantasy", "J.K Rowling");
            booksInStockList.add(book);
        }
        for (int i = 0; i < 2; i++) {
            Book book = new Book("Hitchhiker's guide to the galaxy", "Sci-Fi", "Douglas Adams");
            booksInStockList.add(book);
        }
        for (int i = 0; i < 2; i++) {
            Book book = new Book("It ends with us", "Romance", "Colleen Hoover");
            booksInStockList.add(book);
        }
        for (int i = 0; i < 2; i++) {
            Book book = new Book("Ondskan", "Fictional Autobiography", "Jan Guillou");
            booksInStockList.add(book);
        }
        for (int i = 0; i < 2; i++) {
            Book book = new Book("Tempelriddaren", "Historical Fiction", "Jan Guillou");
            booksInStockList.add(book);
        }
        for (int i = 0; i < 2; i++) {
            Book book = new Book("The Great Gatsby", "Classic", "F. Scott Fitzgerald");
            booksInStockList.add(book);
        }

        for (int i = 0; i < 2; i++) {
            Book book = new Book("The Lord of the Rings", "Fantasy", "J.R.R. Tolkien");
            booksInStockList.add(book);
        }
    }
}
