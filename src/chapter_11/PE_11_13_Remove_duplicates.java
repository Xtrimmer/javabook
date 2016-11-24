package chapter_11;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * (Remove duplicates) Write a method that removes the duplicate elements from
 * an array list of integers using the following header:
 *
 *      public static void removeDuplicate(ArrayList<Integer> list)
 *
 * Write a test program that prompts the user to enter 10 integers to a list and displays
 *
 *      Enter ten integers: 34 5 3 5 6 4 33 2 2 4 (enter)
 *      The distinct integers are 34 5 3 6 4 33 2
 */
public class PE_11_13_Remove_duplicates {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ten integers: ");
        for (int i = 0; i < 10; i++) {
            list.add(scanner.nextInt());
        }
        removeDuplicate(list);
        printList(list);
    }

    private static void printList(ArrayList<Integer> list) {
        System.out.print("The distinct integers are");
        for (Integer i : list) {
            System.out.print(" " + i);
        }
    }

    public static void removeDuplicate(ArrayList<Integer> list) {
        ArrayList<Integer> noDuplicates = new ArrayList<>();
        for (Integer integer : list) {
            if (!noDuplicates.contains(integer)) noDuplicates.add(integer);
        }
        list.clear();
        list.addAll(noDuplicates);
    }

}
