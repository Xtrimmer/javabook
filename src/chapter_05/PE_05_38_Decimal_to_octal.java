package chapter_05;

import java.util.Scanner;

/**
 * (Decimal to octal) Write a program that prompts the user to enter a decimal
 * integer and displays its corresponding octal value. Don’t use Java’s Integer
 * .toOctalString(int) in this program.
 */
public class PE_05_38_Decimal_to_octal {
    public static void main(String[] args) {
        // Declare variables
        Scanner scanner = new Scanner(System.in);
        int decimal;
        String octal = "";

        // Prompt user for input
        System.out.print("Enter a decimal integer: ");
        decimal = scanner.nextInt();
        while (decimal > 0) {
            octal = decimal % 8 + octal;
            decimal /= 8;
        }
        System.out.println("The octal value is: " + octal);
    }
}
