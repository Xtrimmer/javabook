package chapter_05;

import java.util.Scanner;

/**
 * (Decimal to binary) Write a program that prompts the user to enter a decimal
 * integer and displays its corresponding binary value. Don’t use Java’s Integer
 * .toBinaryString(int) in this program.
 */
public class PE_05_37_Decimal_to_binary {
    public static void main(String[] args) {
        // Declare variables
        Scanner scanner = new Scanner(System.in);
        int decimal;
        String binary = "";

        // Prompt user for input
        System.out.print("Enter a decimal integer: ");
        decimal = scanner.nextInt();
        while (decimal > 0){
            binary = decimal % 2 + binary;
            decimal /= 2;
        }
        System.out.println("The binary value is: " + binary);
    }
}
