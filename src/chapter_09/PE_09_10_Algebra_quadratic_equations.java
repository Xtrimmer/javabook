package chapter_09;

import java.util.Scanner;

/**
 * (Algebra: quadratic equations) Design a class named QuadraticEquation for
 * a quadratic equation ax2 + bx + x = 0. The class contains:
 *
 * - Private data fields a, b, and c that represent three coefficients.
 * - A constructor for the arguments for a, b, and c.
 * - Three getter methods for a, b, and c.
 * - A method named getDiscriminant() that returns the discriminant, which is
 *      b^2 - 4 * a * c.
 * - The methods named getRoot1() and getRoot2() for returning two roots of
 *   the equation:
 *      r1 = (-b + √(b^2 - 4 * a * c)) / (2 * a)
 *      and
 *      r2 = (-b - √(b^2 - 4 * a * c)) / (2 * a)
 *
 * These methods are useful only if the discriminant is non-negative. Let these methods
 * return 0 if the discriminant is negative.
 *
 * Draw the UML diagram for the class and then implement the class. Write a test
 * program that prompts the user to enter values for a, b, and c and displays the result
 * based on the discriminant. If the discriminant is positive, display the two roots. If
 * the discriminant is 0, display the one root. Otherwise, display “The equation has
 * no roots.” See Programming Exercise 3.1 for sample runs.
 */
public class PE_09_10_Algebra_quadratic_equations {
    public static void main(String[] args) {
        double[] values = getValues();
        displayResults(values);
    }

    private static double[] getValues() {
        double[] values = new double[3];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter values for a, b, and c: ");
        for (int i = 0; i < 3; i++) {
            values[i] = scanner.nextDouble();
        }
        return values;
    }

    private static void displayResults(double[] values) {
        final int A = 0, B = 1, C = 2;
        QuadraticEquation quadraticEquation = new QuadraticEquation(values[A], values[B], values[C]);
        if (quadraticEquation.getDiscriminant() > 0)
            System.out.printf("The two roots are %.2f & %.2f.", quadraticEquation.getRoot1(), quadraticEquation.getRoot2());
        else if (quadraticEquation.getDiscriminant() == 0)
            System.out.printf("The root is %.2f.", quadraticEquation.getRoot1());
        else System.out.print("The equation has no roots.");
    }

    private static class QuadraticEquation {
        private final double a;
        private final double b;
        private final double c;

        QuadraticEquation(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
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

        double getRoot1() {
            double discriminant = getDiscriminant();
            if (discriminant < 0) return 0;
            else return (-b + Math.sqrt(discriminant)) / (2 * a);
        }

        double getDiscriminant() {
            return Math.pow(b, 2) - (4 * a * c);
        }

        double getRoot2() {
            double discriminant = getDiscriminant();
            if (discriminant < 0) return 0;
            else return (-b - Math.sqrt(discriminant)) / (2 * a);
        }
    }
}

