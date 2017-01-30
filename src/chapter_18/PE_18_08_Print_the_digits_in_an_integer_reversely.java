package chapter_18;

import java.util.Scanner;

/**
 * (Print the digits in an integer reversely) Write a recursive method that displays
 * an int value reversely on the console using the following header:
 *
 *      public static void reverseDisplay(int value)
 *
 * For example, reverseDisplay(12345) displays 54321. Write a test program
 * that prompts the user to enter an integer and displays its reversal.
 */
public class PE_18_08_Print_the_digits_in_an_integer_reversely {
    public static void main(String[] args) {
        int value = promptIntegerValue();
        reverseDisplay(value);
    }

    private static void reverseDisplay(int value) {
        if (value < 0) return;
        if (value < 10) {
            System.out.print(value);
        } else {
            System.out.print(value % 10);
            reverseDisplay(value / 10);
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
