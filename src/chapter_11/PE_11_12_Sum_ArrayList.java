package chapter_11;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * (Sum ArrayList) Write the following method that returns the sum of all numbers
 * in an ArrayList:
 *
 *      public static double sum(ArrayList<Double> list)
 *
 * Write a test program that prompts the user to enter 5 numbers, stores them in an
 * array list, and displays their sum.
 */
public class PE_11_12_Sum_ArrayList {
    public static void main(String[] args) {
        ArrayList<Double> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 5 numbers: ");
        for (int i = 0; i < 5; i++) {
            list.add(scanner.nextDouble());
        }
        System.out.println(sum(list));
    }

    public static double sum(ArrayList<Double> list) {
        double sum = 0;
        for (Double d : list) {
            sum += d;
        }
        return sum;
    }
}
