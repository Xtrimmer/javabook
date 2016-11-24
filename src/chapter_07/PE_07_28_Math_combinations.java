package chapter_07;

import java.util.Scanner;

/**
 * (Math: combinations) Write a program that prompts the user to enter 10 integers
 * and displays all combinations of picking two numbers from the 10.
 */
public class PE_07_28_Math_combinations {
    public static void main(String[] args) {
        int[] numbers = getNumbers(10);
        int count = displayCombinations(numbers);
        System.out.println("There are " + count + " combinations.");
    }

    private static int displayCombinations(int[] numbers) {
        int count = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                System.out.print("[" + numbers[i] + ", " + numbers[j] + "] ");
                count++;
            }
            System.out.println();
        }
        return count;
    }

    public static int[] getNumbers(int size) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[size];
        System.out.print("Enter " + size + " numbers: ");
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }
        return numbers;
    }
}
