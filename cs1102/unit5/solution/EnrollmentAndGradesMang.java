
import java.util.*;

class Student {
    private String name;
    private int id;
    private ArrayList<Course> enrolledCourses; // Map to store courses and grades
    private HashMap<String, Double> grades; // Map to store course IDs and grades

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new ArrayList<>();
        this.grades = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public HashMap<String, Double> getGrades() {
        return grades;
    }

    public void enrollInCourse(Course course) {
        // Check if the student is already enrolled in the course
        for (Course c : enrolledCourses) {
            if (c.getCourseCode().equals(course.getCourseCode())) {
                throw new IllegalArgumentException("Already enrolled in this course.");
            }
        }
        // Enroll the student in the course
        enrolledCourses.add(course);
    }

    public void assignGrade(Course course, double grade) {
        // variable to check if the student is enrolled in the course
        boolean enrolled = false;
        // Check if the student is enrolled in the course
        for (Course c : enrolledCourses) {
            if (c.getCourseCode().equals(course.getCourseCode())) {
                enrolled = true;
                break;
            }
        }
        if (!enrolled) {

            throw new IllegalArgumentException("Unable to add grade!! Student is not enrolled in this course!!");
        }

        // check if grade already assigned
        for (Map.Entry<String, Double> entry : grades.entrySet()) {
            if (entry.getKey() == course.getCourseCode()) {
                throw new IllegalArgumentException("Grade already assigned for this course.");
            }
        }

        // Check if the grade is valid
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        }
        // Add the course and grade to the student's grades
        grades.put(course.getCourseCode(), grade);
    }
}

class Course {
    private String courseCode;
    private String name;
    private int maxCapacity;

    private static int totalEnrolledStudents = 0;

    public Course(String courseCode, String name, int maxCapacity) {
        this.courseCode = courseCode;
        this.name = name;
        this.maxCapacity = maxCapacity;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public static void incrementEnrolledStudents() {
        totalEnrolledStudents++;
    }

    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }
}

class CourseManagement {
    private static ArrayList<Course> courses = new ArrayList<>();
    private static ArrayList<Student> enrolledStudents = new ArrayList<>();
    private static HashMap<Integer, Double> overAllGrades = new HashMap<>();

    // Method to add a new course
    public static void addCourse(String courseCode, String name, int maxCapacity) {
        courses.add(new Course(courseCode, name, maxCapacity));
    }

    public static void enrollStudent(Student student, Course course) {
        // Enroll the student in the course
        student.enrollInCourse(course);
        // Increment the total enrolled students in all Courses
        // This is a static method in the Course class
        Course.incrementEnrolledStudents();
        // variable to check if the student is already enrolled as a student
        boolean alreadyEnrolled = false;
        // Check if the student is already enrolled as a student
        for (Student s : enrolledStudents) {
            if (s.getId() == student.getId()) {
                alreadyEnrolled = true;
                break;
            }
        }
        if (!alreadyEnrolled) {
            enrolledStudents.add(student);
        }

    }

    public static void assignGrade(Student student, Course course, double grade) {
        // Assign a grade to the student for the course
        student.assignGrade(course, grade);
    }

    public static double calculateOverallGrade(Student student) {
        double totalGrade = 0;
        int count = 0;

        for (Map.Entry<String, Double> entry : student.getGrades().entrySet()) {
            totalGrade += entry.getValue();
            count++;
        }

        if (count == 0) {
            return 0;
        }
        // Calculate overall grade
        double overallGrade = totalGrade / count;
        // add the overall grade to the map
        overAllGrades.put(student.getId(), overallGrade);
        return overallGrade;

    }

    public static ArrayList<Course> getCourses() {
        return courses;
    }

    public static ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public static HashMap<Integer, Double> getOverallGrades() {
        return overAllGrades;
    }
}

public class EnrollmentAndGradesMang {
    private static Scanner scanner = new Scanner(System.in);

    // Method to display the main menu
    public static void displayMainMenu() {
        // Display a nicely formatted menu with emojis
        System.out.println("📚 Course Management System 📚");
        System.out.println("c) Courses");
        System.out.println("e) Enrollments");
        System.out.println("g) Grades");
        System.out.println("q) Exit");
        System.out.print("Please select an option: (c|s|e|g|q) ");

        String option = scanner.nextLine().toLowerCase();

        switch (option) {
            case "c":
                displayCoursesMenu();
                break;
            case "e":
                displayEnrollmentsMenu();
                break;
            case "g":
                displayGradesMenu();
                break;
            case "q":
                exit();
                return;
            default:
                System.out.println("Invalid option. Please try again.");
        }

    }

    // Method to display Courses menu
    public static void displayCoursesMenu() {
        System.out.println("a) Add Course");
        System.out.println("v) View Courses");
        System.out.println("b) Back to Main Menu");
        System.out.println("q) Exit");
        System.out.print("Please select an option: (a|v|b|q) ");

        String option = scanner.nextLine().toLowerCase();
        switch (option) {
            case "a":
                addCourse();
                break;
            case "v":
                viewAllCourses();
                break;
            case "b":
                displayMainMenu(); // Go back to main menu
                return;
            case "q":
                exit();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }

    }

    // Method to display Enrollments menu
    public static void displayEnrollmentsMenu() {
        System.out.println("a) Enroll Student");
        System.out.println("v) View Enrollments");
        System.out.println("b) Back to Main Menu");
        System.out.println("q) Exit");
        System.out.print("Please select an option: (a|v|b|q) ");
        String option = scanner.nextLine().toLowerCase();
        switch (option) {
            case "a":
                enrollStudent();
                break;
            case "v":
                viewAllEnrollments();
                break;
            case "b":
                displayMainMenu(); // Go back to main menu
                return;
            case "q":
                exit();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    // Method to display Grades menu
    public static void displayGradesMenu() {
        System.out.println("a) Assign Grade");
        System.out.println("v) View Grades");
        System.out.println("c) Calculate Overall Grade");
        System.out.println("o) Overall Grades");
        System.out.println("b) Back to Main Menu");
        System.out.println("q) Exit");
        System.out.print("Please select an option: (a|v|b|q) ");
        String option = scanner.nextLine().toLowerCase();
        switch (option) {
            case "a":
                assignGrade();
                break;
            case "v":
                viewAllGrades();
                break;
            case "c":
                calculateOverallGrade();
                break;
            case "o":
                viewOverallGrades();
                break;
            case "b":
                displayMainMenu(); // Go back to main menu
                return;
            case "q":
                exit();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    // Method to exit the program
    public static void exit() {
        System.out.println("Exiting system. Goodbye!");
        scanner.close();
        System.exit(0);
    }

    // Method to add a new course
    private static void addCourse() {
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();
        System.out.print("Enter maximum capacity: ");
        int maxCapacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        CourseManagement.addCourse(courseCode, name, maxCapacity);

        System.out.println("Course added successfully!");
        System.out.println("Hit Enter to continue...");
        scanner.nextLine();
        displayCoursesMenu();
    }

    // Method to view all courses
    private static void viewAllCourses() {
        System.out.println("=====================================");
        System.out.println("          Available Courses:");
        System.out.println("=====================================");
        ArrayList<Course> courses = CourseManagement.getCourses();
        for (Course course : courses) {
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Course Name: " + course.getName());
            System.out.println("Maximum Capacity: " + course.getMaxCapacity());
            System.out.println("--------------------------------");
        }
        System.out.println("Hit Enter to continue...");
        scanner.nextLine();
        displayCoursesMenu();
    }

    // Method to enroll a student in a course
    private static void enrollStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = new Student(name, id);

        viewAllCourses();
        System.out.print("Enter course code to enroll in: ");
        String courseCode = scanner.nextLine();
        Course course = null;
        for (Course c : CourseManagement.getCourses()) {
            if (c.getCourseCode().equals(courseCode)) {
                course = c;
                break;
            }
        }
        if (course == null) {
            System.out.println("Invalid course code.");
            System.out.println("Hit Enter to continue...");
            scanner.nextLine();
            displayEnrollmentsMenu();
        }

        try {
            CourseManagement.enrollStudent(student, course);
            System.out.println("Student enrolled successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Hit Enter to continue...");
            scanner.nextLine();
            displayEnrollmentsMenu();
        }

    }

    // Method to view all enrollments
    private static void viewAllEnrollments() {
        System.out.println("=====================================");
        System.out.println("          Enrolled Students:");
        System.out.println("=====================================");
        ArrayList<Student> students = CourseManagement.getEnrolledStudents();
        for (Student student : students) {
            System.out.println("Student Name: " + student.getName());
            System.out.println("Student ID: " + student.getId());
            System.out.println("--------------------------------");
        }
        System.out.println("Hit Enter to continue...");
        scanner.nextLine();
        displayEnrollmentsMenu();
    }

    // Method to assign a grade to a student
    private static void assignGrade() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = null;
        for (Student s : CourseManagement.getEnrolledStudents()) {
            if (s.getId() == id) {
                student = s;
                break;
            }
        }
        if (student == null) {
            System.out.println("Invalid student ID.");
            System.out.println("Hit Enter to continue...");
            scanner.nextLine();
            displayGradesMenu();
        }

        viewAllCourses();
        System.out.print("Enter course code to assign grade: ");
        String courseCode = scanner.nextLine();
        Course course = null;
        for (Course c : CourseManagement.getCourses()) {
            if (c.getCourseCode().equals(courseCode)) {
                course = c;
                break;
            }
        }
        if (course == null) {
            System.out.println("Invalid course code.");
            System.out.println("Hit Enter to continue...");
            scanner.nextLine();
            displayGradesMenu();
        }

        System.out.print("Enter grade: ");
        double grade = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        try {
            CourseManagement.assignGrade(student, course, grade);
            System.out.println("Grade assigned successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Hit Enter to continue...");
            scanner.nextLine();
            displayGradesMenu();
        }
    }

    // Method to view all grades
    private static void viewAllGrades() {
        System.out.println("=====================================");
        System.out.println("          Student Grades:");
        System.out.println("=====================================");
        ArrayList<Student> students = CourseManagement.getEnrolledStudents();
        for (Student student : students) {
            System.out.println("Student Name: " + student.getName());
            System.out.println("Student ID: " + student.getId());
            HashMap<String, Double> grades = student.getGrades();
            for (Map.Entry<String, Double> entry : grades.entrySet()) {
                System.out.println("Course Code: " + entry.getKey() + ", Grade: " + entry.getValue());
            }
            System.out.println("--------------------------------");
        }
        System.out.println("Hit Enter to continue...");
        scanner.nextLine();
        displayGradesMenu();
    }

    // Method to calculate overall grade for a student
    private static void calculateOverallGrade() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = null;
        for (Student s : CourseManagement.getEnrolledStudents()) {
            if (s.getId() == id) {
                student = s;
                break;
            }
        }
        if (student == null) {
            System.out.println("Invalid student ID.");
            System.out.println("Hit Enter to continue...");
            scanner.nextLine();
            displayGradesMenu();
        }

        double overallGrade = CourseManagement.calculateOverallGrade(student);
        System.out.println("Overall Grade for " + student.getName() + ": " + overallGrade);
        System.out.println("Hit Enter to continue...");
        scanner.nextLine();
        displayGradesMenu();
    }

    // Method to view overall grades
    private static void viewOverallGrades() {
        System.out.println("=====================================");
        System.out.println("          Overall Grades:");
        System.out.println("=====================================");
        HashMap<Integer, Double> overallGrades = CourseManagement.getOverallGrades();
        for (Map.Entry<Integer, Double> entry : overallGrades.entrySet()) {
            System.out.println("Student ID: " + entry.getKey() + ", Overall Grade: " + entry.getValue());
        }
        System.out.println("Hit Enter to continue...");
        scanner.nextLine();
        displayGradesMenu();
    }

    public static void main(String[] args) {
        while (true) {
            try {
                displayMainMenu();
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Hit Enter to continue...");
                scanner.nextLine();
            }
        }
    }
}