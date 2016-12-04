package chapter_05;

import java.util.Scanner;

/**
 * (Financial application: compare loans with various interest rates) Write a program
 * that lets the user enter the loan amount and loan period in number of years
 * and displays the monthly and total payments for each interest rate starting from
 * 5% to 8%, with an increment of 1/8. Here is a sample run:
 *
 *  Loan Amount: 10000 (Enter)
 *  Number of Years: 5 (Enter)
 *      Interest Rate     Monthly Payment     Total Payment
 *      5.000%            188.71              11322.74
 *      5.125%            189.29              11357.13
 *      5.250%            189.86              11391.59
 *      ...
 *      7.875%            202.17              12129.97
 *      8.000%            202.76              12165.84
 *
 * For the formula to compute monthly payment, see Listing 2.9, ComputeLoan.java.
 */
public class PE_05_21_Financial_application_compare_loans_with_various_interest_rates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double InterestRateStart = 5;
        double InterestRateEnd = 8;
        System.out.print("Loan Amount:     ");
        double loanAmount = scanner.nextDouble();
        System.out.print("Number of Years: ");
        double numberOfYears = scanner.nextDouble();
        System.out.println("Interest Rate     Monthly Payment     Total Payment");
        double interestRate = InterestRateStart;
        while (interestRate <= InterestRateEnd) {
            double monthlyInterestRate = interestRate / 1200.0;
            double monthlyPayment = loanAmount * monthlyInterestRate /
                    (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
            double totalPayment = monthlyPayment * numberOfYears * 12;
            System.out.printf("%-18.3f", interestRate);
            System.out.printf("%-20.2f", monthlyPayment);
            System.out.printf("%.2f", totalPayment);
            System.out.println();
            interestRate += (1 / 8.0);
        }
    }
}
