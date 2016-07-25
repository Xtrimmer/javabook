package utility;

import java.util.ArrayList;
import java.util.Date;

/**
 * Added for Chapter 11 Exercise 08.
 */
public class Account_11_08 {
    private int id = 0;
    private double balance = 0;
    private static double annualInterestRate = 0;
    private final Date dateCreated = new Date();
    private String name = null;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Account_11_08() {
    }

    public Account_11_08(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public Account_11_08(int id, double balance, String name) {
        this(id, balance);
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public static void setAnnualInterestRate(double annualInterestRate) {
        Account_11_08.annualInterestRate = annualInterestRate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMonthlyInterestRate() {
        return annualInterestRate / 12;
    }

    public double getMonthlyInterest() {
        return balance * (getMonthlyInterestRate() / 100.0);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public int withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction('W', amount, balance,
                    String.format("Withdrawl of $%.2f", amount)));
            return 1;
        } else {
            return -1;
        }
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction('D', amount, balance,
                String.format("Deposit of $%.2f", amount)));
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
