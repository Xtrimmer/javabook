package chapter_18;

import java.util.Scanner;

/**
 * (Decimal to hex) Write a recursive method that converts a decimal number into
 * a hex number as a string. The method header is:
 *
 *      public static String dec2Hex(int value)
 *
 * Write a test program that prompts the user to enter a decimal number and displays
 * its hex equivalent.
 */
public class PE_18_22_Decimal_to_hex {
    public static void main(String[] args) {
        int number = promptUserForInteger();
        System.out.println(dec2Hex(number));
    }

    private static String dec2Hex(int value) {
        return dec2Hex(value, "");
    }

    private static String dec2Hex(int value, String hex) {
        if (value == 0) return hex;
        hex = digit2Hex(value % 16) + hex;
        value /= 16;
        return dec2Hex(value, hex);
    }

    private static String digit2Hex(int value) {
        switch (value) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return value + "";
            case 10:
                return "A";
            case 11:
                return "B";
            case 12:
                return "C";
            case 13:
                return "D";
            case 14:
                return "E";
            case 15:
                return "F";
            default:
                return "";
        }
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
