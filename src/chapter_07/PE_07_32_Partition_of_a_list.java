package chapter_07;

import java.util.Scanner;

/**
 * (Partition of a list) Write the following method that partitions the list using the
 * first element, called a pivot.
 *
 *      public static int partition(int[] list)
 *
 * After the partition, the elements in the list are rearranged so that all the elements
 * before the pivot are less than or equal to the pivot and the elements after the pivot
 * are greater than the pivot. The method returns the index where the pivot is located
 * in the new list. For example, suppose the list is {5, 2, 9, 3, 6, 8}. After the partition,
 * the list becomes {3, 2, 5, 9, 6, 8}. Implement the method in a way that takes
 * at most list.length comparisons. Write a test program that prompts the user
 * to enter a list and displays the list after the partition. Here is a sample run. Note
 * that the first number in the input indicates the number of the elements in the list.
 * This number is not part of the list.
 *
 *      Enter list: 8 10 1 5 16 61 9 11 1 (enter)
 *      After the partition, the list is 9 1 5 1 10 61 11 16
 */
public class PE_07_32_Partition_of_a_list {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter list: ");
        int size = scanner.nextInt();
        int[] list = new int[size];
        for (int i = 0; i < size; i++) {
            list[i] = scanner.nextInt();
        }
        partition(list);
        System.out.print("After the partition, the list is ");
        print(list);
    }

    public static int partition(int[] list) {
        int i = 0;
        for (int j = 1; j < list.length; j++) {
            if (list[j] < list[i]){
                int temp = list[j];
                System.arraycopy(list, i, list, i + 1, j - i);
                list[i] = temp;
                i++;
            }
        }
        return i;
    }

    public static void print(int[] list) {
        for (int aList : list) {
            System.out.print(aList + " ");
        }
    }
}
