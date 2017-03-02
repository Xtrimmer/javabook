package chapter_18;

import java.util.Scanner;

/**
 * (Hex to decimal) Write a recursive method that parses a hex number as a string
 * into a decimal integer. The method header is:
 *
 *      public static int hex2Dec(String hexString)
 *
 * Write a test program that prompts the user to enter a hex string and displays its
 * decimal equivalent.
 */
public class PE_18_24_Hex_to_decimal {
    public static void main(String[] args) {
        String hexString = promptUserForString();
        System.out.println(hex2Dec(hexString));
    }

    private static int hex2Dec(String hexString) {
        return hex2Dec(hexString, 0, hexString.length() - 1);
    }

    private static int hex2Dec(String hexString, int dec, int index) {
        if (index < 0) return dec;
        char ch = hexString.charAt(index);
        int binDigit = Integer.valueOf(ch + "", 16);
        int value = (int) (binDigit * Math.pow(16, hexString.length() - (index + 1)));
        dec += value;
        return hex2Dec(hexString, dec, index - 1);
    }

    private static String promptUserForString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a hex string: ");
        String input = scanner.nextLine().toUpperCase();
        for (char ch : input.toCharArray()) {
            if (!((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'F'))) {
                throw new IllegalArgumentException("Not a hex string.");
            }
        }
        return input;
    }
}
