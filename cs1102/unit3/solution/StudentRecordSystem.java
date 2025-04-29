import java.util.ArrayList;
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
        String firstName = scanner.nextLine();
        System.out.println("Enter student last name:");
        String lastName = scanner.nextLine();

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

    private void viewStudents() {
        // Code to view all students
        System.out.println("Viewing all students...");
        // Implementation here
    }

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
                studentToUpdate.grade = newGrade;
                break;
            case "a":
                System.out.println("Enter new first name:");
                String allNewFirstName = scanner.nextLine();
                System.out.println("Enter new last name:");
                String allNewLastName = scanner.nextLine();
                System.out.println("Enter new grade:");
                double allNewGrade = scanner.nextDouble();
                studentToUpdate.firstName = allNewFirstName;
                studentToUpdate.lastName = allNewLastName;
                studentToUpdate.grade = allNewGrade;
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

    private void deleteStudent() {
        // Code to delete a student
        System.out.println("Deleting a student...");
        // Implementation here
    }

    private void exitSystem() {
        // Code to exit the system
        System.out.println("Exiting the system...");
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
            case "u":
                updateStudent();
                break;
            case "d":
                deleteStudent();
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
        System.out.println("u.) Update Student");
        System.out.println("d.) Delete Student");
        System.out.println("e.) Exit");
        System.out.println("===================================");
        System.out.println("Please select an option (a | v | u | d | e): ");

        // Handle the user input
        handleMenuOption();
    }

    public void startSystem() {
        displayMenu();
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