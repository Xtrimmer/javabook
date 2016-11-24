package chapter_07;

import javax.swing.*;

/**
 * (Algebra: solve quadratic equations) Write a method for solving a quadratic
 * equation using the following header:
 *
 *      public static int solveQuadratic(double[] eqn, double[] roots)
 *
 * The coefficients of a quadratic equation ax^2 + bx + c = 0 are passed to the
 * array eqn and the real roots are stored in roots. The method returns the number
 * of real roots. See Programming Exercise 3.1 on how to solve a quadratic
 * equation.
 *
 * Write a program that prompts the user to enter values for a, b, and c and displays
 * the number of real roots and all real roots.
 */
public class PE_07_25_Algebra_solve_quadratic_equations {
    public static void main(String[] args) {
        double[] eqn = getCoefficients();
        double[] roots = new double[2];
        int rootCount = solveQuadratic(eqn, roots);
        String out = "There are " + rootCount + " root(s)\n";
        if (rootCount > 0) {
            out += rootCount == 1 ? "The root is: " : "The roots are: ";
            for (double root : roots) {
                out += root + " ";
            }
        }
        JOptionPane.showMessageDialog(null, out);
    }

    public static int solveQuadratic(double[] eqn, double[] roots) {
        double discriminant = Math.pow(eqn[1], 2) - (4 * eqn[0] * eqn[2]);
        if (discriminant > 0){
            roots[0] = (-eqn[1] - Math.sqrt(discriminant)) / (2 * eqn[0]);
            roots[1] = (-eqn[1] + Math.sqrt(discriminant)) / (2 * eqn[0]);
            return 2;
        } else if (discriminant == 0) {
            roots[0] = -eqn[1] / (2 * eqn[0]);
            return 1;
        } else {
            return 0;
        }
    }

    public static double[] getCoefficients() {
        double[] array = new double[3];
        String input = JOptionPane.showInputDialog("Enter a value for a:");
        array[0] = Double.parseDouble(input);
        input = JOptionPane.showInputDialog("Enter a value for b:");
        array[1] = Double.parseDouble(input);
        input = JOptionPane.showInputDialog("Enter a value for c:");
        array[2] = Double.parseDouble(input);
        return array;
    }
}
