package com.pluralsight;

import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Book[] inventory = new Book[21];

        for (int i = 0; i < 20; i++) {
            inventory[i] = new Book(i +1, "100-0" + (i + 1), "Book " + (i + 1));
        }

        Book extraBook = new Book(21, "100-021", "Graf Monte-Cristo");
        extraBook.checkOut("Andrei");
        inventory[20] = extraBook;

        boolean userRunning = true;

        while (userRunning) {
            System.out.println("...............Neighborhood Library.............");
            System.out.println("1. Show Available Books");
            System.out.println("2. Show Checked Out Books");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int useChoice = input.nextInt();
            input.nextLine();

            switch (useChoice) {
                case 1:
                    showAvailableBooks(inventory, input);
                    break;
                case 2:
                    showCheckedOutBooks(inventory, input);
                    break;
                case 0:
                    userRunning = false;
                    System.out.println("Come back soon :)");
                    break;
                default:
                    System.out.println("Try again");
            }
        }
        input.close();
    }
    public static void showAvailableBooks(Book[] inventory, Scanner input) {
        System.out.println("\nAvailable Books:");
        boolean anyAvailable = false;

        for (int i = 0; i < inventory.length; i++) {
            Book book = inventory[i];
            if (!book.isCheckedOut()) {
                System.out.println("ID: " + book.getId() +
                        ", Title: " + book.getTitle() +
                        ", ISBN: " + book.getIsbn());
                anyAvailable = true;
            }
        }

        if (!anyAvailable) {
            System.out.println("All books are checked out.");
            return;
        }

        System.out.print("\nEnter the ID of the book to check out (or 0 to go back): ");
        int id = input.nextInt();
        input.nextLine(); // clear newline

        if (id > 0 && id <= inventory.length) {
            Book book = inventory[id - 1];
            if (!book.isCheckedOut()) {
                System.out.print("Enter your name: ");
                String name = input.nextLine();
                book.checkOut(name);
            } else {
                System.out.println("That book is already checked out.");
            }
        }
    }
    public static void showCheckedOutBooks(Book[] inventory, Scanner input) {
        System.out.println("\nChecked Out Books:");
        boolean anyCheckedOut = false;

        for (Book book : inventory) {
            if (book.isCheckedOut()) {
                System.out.println("ID: " + book.getId() +
                        ", Title: " + book.getTitle() +
                        ", ISBN: " + book.getIsbn() +
                        ", Checked Out To: " + book.getCheckedOutTo());
                anyCheckedOut = true;
            }
        }

        if (!anyCheckedOut) {
            System.out.println("Every book is available.\n");
            return;
        }

        System.out.print("\nEnter the ID of the book to check in (or 0 to go back): ");
        int id = input.nextInt();
        input.nextLine();

        if (id > 0 && id <= inventory.length) {
            Book book = inventory[id - 1];
            if (book.isCheckedOut()) {
                book.checkIn();
            } else {
                System.out.println("That book is not currently checked out.");
            }
        }
    }

}
