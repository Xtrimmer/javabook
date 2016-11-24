package chapter_08;

import java.util.Scanner;

/**
 * (Column sorting) Implement the following method to sort the columns in a two-dimensional
 * array. A new array is returned and the original array is intact.
 *
 *      public static double[][] sortColumns(double[][] m)
 *
 * Write a test program that prompts the user to enter a 3 * 3 matrix of double
 * values and displays a new column-sorted matrix. Here is a sample run:
 *
 *      Enter a 3-by-3 matrix row by row:
 *      0.15 0.875 0.375 (enter)
 *      0.55 0.005 0.225 (enter)
 *      0.30 0.12 0.4 (enter)
 *
 *      The column-sorted array is
 *      0.15 0.0050 0.225
 *      0.3 0.12 0.375
 *      0.55 0.875 0.4
 */
public class PE_08_27_Column_sorting {
    public static void main(String[] args) {
        double[][] matrixOriginal = getMatrix(3, 3);
        double[][] matrixSorted = sortColumns(matrixOriginal);
        System.out.println();
        printMatrix(matrixSorted);
    }

    private static double[][] getMatrix(int rows, int columns) {
        double[][] m = new double[rows ][columns];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a " + rows + "-by-" + columns + " matrix row by row:");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                m[r][c] = scanner.nextDouble();
            }
        }
        return m;
    }

    private static double[][] sortColumns(double[][] m) {
        double[][] newArray = m;
        for (int i = 0; i < m[0].length; i++) {
            double[] column = new double[m.length];
            for (int j = 0; j < m.length; j++) {
                column[j] = m[j][i];
            }
            bubbleSort(column);
            for (int j = 0; j < newArray.length; j++) {
                m[j][i] = column[j];
            }
        }
        return newArray;
    }

    private static void printMatrix(double[][] matrix) {
        System.out.println("The row-sorted array is");
        for (double[] row : matrix) {
            for (double element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    private static void bubbleSort(double[] array) {
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
