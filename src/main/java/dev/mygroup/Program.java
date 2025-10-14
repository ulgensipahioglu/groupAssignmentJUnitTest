package dev.mygroup;

import java.util.Scanner;

public class Program {

    Scanner scanner = new Scanner(System.in);
    Library library;

    public void run() {
        library = new Library();
        Boolean exit = false;
        while (!exit) {
            System.out.println("Welcome to the library! \n"
                    + "Press B to borrow a book \n"
                    + "Press D to advance day \n"
                    + "Press R to return a book \n"
                    + "Press L to list all currently borrowed books \n"
                    + "Press A to list all currently borrowed books by an author \n"
                    + "Press G to list all currently borrowed books by genre \n"
                    + "Press E to extend borrowing \n"
                    + "Press Q to exit");
            System.out.print("> ");
            String input = scanner.nextLine();

            switch (input.toUpperCase()) {
                case "B":
                    borrowBook();
                    break;
                case "D":
                    library.advanceDay();
                    break;
                case "R":
                    returnBook();
                    break;
                case "L":
                    library.listBorrowedBooks(true);
                    break;
                case "A":
                    library.listBorrowedBooksBy(Library.AUTHOR);
                    break;
                case "G":
                    library.listBorrowedBooksBy(Library.GENRE);
                    break;
                case "E":
                    extendTime();
                    break;
                case "Q":
                    exit = true;
                    break;
                default:
                    break;
            }
            // library.advanceDay();
        }
    }

    private void extendTime() {
        System.out.println("Which book do you want to extend?");
        library.listBorrowedBooks(false);
        System.out.print("> ");

        String input = scanner.nextLine();
        library.extendTime(input);
    }

    private void returnBook() {
        System.out.println("Which book do you want to return?");
        library.listBorrowedBooks(false);
        System.out.print("> ");

        String input = scanner.nextLine();

        library.returnBook(input);
    }

    private void borrowBook() {
        System.out.println("Which book do you want to borrow?");
        library.listAvailableBooks();
        System.out.print("> ");

        String input = scanner.nextLine();
        library.borrowBook(input);
    }

}
