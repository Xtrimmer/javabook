package chapter_09;

import java.util.Date;

/**
 * (The Account class) Design a class named Account that contains:
 *
 * - A private int data field named id for the account (default 0).
 * - A private double data field named balance for the account (default 0).
 * - A private double data field named annualInterestRate that stores the current
 *   interest rate (default 0). Assume all accounts have the same interest rate.
 * - A private Date data field named dateCreated that stores the date when the
 *   account was created.
 * - A no-arg constructor that creates a default account.
 * - A constructor that creates an account with the specified id and initial balance.
 * - The accessor and mutator methods for id, balance, and annualInterestRate.
 * - The accessor method for dateCreated.
 * - A method named getMonthlyInterestRate() that returns the monthly
 *   interest rate.
 * - A method named getMonthlyInterest() that returns the monthly interest.
 * - A method named withdraw that withdraws a specified amount from the
 *   account.
 * - A method named deposit that deposits a specified amount to the account.
 *
 * Draw the UML diagram for the class and then implement the class. (Hint: The
 * method getMonthlyInterest() is to return monthly interest, not the interest rate.
 * Monthly interest is balance * monthlyInterestRate. monthlyInterestRate
 * is annualInterestRate / 12. Note that annualInterestRate is a percentage,
 * e.g., like 4.5%. You need to divide it by 100.)
 *
 * Write a test program that creates an Account object with an account ID of 1122,
 * a balance of $20,000, and an annual interest rate of 4.5%. Use the withdraw
 * method to withdraw $2,500, use the deposit method to deposit $3,000, and print
 * the balance, the monthly interest, and the date when this account was created.
 */
public class PE_09_07_The_Account_class {
    public static void main(String[] args) {
        Account.setAnnualInterestRate(4.5);
        Account account = new Account(1122, 20000);
        account.withdraw(2500);
        account.deposit(3000);
        System.out.println("Account-" + account.getId());
        System.out.printf("   Balance:          $%,.2f%n", account.getBalance());
        System.out.printf("   Monthly interest: $%,.2f%n", account.getMonthlyInterest());
        System.out.printf("   Date Created:     %s%n", account.getDateCreated().toString());
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

