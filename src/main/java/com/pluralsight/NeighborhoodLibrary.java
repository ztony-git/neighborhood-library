package com.pluralsight;

import java.util.Scanner;

public class NeighborhoodLibrary {
    static Book[] library = {
            new Book(1, "978-1836646334", "Python Illustrated: Not another boring Python book, learn programming the fun way"),
            new Book(2, "979-8855413779", "Lord of Mysteries, Vol. 1: The Clown, Part I"),
            new Book(3, "978-0593395561", "Project Hail Mary"),
            new Book(4, "978-0804139021", "The Martian"),
            new Book(5, "978-1558585362", "The Rainbow Fish"),
            new Book(6, "978-1426224386", "Lost in Space: 5,000 Facts for Navigating the Universe"),
            new Book(7, "978-1668032145", "Protocols: An Operating Manual for the Human Body"),
            new Book(8, "978-0307352156", "Quiet: The Power of Introverts in a World That Can't Stop Talking"),
            new Book(9, "978-0399226908", "The Very Hungry Caterpillar"),
            new Book(10, "978-1800566774", "JavaScript from Beginner to Professional: Learn JavaScript quickly by building fun, interactive, and dynamic web apps, games, and pages"),
            new Book(11, "978-0060935467", "To Kill a Mockingbird"),
            new Book(12, "978-0452284234", "1984"),
            new Book(13, "978-1451673319", "Fahrenheit 451"),
            new Book(14, "978-0140283334", "Lord of the Flies"),
            new Book(15, "978-0395404256", "The Handmaid's Tale"),
            new Book(16, "978-1412811903", "Animal Farm"),
            new Book(17, "978-0743273565", "The Great Gatsby"),
            new Book(18, "978-0142000670", "Of Mice and Men"),
            new Book(19, "978-0143128571", "Romeo and Juliet"),
            new Book(20, "978-0143039952", "The Odyssey")
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String in;

        do {
            printHomeScreen();
            in =  scanner.nextLine().trim();

            switch  (in) {
                case "1" -> {
                    printBooks(false);
                    checkOutBook(scanner, true);
                }

                case "2" -> {
                    printBooks(true);
                    checkOutBook(scanner, false);
                }
            }
        } while (!in.equalsIgnoreCase("3"));
    }

    public static void printDivider() {
        System.out.println("-----------------------------------------------------------------");
    }

    public static void printHomeScreen() {
        printDivider();
        System.out.println("1 - Show available books \n" +
                "2 - Show checked out books \n" +
                "3 - Exit program");
    }

    public static void printBooks(boolean available) {
        printDivider();
        System.out.printf("%-3s %-15s %s" + (available ? " → %s" : "") + "%n", "Id", "ISBN", "Title", "Checked Out By");

        for(Book book : library) {
            if(book.isCheckedOut() == available) {
                book.display();
            }
        }
    }

    public static void checkOutBook(Scanner scanner, boolean isCheckingOut) {
//        Enter an Id to check out, or 0 to go back: 2
//        Enter your name: Maya
//        Dune is checked out to Maya.
        String in;
        do {
            printDivider();
            System.out.printf("C - Check %s Book%nX - Return to Home Menu%n", isCheckingOut ? "Out" : "In");
            in = scanner.nextLine().trim();

            switch (in.toLowerCase()) {
                case "c" -> {
                    String idChosen = "-1";
                    do {
                        try {
                            System.out.print("Enter an Id to check out, or 0 to go back: ");
                            idChosen = scanner.nextLine().trim();

                            if (idChosen.equalsIgnoreCase("0")) {
                                break;
                            }

                            int idInt = Integer.parseInt(idChosen);
                            int found = -1;

                            for (int i = 0; i < library.length; i++) {
                                if (library[i].getId() == idInt && library[i].isCheckedOut() != isCheckingOut) {
                                    found = i;
                                }
                            }
                            if (found == -1)
                                System.out.println("Book Id not found!");
                            else {
                                if (isCheckingOut) {
                                    System.out.print("Enter your name: ");
                                    library[found].setCheckedOutTo(scanner.nextLine().trim());
                                }
                                library[found].setCheckedOut(isCheckingOut);
                                System.out.printf("%s is checked %s %s%n", library[found].getTitle(), isCheckingOut ? "out to" : "in by", library[found].getCheckedOutTo());
//                            System.out.println(library[found].getTitle() + " is checked out to " + library[found].getCheckedOutTo());

                                if (!isCheckingOut) {
                                    library[found].setCheckedOutTo("");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Please enter a valid Id!");
                        }
                    } while(!in.equalsIgnoreCase("0"));
                }
            }
        } while(!in.equalsIgnoreCase("X"));
    }



}
