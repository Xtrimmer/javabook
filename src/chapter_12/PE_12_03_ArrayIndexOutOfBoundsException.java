package chapter_12;

import java.util.Random;
import java.util.Scanner;

/**
 * (ArrayIndexOutOfBoundsException) Write a program that meets the following
 * requirements:
 * 
 * - Creates an array with 100 randomly chosen integers.
 * - Prompts the user to enter the index of the array, then displays the corresponding
 *   element value. If the specified index is out of bounds, display the
 *   message Out of Bounds.
 */
public class PE_12_03_ArrayIndexOutOfBoundsException {
    public static void main(String[] args) {
        int[] array = generateIntArray(100);
        int index = promptInteger();
        try {
            System.out.println(array[index]);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Out of Bounds");
        }
    }

    private static int[] generateIntArray(int size) {
        int[] numbers = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            numbers[i] = random.nextInt();
        }
        return numbers;
    }

    public static int promptInteger() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the index of the array: ");
        return scanner.nextInt();
    }
}
