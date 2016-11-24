package chapter_11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * (Sort ArrayList) Write the following method that sorts an ArrayList of
 * numbers:
 *
 *      public static void sort(ArrayList<Integer> list)
 *
 * Write a test program that prompts the user to enter 5 numbers, stores them in an
 * array list, and displays them in increasing order.
 */
public class PE_11_11_Sort_ArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 5 numbers: ");
        for (int i = 0; i < 5; i++) {
            list.add(scanner.nextInt());
        }
        sort(list);
        System.out.println(list);
    }

    public static void sort(ArrayList<Integer> list) {
        Collections.sort(list);
    }
}
