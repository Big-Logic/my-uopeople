import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    final static ArrayList<Book> books = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    static void clearScreen() {
        // Clear the console screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // search for a book by title
    static boolean searchBookByTitle(String title) {
        // Search for a book by title
        boolean bookFound = false;

        // Check if the book exists in the list
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                bookFound = true;
                break; // Exit the loop if the book is found
            }
        }

        return bookFound; // Return true if book is found, false otherwise
    }

    static Book addBooks() {
        // Prompt the user for book details
        System.out.println("Enter book title: ");
        String title = scanner.nextLine().trim().toLowerCase();

        System.out.println("Enter book author: ");
        String author = scanner.nextLine().trim().toLowerCase();

        System.out.println("Enter quantity to add: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        final boolean bookExists = searchBookByTitle(title);

        // Check if the book already exists
        if (bookExists) {

            Book existingBook = null;

            // Loop through the books list to find the existing book
            for (Book book : books) {
                if (book.title.equalsIgnoreCase(title)) {

                    try {
                        // If the book exists, add quantity
                        book.addQuantity(quantity);

                        existingBook = book; // Store the existing book
                        break; // Exit the loop if the book is found

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        addBooks(); // Call the method again for valid input
                        break; // Exit the loop if the book is found
                    }

                }
            }
            return existingBook;
        }

        try {
            final Book book = new Book(title, author, quantity);
            // Add the book to the books list
            books.add(book);

            return book;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            addBooks(); // Call the method again for valid input
        }

        return null; // Return null if the book is not added
    }

    static void exitLibrary() {
        // print an exit message with emoji attached
        System.out.println("Thank you for using the Library Management System! 📚");
        System.out.println("We hope to see you again soon! 😊");
        System.out.println("Exiting the system...");
        scanner.close();
        try {

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.exit(0);
    }

    static void processMenuSelection() {
        // Prompt the user for input
        System.out.println("Please select an option (a/b/r/d/q): ");
        // Read the user input
        String userInput = scanner.nextLine();

        // Clear the screen
        clearScreen();

        // Process the user input
        // Using switch-case to handle different options
        switch (userInput.toLowerCase()) {
            case "a":
                final Book book = addBooks();
                System.out.println("✅ Book added successfully.");
                System.out.println("Book Title: " + book.title);
                System.out.println("Book Author: " + book.author);
                System.out.println("Book Quantity: " + book.quantity);
                displayMenu(); // Call the method again to display the menu
                break;
            case "b":
                System.out.println("Removing a book...");
                // Remove book logic here
                break;
            case "r":
                System.out.println("Searching for a book...");
                // Search book logic here
                break;
            case "d":
                System.out.println("Displaying all books...");
                // Display all books logic here
                break;
            case "q":
                exitLibrary();
                break;
            default:
                System.out.println("❌ Invalid option. Please try again.");
                processMenuSelection(); // Call the method again for valid input
                break;
        }
    }

    // Method to display the menu
    static void displayMenu() {

        System.out.println("Loading the menu...");

        // Simulate loading time
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        // Display the menu options
        System.out.println("===================================");
        System.out.println("          Library Menu");
        System.out.println("===================================");
        System.out.println("a.) Add Book");
        System.out.println("b.) Remove Book");
        System.out.println("r.) Search Book");
        System.out.println("d.) Display All Books");
        System.out.println("q.) Exit");

        // Prompt the user for input
        processMenuSelection();
    }

    static void startLibrary() {
        // Start the library system
        displayMenu();
    }

    public static void main(String[] args) {
        startLibrary();
    }
}

// Book class for creating book objects
class Book {
    String title;
    String author;
    int quantity = 0;

    // Methods to add quantity
    public int addQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Invalid quantity. Cannot add negative or zero quantity ❌");
        }
        this.quantity += quantity;
        return this.quantity;
    }

    // Methods to remove quantity
    public int removeQuantity(int quantity) {
        // Check if the quantity is valid
        // Check if the quantity to remove is greater than available
        if (quantity <= 0 || this.quantity < quantity) {
            throw new IllegalArgumentException(
                    "Invalid quantity. Cannot remove more than available or quantity is less than 0 ❌");
        }

        // Remove the quantity
        this.quantity -= quantity;

        return this.quantity;
    }

    Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;

        this.addQuantity(quantity);
    }
}