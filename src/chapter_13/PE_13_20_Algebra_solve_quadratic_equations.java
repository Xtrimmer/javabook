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
        if (discriminant > 0) {
            root1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            root2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            JOptionPane.showMessageDialog(null, "The roots are:\n[" + root1 + ", " + root2 + "]");
        } else if (discriminant == 0) {
            root1 = -b / (2 * a);
            JOptionPane.showMessageDialog(null, "The root is:\n[" + root1 + "]");
        } else {
            Complex complexRoot1 = new Complex(-b / (2 * a), Math.sqrt(-discriminant) / (2 * a));
            Complex complexRoot2 = new Complex(-b / (2 * a), -Math.sqrt(-discriminant) / (2 * a));
            JOptionPane.showMessageDialog(null, "The roots are:\n[" + complexRoot1 + ", " + complexRoot2 + "]");
        }
    }

    static class Complex implements Cloneable {
        double a = 0;
        double b = 0;

        public Complex() {
        }

        public Complex(double a) {
            this.a = a;
        }

        public Complex(double a, double b) {
            this.a = a;
            this.b = b;
        }

        public double abs() {
            return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        }

        public Complex add(Complex that) {
            double a = this.a + that.a;
            double b = this.b + that.b;
            return new Complex(a, b);
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            double a = Math.round(this.a * 10000) / 10000.0;
            double b = Math.round(this.b * 10000) / 10000.0;
            if (b == 0) {
                return a + "";
            } else {
                return "(" + a + " + " + b + "i)";
            }
        }

        public Complex divide(Complex that) {
            double a = ((this.a * that.a) + (this.b * that.b))
                    / (Math.pow(that.a, 2) + Math.pow(that.b, 2));
            double b = ((this.b * that.a) - (this.a * that.b))
                    / (Math.pow(that.a, 2) + Math.pow(that.b, 2));
            return new Complex(a, b);
        }

        public String getImaginaryPart() {
            double b = Math.round(this.b * 10000) / 10000.0;
            return b + "i";
        }

        public double getRealPart() {
            double a = Math.round(this.a * 10000) / 10000.0;
            return a;
        }

        public Complex multiply(Complex that) {
            double a = (this.a * that.a) - (this.b * that.b);
            double b = (this.b * that.a) + (this.a * that.b);
            return new Complex(a, b);
        }

        public Complex subtract(Complex that) {
            double a = this.a - that.a;
            double b = this.b - that.b;
            return new Complex(a, b);
        }
    }
}
