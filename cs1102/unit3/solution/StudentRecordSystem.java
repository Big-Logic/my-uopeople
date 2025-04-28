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

    // Private methods for each operation
    private void addStudent() {
        System.out.println("Enter student first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter student last name:");
        String lastName = scanner.nextLine();

        int studentId = ++id; // Increment ID for each new student

        System.out.println("Adding student...");

        // Simulate a delay for adding the student
        try {
            Thread.sleep(1000); // 2 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Create a new student object and add it to the list
        Student student = new Student(studentId, firstName, lastName);
        students.add(student);
        System.out.println("Student added successfully!");
        // Display the student details
        System.out.println("==========================");
        System.out.println("    Student Details");
        System.out.println("==========================");
        System.out.println("Student ID: " + student.id);
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

    private void updateStudent() {
        // Code to update a student
        System.out.println("Updating a student...");
        // Implementation here
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
    int id;
    String firstName;
    String lastName;
    double grade = 0.00;

    // Method to update student
    Student updateStudent() {
        System.out.println("Student updated");
        return this;
    }

    // Constructor
    Student(int studentId, String studentFirstName, String studentLastName) {
        id = studentId;
        firstName = studentFirstName;
        lastName = studentLastName;
    }
}