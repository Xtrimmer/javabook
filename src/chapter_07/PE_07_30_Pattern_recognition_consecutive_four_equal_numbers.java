package chapter_07;

import java.util.Scanner;

/**
 * (Pattern recognition: consecutive four equal numbers) Write the following
 * method that tests whether the array has four consecutive numbers with the same
 * value.
 *
 *      public static boolean isConsecutiveFour(int[] values)
 *
 * Write a test program that prompts the user to enter a series of integers and displays
 * if the series contains four consecutive numbers with the same value. Your
 * program should first prompt the user to enter the input size—i.e., the number of
 * values in the series. Here are sample runs:
 *
 *      Enter the number of values: 8 (enter)
 *      Enter the values: 3 4 5 5 5 5 4 5 (enter)
 *      The list has consecutive fours
 *
 *      Enter the number of values: 9 (enter)
 *      Enter the values: 3 4 5 5 6 5 5 4 5 (enter)
 *      The list has no consecutive fours
 */
public class PE_07_30_Pattern_recognition_consecutive_four_equal_numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of values: ");
        int count = scanner.nextInt();
        int[] array = new int[count];
        System.out.print("Enter the values: ");
        for (int i = 0; i < count; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.print("The list has ");
        System.out.print(isConsecutiveFour(array) ? "" : "no ");
        System.out.print("consecutive fours");
    }

    public static boolean isConsecutiveFour(int[] values) {
        for (int i = 0; i < values.length - 3; i++) {
            if ((values[i] == values[i+1]) &&
                    (values[i+1] == values[i+2]) &&
                    (values[i+2] == values[i+3])) {
                return true;
            }
        }
        return false;
    }
}
