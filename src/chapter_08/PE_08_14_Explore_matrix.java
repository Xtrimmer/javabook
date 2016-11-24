package chapter_08;

import java.util.Scanner;

/**
 * (Explore matrix) Write a program that prompts the user to enter the length of a
 * square matrix, randomly fills in 0s and 1s into the matrix, prints the matrix, and
 * finds the rows, columns, and diagonals with all 0s or 1s. Here is a sample run of
 * the program:
 *
 *      Enter the size for the matrix: 4 (enter)
 *      0111
 *      0000
 *      0100
 *      1111
 *      All 0s on row 1
 *      All 1s on row 3
 *      No same numbers on a column
 *      No same numbers on the major diagonal
 *      No same numbers on the sub-diagonal
 */
public class PE_08_14_Explore_matrix {
    public static void main(String[] args) {
        int size = getSize();
        int[][] matrix = makeMatrix(size);
        printMatrix(matrix);
        exploreMatrix(matrix);
    }

    public static int[][] makeMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (int)(Math.random() * 2);
            }
        }
        return matrix;
    }

    public static int getSize() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size for the matrix: ");
        return scanner.nextInt();
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] aMatrix : matrix) {
            for (int anAMatrix : aMatrix) {
                System.out.print(anAMatrix);
            }
            System.out.println();
        }
    }

    public static void exploreMatrix(int[][] matrix) {
        int rowVal;
        int colVal;

        boolean hasEqualRow = false;
        boolean hasEqualCol = false;
        boolean majDiagSimilar = true;
        boolean subDiagSimilar = true;
        for (int i = 0; i < matrix.length; i++) {
            rowVal = matrix[i][0];
            colVal = matrix[0][i];
            boolean rowIsSimilar = true;
            boolean colIsSimilar = true;
            for (int j = 0; j < matrix.length; j++) {
                rowIsSimilar &= matrix[i][j] == matrix[i][0];
                colIsSimilar &= matrix[j][i] == matrix[0][i];
            }
            if (rowIsSimilar) {
                System.out.println("All " + rowVal + "s on row " + i);
                hasEqualRow = true;
            }
            if (colIsSimilar) {
                System.out.println("All " + colVal + "s on column " + i);
                hasEqualCol = true;
            }
            majDiagSimilar &= matrix[i][i] == matrix[0][0];
            subDiagSimilar &= matrix[i][matrix.length - (i + 1)] == matrix[0][matrix.length - 1];
        }
        if (!hasEqualRow) System.out.println("No same numbers on a row");
        if (!hasEqualCol) System.out.println("No same numbers on a column");
        if (majDiagSimilar) System.out.println("All " + matrix[0][0] + "s on the major diagonal");
        else System.out.println("No same numbers on the major diagonal");
        if (subDiagSimilar) System.out.println("All " + matrix[0][matrix.length - 1] + "s on the sub-diagonal");
        else System.out.println("No same numbers on the sub-diagonal");
    }
}
