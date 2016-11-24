package chapter_08;

import java.util.Scanner;

/**
 * (Algebra: add two matrices) Write a method to add two matrices. The header of
 * the method is as follows:
 *
 *      public static double[][] addMatrix(double[][] a, double[][] b)
 *
 * In order to be added, the two matrices must have the same dimensions and the
 * same or compatible types of elements. Let c be the resulting matrix. Each element
 * cij is aij + bij. For example, for two 3 * 3 matrices a and b, c is
 *
 *      a11 a12 a13       b11 b12 b13       a11 + b11 a12 + b12 a13 + b13
 *      a21 a22 a23   +   b21 b22 b23   =   a21 + b21 a22 + b22 a23 + b23
 *      a31 a32 a33       b31 b32 b33       a31 + b31 a32 + b32 a33 + b33
 *
 * Write a test program that prompts the user to enter two 3 * 3 matrices and
 * displays their sum. Here is a sample run:
 *
 *      Enter matrix1: 1 2 3 4 5 6 7 8 9
 *      Enter matrix2: 0 2 4 1 4.5 2.2 1.1 4.3 5.2
 *      The matrices are added as follows
 *      1.0 2.0 3.0     0.0 2.0 4.0     1.0 4.0 7.0
 *      4.0 5.0 6.0  +  1.0 4.5 2.2  =  5.0 9.5 8.2
 *      7.0 8.0 9.0     1.1 4.3 5.2     8.1 12.3 14.2
 */
public class PE_08_05_Algebra_add_two_matrices {
    public static void main(String[] args) {
        double[][] matrix1 = getMatrix(3, 3);
        double[][] matrix2 = getMatrix(3, 3);
        double[][] sum = addMatrix(matrix1, matrix2);
        System.out.println("The matrices are added as follows");
        printResult(matrix1, matrix2, sum);
    }

    public static double[][] getMatrix(int rows, int columns) {
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

    public static double[][] addMatrix(double[][] a, double[][] b) {
        if (a.length == 0) return null;
        if (a.length != b.length) return null;
        for (int i = 0; i < a.length; i++) {
            if (a[0].length != b[i].length) return null;
        }
        double[][] sum = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                sum[i][j] = a[i][j] + b[i][j];
            }
        }
        return sum;
    }

    public static void printResult(double[][] addend1, double[][] addend2, double[][] sum){
        for (int i = 0; i < addend1.length; i++) {
            for (int j = 0; j < addend1[0].length; j++) {
                System.out.printf("%4.1f ", addend1[i][j]);
            }
            System.out.print(i == addend1.length / 2 ? " +  " : "    ");
            for (int j = 0; j < addend2[0].length; j++) {
                System.out.printf("%4.1f ", addend2[i][j]);
            }
            System.out.print(i == addend1.length / 2 ? " =  " : "    ");
            for (int j = 0; j < sum[0].length; j++) {
                System.out.printf("%4.1f ", sum[i][j]);
            }
            System.out.println();
        }
    }
}
