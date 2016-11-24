package chapter_08;

import java.util.Scanner;

/**
 * (Sum the major diagonal in a matrix) Write a method that sums all the numbers
 * in the major diagonal in an n * n matrix of double values using the following
 * header:
 *
 *      public static double sumMajorDiagonal(double[][] m)
 *
 * Write a test program that reads a 4-by-4 matrix and displays the sum of all its
 * elements on the major diagonal. Here is a sample run:
 *
 *      Enter a 4-by-4 matrix row by row:
 *      1 2 3 4.0 (enter)
 *      5 6.5 7 8 (enter)
 *      9 10 11 12 (enter)
 *      13 14 15 16 (enter)
 *      Sum of the elements in the major diagonal is 34.5
 */
public class PE_08_02_Sum_the_major_diagonal_in_a_matrix {
    public static void main(String[] args) {
        double[][] matrix = getMatrix(4, 4);
        System.out.println("Sum of the elements in the major diagonal is " + sumMajorDiagonal(matrix));
    }

    public static double[][] getMatrix(int rows, int columns) {
        double[][] m = new double[rows ][columns];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a 4-by-4 matrix row by row:");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                m[r][c] = scanner.nextDouble();
            }
        }
        return m;
    }

    public static double sumMajorDiagonal(double[][] m) {
        double sum = 0;
        for (int r = 0; r < m.length; r++) {
            sum += m[r][r];
        }
        return sum;
    }
}
