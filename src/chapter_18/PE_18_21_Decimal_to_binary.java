package chapter_18;

import java.util.Scanner;

/**
 * (Decimal to binary) Write a recursive method that converts a decimal number
 * into a binary number as a string. The method header is:
 *
 *      public static String dec2Bin(int value)
 *
 * Write a test program that prompts the user to enter a decimal number and displays
 * its binary equivalent.
 */
public class PE_18_21_Decimal_to_binary {
    public static void main(String[] args) {
        int number = promptUserForInteger();
        System.out.println(dec2Bin(number));
    }

    private static String dec2Bin(int value) {
        return dec2Bin(value, "");
    }

    private static String dec2Bin(int value, String bin) {
        if (value == 0) return bin;
        bin = (value % 2 == 0 ? "0" : "1") + bin;
        value /= 2;
        return dec2Bin(value, bin);
    }

    private static int promptUserForInteger() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            System.out.println("Error parsing Integer Value!");
            System.exit(1);
        }
        return 0;
    }

}
