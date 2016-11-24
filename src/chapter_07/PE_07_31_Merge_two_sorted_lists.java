package chapter_07;

import java.util.Scanner;

/**
 * (Merge two sorted lists) Write the following method that merges two sorted lists
 * into a new sorted list.
 *
 *      public static int[] merge(int[] list1, int[] list2)
 *
 * Implement the method in a way that takes at most list1.length + list2.
 * length comparisons. Write a test program that prompts the user to enter two
 * sorted lists and displays the merged list. Here is a sample run. Note that the first
 * number in the input indicates the number of the elements in the list. This number
 * is not part of the list.
 *
 *      Enter list1: 5 1 5 16 61 111 (enter)
 *      Enter list2: 4 2 4 5 6 (enter)
 *      The merged list is 1 2 4 5 5 6 16 61 111
 */
public class PE_07_31_Merge_two_sorted_lists {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter list1: ");
        int size = scanner.nextInt();
        int[] list1 = new int[size];
        for (int i = 0; i < size; i++) {
            list1[i] = scanner.nextInt();
        }
        System.out.print("Enter list2: ");
        size = scanner.nextInt();
        int[] list2 = new int[size];
        for (int i = 0; i < size; i++) {
            list2[i] = scanner.nextInt();
        }
        System.out.print("The new list is ");
        print(merge(list1, list2));
    }

    public static int[] merge(int[] list1, int[] list2) {
        int size = list1.length + list2.length;
        int[] newList = new int[size];
        int p1 = 0, p2 = 0;
        while (p1 < list1.length && p2 < list2.length) {
            if (list1[p1] < list2[p2]) {
                newList[p1 + p2] = list1[p1++];
            } else {
                newList[p1 + p2] = list2[p2++];
            }
        }
        while (p1 < list1.length) newList[p1 + p2] = list1[p1++];
        while (p2 < list2.length) newList[p1 + p2] = list1[p2++];
        return newList;
    }

    public static void print(int[] list) {
        for (int aList : list) {
            System.out.print(aList + " ");
        }
    }
}
