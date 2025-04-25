package com.umesh;


import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
    private String name;
    private int pin;
    private double balance;
    private ArrayList<String> history = new ArrayList<>();

    public BankAccount(String name, int pin, double balance) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        history.add("🔐 Account created with ₹" + balance);
    }

    public String getName() {
        return name;
    }

    public boolean authenticate(int inputPin) {
        return this.pin == inputPin;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            history.add("💸 Withdrew ₹" + amount + " | Balance: ₹" + balance);
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            history.add("💰 Deposited ₹" + amount + " | Balance: ₹" + balance);
        }
    }

    public void printHistory() {
        System.out.println("\n📜 Transaction History:");
        for (String entry : history) {
            System.out.println(" - " + entry);
        }
    }
}

public class ATM {
    private static ArrayList<BankAccount> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static BankAccount currentUser = null;

    public static void main(String[] args) {
        users.add(new BankAccount("Umesh", 7777, 10000));
        users.add(new BankAccount("Rituraj", 2222, 12000));
        users.add(new BankAccount("Vishesh", 3333, 15000));

        System.out.println("👋 Welcome to the SmartBank ATM");

        while (true) {
            System.out.println("\n1. Log In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> login();
                case 2 -> createAccount();
                case 3 -> {
                    System.out.println("👋 Thank you! Visit again.");
                    return;
                }
                default -> System.out.println("❗ Invalid option.");
            }
        }
    }

    private static void login() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        int pin = getIntInput();

        for (BankAccount user : users) {
            if (user.getName().equalsIgnoreCase(name) && user.authenticate(pin)) {
                currentUser = user;
                System.out.println("✅ Welcome back, " + user.getName() + "!");
                showMenu();
                return;
            }
        }

        System.out.println("❌ Invalid name or PIN.");
    }

    private static void createAccount() {
        System.out.print("Choose a name: ");
        String name = scanner.nextLine();

        for (BankAccount user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                System.out.println("⚠️ This name is already taken.");
                return;
            }
        }

        System.out.print("Set a 4-digit PIN: ");
        int pin = getIntInput();

        System.out.print("Initial deposit amount: ₹");
        double balance = getDoubleInput();

        BankAccount newUser = new BankAccount(name, pin, balance);
        users.add(newUser);
        System.out.println("✅ Account created. You can now log in.");
    }

    private static void showMenu() {
        while (true) {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. View Transaction History");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> System.out.printf("💰 Balance: ₹%.2f%n", currentUser.getBalance());
                case 2 -> {
                    System.out.print("Withdraw amount: ₹");
                    double amt = getDoubleInput();
                    if (currentUser.withdraw(amt))
                        System.out.println("✅ Withdrawal successful.");
                    else
                        System.out.println("❌ Failed. Check amount or balance.");
                }
                case 3 -> {
                    System.out.print("Deposit amount: ₹");
                    double amt = getDoubleInput();
                    currentUser.deposit(amt);
                    System.out.println("✅ Deposit successful.");
                }
                case 4 -> currentUser.printHistory();
                case 5 -> {
                    System.out.println("👋 Logged out. See you soon, " + currentUser.getName() + "!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Enter a valid amount: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
