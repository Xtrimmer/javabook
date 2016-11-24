package chapter_08;

import java.util.Scanner;

/**
 * (Identical arrays) The two-dimensional arrays m1 and m2 are identical if they
 * have the same contents. Write a method that returns true if m1 and m2 are identical,
 * using the following header:
 *
 *      public static boolean equals(int[][] m1, int[][] m2)
 *
 * Write a test program that prompts the user to enter two 3 * 3 arrays of integers
 * and displays whether the two are identical. Here are the sample runs.
 *
 *      Enter list1: 51 25 22 6 1 4 24 54 6 (enter)
 *      Enter list2: 51 22 25 6 1 4 24 54 6 (enter)
 *      The two arrays are identical
 *
 *      Enter list1: 51 5 22 6 1 4 24 54 6 (enter)
 *      Enter list2: 51 22 25 6 1 4 24 54 6 (enter) *
 *      The two arrays are not identical
 */
public class PE_08_29_Identical_arrays {
    public static void main(String[] args) {
        System.out.print("Enter list1: ");
        int[][] m1 = getMatrix(3, 3);
        System.out.print("Enter list2: ");
        int[][]m2 = getMatrix(3, 3);
        System.out.println(equals(m1, m2) ?
                "The two arrays are identical" :
                "The two arrays are not identical");
    }

    private static int[][] getMatrix(int rows, int columns) {
        int[][] m = new int[rows ][columns];
        Scanner scanner = new Scanner(System.in);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                m[r][c] = scanner.nextInt();
            }
        }
        return m;
    }

    private static boolean equals(int[][] m1, int[][] m2) {
        if (m1.length != m2.length) return false;
        for (int rowIndex = 0; rowIndex < m1.length; rowIndex++) {
            if (m1[rowIndex].length != m2[rowIndex].length) return false;
        }
        for (int[] row : m1) {
            for (int element : row) {
                if (!containsElement(m2, element)) return false;
            }
        }
        return true;
    }

    private static boolean containsElement(int[][] m, int element) {
        for (int[] row : m) {
            for (int rowElement : row) {
                if (rowElement == element) return true;
            }
        }
        return false;
    }
}
