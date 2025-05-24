// Define the Vehicle interface

import java.util.Scanner;

interface Vehicle {
    String getMake();

    String getModel();

    int getMenufactureYear();
}

// Define the CarVehicle interface
interface CarVehicle {
    void setNumberOfDoors(int doors);

    int getNumberOfDoors();

    void setFuelType(String fuelType);

    String getFuelType();
}

// Define the MotorVehicle interface
interface MotorVehicle {
    void setNumberOfWheels(int wheels);

    int getNumberOfWheels();

    void setMotorcycleType(String type);

    String getMotorcycleType();
}

// Define the TruckVehicle interface
interface TruckVehicle {
    void setCargoCapacity(double capacity);

    double getCargoCapacity();

    void setTransmissionType(String transmissionType);

    String getTransmissionType();
}

// Implement the Car class
class Car implements Vehicle, CarVehicle {
    private String make;
    private String model;
    private int menufactureYear;
    private int numberOfDoors;
    private String fuelType;

    public Car(String make, String model, int menufactureYear, int numberOfDoors, String fuelType) {
        this.make = make;
        this.model = model;
        this.menufactureYear = menufactureYear;
        this.fuelType = fuelType;
        setNumberOfDoors(numberOfDoors);
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getMenufactureYear() {
        return menufactureYear;
    }

    @Override
    public void setNumberOfDoors(int doors) {
        if (doors <= 0) {
            throw new IllegalArgumentException("Number of doors must be greater than 0");
        }
        this.numberOfDoors = doors;
    }

    @Override
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    @Override
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String getFuelType() {
        return fuelType;
    }
}

// Implement the Motorcycle class
class Motorcycle implements Vehicle, MotorVehicle {
    private String make;
    private String model;
    private int menufactureYear;
    private int numberOfWheels;
    private String motorcycleType;

    public Motorcycle(String make, String model, int menufactureYear, int numberOfWheels, String motorcycleType) {
        this.make = make;
        this.model = model;
        this.menufactureYear = menufactureYear;
        this.motorcycleType = motorcycleType;
        setNumberOfWheels(numberOfWheels);

    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getMenufactureYear() {
        return menufactureYear;
    }

    @Override
    public void setNumberOfWheels(int wheels) {
        if (wheels <= 0) {
            throw new IllegalArgumentException("Number of wheels must be greater than 0");
        }
        this.numberOfWheels = wheels;
    }

    @Override
    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    @Override
    public void setMotorcycleType(String type) {
        this.motorcycleType = type;
    }

    @Override
    public String getMotorcycleType() {
        return motorcycleType;
    }
}

// Implement the Truck class
class Truck implements Vehicle, TruckVehicle {
    private String make;
    private String model;
    private int menufactureYear;
    private double cargoCapacity;
    private String transmissionType;

    public Truck(String make, String model, int menufactureYear, double cargoCapacity, String transmissionType) {
        this.make = make;
        this.model = model;
        this.menufactureYear = menufactureYear;
        this.cargoCapacity = cargoCapacity;
        this.transmissionType = transmissionType;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getMenufactureYear() {
        return menufactureYear;
    }

    @Override
    public void setCargoCapacity(double capacity) {
        this.cargoCapacity = capacity;
    }

    @Override
    public double getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    @Override
    public String getTransmissionType() {
        return transmissionType;
    }
}

// Main program to test the classes
public class Vehicles {

    private static Scanner scanner = new Scanner(System.in);

    // method to exit the program
    private static void exitProgram() {
        System.out.println("Exiting the program...");
        scanner.close();
        System.exit(0);
    }

    // method to return to the main menu
    private static void returnToMainMenu() {
        System.out.println("Hit 'Enter' to return to the main menu...");
        scanner.nextLine();

        System.out.println("Returning to the main menu...");
        displayMenu();
    }

    // method to create a car
    private static void createCar() {
        // Prompt user for car details
        System.out.println("Enter Car Make:");
        String make = scanner.nextLine();

        System.out.println("Enter Car Model:");
        String model = scanner.nextLine();

        System.out.println("Enter Car  menufacture year:");
        int menufactureYear = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Number of Doors:");
        int numberOfDoors = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Fuel Type:");
        String fuelType = scanner.nextLine();

        try {
            Car car = new Car(make, model, menufactureYear, numberOfDoors, fuelType);
            // print car details
            System.out.println("Car Details:");
            System.out.println("Make: " + car.getMake());
            System.out.println("Model: " + car.getModel());
            System.out.println("Year: " + car.getMenufactureYear());
            System.out.println("Doors: " + car.getNumberOfDoors());
            System.out.println("Fuel Type: " + car.getFuelType());

            returnToMainMenu();

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
            returnToMainMenu();

        }
    }

    // method to create a motorcycle
    private static void createMotorcycle() {
        // Prompt user for motorcycle details
        System.out.println("Enter Motorcycle Make:");
        String make = scanner.nextLine();

        System.out.println("Enter Motorcycle Model:");
        String model = scanner.nextLine();

        System.out.println("Enter Motorcycle Year:");
        int menufactureYear = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        System.out.println("Enter Number of Wheels:");
        int numberOfWheels = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        System.out.println("Enter Motorcycle Type:");
        String motorcycleType = scanner.nextLine();

        try {
            Motorcycle motorcycle = new Motorcycle(make, model, menufactureYear, numberOfWheels, motorcycleType);

            // print motorcycle details
            System.out.println("Motorcycle Details:");
            System.out.println("Make: " + motorcycle.getMake());
            System.out.println("Model: " + motorcycle.getModel());
            System.out.println("Year: " + motorcycle.getMenufactureYear());
            System.out.println("Wheels: " + motorcycle.getNumberOfWheels());
            System.out.println("Type: " + motorcycle.getMotorcycleType());

            returnToMainMenu();

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
            returnToMainMenu();

        }
    }

    // method to create a truck
    private static void createTruck() {
        // Prompt user for truck details
        System.out.println("Enter Truck Make:");
        String make = scanner.nextLine();

        System.out.println("Enter Truck Model:");
        String model = scanner.nextLine();

        System.out.println("Enter Truck Year:");
        int menufactureYear = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        System.out.println("Enter Cargo Capacity (in tons):");
        double cargoCapacity = scanner.nextDouble();
        scanner.nextLine(); // consume the newline character

        System.out.println("Enter Transmission Type:");
        String transmissionType = scanner.nextLine();

        try {
            Truck truck = new Truck(make, model, menufactureYear, cargoCapacity, transmissionType);
            // print truck details
            System.out.println("Truck Details:");
            System.out.println("Make: " + truck.getMake());
            System.out.println("Model: " + truck.getModel());
            System.out.println("Year: " + truck.getMenufactureYear());
            System.out.println("Cargo Capacity: " + truck.getCargoCapacity() + " tons");
            System.out.println("Transmission Type: " + truck.getTransmissionType());
            // return to main menu
            returnToMainMenu();

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
            returnToMainMenu();

        }
    }

    // method to handle menu options
    private static void handleMenuOption() {
        String option = scanner.nextLine();
        switch (option) {
            case "c":

                createCar();
                break;
            case "m":

                createMotorcycle();
                break;

            case "t":

                createTruck();
                break;

            case "e":
                exitProgram();

            default:
                System.out.println("Invalid option. Please try again.");
                displayMenu();
        }
    }

    public static void displayMenu() {
        System.out.println("Welcome to the Vehicle Management System");
        System.out.println("c. Create a Car");
        System.out.println("m. Create a Motorcycle");
        System.out.println("t. Create a Truck");
        System.out.println("e. Exit");
        System.out.println("Please select an option: (c/m/t/e)");
        handleMenuOption();
    }

    public static void main(String[] args) {
        while (true) {
            try {

                // Display the menu
                displayMenu();

            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Please try again.");
                scanner.nextLine(); // consume the newline character
            }

        }
    }
}