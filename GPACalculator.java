// GPACalculator.java
import java.util.Scanner;

public class GPACalculator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter no of courses: ");
        int numCourses = input.nextInt();

        double totalGradePoints = 0;
        double totalCreditUnits = 0;

        for (int i = 1; i <= numCourses; i++) {
            System.out.println("\nCourse " + i + ":");

            System.out.print("Enter credit unit: ");
            double creditUnit = input.nextDouble();

            System.out.print("Enter grade (A, B, C, D, F): ");
            String grade = input.next().toUpperCase();

            double gradePoint = 0;

            switch (grade) {
                case "A":
                    gradePoint = 5.0;
                    break;
                case "B":
                    gradePoint = 4.0;
                    break;
                case "C":
                    gradePoint = 3.0;
                    break;
                case "D":
                    gradePoint = 2.0;
                    break;
                case "F":
                    gradePoint = 0.0;
                    break;
                default:
                    System.out.println("Invalid grade entered! Try again.");
                    i--; // to repeat
                    continue;
            }

            totalGradePoints += gradePoint * creditUnit;
            totalCreditUnits += creditUnit;
        }

        double gpa = totalGradePoints / totalCreditUnits;

        System.out.println("Total Credit Units: " + totalCreditUnits);
        System.out.println("Total Grade Points: " + totalGradePoints);
        System.out.printf("Your GPA is: %.2f\n", gpa);
    }
}
