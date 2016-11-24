package chapter_11;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * (Largest rows and columns) Write a program that randomly fills in 0s and 1s
 * into an n-by-n matrix, prints the matrix, and finds the rows and columns with the
 * most 1s. (Hint: Use two ArrayLists to store the row and column indices with
 * the most 1s.) Here is a sample run of the program:
 *
 *      Enter the array size n: 4 (enter)
 *      The random array is
 *      0011
 *      0011
 *      1101
 *      1010
 *      The largest row index: 2
 *      The largest column index: 2, 3
 */
public class PE_11_09_Largest_rows_and_columns {
    private static final int ROW = 0;
    private static final int COLUMN = 1;

    public static void main(String[] args) {
        int size = promptIntegerValue();
        int[][] binaryMatrix = generateSquareBinaryMatrix(size);
        printMatrix(binaryMatrix);
        ArrayList[] largestCountIndices = getLargestCountIndices(binaryMatrix);
        System.out.println("The largest row index: " + largestCountIndices[ROW]);
        System.out.println("The largest column index: " + largestCountIndices[COLUMN]);
    }

    public static int promptIntegerValue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the array size n: ");
        return scanner.nextInt();
    }

    private static void printMatrix(int[][] matrix) {
        System.out.println("The random array is");
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    private static int[][] generateSquareBinaryMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (int)(Math.random() * 2);
            }
        }
        return matrix;
    }

    private static ArrayList[] getLargestCountIndices(int[][] binaryMatrix) {
        ArrayList[] largestCountIndices = new ArrayList[2];
        largestCountIndices[ROW] = new ArrayList<Integer>();
        largestCountIndices[COLUMN] = new ArrayList<Integer>();
        int[] maxCount = new int[2];
        int Count[] = new int[2];
        for (int i = 0; i < binaryMatrix.length; i++) {
            Count[ROW] = 0;
            Count[COLUMN] = 0;
            for (int j = 0; j < binaryMatrix.length; j++) {
                if (binaryMatrix[i][j] == 1) Count[ROW]++;
                if (binaryMatrix[j][i] == 1) Count[COLUMN]++;
            }
            for (int j = 0; j < 2; j++) {
                if (Count[j] > maxCount[j]) {
                    maxCount[j] = Count[j];
                    largestCountIndices[j].clear();
                    largestCountIndices[j].add(i);
                } else if (Count[j] == maxCount[j]) {
                    largestCountIndices[j].add(i);
                }
            }
        }
        return largestCountIndices;
    }
}
