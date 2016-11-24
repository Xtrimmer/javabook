package chapter_08;

/**
 * (Even number of 1s) Write a program that generates a 6-by-6 two-dimensional
 * matrix filled with 0s and 1s, displays the matrix, and checks if every row and
 * every column have an even number of 1s.
 */
public class PE_08_22_Even_number_of_1s {
    public static void main(String[] args) {
        int[][] binaryMatrix = generateSquareBinaryMatrix(6);
        printMatrix(binaryMatrix);
        boolean isAllEven = checkAllEven(binaryMatrix);
        System.out.println(isAllEven ? "every row and every column have an even number of 1s" :
        "every row and every column do not have an even number of 1s");
    }

    private static boolean checkAllEven(int[][] binaryMatrix) {
        int rowCount;
        int columnCount;
        for (int i = 0; i < binaryMatrix.length; i++) {
            rowCount = 0;
            columnCount = 0;
            for (int j = 0; j < binaryMatrix.length; j++) {
                if (binaryMatrix[i][j] == 1) rowCount++;
                if (binaryMatrix[j][i] == 1) columnCount++;
            }
            if (rowCount % 2 != 0 || columnCount % 2 != 0) return false;
        }
        return true;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element);
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
}
