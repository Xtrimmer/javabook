package chapter_09;

import java.util.Scanner;

/**
 * (Algebra: 2 * 2 linear equations) Design a class named LinearEquation for a
 * 2 * 2 system of linear equations:
 *
 *      ax + by = e
 *      cx + dy = f
 *      x = (ed - bf) / (ad - bc)
 *      y = (af - ec) / (ad - bc)
 *
 * The class contains:
 *
 * - Private data fields a, b, c, d, e, and f.
 * - A constructor with the arguments for a, b, c, d, e, and f.
 * - Six getter methods for a, b, c, d, e, and f.
 * - A method named isSolvable() that returns true if ad - bc is not 0.
 * - Methods getX() and getY() that return the solution for the equation.
 *
 * Draw the UML diagram for the class and then implement the class. Write a test
 * program that prompts the user to enter a, b, c, d, e, and f and displays the result.
 * If ad - bc is 0, report that “The equation has no solution.” Here are some sample runs:
 *
 *      Enter a, b, c, d, e, f: 9.0 4.0 3.0 -5.0 -6.0 -21.0 (enter)
 *      x is -2.0 and y is 3.0
 *
 *      Enter a, b, c, d, e, f: 1.0 2.0 2.0 4.0 4.0 5.0 (enter)
 *      The equation has no solution
 */
public class PE_09_11_Algebra_2_by_2_linear_equations {
    public static void main(String[] args) {
        final int A = 0, B = 1, C = 2, D = 3, E = 4, F = 5;
        double[] values = promptValues();
        LinearEquation linearEquation = new LinearEquation(
                values[A], values[B], values[C], values[D], values[E], values[F]);
        printResults(linearEquation);
    }

    private static double[] promptValues() {
        double[] values = new double[6];
        final Scanner SCANNER = new Scanner(System.in);
        System.out.print("Enter a, b, c, d, e, f: ");
        for (int i = 0; i < values.length; i++) {
            values[i] = SCANNER.nextDouble();
        }
        return values;
    }

    private static void printResults(LinearEquation linearEquation) {
        System.out.printf(linearEquation.isSolvable() ?
                        "x is %.2f and y is %.2f" : "The equation has no solution.",
                linearEquation.getX(), linearEquation.getY());
    }

    private static class LinearEquation {
        private final double a;
        private final double b;
        private final double c;
        private final double d;
        private final double e;
        private final double f;

        LinearEquation(double a, double b, double c, double d, double e, double f) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            this.f = f;
        }

        double getA() {
            return a;
        }

        double getB() {
            return b;
        }

        double getC() {
            return c;
        }

        double getD() {
            return d;
        }

        double getE() {
            return e;
        }

        double getF() {
            return f;
        }

        boolean isSolvable() {
            return a * d - b * c != 0;
        }

        double getX() {
            return (e * d - b * f) / (a * d - b * c);
        }

        double getY() {
            return (a * f - e * c) / (a * d - b * c);
        }
    }
}

