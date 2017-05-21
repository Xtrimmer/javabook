package chapter_11;

import java.util.ArrayList;
import java.util.Date;

/**
 * (New Account class) An Account class was specified in Programming
 * Exercise 9.7. Design a new Account class as follows:
 *
 * - Add a new data field name of the String type to store the name of the
 *   customer.
 * - Add a new constructor that constructs an account with the specified name, id,
 *   and balance.
 * - Add a new data field named transactions whose type is ArrayList
 *   that stores the transaction for the accounts. Each transaction is an instance
 *   of the Transaction class. The Transaction class is defined as shown in
 *   Figure 11.6.
 *
 * FIGURE 11.6 (p. 446) The Transaction class describes a transaction for a bank account.
 *
 * - Modify the withdraw and deposit methods to add a transaction to the
 *   transactions array list.
 * - All other properties and methods are the same as in Programming Exercise 9.7.
 *
 * Write a test program that creates an Account with annual interest rate 1.5%,
 * balance 1000, id 1122, and name George. Deposit $30, $40, and $50 to the
 * account and withdraw $5, $4, and $2 from the account. Print an account summary
 * that shows account holder name, interest rate, balance, and all transactions.
 */
public class PE_11_08_New_Account_class {
    public static void main(String[] args) {
        Account account = new Account(1122, 1000, "George");
        Account.setAnnualInterestRate(1.5);

        account.deposit(30);
        account.deposit(40);
        account.deposit(50);

        account.withdraw(5);
        account.withdraw(4);
        account.withdraw(2);

        printAccountSummary(account);
    }

    private static void printAccountSummary(Account account) {
        System.out.println("ACCOUNT SUMMARY:");
        System.out.println("----------------------------------------");
        System.out.println("Name:          " + account.getName());
        System.out.println("Interest rate: " + Account.getAnnualInterestRate() + "%");
        System.out.println("Balance:       $" + account.getBalance());
        System.out.println();
        System.out.println("TRANSACTIONS:");
        for (Transaction t : account.getTransactions()) {
            System.out.println(t);
        }
    }

    static class Account {
        private static double annualInterestRate = 0;
        private final Date dateCreated = new Date();
        private final ArrayList<Transaction> transactions = new ArrayList<>();
        private int id = 0;
        private double balance = 0;
        private String name = null;

        public Account() {
        }

        public Account(int id, double balance) {
            this.id = id;
            this.balance = balance;
        }

        public Account(int id, double balance, String name) {
            this(id, balance);
            this.name = name;
        }

        public static double getAnnualInterestRate() {
            return annualInterestRate;
        }

        public static void setAnnualInterestRate(double annualInterestRate) {
            Account.annualInterestRate = annualInterestRate;
        }

        public void deposit(double amount) {
            balance += amount;
            transactions.add(new Transaction('D', amount, balance,
                    String.format("Deposit of $%.2f", amount)));
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public Date getDateCreated() {
            return dateCreated;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getMonthlyInterest() {
            return balance * (getMonthlyInterestRate() / 100.0);
        }

        public double getMonthlyInterestRate() {
            return annualInterestRate / 12;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<Transaction> getTransactions() {
            return transactions;
        }

        @Override
        public String toString() {
            return "Account{" +
                    "id=" + id +
                    ", balance=" + balance +
                    ", dateCreated=" + dateCreated +
                    '}';
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
    }

    static class Transaction {
        private Date date = new Date();
        private char type;
        private double amount;
        private double balance;
        private String description;

        public Transaction() {
        }

        public Transaction(char type, double amount, double balance, String description) {
            this.type = type;
            this.amount = amount;
            this.balance = balance;
            this.description = description;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public char getType() {
            return type;
        }

        public void setType(char type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "date=" + date +
                    ", type=" + type +
                    ", amount=" + amount +
                    ", balance=" + balance +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
