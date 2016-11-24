package chapter_11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * (Maximum element in ArrayList) Write the following method that returns the
 * maximum value in an ArrayList of integers. The method returns null if the
 * list is null or the list size is 0.
 *
 *      public static Integer max(ArrayList<Integer> list)
 *
 * Write a test program that prompts the user to enter a sequence of numbers ending
 * with 0, and invokes this method to return the largest number in the input.
 */
public class PE_11_04_Maximum_element_in_ArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> list = getIntegerList();
        Integer max = max(list);
        System.out.println("Max = " + max);
    }

    private static ArrayList<Integer> getIntegerList() {
        ArrayList<Integer> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a sequence of numbers ending with 0: ");
        int next = scanner.nextInt();
        while (next != 0) {
            list.add(next);
            next = scanner.nextInt();
        }
        return list;
    }

    public static Integer max(ArrayList<Integer> list) {
        return list.isEmpty() ? null : Collections.max(list);
    }
}
