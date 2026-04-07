import java.util.Scanner;  // Import Scanner class to allow user input

public class factorial {

    // Method to calculate factorial of a number n
    // Example: factorial(5) = 1 × 2 × 3 × 4 × 5 = 120
    public static long factorial(int n) {
        long result = 1;

        // Loop from 2 up to n, multiplying each number
        // This builds the factorial step by step
        for (int i = 2; i <= n; i++) {
            result *= i;  // result = result × i
        }
        return result;  // Return the final factorial value
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create scanner object for input

        System.out.print("Enter a number: ");
        int n = scanner.nextInt();  // Read the user's number

        // Display the factorial to the user
        System.out.println("Factorial of " + n + " is: " + factorial(n));
    }
}
