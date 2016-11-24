package chapter_07;

import java.util.Scanner;

/**
 * (Reverse the numbers entered) Write a program that reads ten integers and displays
 * them in the reverse of the order in which they were read.
 */
public class PE_07_02_Reverse_the_numbers_entered {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[10];
        System.out.print("Enter 10 numbers: ");
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = scanner.nextInt();
        }
        reverseArray(numbers);
        printArray(numbers);

    }

    public static void reverseArray(int[] array){
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }

    public static void printArray(int[] array){
        for (int anArray : array) {
            System.out.print(anArray + " ");
        }
    }
}
