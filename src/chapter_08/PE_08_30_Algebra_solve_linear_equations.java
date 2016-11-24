package chapter_08;

import java.util.Scanner;

/**
 * (Algebra: solve linear equations) Write a method that solves the following
 * 2 * 2 system of linear equations:
 *
 *      a[0][0] * x + a[0][1] * y = b[0]
 *      a[1][0] * x + a[1][1] * y = b[1]
 *      x = (b[0] * a[1][1] - b[1] * a[0][1]) / (a[0][0] * a[1][1] - a[0][1] * a[1][0])
 *      y = (b[1] * a[0][0] - b[0] * a[1][0]) / (a[0][0] * a[1][1] - a[0][1] * a[1][0])
 *
 * The method header is
 *
 *      public static double[] linearEquation(double[][] a, double[] b)
 *
 * The method returns null if a[0][0] * a[1][1] - a[0][1] * a[1][0] is 0. Write a test program that
 * prompts the user to enter a00, a01, a10, a11, b0, and b1, and displays the result. If
 * a00a11 - a01a10 is 0, report that “The equation has no solution.” A sample run is
 * similar to Programming Exercise 3.3.
 *
 *      Enter a, b, c, d, e, f: 9.0 4.0 3.0 -5.0 -6.0 -21.0 (enter)
 *      x is -2.0 and y is 3.0
 *
 *      Enter a, b, c, d, e, f: 1.0 2.0 2.0 4.0 4.0 5.0 (enter)
 *      The equation has no solution
 */
public class PE_08_30_Algebra_solve_linear_equations {
    public static void main(String[] args) {
        printIntro();
        double[][] a = get2DDoubleArray(2, 2);
        double[] b = get1DDoubleArray(2);
        double[] solution = linearEquation(a, b);
        printResult(solution);
    }

    private static void printResult(double[] solution) {
        final int X = 0, Y = 1;
        System.out.println(solution == null ? "The equation has no solution." :
                "x is " + solution[X] + " and y is " + solution[Y]);
    }

    private static double[] get1DDoubleArray(int size) {
        double[] m = new double[size];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter e, f: ");
        for (int i = 0; i < size; i++) {
            m[i] = scanner.nextDouble();
        }
        return m;
    }

    private static void printIntro() {
        System.out.println("You can use Cramer's rule to solve the\n" +
                "following 2 X 2 system of linear equation:\n" +
                "\n" +
                "       ax + by = e\n" +
                "       cx + dy = f\n" +
                "       x = (ed - bf) / (ad - bc)\n" +
                "       y = (af - ec) / (ad - bc)\n");
    }

    private static double[][] get2DDoubleArray(int rows, int columns) {
        double[][] m = new double[rows ][columns];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a, b, c, d: ");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                m[r][c] = scanner.nextDouble();
            }
        }
        return m;
    }

    private static double[] linearEquation(double[][] a, double[] b) {
        if (a[0][0] * a[1][1] - a[0][1] * a[1][0] == 0) return null;
        double x = (b[0] * a[1][1] - b[1] * a[0][1]) / (a[0][0] * a[1][1] - a[0][1] * a[1][0]);
        double y = (b[1] * a[0][0] - b[0] * a[1][0]) / (a[0][0] * a[1][1] - a[0][1] * a[1][0]);
        return new double[]{x, y};
    }
}
