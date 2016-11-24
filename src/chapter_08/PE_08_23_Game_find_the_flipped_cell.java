package chapter_08;

import java.util.Scanner;

/**
 * (Game: find the flipped cell) Suppose you are given a 6-by-6 matrix filled with
 * 0s and 1s. All rows and all columns have an even number of 1s. Let the user flip
 * one cell (i.e., flip from 1 to 0 or from 0 to 1) and write a program to find which
 * cell was flipped. Your program should prompt the user to enter a 6-by-6 array
 * with 0s and 1s and find the first row r and first column c where the even number
 * of the 1s property is violated (i.e., the number of 1s is not even). The flipped cell
 * is at (r, c). Here is a sample run:
 *
 *      Enter a 6-by-6 matrix row by row:
 *      1 1 1 0 1 1 (enter)
 *      1 1 1 1 0 0 (enter)
 *      0 1 0 1 1 1 (enter)
 *      1 1 1 1 1 1 (enter)
 *      0 1 1 1 1 0 (enter)
 *      1 0 0 0 0 1 (enter)
 *      The flipped cell is at (0, 1)
 */
public class PE_08_23_Game_find_the_flipped_cell {
    public static void main(String[] args) {
        int[][] binaryMatrix = getSquareBinaryMatrix(6);
        //int[][] binaryMatrix = generateSquareBinaryMatrix(6);
        //printMatrix(binaryMatrix);
        int[] flippedCellCoordinates = getFirstFlippedCell(binaryMatrix);
        printFlippedCellCoordinates(flippedCellCoordinates);
    }

    private static void printFlippedCellCoordinates(int[] flippedCellCoordinates) {
        final int X = 0;
        final int Y = 1;
        if (flippedCellCoordinates[X] == -1) System.out.println("No cells need flipping");
        else System.out.println("The flipped cell is at (" +
                flippedCellCoordinates[X] + ", " + flippedCellCoordinates[Y] + ")");
    }

    private static int[][] getSquareBinaryMatrix(int size) {
        int[][] matrix = new int[size][size];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a 6-by-6 matrix row by row:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    private static int[] getFirstFlippedCell(int[][] binaryMatrix) {
        final int X = 0;
        final int Y = 1;
        int[] flippedCellCoordinates = new int[2];
        for (int row = 0; row < binaryMatrix.length; row++) {
            int rowCount = 0;
            for (int element : binaryMatrix[row]) {
                if (element == 1) rowCount++;
            }
            if (rowCount % 2 != 0) {
                flippedCellCoordinates[X] = row;
                for (int column = 0; column < binaryMatrix[row].length; column++) {
                    int columnCount = 0;
                    for (int element = 0; element < binaryMatrix.length; element++) {
                        if (binaryMatrix[element][column] == 1) columnCount++;
                    }
                    if (columnCount % 2 != 0) {
                        flippedCellCoordinates[Y] = column;
                        return flippedCellCoordinates;
                    }
                }
            }
        }
        return new int[]{-1, -1};
    }

    // For testing purposes
    private static int[][] generateSquareBinaryMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (int) (Math.random() * 2);
            }
        }
        return matrix;
    }

    //for testing purposes
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
