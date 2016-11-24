package chapter_07;

import java.util.Scanner;

/**
 * (Sorted?) Write the following method that returns true if the list is already sorted
 * in increasing order.
 *
 *      public static boolean isSorted(int[] list)
 *
 * Write a test program that prompts the user to enter a list and displays whether
 * the list is sorted or not. Here is a sample run. Note that the first number in the
 * input indicates the number of the elements in the list. This number is not part
 * of the list.
 *
 *      Enter list: 8 10 1 5 16 61 9 11 1 (enter)
 *      The list is not sorted
 *
 *      Enter list: 10 1 1 3 4 4 5 7 9 11 21 (enter)
 *      The list is already sorted
 */
public class PE_07_19_Sorted {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter list: ");
        final int SIZE = scanner.nextInt();
        int[] list = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            list[i] = scanner.nextInt();
        }
        System.out.println(isSorted(list) ?
                "The list is already sorted" : "The list is not sorted");
    }

    public static boolean isSorted(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i] > list[i + 1]) return false;
        }
        return true;
    }
}
