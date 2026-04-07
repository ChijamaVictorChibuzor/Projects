import java.util.ArrayList;
import java.util.Scanner;

public class BankApp {
    // simple account record
    static class Account {
        int id;
        String name;
        double bal;
        Account(int id, String name, double bal) { this.id = id; this.name = name; this.bal = bal; }
        @Override public String toString() { return id + ": " + name + " (N" + String.format("%.2f", bal) + ")"; }
    }

    static ArrayList<Account> list = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int nextId = 1;

    public static void main(String[] args) {
        // main loop
        while (true) {
            System.out.println("\n1.Create 2.Deposit 3.Withdraw 4.List 5.Exit");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();
            if (c.equals("1")) create();
            else if (c.equals("2")) deposit();
            else if (c.equals("3")) withdraw();
            else if (c.equals("4")) listAll();
            else if (c.equals("5")) { System.out.println("Bye"); break; }
            else System.out.println("Invalid");
        }
    }

    // create account with optional initial deposit
    static void create() {
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) { System.out.println("Name required."); return; }
        System.out.print("Initial deposit (or enter for 0): ");
        double amt = parseDoubleOrZero(sc.nextLine().trim());
        Account a = new Account(nextId++, name, Math.max(0, amt));
        list.add(a);
        System.out.println("Created -> " + a);
    }

    // deposit to account id
    static void deposit() {
        Account a = findByIdPrompt(); if (a==null) return;
        System.out.print("Amount to deposit: ");
        double amt = parseDoubleOrZero(sc.nextLine().trim());
        if (amt <= 0) { System.out.println("Amount must be > 0"); return; }
        a.bal += amt;
        System.out.println("New balance: N" + String.format("%.2f", a.bal));
    }

    // withdraw with simple balance check
    static void withdraw() {
        Account a = findByIdPrompt(); if (a==null) return;
        System.out.print("Amount to withdraw: ");
        double amt = parseDoubleOrZero(sc.nextLine().trim());
        if (amt <= 0) { System.out.println("Amount must be > 0"); return; }
        if (amt > a.bal) { System.out.println("Insufficient funds."); return; }
        a.bal -= amt;
        System.out.println("New balance: N" + String.format("%.2f", a.bal));
    }

    // list accounts or message
    static void listAll() {
        if (list.isEmpty()) { System.out.println("No accounts."); return; }
        for (Account a : list) System.out.println(a);
    }

    // prompt and find account
    static Account findByIdPrompt() {
        if (list.isEmpty()) { System.out.println("No accounts."); return null; }
        System.out.print("Account id: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            for (Account a : list) if (a.id == id) return a;
            System.out.println("Not found.");
        } catch (Exception e) { System.out.println("Invalid id."); }
        return null;
    }

    // parse double or return 0 on bad input
    static double parseDoubleOrZero(String s) {
        if (s.isEmpty()) return 0;
        try { return Double.parseDouble(s); } catch (Exception e) { return 0; }
    }
}
