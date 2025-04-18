package com.pluralsight;

import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Book[] inventory = new Book[20];

        for (int i = 0; i < inventory.length; i++) {
            inventory[i] = new Book(i +1, "100-0" + (i + 1), "Book " + (i + 1));
        }

//        showAvailableBooks(inventory);
//        showCheckedOutBooks(inventory);

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
                    showAvailableBooks(inventory);
                    break;
                case 2:
                    showCheckedOutBooks(inventory);
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
    public static void showAvailableBooks(Book[] inventory) {
        System.out.println("Available Books:");
        for (int i = 0; i < inventory.length; i++) {
            Book book = inventory[i];
            if (!book.isCheckedOut()) {
                System.out.println("ID: " + book.getId() +
                        ", Title: " + book.getTitle() +
                        ", ISBN: " + book.getIsbn());
            }
        }
    }
    public static void showCheckedOutBooks(Book[] inventory) {
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
        }
    }

}
