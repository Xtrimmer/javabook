package chapter_07;

import java.util.Arrays;
import java.util.Scanner;

/**
 * (Revise selection sort) In Section 7.11, you used selection sort to sort an array.
 * The selection-sort method repeatedly finds the smallest number in the current
 * array and swaps it with the first. Rewrite this program by finding the largest number
 * and swapping it with the last. Write a test program that reads in ten double
 * numbers, invokes the method, and displays the sorted numbers.
 *
 * 1 3 5 7 9 0 2 4 6 8
 */
public class PE_07_20_Revise_selection_sort {
    public static void main(String[] args) {
        final int SIZE = 10;
        double[] list = new double[SIZE];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter " + SIZE + " numbers: ");
        for (int i = 0; i < SIZE; i++) {
            list[i] = scanner.nextDouble();
        }
        selectionSortRevised(list);
        System.out.println(Arrays.toString(list));
    }

    public static void selectionSortRevised(double[] list) {
        for (int i = list.length - 1; i > 0; i--) {
            // Find the maximum in the list[i..list.length-1]
            double currentMax = list[i];
            int currentMaxIndex = i;

            for (int j = i - 1; j >= 0; j--) {
                if (currentMax < list[j]) {
                    currentMax = list[j];
                    currentMaxIndex = j;
                }
            }

            // Swap list[i] with list[currentMaxIndex] if necessary
            if (currentMaxIndex != i) {
                list[currentMaxIndex] = list[i];
                list[i] = currentMax;
            }
        }
    }
}
