package chapter_18;

import java.util.Scanner;

/**
 * (Find the largest number in an array) Write a recursive method that returns the
 * largest integer in an array. Write a test program that prompts the user to enter a
 * list of eight integers and displays the largest element.
 */
public class PE_18_13_Find_the_largest_number_in_an_array {
    public static void main(String[] args) {
        int[] values = promptIntegerValues(8);
        System.out.println(getLargestValue(values));
    }

    private static int[] promptIntegerValues(int size) {
        int[] values = new int[size];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter eight integers: ");
        for (int i = 0; i < size; i++) {
            values[i] = scanner.nextInt();
        }
        return values;
    }

    private static int getLargestValue(int[] values) {
        return getLargestValue(values, values.length - 1, Integer.MIN_VALUE);
    }

    private static int getLargestValue(int[] values, int index, int max) {
        if (index < 0) return max;
        if (values[index] > max) max = values[index];
        return getLargestValue(values, index - 1, max);
    }
}
