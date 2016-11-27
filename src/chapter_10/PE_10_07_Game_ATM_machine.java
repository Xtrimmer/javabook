package chapter_10;

import java.util.Date;
import java.util.Scanner;

/**
 * (Game: ATM machine) Use the Account class created in Programming Exercise
 * 9.7 to simulate an ATM machine. Create ten accounts in an array with id
 * 0, 1, . . . , 9, and initial balance $100. The system prompts the user to enter an
 * id. If the id is entered incorrectly, ask the user to enter a correct id. Once an id
 * is accepted, the main menu is displayed as shown in the sample run. You can
 * enter a choice 1 for viewing the current balance, 2 for withdrawing money, 3 for
 * depositing money, and 4 for exiting the main menu. Once you exit, the system
 * will prompt for an id again. Thus, once the system starts, it will not stop.
 *
 *      Enter an id: 4 (enter)
 *      Main menu
 *      1: check balance
 *      2: withdraw
 *      3: deposit
 *      4: exit
 *      Enter a choice: 1 (enter)
 *      The balance is 100.0
 *
 *      Main menu
 *      1: check balance
 *      2: withdraw
 *      3: deposit
 *      4: exit
 *      Enter a choice: 2 (enter)
 *      Enter an amount to withdraw: 3 (enter)
 *
 *      Main menu
 *      1: check balance
 *      2: withdraw
 *      3: deposit
 *      4: exit
 *      Enter a choice: 1 (enter)
 *      The balance is 97.0
 *
 *      Main menu
 *      1: check balance
 *      2: withdraw
 *      3: deposit
 *      4: exit
 *      Enter a choice: 3 (enter)
 *      Enter an amount to deposit: 10 (enter)
 *
 *      Main menu
 *      1: check balance
 *      2: withdraw
 *      3: deposit
 *      4: exit
 *      Enter a choice: 1 (enter)
 *      The balance is 107.0
 *
 *      Main menu
 *      1: check balance
 *      2: withdraw
 *      3: deposit
 *      4: exit
 *      Enter a choice: 4 (enter)
 *
 *      Enter an id:
 */
public class PE_10_07_Game_ATM_machine {
    private static final Account[] accounts = initializeAccounts(10);

    public static void main(String[] args) {
        startATM();
    }

    private static void startATM() {
        while (true) {
            int accountNumber = promptAccountNumber();
            startAccountMenu(accountNumber);
            System.out.println();
        }
    }

    private static void startAccountMenu(int accountNumber) {
        while (true) {
            printMenu();
            int choice = promptMenuChoice();
            switch (choice) {
                case 1:
                    printBalance(accountNumber);
                    break;
                case 2:
                    withdraw(accountNumber);
                    break;
                case 3:
                    deposit(accountNumber);
                    break;
                case 4:
                    return;
            }
            System.out.println();
        }
    }

    private static void deposit(int accountNumber) {
        double depositAmount = promptAmount("deposit");
        accounts[accountNumber].deposit(depositAmount);
    }

    private static void withdraw(int accountNumber) {
        double withdrawAmount = promptAmount("withdraw");
        accounts[accountNumber].withdraw(withdrawAmount);
    }

    private static double promptAmount(String s) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an amount to " + s + ": ");
        return scanner.nextDouble();
    }

    private static void printBalance(int accountNumber) {
        System.out.printf("The balance is $%.2f%n",
                accounts[accountNumber].getBalance());
    }

    private static int promptMenuChoice() {
        Scanner scanner = new Scanner(System.in);
        boolean valid;
        int choice;
        do {
            valid = true;
            System.out.print("Enter a choice: ");
            choice = scanner.nextInt();
            if (choice < 1 || choice > 4) {
                System.out.print("Invalid choice. ");
                valid = false;
            }
        } while (!valid);
        return choice;
    }

    private static void printMenu() {
        System.out.println("Main menu");
        System.out.println("1: check balance");
        System.out.println("2: withdraw");
        System.out.println("3: deposit");
        System.out.println("4: exit");
    }

    private static int promptAccountNumber() {
        Scanner scanner = new Scanner(System.in);
        boolean valid;
        int id;
        do {
            valid = true;
            System.out.print("Enter an id: ");
            id = scanner.nextInt();
            if (id < 0 || id >= accounts.length) {
                System.out.print("Invalid ID. ");
                valid = false;
            }
        } while (!valid);
        return id;
    }

    private static Account[] initializeAccounts(int size) {
        Account[] accounts = new Account[size];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(i, 100);
        }
        return accounts;
    }

    private static class Account {
        private static double annualInterestRate = 0;
        private final Date dateCreated = new Date();
        private int id = 0;
        private double balance = 0;

        Account() {
        }

        Account(int id, double balance) {
            this.id = id;
            this.balance = balance;
        }

        static double getAnnualInterestRate() {
            return annualInterestRate;
        }

        static void setAnnualInterestRate(double annualInterestRate) {
            Account.annualInterestRate = annualInterestRate;
        }

        int getId() {
            return id;
        }

        void setId(int id) {
            this.id = id;
        }

        double getBalance() {
            return balance;
        }

        void setBalance(double balance) {
            this.balance = balance;
        }

        Date getDateCreated() {
            return dateCreated;
        }

        double getMonthlyInterest() {
            return balance * (getMonthlyInterestRate() / 100.0);
        }

        double getMonthlyInterestRate() {
            return annualInterestRate / 12;
        }

        void withdraw(double amount) {
            if (balance >= amount) {
                balance -= amount;
            }
        }

        void deposit(double amount) {
            balance += amount;
        }
    }
}
