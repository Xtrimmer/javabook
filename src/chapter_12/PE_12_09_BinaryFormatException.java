package chapter_12;

import java.util.Scanner;

/**
 * (BinaryFormatException) Exercise 12.7 implements the bin2Dec method
 * to throw a NumberFormatException if the string is not a binary string.
 * Define a custom exception called BinaryFormatException. Implement the
 * bin2Dec method to throw a BinaryFormatException if the string is not a
 * binary string.
 */
public class PE_12_09_BinaryFormatException {
    public static void main(String[] args) {
        String binaryNumber = promptStringValue();
        int decimalNumber = bin2Dec(binaryNumber);
        System.out.println(decimalNumber);
    }

    private static int bin2Dec(String binaryString)
            throws BinaryFormatException {
        int decimalNumber = 0;
        char binaryNumber;
        for (int i = binaryString.length() - 1, pow = 0; i >= 0; i--, pow++) {
            binaryNumber = binaryString.charAt(i);
            if (binaryNumber == '0' || binaryNumber == '1') {
                decimalNumber += (Math.pow(2, pow) * (binaryNumber - '0'));
            } else {
                throw new BinaryFormatException("Not a binary number: " + binaryString);
            }
        }
        return decimalNumber;
    }

    private static String promptStringValue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a binary number: ");
        return scanner.nextLine();
    }

    static class BinaryFormatException extends NumberFormatException {

        public BinaryFormatException() {
        }

        public BinaryFormatException(String s) {
            super(s);
        }
    }
}