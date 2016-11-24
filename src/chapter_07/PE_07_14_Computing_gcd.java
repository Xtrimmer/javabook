package chapter_07;

import java.util.Scanner;

/**
 * (Computing gcd) Write a method that returns the gcd of an unspecified number
 * of integers. The method header is specified as follows:
 *
 *      public static int gcd(int... numbers)
 *
 * Write a test program that prompts the user to enter five numbers, invokes the
 * method to find the gcd of these numbers, and displays the gcd.
 */
public class PE_07_14_Computing_gcd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int SIZE = 5;
        int[] numbers = new int[SIZE];
        System.out.print("Enter " + SIZE + " numbers: ");
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println("The GCD is: " + gcd(numbers));
    }

    public static int min(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) min = array[i];
        }
        return min;
    }

    public static int gcd(int... numbers) {
        int min = min(numbers);
        for (int i = min; i > 0; i--) {
            boolean gcd = true;
            for (int number : numbers) {
                gcd = gcd && (number % i == 0);
            }
            if (gcd) return i;
        }
        return 1;
    }
}
