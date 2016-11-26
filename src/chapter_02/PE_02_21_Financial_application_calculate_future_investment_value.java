package chapter_02;

import java.util.Scanner;

/**
 * (Financial application: calculate future investment value) Write a program that
 * reads in investment amount, annual interest rate, and number of years, and displays
 * the future investment value using the following formula:
 *
 *      futureInvestmentValue = investmentAmount x (1 + monthlyInterestRate)^numberOfYears*12
 *
 * For example, if you enter amount 1000, annual interest rate 3.25%, and number of
 * years 1, the future investment value is 1032.98.
 */
public class PE_02_21_Financial_application_calculate_future_investment_value {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter investment amount: ");
        double investmentAmount = SCANNER.nextDouble();
        System.out.print("Enter annual interest rate in percentage: ");
        double annualInterestRate = SCANNER.nextDouble();
        System.out.print("Enter number of years: ");
        int numberOfYears = SCANNER.nextInt();

        double monthlyInterestRate = annualInterestRate / 1200d;
        double futureInvestmentValue = investmentAmount * Math.pow(1 + monthlyInterestRate, numberOfYears * 12);

        futureInvestmentValue *= 100;
        futureInvestmentValue = Math.round(futureInvestmentValue) / 100d;

        System.out.println("Accumulated value is $" + futureInvestmentValue);
    }
}
