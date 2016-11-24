package chapter_13;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * (Convert decimals to fractions) Write a program that prompts the user to enter
 * a decimal number and displays the number in a fraction. Hint: read the decimal
 * number as a string, extract the integer part and fractional part from the string,
 * and use the BigInteger implementation of the Rational class in Programming
 * Exercise 13.15 to obtain a rational number for the decimal number. Here are some
 * sample runs:
 *
 *      Enter a decimal number: 3.25 (enter)
 *      The fraction number is 13/4
 *
 *      Enter a decimal number: -0.45452 (enter)
 *      The fraction number is -11363/25000
 */
public class PE_13_19_Convert_decimals_to_fractions {
    public static void main(String[] args) {
        String input = promptDoubleValue("Enter a decimal number: ");
        String[] number = input.split("\\.");
        int decimalPlaces = number[1].length();
        boolean isNegative = extractNegativity(number);
        BigInteger fractionalDenominator = new BigInteger("10").pow(decimalPlaces);
        BigInteger integerPart = new BigInteger(number[0]).multiply(fractionalDenominator);
        BigInteger fractionalNumerator = new BigInteger(number[1]).add(integerPart);
        if (isNegative) fractionalNumerator = fractionalNumerator.negate();
        System.out.println(new PE_13_15_Rational(fractionalNumerator, fractionalDenominator));
    }

    private static boolean extractNegativity(String[] s) {
        if (s[0].charAt(0) == '-') {
            s[0] = s[0].substring(1);
            return true;
        }
        return false;
    }

    private static String promptDoubleValue(String s) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(s);
        boolean valid;
        double input = 0;
        do {
            valid = true;
            if (scanner.hasNextDouble()) {
                input = scanner.nextDouble();
            } else {
                valid = false;
                System.out.println("Invalid input. Try again.");
                scanner.nextLine();
            }
        } while (!valid);
        return input + "";
    }
}
