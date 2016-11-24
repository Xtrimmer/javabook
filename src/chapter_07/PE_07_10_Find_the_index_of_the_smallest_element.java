package chapter_07;

import java.util.Arrays;

/**
 * (Find the index of the smallest element) Write a method that returns the index of
 * the smallest element in an array of integers. If the number of such elements is
 * greater than 1, return the smallest index. Use the following header:
 *
 *      public static int indexOfSmallestElement(double[] array)
 *
 * Write a test program that prompts the user to enter ten numbers, invokes this
 * method to return the index of the smallest element, and displays the index.
 */
public class PE_07_10_Find_the_index_of_the_smallest_element {
    public static void main(String[] args) {
        double[] array = new double[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((int)(Math.random() * 100)) / 10.0;
        }
        System.out.println(Arrays.toString(array));
        System.out.println("The smallest index is: " + indexOfSmallestElement(array));
    }

    public static int indexOfSmallestElement(double[] array) {
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[index]) {
                index = i;
            }
        }
        return index;
    }
}
