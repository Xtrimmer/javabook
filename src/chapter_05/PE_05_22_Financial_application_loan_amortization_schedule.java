package chapter_05;

import java.util.Scanner;

/**
 * (Financial application: loan amortization schedule) The monthly payment for a
 * given loan pays the principal and the interest. The monthly interest is computed
 * by multiplying the monthly interest rate and the balance (the remaining principal).
 * The principal paid for the month is therefore the monthly payment minus
 * the monthly interest. Write a program that lets the user enter the loan amount,
 * number of years, and interest rate and displays the amortization schedule for the
 * loan. Here is a sample run:
 *
 *      Loan Amount: 10000 (enter)
 *      Number of Years: 1 (enter)
 *      Annual Interest Rate: 7 (enter)
 *
 *      Monthly Payment: 865.26
 *      Total Payment: 10383.21
 *
 *      Payment#   Interest   Principal   Balance
 *      1          58.33      806.93      9193.07
 *      2          53.62      811.64      8381.43
 *      ...
 *      11         10.0       855.26       860.27
 *      12          5.01      860.25         0.01
 *
 * Note:
 * The balance after the last payment may not be zero. If so, the last payment should be
 * the normal monthly payment plus the final balance.
 *
 * Hint:
 * Write a loop to display the table. Since the monthly payment is the
 * same for each month, it should be computed before the loop. The balance
 * is initially the loan amount. For each iteration in the loop, compute the
 * interest and principal, and update the balance. The loop may look like this:
 *
 *  for (i = 1; i <= numberOfYears * 12; i++) {
 *      interest = monthlyInterestRate * balance;
 *      principal = monthlyPayment - interest;
 *      balance = balance - principal;
 *      System.out.println(i + "\t\t" + interest + "\t\t" + principal + "\t\t" + balance);
 *  }
 */
public class PE_05_22_Financial_application_loan_amortization_schedule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double loanAmount;
        double annualInterestRate;
        double monthlyInterestRate;
        double monthlyPayment;
        double totalPayment;
        double interest;
        double principal;
        double balance;
        int numberOfYears;

        System.out.print("Loan Amount: ");
        loanAmount = balance = scanner.nextDouble();
        System.out.print("Number of Years: ");
        numberOfYears = scanner.nextInt();
        System.out.print("Annual Interest Rate: ");
        annualInterestRate = scanner.nextDouble();
        monthlyInterestRate = annualInterestRate / 1200.0;
        monthlyPayment = loanAmount * monthlyInterestRate /
                (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
        totalPayment = monthlyPayment * numberOfYears * 12;
        System.out.println();
        System.out.printf("Monthly Payment: %.2f%n", monthlyPayment);
        System.out.printf("Total Payment: %.2f%n", totalPayment);
        System.out.println();
        System.out.println("Payment#   Interest   Principal   Balance");
        for (int i = 1; i <= numberOfYears * 12; i++) {
            interest = monthlyInterestRate * balance;
            principal = monthlyPayment - interest;
            balance = balance - principal;
            System.out.printf("%-11d%-11.2f%-12.2f%7.2f%n",i, interest, principal, balance);
        }
    }
}
