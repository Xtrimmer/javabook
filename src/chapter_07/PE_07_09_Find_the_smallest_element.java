package chapter_07;

import java.util.Scanner;

/**
 * (Find the smallest element) Write a method that finds the smallest element in an
 * array of double values using the following header:
 *
 *      public static double min(double[] array)
 *
 * Write a test program that prompts the user to enter ten numbers, invokes this
 * method to return the minimum value, and displays the minimum value. Here is a
 * sample run of the program:
 *
 *      Enter ten numbers: 1.9 2.5 3.7 2 1.5 6 3 4 5 2 (enter)
 *      The minimum number is: 1.5
 */
public class PE_07_09_Find_the_smallest_element {
    public static void main(String[] args) {
        final int SIZE = 10;
        Scanner scanner = new Scanner(System.in);
        double[] numbers = new double[SIZE];
        System.out.print("Enter ten numbers: ");
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = scanner.nextDouble();
        }
        System.out.println("The minimum number is: " + min(numbers));
    }

    public static double min(double[] array) {
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) min = array[i];
        }
        return min;
    }
}
