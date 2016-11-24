package chapter_07;

import java.util.Scanner;

/**
 * (Strictly identical arrays) The arrays list1 and list2 are strictly identical
 * if their corresponding elements are equal. Write a method that returns true if
 * list1 and list2 are strictly identical, using the following header:
 *
 *      public static boolean equals(int[] list1, int[] list2)
 *
 * Write a test program that prompts the user to enter two lists of integers and displays
 * whether the two are strictly identical. Here are the sample runs. Note that
 * the first number in the input indicates the number of the elements in the list. This
 * number is not part of the list.
 *
 *      Enter list1: 5 2 5 6 1 6 (enter)
 *      Enter list2: 5 2 5 6 1 6 (enter)
 *      Two lists are strictly identical
 *
 *      Enter list1: 5 2 5 6 6 1 (enter)
 *      Enter list2: 5 2 5 6 1 6 (enter)
 *      Two lists are not strictly identical
 * */
public class PE_07_26_Strictly_identical_arrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter list1: ");
        int[] list1 = new int[scanner.nextInt()];
        for (int i = 0; i < list1.length; i++) {
            list1[i] = scanner.nextInt();
        }
        System.out.print("Enter list2: ");
        int[] list2 = new int[scanner.nextInt()];
        for (int i = 0; i < list2.length; i++) {
            list2[i] = scanner.nextInt();
        }
        System.out.println(equals(list1, list2) ?
                "Two lists are strictly identical" : "Two lists are not strictly identical");
    }

    public static boolean equals(int[] list1, int[] list2) {
        if (list1.length != list2.length) return false;
        for (int i = 0; i < list1.length; i++) {
            if (list1[i] != list2[i]) return false;
        }
        return true;
    }
}
