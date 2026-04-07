import java.util.Scanner;

// Class to hold the student data (The Data Structure)
class Student {
    private int studentId;
    private String name;
    private String major;
    private int graduationYear;

    public Student(int id, String name, String major, int year) {
        this.studentId = id;
        this.name = name;
        this.major = major;
        this.graduationYear = year;
    }

    public void displayInfo() {
        System.out.println("\n--- Completed Student Form Data ---");
        System.out.println("ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Major: " + major);
        System.out.println("Expected Graduation: " + graduationYear);
        System.out.println("-----------------------------------");
    }
}

// Class to handle the form logic (The Input Collector)
public class StudentFormDemo {
    public static void main(String[] args) {
        // Create a Scanner object to read user input from the console
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--- Welcome to the Student Registration Form ---");
        
        // 1. Get Student ID (Integer)
        System.out.print("Enter Student ID (e.g., 1001): ");
        // Ensure that the input is an integer before reading the next line
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a numerical ID.");
            scanner.next(); // Consume the invalid input
        }
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline character

        // 2. Get Student Name (String)
        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine();

        // 3. Get Student Major (String)
        System.out.print("Enter Major: ");
        String major = scanner.nextLine();

        // 4. Get Graduation Year (Integer)
        System.out.print("Enter Expected Graduation Year (e.g., 2028): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a numerical year.");
            scanner.next(); // Consume the invalid input
        }
        int year = scanner.nextInt();

        // Close the scanner
        scanner.close(); 
        
        // Create the Student object with the collected data
        Student newStudent = new Student(id, name, major, year);
        
        // Display the data collected from the "form"
        newStudent.displayInfo();
    }
}
