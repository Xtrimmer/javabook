package chapter_08;

/**
 * (Largest row and column) Write a program that randomly fills in 0s and 1s into
 * a 4-by-4 matrix, prints the matrix, and finds the first row and column with the
 * most 1s. Here is a sample run of the program:
 *
 *      0011
 *      0011
 *      1101
 *      1010
 *      The largest row index: 2
 *      The largest column index: 2
 */
public class PE_08_10_Largest_row_and_column {
    public static void main(String[] args) {
        int[][] matrix = newMatrix(4, 4);
        printMatrix(matrix);
        int[] maxIndices = getMaxIndices(matrix);
        System.out.println("The largest row index: " + maxIndices[0]);
        System.out.println("The largest column index: " + maxIndices[1]);
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] aMatrix : matrix) {
            for (int anAMatrix : aMatrix) {
                System.out.print(anAMatrix);
            }
            System.out.println();
        }
    }

    public static int[][] newMatrix(int rows, int columns) {
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = (int)(Math.random() * 2);
            }
        }
        return matrix;
    }

    public static int[] getMaxIndices(int[][] matrix) {
        int colIndex = 0;
        int rowIndex = 0;
        int maxRow= 0;
        int maxCol = 0;
        for (int i = 0; i < matrix.length; i++) {
            int sumRow = 0;
            int sumCol = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sumRow += matrix[i][j];
                sumCol += matrix[j][i];
            }
            if (sumRow > maxRow) {
                maxRow = sumRow;
                rowIndex = i;
            }
            if (sumCol > maxCol) {
                maxCol = sumCol;
                colIndex = i;
            }
        }
        int[] indices = new int[2];
        indices[0] = rowIndex;
        indices[1] = colIndex;
        return indices;
    }
}
