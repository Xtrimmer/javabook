package chapter_08;

import java.util.Scanner;

/**
 * (Algebra: multiply two matrices) Write a method to multiply two matrices. The
 * header of the method is:
 *
 *      public static double[][] multiplyMatrix(double[][] a, double[][] b)
 *
 * To multiply matrix a by matrix b, the number of columns in a must be the same as
 * the number of rows in b, and the two matrices must have elements of the same or
 * compatible types. Let c be the result of the multiplication. Assume the column size
 * of matrix a is n. Each element cij is ai1 * b1j + ai2 * b2j + c + ain * bnj.
 * For example, for two 3 * 3 matrices a and b, c is
 *
 *      a11 a12 a13   b11 b12 b13   c11 c12 c13
 *      a21 a22 a23 * b21 b22 b23 = c21 c22 c23
 *      a31 a32 a33   b31 b32 b33   c31 c32 c33
 *
 * where cij = ai1 * b1j + ai2 * b2j + ai3 * b3j.
 * Write a test program that prompts the user to enter two 3 * 3 matrices and displays
 * their product. Here is a sample run:
 *
 *      Enter matrix1: 1 2 3 4 5 6 7 8 9 (enter)
 *      Enter matrix2: 0 2 4 1 4.5 2.2 1.1 4.3 5.2 (enter)
 *      The multiplication of the matrices is
 *      1 2 3     0 2.0 4.0     5.3 23.9 24
 *      4 5 6  *  1 4.5 2.2  =  11.6 56.3 58.2
 *      7 8 9     1.1 4.3 5.2   17.9 88.7 92.4
 */
public class PE_08_06_Algebra_multiply_two_matrices {
    public static void main(String[] args) {
        double[][] matrix1 = getMatrix(3, 3);
        double[][] matrix2 = getMatrix(3, 3);
        double[][] sum = multiplyMatrix(matrix1, matrix2);
        System.out.println("The multiplication of the matrices is");
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

    public static double[][] multiplyMatrix(double[][] a, double[][] b) {
        if (a.length == 0) return null;
        for (double[] anA : a) {
            if (a[0].length != b.length) return null;
        }
        double[][] product = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    product[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return product;
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
