package chapter_08;

import java.util.Scanner;

/**
 * (Markov matrix) An n * n matrix is called a positive Markov matrix if each
 * element is positive and the sum of the elements in each column is 1. Write the
 * following method to check whether a matrix is a Markov matrix.
 *
 *      public static boolean isMarkovMatrix(double[][] m)
 *
 * Write a test program that prompts the user to enter a 3 * 3 matrix of double
 * values and tests whether it is a Markov matrix. Here are sample runs:
 *
 *      Enter a 3-by-3 matrix row by row:
 *      0.15 0.875 0.375 (enter)
 *      0.55 0.005 0.225 (enter)
 *      0.30 0.12 0.4 (enter)
 *      It is a Markov matrix
 *
 *      Enter a 3-by-3 matrix row by row:
 *      0.95 -0.875 0.375 (enter)
 *      0.65 0.005 0.225 (enter)
 *      0.30 0.22 -0.4 (enter)
 *      It is not a Markov matrix
 */
public class PE_08_25_Markov_matrix {
    public static void main(String[] args) {
        double[][] matrix = getMatrix(3, 3);
        boolean isMarkovMatrix = checkIfMarkovMatrix(matrix);
        System.out.println(isMarkovMatrix ?
                "It is a Markov matrix" : "It is not a Markov matrix");
    }

    private static boolean checkIfMarkovMatrix(double[][] matrix) {
        boolean isValid = checkEachElementPositive(matrix);
        return isValid & checkAllColumnsSumToOne(matrix);
    }

    private static boolean checkAllColumnsSumToOne(double[][] matrix) {
        for (int columnIndex = 0; columnIndex < matrix[0].length; columnIndex++) {
            double sum = 0;
            for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
                sum += matrix[rowIndex][columnIndex];
            }
            if (sum != 1) return false;
        }
        return true;
    }

    private static boolean checkEachElementPositive(double[][] matrix) {
        boolean isPositive = true;
        for (double[] row : matrix) {
            for (double element : row) {
                isPositive &= element >= 0;
            }
        }
        return isPositive;
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
}
