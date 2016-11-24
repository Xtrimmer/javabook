package chapter_11;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * (Combine two lists) Write a method that returns the union of two array lists of
 * integers using the following header:
 *
 *      public static ArrayList<Integer> union(
 *          ArrayList<Integer> list1, ArrayList<Integer> list2)
 *
 * For example, the union of two array lists {2, 3, 1, 5} and {3, 4, 6} is
 * {2, 3, 1, 5, 3, 4, 6}. Write a test program that prompts the user to enter two lists,
 * each with five integers, and displays their union. The numbers are separated by
 * exactly one space in the output. Here is a sample run:
 *
 *      Enter five integers for list1: 3 5 45 4 3 (enter)
 *      Enter five integers for list2: 33 51 5 4 13 (enter)
 *      The combined list is 3 5 45 4 3 33 51 5 4 13
 */
public class PE_11_14_Combine_two_lists {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        //Add items to list1
        System.out.print("Enter five integers for list1: ");
        for (int i = 0; i < 5; i++) {
            list1.add(scanner.nextInt());
        }

        //Add items to list2
        System.out.print("Enter five integers for list2: ");
        for (int i = 0; i < 5; i++) {
            list2.add(scanner.nextInt());
        }

        ArrayList<Integer> union = union(list1, list2);
        printList(union);
    }

    private static void printList(ArrayList<Integer> list) {
        System.out.print("The combined list is");
        for (Integer i : list) {
            System.out.print(" " + i);
        }
    }

    public static ArrayList<Integer> union(
            ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> union = new ArrayList<>();
        union.addAll(list1);
        union.addAll(list2);
        return union;
    }
}
