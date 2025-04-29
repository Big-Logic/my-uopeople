import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentRecordSystem {

    private final Scanner scanner = new Scanner(System.in);

    private final ArrayList<Student> students = new ArrayList<>();

    private int id = 0;

    // Private method to clear the console using ANSI escape codes
    private void clearConsole() {
        // Clear the console using ANSI escape codes
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Private method to display a message with a delay
    private void displayMessageWithDelay(String message, int delay) {
        System.out.println(message);
        try {
            Thread.sleep(delay); // Delay in milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Private methods for each operation
    private void addStudent() {
        System.out.println("Enter student first name:");
        String firstName = scanner.nextLine().trim();
        System.out.println("Enter student last name:");
        String lastName = scanner.nextLine().trim();

        int studentId = ++id; // Increment ID for each new student

        // Simulate a delay for adding the student
        displayMessageWithDelay("Adding student...", 2000);

        // Create a new student object and add it to the list
        Student student = new Student(studentId, firstName, lastName);
        students.add(student);
        System.out.println("Student added successfully!");
        // Display the student details
        System.out.println("==========================");
        System.out.println("    Student Details");
        System.out.println("==========================");
        System.out.println("Student ID: " + student.getId());
        System.out.println("Student First Name: " + student.firstName);
        System.out.println("Student Last Name: " + student.lastName);
        System.out.println("Student Grade: " + student.grade);
        System.out.println("==========================");

        System.out.println("Hit enter to continue...");
        scanner.nextLine(); // Wait for user to hit enter
        clearConsole(); // Clear the console
        displayMenu(); // Show the menu again
    }

    // Private method to search for a student by ID
    private void searchStudentById() {
        // Prompt the user for student ID
        System.out.println("Enter student ID to search:");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        // Simulate a delay for searching
        displayMessageWithDelay("Searching for student...", 1000);
        // Loop through the list of students to find the student with the given ID

        // boolean variable to check if the student exists
        boolean studentExists = false;

        // Student object to hold the student
        Student studentToDisplay = null;

        for (Student student : students) {
            if (student.getId() == studentId) {
                // If found, display the student details
                studentExists = true;
                studentToDisplay = student;
                break;
            }
            ;
        }
        ;
        // If the student ID exists, display the details
        if (studentExists) {
            // If found, display the student details
            System.out.println("==========================");
            System.out.println("    Student Details");
            System.out.println("==========================");
            System.out.println("Student ID: " + studentToDisplay.getId());
            System.out.println("Student First Name: " + studentToDisplay.firstName);
            System.out.println("Student Last Name: " + studentToDisplay.lastName);
            System.out.println("Student Grade: " + studentToDisplay.grade);
            System.out.println("==========================");
        } else {
            // If not found, display an error message
            System.out.println("❌ Student ID not found. Please try again.");
        }
        System.out.println("Hit enter to continue...");
        scanner.nextLine(); // Wait for user to hit enter
        displayMessageWithDelay("loading dashboard", 1000);
        clearConsole(); // Clear the console
        displayMenu(); // Show the menu again
    }

    // Private method to view all students
    private void viewStudents() {
        // Check if there are any students in the list
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            // Display the list of students
            // Print the header
            System.out.println("==========================");
            System.out.println("    List of Students");
            System.out.println("==========================");

            for (Student student : students) {
                System.out.println("ID: " + student.getId() + ", Name: " + student.firstName + " " + student.lastName
                        + ", Grade: " + student.grade);
                // Print a separator line
                System.out.println("--------------------------");
            }
            System.out.println("==========================");
        }

        System.out.println("Hit enter to continue...");
        scanner.nextLine(); // Wait for user to hit enter
        clearConsole(); // Clear the console
        displayMenu(); // Show the menu again
    }

    // Private method to handle updating student details
    private void handleUpdateStudentMenuOption(Student studentToUpdate) {
        // Display a menu to select which detail to update
        System.out.println("Select detail to update:");
        System.out.println("f.) First Name");
        System.out.println("l.) Last Name");
        System.out.println("g.) Grade");
        System.out.println("a.) All");
        System.out.println("c.) Cancel");
        // Get the user's choice
        String choice = scanner.nextLine().trim().toLowerCase();

        switch (choice) {
            case "f":
                System.out.println("Enter new first name:");
                String newFirstName = scanner.nextLine();
                studentToUpdate.firstName = newFirstName;
                break;
            case "l":
                System.out.println("Enter new last name:");
                String newLastName = scanner.nextLine();
                studentToUpdate.lastName = newLastName;
                break;
            case "g":
                System.out.println("Enter new grade:");
                double newGrade = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character
                if (newGrade < 0 || newGrade > 100) {
                    System.out.println("❌ Invalid grade. Please enter a grade between 0 and 100.");
                    return; // Exit the method if the grade is invalid
                }
                // Update the student's grade
                studentToUpdate.grade = newGrade;
                break;
            case "a":
                System.out.println("Enter new first name:");
                String allNewFirstName = scanner.nextLine();
                System.out.println("Enter new last name:");
                String allNewLastName = scanner.nextLine();
                System.out.println("Enter new grade:");
                double allNewGrade = scanner.nextDouble();
                studentToUpdate.grade = allNewGrade;
                if (allNewGrade < 0 || allNewGrade > 100) {
                    System.out.println("❌ Invalid grade. Please enter a grade between 0 and 100.");
                    return; // Exit the method if the grade is invalid
                }
                studentToUpdate.firstName = allNewFirstName;
                studentToUpdate.lastName = allNewLastName;
                scanner.nextLine(); // Consume the newline character
                break;
            case "c":
                System.out.println("Update cancelled.");
                return; // Exit the method if the user chooses to cancel
            default:
                System.out.println("❌ Invalid option. Please select a valid option.");
                return; // Exit the method if the choice is invalid
        }

        // Display the updated student details
        System.out.println("Student updated successfully!");
        System.out.println("==========================");
        System.out.println("    Updated Student Details");
        System.out.println("==========================");
        System.out.println("Student ID: " + studentToUpdate.getId());
        System.out.println("Student First Name: " + studentToUpdate.firstName);
        System.out.println("Student Last Name: " + studentToUpdate.lastName);
        System.out.println("Student Grade: " + studentToUpdate.grade);
        System.out.println("==========================");
    }

    // Private method to update a student
    private void updateStudent() {

        // Prompt the user for student ID
        System.out.println("Enter student ID to update:");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // delay for 1 seconds
        displayMessageWithDelay("Searching for student...", 1000);

        // assign a boolean variable to check if the student exists
        boolean studentExists = false;

        // assign a variable to hold the student object
        Student studentToUpdate = null;

        // Loop through the list of students to check if the ID exists
        for (Student student : students) {
            if (student.getId() == studentId) {
                studentExists = true;
                studentToUpdate = student;
                break;

            }
        }

        // If the student ID exists, prompt for new details
        if (studentExists) {

            // Get the user's choice
            handleUpdateStudentMenuOption(studentToUpdate);

        } else {
            // If the student ID does not exist, display an error message
            System.out.println("❌ Student ID not found. Please try again.");
        }

        System.out.println("Hit enter to continue...");
        scanner.nextLine(); // Wait for user to hit enter
        displayMessageWithDelay("loading dashboard", 1000);
        clearConsole(); // Clear the console
        displayMenu(); // Show the menu again
    }

    private void exitSystem() {
        // Simulate a delay for exiting
        displayMessageWithDelay("Exiting the system...", 1000);
        scanner.close();
        System.exit(0);
    }

    // Private method to handle menu options
    private void handleMenuOption() {
        String userInput = scanner.nextLine().toLowerCase().trim();
        switch (userInput) {
            case "a":
                addStudent();
                break;
            case "v":
                viewStudents();
                break;
            case "s":
                searchStudentById();
                break;
            case "u":
                updateStudent();
                break;
            case "e":
                exitSystem();
                break;
            default:
                // print a message if the user input is invalid with an emoji
                System.out.println("❌ Invalid option. Please select an option (a | v | u | d | e):");
                handleMenuOption();
        }
    }

    // Private method to display the menu
    private void displayMenu() {
        // display a well styled menu with emojis attached
        System.out.println("===================================");
        System.out.println("    📚 Student Record System 📚");
        System.out.println("===================================");
        System.out.println("a.) Add Student");
        System.out.println("v.) View Students");
        System.out.println("s.) Search Student");
        System.out.println("u.) Update Student");
        System.out.println("e.) Exit");
        System.out.println("===================================");
        System.out.println("Please select an option (a | v | u | d | e): ");

        // Handle the user input
        handleMenuOption();
    }

    public void startSystem() {
        while (true) {
            try {
                displayMenu();
            } catch (InputMismatchException e) {
                System.out.println("An unexpected error occurred! ❌");
                scanner.nextLine(); // Consume the invalid input
                System.out.println("Press Enter to return to the menu...");
                scanner.nextLine(); // Wait for user input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred! ❌");
            }
        }
    }

    // Constructor
    public StudentRecordSystem() {

    }

    public static void main(String[] args) {
        StudentRecordSystem system = new StudentRecordSystem();
        system.startSystem();
    }
}

class Student {
    private int id;
    String firstName;
    String lastName;
    double grade = 0.00;

    // Public method to get student ID
    public int getId() {
        return id;
    }

    // Public method to set student ID
    public void setId(int studentId) {
        id = studentId;
    }

    // Method to update student
    Student updateStudent() {
        System.out.println("Student updated");
        return this;
    }

    // Constructor
    Student(int studentId, String studentFirstName, String studentLastName) {

        firstName = studentFirstName;
        lastName = studentLastName;

        setId(studentId);
    }
}