package chapter_18;

import java.util.Scanner;

/**
 * (Sum the digits in an integer using recursion) Write a recursive method that
 * computes the sum of the digits in an integer. Use the following method header:
 *
 *      public static int sumDigits(long n)
 *
 * For example, sumDigits(234) returns 2 + 3 + 4 = 9. Write a test program
 * that prompts the user to enter an integer and displays its sum.
 */
public class PE_18_11_Sum_the_digits_in_an_integer_using_recursion {
    public static void main(String[] args) {
        int number = promptIntegerValue();
        System.out.println("The sum of digits: " + sumDigits(number));
    }

    private static int sumDigits(long n) {
        return sumDigits(n, 0);
    }

    private static int sumDigits(long n, int result) {
        if (n <= 0) {
            return result;
        } else {
            return sumDigits(n / 10, result + (int) (n % 10));
        }
    }

    private static int promptIntegerValue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        return 0;
    }
}
