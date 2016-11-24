package chapter_11;

import utility.Account;
import utility.CheckingAccount;
import utility.SavingsAccount;

/**
 * (Subclasses of Account) In Programming Exercise 9.7, the Account class was
 * defined to model a bank account. An account has the properties account number,
 * balance, annual interest rate, and date created, and methods to deposit and withdraw
 * funds. Create two subclasses for checking and saving accounts. A checking
 * account has an overdraft limit, but a savings account cannot be overdrawn.
 *
 * Draw the UML diagram for the classes and then implement them. Write
 * a test program that creates objects of Account, SavingsAccount, and
 * CheckingAccount and invokes their toString() methods.
 */
public class PE_11_03_Subclasses_of_Account {
    public static void main(String[] args) {
        Account[] accounts = new Account[3];

        accounts[0] = new Account(10001, 101.0);
        accounts[1] = new CheckingAccount(20002, 202.0, 50.0);
        accounts[2] = new SavingsAccount(30003, 303.0);

        for (Account account: accounts) {
            System.out.println(account);
        }
    }
}
