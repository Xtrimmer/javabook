package chapter_08;

import java.util.Scanner;

/**
 * (Strictly identical arrays) The two-dimensional arrays m1 and m2 are strictly
 * identical if their corresponding elements are equal. Write a method that returns
 * true if m1 and m2 are strictly identical, using the following header:
 *
 *      public static boolean equals(int[][] m1, int[][] m2)
 *
 * Write a test program that prompts the user to enter two 3 * 3 arrays of
 * integers and displays whether the two are strictly identical. Here are the
 * sample runs.
 *
 *      Enter list1: 51 22 25 6 1 4 24 54 6 (enter)
 *      Enter list2: 51 22 25 6 1 4 24 54 6 (enter)
 *      The two arrays are strictly identical
 *
 *      Enter list1: 51 25 22 6 1 4 24 54 6 (enter)
 *      Enter list2: 51 22 25 6 1 4 24 54 6 (enter)
 *      The two arrays are not strictly identical
 */
public class PE_08_28_Strictly_identical_arrays {
    public static void main(String[] args) {
        System.out.print("Enter list1: ");
        int[][] m1 = getMatrix(3, 3);
        System.out.print("Enter list2: ");
        int[][]m2 = getMatrix(3, 3);
        System.out.println(equals(m1, m2) ?
                "The two arrays are strictly identical" :
                "The two arrays are not strictly identical");
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
        for (int rowIndex = 0; rowIndex < m1.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < m1[rowIndex].length; columnIndex++) {
                if (m1[rowIndex][columnIndex] != m2[rowIndex][columnIndex]) return false;
            }
        }
        return true;
    }
}
