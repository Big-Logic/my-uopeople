import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.util.*;

class Student {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String major;

    public Student(int id, String firstName, String middleName, String lastName, String major) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.major = major;
    }

    // Create all getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}

class Course {
    private String courseCode;
    private String name;
    private int maxCapacity;

    public Course(String courseCode, String name, int maxCapacity) {
        this.courseCode = courseCode;
        this.name = name;
        setMaxCapacity(maxCapacity);
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

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxCapacity(int maxCapacity) {
        // Check if maxCapacity is greater than 0
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Max capacity must be greater than 0.");
        }
        // Set the max capacity
        this.maxCapacity = maxCapacity;
    }
}

class Grade {
    private int studentId;
    private String courseCode;
    private double grade;

    public Grade(int studentId, String courseCode, double grade) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        setGrade(grade);
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        }
        this.grade = grade;
    }
}

class SchoolManagement {
    private static ArrayList<Course> courses = new ArrayList<>();
    private static HashMap<Integer, ArrayList<Grade>> grades = new HashMap<>();
    private static ArrayList<Student> enrolledStudents = new ArrayList<>();
    private static HashMap<Integer, ArrayList<Course>> studentCourses = new HashMap<>();

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

    public static void enrollStudent(Student student) {
        // Check if the student is already enrolled
        for (Student s : enrolledStudents) {
            if (s.getId() == student.getId()) {
                throw new IllegalArgumentException("Student is already enrolled.");
            }
        }
        // Add the student to the list of enrolled students
        enrolledStudents.add(student);
    }

    public static void assignGrade(int studentId, String courseCode, double grade) {
        // Check if the student exists
        Student student = enrolledStudents.stream()
                .filter(s -> s.getId() == studentId)
                .findFirst()
                .orElse(null);
        if (student == null) {
            throw new IllegalArgumentException("Student not found.");
        }

        // Check if the course exists
        Course course = courses.stream()
                .filter(c -> c.getCourseCode().equals(courseCode))
                .findFirst()
                .orElse(null);

        ArrayList<Grade> studentGrades = grades.get(studentId);

        if (course == null) {

            throw new IllegalArgumentException("Course not found.");
        }

        // Check if the student is enrolled in the course
        ArrayList<Course> courses = studentCourses.get(studentId);
        if (courses == null || !courses.stream().anyMatch(c -> c.getCourseCode().equals(courseCode))) {
            throw new IllegalArgumentException("Student is not enrolled in this course.");
        }

        if (studentGrades == null) {
            studentGrades = new ArrayList<>();
            grades.put(studentId, studentGrades);
        } else {
            // Check if the course already has a grade for the student
            for (Grade g : studentGrades) {
                if (g.getCourseCode().equals(courseCode)) {
                    throw new IllegalArgumentException("Grade for this course already exists for the student.");
                }
            }
        }
        // Create a new grade for the student
        Grade gradeObj = new Grade(studentId, courseCode, grade);
        studentGrades.add(gradeObj);

    }

    // Method to enroll a student in a course
    public static void enrollStudentInCourse(int studentId, String courseCode) {
        Course course = courses.stream()
                .filter(c -> c.getCourseCode().equals(courseCode))
                .findFirst()
                .orElse(null);
        // Check if the student is already enrolled in the course
        if (studentCourses.containsKey(studentId)) {
            ArrayList<Course> courses = studentCourses.get(studentId);
            // Check if the course already exists in the student's list of courses
            for (Course c : courses) {
                if (c.getCourseCode().equals(courseCode)) {
                    throw new IllegalArgumentException("Student is already enrolled in this course.");
                }
            }
        } else {
            studentCourses.put(studentId, new ArrayList<>());
        }
        // Add the course to the student's list of courses
        studentCourses.get(studentId).add(course);
    }

    public static ArrayList<Course> getCourses() {
        return courses;
    }

    public static HashMap<Integer, ArrayList<Grade>> getOverallGrades() {
        return grades;
    }

    public static ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public static HashMap<Integer, ArrayList<Course>> getStudentCourses() {
        return studentCourses;
    }

    public static HashMap<Integer, ArrayList<Grade>> getGrades() {
        return grades;
    }
}

public class StudentManagementSystem {
    private static JFrame frame = new JFrame("Student Management System");
    private static JPanel mainPanel = new JPanel(new CardLayout()); // CardLayout for switching pages

    // Method to display the main menu
    private void displayMainMenu() {
        // Menu bar
        JMenuBar menuBar = new JMenuBar();

        JMenuItem studentMenu = new JMenuItem("Students");
        JMenuItem courseMenu = new JMenuItem("Courses");
        JMenuItem enrollmentMenu = new JMenuItem("Enrollments");
        JMenuItem gradeMenu = new JMenuItem("Grades");

        // Add menu to the menu bar
        menuBar.add(studentMenu);
        menuBar.add(courseMenu);
        menuBar.add(enrollmentMenu);
        menuBar.add(gradeMenu);

        // Add style to display menu at the left side
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Add menu to the frame
        frame.setJMenuBar(menuBar);

        // Add event listeners to menu items
        studentMenu.addActionListener(e -> {
            switchPage("students");
        });

        courseMenu.addActionListener(e -> {
            switchPage("courses");
        });

        enrollmentMenu.addActionListener(e -> {
            // Placeholder for enrollment page
            switchPage("enrollments");
        });

        gradeMenu.addActionListener(e -> {
            switchPage("grades");
        });

    }
    /*
     * 
     * 
     * 
     * 
     * STUDENTS FEATURES
     * 
     * 
     * 
     * 
     * 
     */

    // Method to refresh the student table
    private void refreshStudentTable(DefaultTableModel model) {
        // Clear the existing rows
        model.setRowCount(0);

        // Add rows for each enrolled student
        for (Student student : SchoolManagement.getEnrolledStudents()) {
            model.addRow(new Object[] {
                    student.getId(),
                    student.getFirstName(),
                    student.getMiddleName(),
                    student.getLastName(),
                    student.getMajor()
            });
        }
    }

    // Method to list all enrolled students
    private JTable listEnrolledStudents(JTable studentTable, DefaultTableModel model) {

        for (Student student : SchoolManagement.getEnrolledStudents()) {

            model.addRow(new Object[] { student.getId(), student.getFirstName(), student.getMiddleName(),
                    student.getLastName(), student.getMajor(), });
        }

        return studentTable;
    }

    // Method to show the form for adding a new student
    private void showAddStudentForm(DefaultTableModel model) {
        // Create a dialog for adding a student
        JDialog dialog = new JDialog(frame, "Add Student", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(frame);

        // Create components
        // JLabel idLabel = new JLabel("ID:");
        // JTextField idField = new JTextField(15);

        JLabel firstNameLabel = new JLabel("*First Name:");
        JTextField firstNameField = new JTextField(15);

        JLabel middleNameLabel = new JLabel("Middle Name:");
        JTextField middleNameField = new JTextField(15);

        JLabel lastNameLabel = new JLabel("*Last Name:");
        JTextField lastNameField = new JTextField(15);

        JLabel majorLabel = new JLabel("*Major:");
        JTextField majorField = new JTextField(15);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            try {
                // Generate a new ID for the student
                int id = SchoolManagement.getEnrolledStudents().size() + 1; // Auto-increment ID

                String firstName = firstNameField.getText();
                String middleName = middleNameField.getText();
                String lastName = lastNameField.getText();
                String major = majorField.getText();

                if (firstName.isEmpty() || lastName.isEmpty() || major.isEmpty()) {
                    throw new IllegalArgumentException("All fields marked * are required.");
                }

                Student student = new Student(id, firstName, middleName, lastName, major);
                SchoolManagement.enrollStudent(student);
                JOptionPane.showMessageDialog(dialog, "Student added successfully!");
                dialog.dispose();
                refreshStudentTable(model);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "ID must be a number.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage());
            }
        });

        // Add action listener for the cancel button
        cancelButton.addActionListener(e -> dialog.dispose());

        // Layout configuration using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the dialog
        // gbc.gridx = 0;
        // gbc.gridy = 0;
        // dialog.add(idLabel, gbc);

        // gbc.gridx = 1;
        // dialog.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(firstNameLabel, gbc);

        gbc.gridx = 1;
        dialog.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(middleNameLabel, gbc);

        gbc.gridx = 1;
        dialog.add(middleNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        dialog.add(lastNameLabel, gbc);

        gbc.gridx = 1;
        dialog.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        dialog.add(majorLabel, gbc);

        gbc.gridx = 1;
        dialog.add(majorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        dialog.add(submitButton, gbc);

        gbc.gridx = 1;
        dialog.add(cancelButton, gbc);

        // Display the dialog
        dialog.setVisible(true);
    }

    // Method to show the form for updating a student
    private void showUpdateStudentForm(DefaultTableModel model) {
        // Create a dialog for updating a student
        JDialog dialog = new JDialog(frame, "Update Student", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(frame);

        // Create a dropdown for selecting a student by ID
        JLabel selectStudentLabel = new JLabel("Select Student ID:");
        JComboBox<Integer> studentDropdown = new JComboBox<>();
        for (Student student : SchoolManagement.getEnrolledStudents()) {
            studentDropdown.addItem(student.getId());
        }

        // Create components for updating student details
        JLabel firstNameLabel = new JLabel("*First Name:");
        JTextField firstNameField = new JTextField(15);

        JLabel middleNameLabel = new JLabel("Middle Name:");
        JTextField middleNameField = new JTextField(15);

        JLabel lastNameLabel = new JLabel("*Last Name:");
        JTextField lastNameField = new JTextField(15);

        JLabel majorLabel = new JLabel("*Major:");
        JTextField majorField = new JTextField(15);

        JButton submitButton = new JButton("Update");
        JButton cancelButton = new JButton("Cancel");

        // Add action listener for the dropdown to populate fields
        studentDropdown.addActionListener(e -> {
            int selectedId = (int) studentDropdown.getSelectedItem();
            Student selectedStudent = SchoolManagement.getEnrolledStudents().stream()
                    .filter(student -> student.getId() == selectedId)
                    .findFirst()
                    .orElse(null);

            if (selectedStudent != null) {
                firstNameField.setText(selectedStudent.getFirstName());
                middleNameField.setText(selectedStudent.getMiddleName());
                lastNameField.setText(selectedStudent.getLastName());
                majorField.setText(selectedStudent.getMajor());
            }
        });

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            try {
                int selectedId = (int) studentDropdown.getSelectedItem();
                String updatedFirstName = firstNameField.getText();
                String updatedMiddleName = middleNameField.getText();
                String updatedLastName = lastNameField.getText();
                String updatedMajor = majorField.getText();

                if (updatedFirstName.isEmpty() || updatedLastName.isEmpty() || updatedMajor.isEmpty()) {
                    throw new IllegalArgumentException("All fields marked * are required.");
                }

                Student selectedStudent = SchoolManagement.getEnrolledStudents().stream()
                        .filter(student -> student.getId() == selectedId)
                        .findFirst()
                        .orElse(null);

                if (selectedStudent != null) {
                    selectedStudent.setFirstName(updatedFirstName);
                    selectedStudent.setMiddleName(updatedMiddleName);
                    selectedStudent.setLastName(updatedLastName);
                    selectedStudent.setMajor(updatedMajor);
                    JOptionPane.showMessageDialog(dialog, "Student updated successfully!");
                    dialog.dispose();
                    refreshStudentTable(model);
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage());
            }
        });

        // Add action listener for the cancel button
        cancelButton.addActionListener(e -> dialog.dispose());

        // Layout configuration using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the dialog
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(selectStudentLabel, gbc);

        gbc.gridx = 1;
        dialog.add(studentDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(firstNameLabel, gbc);

        gbc.gridx = 1;
        dialog.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(middleNameLabel, gbc);

        gbc.gridx = 1;
        dialog.add(middleNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        dialog.add(lastNameLabel, gbc);

        gbc.gridx = 1;
        dialog.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        dialog.add(majorLabel, gbc);

        gbc.gridx = 1;
        dialog.add(majorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        dialog.add(submitButton, gbc);

        gbc.gridx = 1;
        dialog.add(cancelButton, gbc);

        // Display the dialog
        dialog.setVisible(true);
    }

    private void showDeleteStudentForm(DefaultTableModel model) {
        // Create a dialog for deleting a student
        JDialog dialog = new JDialog(frame, "Delete Student", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(frame);

        // Create a dropdown for selecting a student by ID
        JLabel selectStudentLabel = new JLabel("Select Student ID:");
        JComboBox<Integer> studentDropdown = new JComboBox<>();
        for (Student student : SchoolManagement.getEnrolledStudents()) {
            studentDropdown.addItem(student.getId());
        }

        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Cancel");

        // Add action listener for the delete button
        deleteButton.addActionListener(e -> {
            try {
                int selectedId = (int) studentDropdown.getSelectedItem();
                Student selectedStudent = SchoolManagement.getEnrolledStudents().stream()
                        .filter(student -> student.getId() == selectedId)
                        .findFirst()
                        .orElse(null);

                if (selectedStudent != null) {
                    SchoolManagement.getEnrolledStudents().remove(selectedStudent);
                    JOptionPane.showMessageDialog(dialog, "Student deleted successfully!");
                    dialog.dispose();
                    refreshStudentTable(model);
                } else {
                    throw new IllegalArgumentException("Student not found.");
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage());
            }
        });

        // Add action listener for the cancel button
        cancelButton.addActionListener(e -> dialog.dispose());

        // Layout configuration using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the dialog
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(selectStudentLabel, gbc);

        gbc.gridx = 1;
        dialog.add(studentDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(deleteButton, gbc);

        gbc.gridx = 1;
        dialog.add(cancelButton, gbc);

        // Display the dialog
        dialog.setVisible(true);
    }

    // Method to create a button panel
    private JPanel createStudentButtonPanel(DefaultTableModel model) {

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton addStudentButton = new JButton("Add Student");
        JButton updateStudentButton = new JButton("Update Student");
        JButton deleteStudentButton = new JButton("Delete Student");

        // Add action listeners to buttons
        addStudentButton.addActionListener(e -> {

            showAddStudentForm(model);
        });

        updateStudentButton.addActionListener(e -> {
            showUpdateStudentForm(model);
        });

        deleteStudentButton.addActionListener(e -> {
            showDeleteStudentForm(model);
        });

        // Add buttons to the panel
        buttonPanel.add(addStudentButton);
        buttonPanel.add(updateStudentButton);
        buttonPanel.add(deleteStudentButton);

        return buttonPanel;
    }

    // Method to display students dashboard
    private JPanel displayStudentsDashboard() {

        // Create a new panel for students dashboard
        JPanel studentsPanel = new JPanel();
        studentsPanel.setLayout(new BorderLayout());

        // Create a table model for displaying students
        // Create a table to display students
        String[] columnNames = { "ID", "First Name", "Middle Name", "Last Name", "Major" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable studentTable = new JTable(model);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(listEnrolledStudents(studentTable, model));
        studentsPanel.add(scrollPane, BorderLayout.CENTER);
        studentsPanel.add(createStudentButtonPanel(model), BorderLayout.SOUTH);

        return studentsPanel;
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * COURSES FEATURES
     */

    // Implement the same features in students for courses
    private JPanel displayCoursesDashboard() {
        JPanel coursesPanel = new JPanel();
        coursesPanel.setLayout(new BorderLayout());

        // Create a table model for displaying courses
        String[] columnNames = { "Course Code", "Name", "Max Capacity" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable courseTable = new JTable(model);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(courseTable);
        coursesPanel.add(scrollPane, BorderLayout.CENTER);
        coursesPanel.add(createCourseButtonPanel(model), BorderLayout.SOUTH);

        return coursesPanel;
    }

    // Method to create a button panel for courses
    private JPanel createCourseButtonPanel(DefaultTableModel model) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton addCourseButton = new JButton("Add Course");
        JButton updateCourseButton = new JButton("Update Course");
        JButton deleteCourseButton = new JButton("Delete Course");

        // Add action listeners to buttons
        addCourseButton.addActionListener(e -> {
            showAddCourseForm(model);
        });

        updateCourseButton.addActionListener(e -> {
            showUpdateCourseForm(model);
        });

        deleteCourseButton.addActionListener(e -> {
            showDeleteCourseForm(model);
        });

        // Add buttons to the panel
        buttonPanel.add(addCourseButton);
        buttonPanel.add(updateCourseButton);
        buttonPanel.add(deleteCourseButton);

        return buttonPanel;
    }

    // Method to show the form for adding a new course
    private void showAddCourseForm(DefaultTableModel model) {
        // Create a dialog for adding a course
        JDialog dialog = new JDialog(frame, "Add Course", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(frame);

        // Create components
        JLabel courseCodeLabel = new JLabel("*Course Code:");
        JTextField courseCodeField = new JTextField(15);

        JLabel nameLabel = new JLabel("*Name:");
        JTextField nameField = new JTextField(15);

        JLabel maxCapacityLabel = new JLabel("*Max Capacity:");
        JTextField maxCapacityField = new JTextField(15);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            try {
                String courseCode = courseCodeField.getText();
                String name = nameField.getText();
                int maxCapacity = Integer.parseInt(maxCapacityField.getText());

                if (courseCode.isEmpty() || name.isEmpty() || maxCapacity <= 0) {
                    throw new IllegalArgumentException(
                            "All fields marked * are required and Max Capacity must be > 0.");
                }

                SchoolManagement.addCourse(courseCode, name, maxCapacity);
                JOptionPane.showMessageDialog(dialog, "Course added successfully!");
                dialog.dispose();
                refreshCourseTable(model);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Max Capacity must be a number.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage());
            }
        });

        // Add action listener for the cancel button
        cancelButton.addActionListener(e -> dialog.dispose());

        // Layout configuration using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the dialog
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(courseCodeLabel, gbc);

        gbc.gridx = 1;
        dialog.add(courseCodeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(nameLabel, gbc);

        gbc.gridx = 1;
        dialog.add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(maxCapacityLabel, gbc);
        gbc.gridx = 1;
        dialog.add(maxCapacityField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        dialog.add(submitButton, gbc);
        gbc.gridx = 1;
        dialog.add(cancelButton, gbc);
        // Display the dialog
        dialog.setVisible(true);
    }

    // Method to refresh the course table
    private void refreshCourseTable(DefaultTableModel model) {
        // Clear the existing rows
        model.setRowCount(0);

        // Add rows for each course
        for (Course course : SchoolManagement.getCourses()) {
            model.addRow(new Object[] { course.getCourseCode(), course.getName(), course.getMaxCapacity() });
        }
    }

    // Method to show the form for updating a course
    private void showUpdateCourseForm(DefaultTableModel model) {
        // Create a dialog for updating a course
        JDialog dialog = new JDialog(frame, "Update Course", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(frame);

        // Create a dropdown for selecting a course by code
        JLabel selectCourseLabel = new JLabel("Select Course Code:");
        JComboBox<String> courseDropdown = new JComboBox<>();
        for (Course course : SchoolManagement.getCourses()) {
            courseDropdown.addItem(course.getCourseCode());
        }

        // Create components for updating course details
        JLabel nameLabel = new JLabel("*Name:");
        JTextField nameField = new JTextField(15);

        JLabel maxCapacityLabel = new JLabel("*Max Capacity:");
        JTextField maxCapacityField = new JTextField(15);

        JButton submitButton = new JButton("Update");
        JButton cancelButton = new JButton("Cancel");

        // Add action listener for the dropdown to populate fields
        courseDropdown.addActionListener(e -> {
            String selectedCode = (String) courseDropdown.getSelectedItem();
            Course selectedCourse = SchoolManagement.getCourses().stream()
                    .filter(course -> course.getCourseCode().equals(selectedCode))
                    .findFirst()
                    .orElse(null);

            if (selectedCourse != null) {
                nameField.setText(selectedCourse.getName());
                maxCapacityField.setText(String.valueOf(selectedCourse.getMaxCapacity()));
            }
        });

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            try {
                String selectedCode = (String) courseDropdown.getSelectedItem();
                String updatedName = nameField.getText();
                int updatedMaxCapacity = Integer.parseInt(maxCapacityField.getText());

                if (updatedName.isEmpty() || updatedMaxCapacity <= 0) {
                    throw new IllegalArgumentException(
                            "All fields marked * are required and Max Capacity must be > 0.");
                }

                Course selectedCourse = SchoolManagement.getCourses().stream()
                        .filter(course -> course.getCourseCode().equals(selectedCode))
                        .findFirst()
                        .orElse(null);

                if (selectedCourse != null) {
                    selectedCourse.setName(updatedName);
                    selectedCourse.setMaxCapacity(updatedMaxCapacity);
                    JOptionPane.showMessageDialog(dialog, "Course updated successfully!");
                    dialog.dispose();
                    refreshCourseTable(model);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Max Capacity must be a number.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage());
            }
        });

        // Add action listener for the cancel button
        cancelButton.addActionListener(e -> dialog.dispose());

        // Layout configuration using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the dialog
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(selectCourseLabel, gbc);

        gbc.gridx = 1;
        dialog.add(courseDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(nameLabel, gbc);

        gbc.gridx = 1;
        dialog.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(maxCapacityLabel, gbc);

        gbc.gridx = 1;
        dialog.add(maxCapacityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        dialog.add(submitButton, gbc);

        gbc.gridx = 1;
        dialog.add(cancelButton, gbc);

        // Display the dialog
        dialog.setVisible(true);
    }

    // Method to show the form for deleting a course
    private void showDeleteCourseForm(DefaultTableModel model) {
        // Create a dialog for deleting a course
        JDialog dialog = new JDialog(frame, "Delete Course", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(frame);

        // Create a dropdown for selecting a course by code
        JLabel selectCourseLabel = new JLabel("Select Course Code:");
        JComboBox<String> courseDropdown = new JComboBox<>();
        for (Course course : SchoolManagement.getCourses()) {
            courseDropdown.addItem(course.getCourseCode());
        }

        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Cancel");

        // Add action listener for the delete button
        deleteButton.addActionListener(e -> {
            try {
                String selectedCode = (String) courseDropdown.getSelectedItem();
                Course selectedCourse = SchoolManagement.getCourses().stream()
                        .filter(course -> course.getCourseCode().equals(selectedCode))
                        .findFirst()
                        .orElse(null);

                if (selectedCourse != null) {
                    SchoolManagement.getCourses().remove(selectedCourse);
                    JOptionPane.showMessageDialog(dialog, "Course deleted successfully!");
                    dialog.dispose();
                    refreshCourseTable(model);
                } else {
                    throw new IllegalArgumentException("Course not found.");
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage());
            }
        });

        // Add action listener for the cancel button
        cancelButton.addActionListener(e -> dialog.dispose());

        // Layout configuration using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the dialog
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(selectCourseLabel, gbc);

        gbc.gridx = 1;
        dialog.add(courseDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(deleteButton, gbc);

        gbc.gridx = 1;
        dialog.add(cancelButton, gbc);

        // Display the dialog
        dialog.setVisible(true);
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * ENROLLMENTS FEATURES
     * 
     * 
     * 
     */

    private JPanel displayEnrollmentsDashboard() {
        JPanel enrollmentsPanel = new JPanel();
        enrollmentsPanel.setLayout(new BorderLayout());

        // Create a table model for displaying enrollments
        String[] columnNames = { "Student ID", "Course Code" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable enrollmentTable = new JTable(model);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(enrollmentTable);
        enrollmentsPanel.add(scrollPane, BorderLayout.CENTER);
        enrollmentsPanel.add(createEnrollmentButtonPanel(model), BorderLayout.SOUTH);

        return enrollmentsPanel;
    }

    // Method to create a button panel for enrollments
    private JPanel createEnrollmentButtonPanel(DefaultTableModel model) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton enrollStudentButton = new JButton("Enroll Student");

        // Add action listeners to buttons
        enrollStudentButton.addActionListener(e -> {
            showEnrollStudentForm(model);
        });

        // Add buttons to the panel
        buttonPanel.add(enrollStudentButton);
        return buttonPanel;
    }

    // Method to show the form for enrolling a student in a course
    private void showEnrollStudentForm(DefaultTableModel model) {
        // Create a dialog for enrolling a student
        JDialog dialog = new JDialog(frame, "Enroll Student", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(frame);

        // Create components
        JLabel studentIdLabel = new JLabel("*Student ID:");
        JTextField studentIdField = new JTextField(15);

        JLabel courseCodeLabel = new JLabel("*Course Code:");
        JTextField courseCodeField = new JTextField(15);

        JButton submitButton = new JButton("Enroll");
        JButton cancelButton = new JButton("Cancel");

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            try {
                int studentId = Integer.parseInt(studentIdField.getText());
                String courseCode = courseCodeField.getText();

                if (courseCode.isEmpty()) {
                    throw new IllegalArgumentException("Course Code is required.");
                }

                Student student = SchoolManagement.getEnrolledStudents().stream()
                        .filter(s -> s.getId() == studentId)
                        .findFirst()
                        .orElse(null);

                if (student == null) {
                    throw new IllegalArgumentException("Student not found.");
                }

                Course course = SchoolManagement.getCourses().stream()
                        .filter(c -> c.getCourseCode().equals(courseCode))
                        .findFirst()
                        .orElse(null);

                if (course == null) {
                    throw new IllegalArgumentException("Course not found.");
                }

                // Enroll the student in the course

                SchoolManagement.enrollStudentInCourse(studentId, courseCode);
                JOptionPane.showMessageDialog(dialog, "Student enrolled successfully!");
                dialog.dispose();
                refreshEnrollmentTable(model);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Student ID must be a number.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage());
            }
        });

        // Add action listener for the cancel button
        cancelButton.addActionListener(e -> dialog.dispose());

        // Layout configuration using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the dialog
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(studentIdLabel, gbc);
        gbc.gridx = 1;
        dialog.add(studentIdField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(courseCodeLabel, gbc);
        gbc.gridx = 1;
        dialog.add(courseCodeField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(submitButton, gbc);
        gbc.gridx = 1;
        dialog.add(cancelButton, gbc);
        // Display the dialog
        dialog.setVisible(true);
    }

    // Method to refresh the enrollment table
    private void refreshEnrollmentTable(DefaultTableModel model) {
        // Clear the existing rows
        model.setRowCount(0);

        for (Map.Entry<Integer, ArrayList<Course>> entry : SchoolManagement.getStudentCourses().entrySet()) {
            int studentId = entry.getKey();
            for (Course course : entry.getValue()) {
                model.addRow(new Object[] { studentId, course.getCourseCode(), course.getName() });
            }
        }
    }

    /*
     * 
     * 
     * 
     * 
     * ASSIGN GRADES FEATURES
     * 
     * 
     */

    // Method to display the grades dashboard
    private JPanel displayGradesDashboard() {
        JPanel gradesPanel = new JPanel();
        gradesPanel.setLayout(new BorderLayout());

        // Create a table model for displaying grades
        String[] columnNames = { "Student ID", "Course Code", "Grade" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable gradeTable = new JTable(model);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(gradeTable);
        gradesPanel.add(scrollPane, BorderLayout.CENTER);
        gradesPanel.add(createGradeButtonPanel(model), BorderLayout.SOUTH);

        return gradesPanel;
    }

    // Method to create a button panel for grades
    private JPanel createGradeButtonPanel(DefaultTableModel model) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton assignGradeButton = new JButton("Assign Grade");

        // Add action listeners to buttons
        assignGradeButton.addActionListener(e -> {
            showAssignGradeForm(model);
        });

        // Add buttons to the panel
        buttonPanel.add(assignGradeButton);
        return buttonPanel;
    }

    // Method to show the form for assigning a grade to a student
    private void showAssignGradeForm(DefaultTableModel model) {
        // Create a dialog for assigning a grade
        JDialog dialog = new JDialog(frame, "Assign Grade", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(frame);

        // Create components
        JLabel studentIdLabel = new JLabel("*Student ID:");
        JTextField studentIdField = new JTextField(15);

        JLabel courseCodeLabel = new JLabel("*Course Code:");
        JTextField courseCodeField = new JTextField(15);

        JLabel gradeLabel = new JLabel("*Grade:");
        JTextField gradeField = new JTextField(15);

        JButton submitButton = new JButton("Assign");
        JButton cancelButton = new JButton("Cancel");

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            try {
                int studentId = Integer.parseInt(studentIdField.getText());
                String courseCode = courseCodeField.getText();

                // grade is a double
                Double grade = Double.parseDouble(gradeField.getText());

                if (courseCode.isEmpty() || grade == null) {
                    throw new IllegalArgumentException("Course Code and Grade are required.");
                }

                Student student = SchoolManagement.getEnrolledStudents().stream()
                        .filter(s -> s.getId() == studentId)
                        .findFirst()
                        .orElse(null);

                if (student == null) {
                    throw new IllegalArgumentException("Student not found.");
                }

                Course course = SchoolManagement.getCourses().stream()
                        .filter(c -> c.getCourseCode().equals(courseCode))
                        .findFirst()
                        .orElse(null);

                if (course == null) {
                    throw new IllegalArgumentException("Course not found.");
                }

                // Assign the grade to the student
                SchoolManagement.assignGrade(studentId, courseCode, grade);
                JOptionPane.showMessageDialog(dialog, "Grade assigned successfully!");
                dialog.dispose();
                refreshGradeTable(model);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Student ID must be a number.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage());
            }
        });

        // Add action listener for the cancel button
        cancelButton.addActionListener(e -> dialog.dispose());

        // Layout configuration using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Add components to the dialog
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(studentIdLabel, gbc);

        gbc.gridx = 1;
        dialog.add(studentIdField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(courseCodeLabel, gbc);
        gbc.gridx = 1;
        dialog.add(courseCodeField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(gradeLabel, gbc);
        gbc.gridx = 1;
        dialog.add(gradeField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        dialog.add(submitButton, gbc);
        gbc.gridx = 1;
        dialog.add(cancelButton, gbc);
        // Display the dialog
        dialog.setVisible(true);
    }

    // Method to refresh the grade table
    private void refreshGradeTable(DefaultTableModel model) {
        // Clear the existing rows
        model.setRowCount(0);

        // Add rows for each student's grades
        for (Map.Entry<Integer, ArrayList<Grade>> entry : SchoolManagement.getGrades().entrySet()) {
            int studentId = entry.getKey();
            ArrayList<Grade> grades = entry.getValue();
            for (Grade grade : grades) {
                model.addRow(new Object[] { studentId, grade.getCourseCode(), grade.getGrade() });
            }

        }
    }

    /*
     * 
    
    
    
    
    
    
     */

    // Method to initialize pages
    private void initializePages() {
        // Add pages to main panel
        mainPanel.add(displayStudentsDashboard(), "students");
        mainPanel.add(displayCoursesDashboard(), "courses");
        mainPanel.add(displayEnrollmentsDashboard(), "enrollments");
        mainPanel.add(displayGradesDashboard(), "grades");
    }

    // Method to switch pages
    private void switchPage(String pageName) {
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel, pageName);
    }

    // Method to display the dashboard
    private void displayDashboard() {

        // frame.setTitle("Student Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Initialize pages
        initializePages();

        // Add main panel to frame
        frame.add(mainPanel);

        // MENU
        displayMainMenu();

        // Display the window.
        // frame.pack();
        frame.setVisible(true);
    }

    public void startSystem() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                displayDashboard();
            }
        });
    }

    public StudentManagementSystem() {

    };

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        sms.startSystem();
    }
}
