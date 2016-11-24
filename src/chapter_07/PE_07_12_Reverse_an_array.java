package chapter_07;

import java.util.Arrays;
import java.util.Scanner;

/**
 * (Reverse an array) The reverse method in Section 7.7 reverses an array by
 * copying it to a new array. Rewrite the method that reverses the array passed in
 * the argument and returns this array. Write a test program that prompts the user to
 * enter ten numbers, invokes the method to reverse the numbers, and displays the
 * numbers.
 */
public class PE_07_12_Reverse_an_array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[10];
        System.out.print("Enter 10 numbers: ");
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = scanner.nextInt();
        }
        numbers = reverse(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    public static int[] reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }
}
