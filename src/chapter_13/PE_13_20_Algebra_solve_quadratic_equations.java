package chapter_13;

import javax.swing.*;

/**
 * (Algebra: solve quadratic equations) Rewrite Programming Exercise 3.1 to obtain
 * imaginary roots if the determinant is less than 0 using the Complex class in
 * Programming Exercise 13.17. Here are some sample runs.
 *
 *      Enter a, b, c: 1 3 1 (enter)
 *      The roots are -0.381966 and -2.61803
 *
 *      Enter a, b, c: 1 2 1 (enter)
 *      The root is -1
 *
 *      Enter a, b, c: 1 2 3 (enter)
 *      The roots are -1.0 + 1.4142i and -1.0 + -1.4142i
 */
public class PE_13_20_Algebra_solve_quadratic_equations {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter a value for a:");
        double a = Double.parseDouble(input);
        input = JOptionPane.showInputDialog("Enter a value for b:");
        double b = Double.parseDouble(input);
        input = JOptionPane.showInputDialog("Enter a value for c:");
        double c = Double.parseDouble(input);

        double discriminant = Math.pow(b, 2) - (4 * a * c);
        double root1;
        double root2;
        if (discriminant > 0){
            root1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            root2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            JOptionPane.showMessageDialog(null, "The roots are:\n[" + root1 + ", " + root2 + "]");
        } else if (discriminant == 0) {
            root1 = -b / (2 * a);
            JOptionPane.showMessageDialog(null, "The root is:\n[" + root1 + "]");
        } else {
            PE_13_17_Complex complexRoot1 = new PE_13_17_Complex(-b / (2 * a), Math.sqrt(-discriminant) / (2 * a));
            PE_13_17_Complex complexRoot2 = new PE_13_17_Complex(-b / (2 * a), -Math.sqrt(-discriminant) / (2 * a));
            JOptionPane.showMessageDialog(null, "The roots are:\n[" + complexRoot1 + ", " + complexRoot2 + "]");
        }
    }
}
