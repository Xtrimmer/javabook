package chapter_11;

import utility.Account_11_08;
import utility.Transaction;

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
        Account_11_08 account = new Account_11_08(1122, 1000, "George");
        Account_11_08.setAnnualInterestRate(1.5);

        account.deposit(30);
        account.deposit(40);
        account.deposit(50);

        account.withdraw(5);
        account.withdraw(4);
        account.withdraw(2);

        printAccountSummary(account);
    }

    private static void printAccountSummary(Account_11_08 account) {
        System.out.println("ACCOUNT SUMMARY:");
        System.out.println("----------------------------------------");
        System.out.println("Name:          " + account.getName());
        System.out.println("Interest rate: " + Account_11_08.getAnnualInterestRate() + "%");
        System.out.println("Balance:       $" + account.getBalance());
        System.out.println();
        System.out.println("TRANSACTIONS:");
        for (Transaction t: account.getTransactions()) {
            System.out.println(t);
        }
    }
}
