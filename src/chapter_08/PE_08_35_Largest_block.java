package chapter_08;

import java.util.Scanner;

/**
 * (Largest block) Given a square matrix with the elements 0 or 1, write a program
 * to find a maximum square submatrix whose elements are all 1s. Your program
 * should prompt the user to enter the number of rows in the matrix. The program
 * then displays the location of the first element in the maximum square submatrix
 * and the number of the rows in the submatrix. Here is a sample run:
 *
 *      Enter the number of rows in the matrix: 5 (enter)
 *      Enter the matrix row by row:
 *      1 0 1 0 1 (enter)
 *      1 1 1 0 1 (enter)
 *      1 0 1 1 1 (enter)
 *      1 0 1 1 1 (enter)
 *      1 0 1 1 1 (enter)
 *
 *      The maximum square submatrix is at (2, 2) with size 3
 *
 * Your program should implement and use the following method to find the maximum
 * square submatrix:
 *
 *      public static int[] findLargestBlock(int[][] m)
 *
 * The return value is an array that consists of three values. The first two values are
 * the row and column indices for the first element in the submatrix, and the third
 * value is the number of the rows in the submatrix.
 */
public class PE_08_35_Largest_block {
    private final static int rowIndex = 0;
    private final static int columnIndex = 1;
    private final static int submatrixSize = 2;

    public static void main(String[] args) {
        int matrixSize = promptMatrixSize();
        int[][] matrix = prompt2DIntArray(matrixSize, matrixSize);
        /*
        for testing purposes:
        int[][] matrix = generateSquareBinaryMatrix(15);
        printMatrix(matrix);
        */
        int[] largestBlock = findLargestBlock(matrix);
        printResults(largestBlock);
    }

    private static void printResults(int[] largestBlock) {
        System.out.print("The maximum square submatrix is at (" +
                largestBlock[rowIndex] + ", " + largestBlock[columnIndex] +
                ") with size " + largestBlock[submatrixSize]);
    }

    private static int promptMatrixSize() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the matrix: ");
        return scanner.nextInt();
    }

    private static int[][] prompt2DIntArray(int rows, int columns) {
        int[][] m = new int[rows][columns];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the matrix row by row:");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                m[r][c] = scanner.nextInt();
            }
        }
        return m;
    }

    private static int[] findLargestBlock(int[][] m) {
        int[] largestBlock = new int[3];
        for (int r = 0; r < m.length; r++) {
            for (int c = 0; c < m.length; c++) {
                if (m[r][c] == 0) continue;
                int largestBlockSizeAtIndex = checkAllBlocksAtIndex(r, c, m);
                if (largestBlockSizeAtIndex > largestBlock[submatrixSize]) {
                    largestBlock[rowIndex] = r;
                    largestBlock[columnIndex] = c;
                    largestBlock[submatrixSize] = largestBlockSizeAtIndex;
                }
            }
        }
        return largestBlock;
    }

    private static int checkAllBlocksAtIndex(int row, int col, int[][] m) {
        int largestBlockPossible = m.length - (row > col ? row : col);
        for (int blockSize = largestBlockPossible; blockSize >= 0 ; blockSize--) {
            if (isAllOnes(row, col, m, blockSize)) return blockSize;
        }
        return 0;
    }

    private static boolean isAllOnes(int row, int col, int[][] m, int maxSquareSize) {
        for (int r = row; r < maxSquareSize + row; r++) {
            for (int c = col; c < maxSquareSize + col; c++) {
                if (m[r][c] == 0) return false;
            }
        }
        return true;
    }

    /*
    for testing purposes:
    private static int[][] generateSquareBinaryMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (int)(Math.random() * 2);
            }
        }
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
    */
}

