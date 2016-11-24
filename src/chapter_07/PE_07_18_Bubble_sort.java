package chapter_07;

import java.util.Arrays;
import java.util.Scanner;

/**
 * (Bubble sort) Write a sort method that uses the bubble-sort algorithm. The bubblesort
 * algorithm makes several passes through the array. On each pass, successive
 * neighboring pairs are compared. If a pair is not in order, its values are swapped;
 * otherwise, the values remain unchanged. The technique is called a bubble sort or
 * sinking sort because the smaller values gradually “bubble” their way to the top
 * and the larger values “sink” to the bottom. Write a test program that reads in ten
 * double numbers, invokes the method, and displays the sorted numbers.
 */
public class PE_07_18_Bubble_sort {
    public static void main(String[] args) {
        final int SIZE = 10;
        double[] numbers = new double[SIZE];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter " + SIZE + " double values: ");
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = scanner.nextDouble();
        }
        bubbleSort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    public static void bubbleSort(double[] array) {
        boolean sorted;
        double temp;
        do {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    sorted = false;
                }
            }
        } while (!sorted);
    }
}
