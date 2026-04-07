import java.util.Scanner;

public class BankAccount {
    private String accountNumber;
    private String accountOwner;
    private double balance;

    // Constructor to initialize the account
    public BankAccount(String owner, String number, double initialBalance) {
        this.accountOwner = owner;
        this.accountNumber = number;
        // prevent negative starting balance
        if (initialBalance < 0) {
            System.out.println("Initial balance negative -> set to 0.");
            this.balance = 0;
        } else {
            this.balance = initialBalance;
        }
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: $" + String.format("%.2f", balance));
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + String.format("%.2f", balance));
        } else if (amount > 0) {
            System.out.println("Withdrawal failed. Insufficient funds. Current balance: $" + String.format("%.2f", balance));
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    // Getter method to check the balance
    public double getBalance() {
        return balance;
    }

    // Method to print account details
    public void displayAccountDetails() {
        System.out.println("\n--- Bank Account Details ---");
        System.out.println("Owner: " + accountOwner);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: $" + String.format("%.2f", balance));
    }

    // short toString for easier printing
    @Override
    public String toString() {
        return accountOwner + " | " + accountNumber + " | $" + String.format("%.2f", balance);
    }

    // Small interactive demo so this file runs by itself
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter account owner name: ");
        String owner = sc.nextLine().trim();
        if (owner.isEmpty()) owner = "Unknown";

        System.out.print("Enter account number: ");
        String number = sc.nextLine().trim();
        if (number.isEmpty()) number = "000000";

        System.out.print("Enter initial balance (or press Enter for 0): ");
        double init = 0;
        String b = sc.nextLine().trim();
        if (!b.isEmpty()) {
            try { init = Double.parseDouble(b); }
            catch (NumberFormatException ex) { System.out.println("Invalid number, using 0."); init = 0; }
        }

        BankAccount acc = new BankAccount(owner, number, init);
        System.out.println("Account created: " + acc);

        // tiny menu loop
        while (true) {
            System.out.println("\nChoose: 1)Deposit  2)Withdraw  3)Details  4)Exit");
            System.out.print("Option: ");
            String opt = sc.nextLine().trim();
            if (opt.equals("1")) {
                System.out.print("Amount to deposit: ");
                try {
                    double amt = Double.parseDouble(sc.nextLine().trim());
                    acc.deposit(amt);
                } catch (Exception e) { System.out.println("Invalid amount."); }
            } else if (opt.equals("2")) {
                System.out.print("Amount to withdraw: ");
                try {
                    double amt = Double.parseDouble(sc.nextLine().trim());
                    acc.withdraw(amt);
                } catch (NumberFormatException e) { System.out.println("Invalid amount."); }
            } else if (opt.equals("3")) {
                acc.displayAccountDetails();
            } else if (opt.equals("4")) {
                System.out.println("Exiting. Final: " + acc);
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }

        sc.close();
    }
}
