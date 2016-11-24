package chapter_07;

import java.util.Scanner;

/**
 * (Print distinct numbers) Write a program that reads in ten numbers and displays
 * the number of distinct numbers and the distinct numbers separated by exactly one
 * space (i.e., if a number appears multiple times, it is displayed only once). (Hint:
 * Read a number and store it to an array if it is new. If the number is already in the
 * array, ignore it.) After the input, the array contains the distinct numbers. Here is
 * the sample run of the program:
 *
 *      Enter ten numbers: 1 2 3 2 1 6 3 4 5 2 (enter)
 *      The number of distinct number is 6
 *      The distinct numbers are: 1 2 3 6 4 5
 */
public class PE_07_05_Print_distinct_numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int NUMBER_OF_INPUTS = 10;
        int[] numbers = new int[NUMBER_OF_INPUTS];
        int distinctCount = 0;
        System.out.print("Enter ten numbers: ");
        for (int i = 0; i < NUMBER_OF_INPUTS; i++) {
            int number = scanner.nextInt();
            if (!valueExists(numbers, distinctCount, number)) {
                numbers[distinctCount] = number;
                distinctCount++;
            }
        }
        System.out.println("The number of distinct number is " + distinctCount);
        printNumbers(numbers, distinctCount);
    }

    public static boolean valueExists(int[] array, int count, int number) {
        for (int i = 0; i < count; i++) {
            if (array[i] == number) return true;
        }
        return false;
    }

    public static void printNumbers(int[] array, int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
