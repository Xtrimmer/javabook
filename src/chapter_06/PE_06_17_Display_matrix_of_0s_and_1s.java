package chapter_06;

import java.util.Scanner;

/**
 * (Display matrix of 0s and 1s) Write a method that displays an n-by-n matrix using
 * the following header:
 *
 *      public static void printMatrix(int n)
 *
 * Each element is 0 or 1, which is generated randomly. Write a test program that
 * prompts the user to enter n and displays an n-by-n matrix. Here is a sample run:
 *
 *      Enter n: 3 (enter)
 *      0 1 0
 *      0 0 0
 *      1 1 1
 */
public class PE_06_17_Display_matrix_of_0s_and_1s {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int n = scanner.nextInt();
        printMatrix(n);
    }

    public static void printMatrix(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print((int) (Math.random() * 2) + " ");
            }
            System.out.println();
        }
    }
}
