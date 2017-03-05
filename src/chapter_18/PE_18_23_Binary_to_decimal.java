package chapter_18;

import java.util.Scanner;

/**
 * (Binary to decimal) Write a recursive method that parses a binary number as a
 * string into a decimal integer. The method header is:
 *
 *      public static int bin2Dec(String binaryString)
 *
 * Write a test program that prompts the user to enter a binary string and displays
 * its decimal equivalent.
 */
public class PE_18_23_Binary_to_decimal {
    public static void main(String[] args) {
        String binaryString = promptUserForString();
        System.out.println(bin2Dec(binaryString));
    }

    private static int bin2Dec(String binaryString) {
        return bin2Dec(binaryString, 0, binaryString.length() - 1);
    }

    private static int bin2Dec(String binaryString, int dec, int index) {
        if (index < 0) return dec;
        char ch = binaryString.charAt(index);
        int binDigit = Integer.valueOf(ch + "");
        int value = (int) (binDigit * Math.pow(2, binaryString.length() - (index + 1)));
        dec += value;
        return bin2Dec(binaryString, dec, index - 1);
    }

    private static String promptUserForString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a binary string: ");
        String input = scanner.nextLine();
        for (char ch : input.toCharArray()) {
            if (ch != '0' && ch != '1') {
                throw new IllegalArgumentException("Not a binary string.");
            }
        }
        return input;
    }
}
