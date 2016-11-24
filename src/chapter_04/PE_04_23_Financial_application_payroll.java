package chapter_04;
import java.util.Scanner;
/**
 * (Financial application: payroll) Write a program that reads the following information
 * and prints a payroll statement:
 * Employee’s name (e.g., Smith)
 * Number of hours worked in a week (e.g., 10)
 * Hourly pay rate (e.g., 9.75)
 * Federal tax withholding rate (e.g., 20%)
 * State tax withholding rate (e.g., 9%)
 */
public class PE_04_23_Financial_application_payroll {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter employee's name: ");
        String employeeName = SCANNER.next();
        System.out.print("Enter number of hours worked in a week: ");
        double hours = SCANNER.nextDouble();
        System.out.print("Enter hourly pay rate: ");
        double payRate = SCANNER.nextDouble();
        System.out.print("Enter federal tax withholding rate: ");
        double federalTaxRate = SCANNER.nextDouble();
        System.out.print("Enter state tax withholding rate: ");
        double stateTaxRate = SCANNER.nextDouble();

        System.out.println();
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Hours Worked: " + hours);
        System.out.println("Pay Rate: $" + payRate);
        double grossPay = Math.round(hours * payRate * 100) / 100d;
        System.out.println("Gross Pay: $" + grossPay);
        System.out.println("Deductions: ");
        double federalWithholding = Math.round(grossPay * federalTaxRate * 100) / 100d;
        System.out.println("\tFederal Withholding (" + federalTaxRate * 100 + "%): $" + federalWithholding);
        double stateWithholding = Math.round(grossPay * stateTaxRate * 100) / 100d;
        System.out.println("\tState Withholding (" + stateTaxRate * 100 + "%): $" + stateWithholding);
        double totalWithholding = federalWithholding + stateWithholding;
        System.out.println("\tTotal Deduction: $" + totalWithholding);
        System.out.println("Net Pay: $" + (grossPay - totalWithholding));
    }
}
