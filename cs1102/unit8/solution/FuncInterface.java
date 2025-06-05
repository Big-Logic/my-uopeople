import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;

    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }
}

public class FuncInterface {
    public static void main(String[] args) {
        // 1. Read dataset and store in a collection
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 35, "HR", 50000),
                new Employee("Bob", 28, "IT", 60000),
                new Employee("Charlie", 40, "Finance", 70000),
                new Employee("Diana", 32, "IT", 65000),
                new Employee("Eve", 25, "HR", 48000));

        // 2. Function interface to concatenate name and department
        Function<Employee, String> nameDeptConcat = e -> e.getName() + " - " + e.getDepartment();

        // 3. Use streams to generate new collection of concatenated strings
        List<String> nameDeptList = employees.stream()
                .map(nameDeptConcat)
                .collect(Collectors.toList());

        System.out.println("Employee Name and Department:");
        nameDeptList.forEach(System.out::println);

        // 4. Find average salary using streams
        double avgSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);

        System.out.println("\nAverage Salary: " + avgSalary);

        // 5. Filter employees above age threshold (e.g., 30)
        int ageThreshold = 30;
        List<Employee> filteredEmployees = employees.stream()
                .filter(e -> e.getAge() > ageThreshold)
                .collect(Collectors.toList());

        System.out.println("\nEmployees above age " + ageThreshold + ":");
        filteredEmployees.forEach(e -> System.out.println(e.getName() + " (" + e.getAge() + ")"));
    }
}