
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
    private static HashMap<Integer, Double> overAllGrades = new HashMap<>();
    private static ArrayList<Student> enrolledStudents = new ArrayList<>();

    // Method to add a new course
    public static Course addCourse(String courseCode, String name, int maxCapacity) {
        Course course = new Course(courseCode, name, maxCapacity);
        // Check if the course already exists
        for (Course c : courses) {
            if (c.getCourseCode().equals(courseCode) || c.getName().equals(name) || maxCapacity <= 0) {
                throw new IllegalArgumentException("Course already exists or maxCapacity is <= 0.");
            }
        }
        courses.add(course);
        return course;
    }

    public static void enrollStudent(Student student, Course course) {
        // Enroll the student in the course
        student.enrollInCourse(course);
        // Increment the total enrolled students in all Courses
        // This is a static method in the Course class
        Course.incrementEnrolledStudents();

        // Add the student to the list of enrolled students
        boolean alreadyEnrolled = false;
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

    // Method to calculate the overall grade for a student
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

    public static HashMap<Integer, Double> getOverallGrades() {
        return overAllGrades;
    }

    public static ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
}

public class EnrollmentAndGradesMang {
    private static Scanner scanner = new Scanner(System.in);

    // Method to display the main menu
    public static void displayMainMenu() {
        // Display a nicely formatted menu with emojis
        System.out.println("📚 Enrollment & Grades Management System 📚");
        System.out.println("a) Add new Course");
        System.out.println("e) Enroll Student");
        System.out.println("g) Asign Grade");
        System.out.println("c) Calculate Overall Grade");
        System.out.println("q) Exit");
        System.out.print("Please select an option: (a|e|g|c|q|) ");

        String option = scanner.nextLine().toLowerCase();

        switch (option) {
            case "a":
                addCourse();
                break;
            case "e":
                enrollStudent();
                break;
            case "g":
                assignGrade();
                break;
            case "c":
                calculateOverallGrade();
                break;
            case "q":
                exit();
                return;
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

    public static void clearScreen() {
        // Clear the console screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Method to return to the main menu
    public static void returnToMainMenu() {
        System.out.println("Hit Enter to continue...");
        scanner.nextLine();
        clearScreen();
        displayMainMenu();
    }

    // Method to add a new course
    private static void addCourse() {
        clearScreen();
        System.out.println("📚 Add New Course 📚");
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();
        System.out.print("Enter maximum capacity: ");
        int maxCapacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {

            Course course = CourseManagement.addCourse(courseCode, name, maxCapacity);

            System.out.println("Course added successfully!");
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Course Name: " + course.getName());
            System.out.println("Maximum Capacity: " + course.getMaxCapacity());
            //
            returnToMainMenu();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            returnToMainMenu();
        }

    }

    // Method to enroll a student in a course
    private static void enrollStudent() {
        clearScreen();
        System.out.println("📚 Enroll Student 📚");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        int id = Math.abs(new Random().nextInt());
        Student student = new Student(name, id);

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
            returnToMainMenu();
            return;
        }

        try {
            CourseManagement.enrollStudent(student, course);
            System.out.println("Student enrolled successfully!");
            System.out.println("Student ID: " + student.getId());
            System.out.println("Student Name: " + student.getName());
            System.out.println("--------------------------------");
            System.out.println("Enrolled in Course: " + course.getName());
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Maximum Capacity: " + course.getMaxCapacity());
            System.out.println("--------------------------------");
            returnToMainMenu();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            returnToMainMenu();
        }

    }

    // Method to assign a grade to a student
    private static void assignGrade() {
        clearScreen();
        System.out.println("📚 Assign Grade 📚");
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
            returnToMainMenu();
            return;
        }

        ArrayList<Course> enrolledCourses = student.getEnrolledCourses();
        System.out.print("Enter course code to assign grade: ");
        String courseCode = scanner.nextLine();
        Course course = null;
        for (Course c : enrolledCourses) {
            if (c.getCourseCode().equals(courseCode)) {
                course = c;
                break;
            }
        }
        if (course == null) {
            System.out.println("Invalid course code or student is not enroll in the course.");
            returnToMainMenu();
            return;
        }

        System.out.print("Enter grade: ");
        double grade = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        try {
            CourseManagement.assignGrade(student, course, grade);
            System.out.println("Grade assigned successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            returnToMainMenu();
        }
    }

    // Method to calculate overall grade for a student
    private static void calculateOverallGrade() {
        clearScreen();
        System.out.println("📚 Calculate Overall Grade 📚");
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
            returnToMainMenu();
            return;
        }

        double overallGrade = CourseManagement.calculateOverallGrade(student);
        System.out.println("Overall Grade for " + student.getName() + ": " + overallGrade);
        returnToMainMenu();
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