package chapter_08;

import java.util.Scanner;

/**
 * (Sum elements column by column) Write a method that returns the sum of all the
 * elements in a specified column in a matrix using the following header:
 *
 *      public static double sumColumn(double[][] m, int columnIndex)
 *
 * Write a test program that reads a 3-by-4 matrix and displays the sum of each
 * column. Here is a sample run:
 *
 *      Enter a 3-by-4 matrix row by row:
 *      1.5 2 3 4 (enter)
 *      5.5 6 7 8 (enter)
 *      9.5 1 3 1 (enter)
 *      Sum of the elements at column 0 is 16.5
 *      Sum of the elements at column 1 is 9.0
 *      Sum of the elements at column 2 is 13.0
 *      Sum of the elements at column 3 is 13.0
 */
public class PE_08_01_Sum_elements_column_by_column {
    public static void main(String[] args) {
        double[][] matrix = getMatrix(3, 4);
        for (int i = 0; i < matrix[0].length; i++) {
            System.out.println("Sum of the elements at column " + i + " is " + sumColumn(matrix, i));
        }
    }

    public static double[][] getMatrix(int rows, int columns){
        Scanner scanner = new Scanner(System.in);
        double[][] matrix = new double[rows][columns];
        System.out.println("Enter a 3-by-4 matrix row by row:");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                matrix[r][c] = scanner.nextDouble();
            }
        }
        return matrix;
    }

    public static double sumColumn(double[][] m, int columnIndex) {
        double sum = 0;
        for (double[] aM : m) {
            sum += aM[columnIndex];
        }
        return sum;
    }
}
