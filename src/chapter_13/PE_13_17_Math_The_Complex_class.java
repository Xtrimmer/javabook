package chapter_13;

import java.util.Scanner;

/**
 * (Math: The Complex class) A complex number is a number in the form a + bi,
 * where a and b are real numbers and i is √-1. The numbers a and b are known
 * as the real part and imaginary part of the complex number, respectively. You can
 * perform addition, subtraction, multiplication, and division for complex numbers
 * using the following formulas:
 *
 *      a + bi + c + di = (a + c) + (b + d)i
 *      a + bi - (c + di) = (a - c) + (b - d)i
 *      (a + bi) * (c + di) = (ac - bd) + (bc + ad)i
 *      (a + bi) / (c + di) = (ac + bd) / (c2 + d2) + (bc - ad)i / (c2 + d2)
 *
 * You can also obtain the absolute value for a complex number using the following
 * formula:
 *
 *      |a + bi|  = √(a^2 + b^2)
 *
 * (A complex number can be interpreted as a point on a plane by identifying the (a,b)
 * values as the coordinates of the point. The absolute value of the complex number
 * corresponds to the distance of the point to the origin, as shown in Figure 13.10b
 * on page 531.)
 *
 * Design a class named Complex for representing complex numbers and the
 * methods add, subtract, multiply, divide, and abs for performing complexnumber
 * operations, and override toString method for returning a string representation
 * for a complex number. The toString method returns (a + bi) as a
 * string. If b is 0, it simply returns a. Your Complex class should also implement the
 * Cloneable interface.
 *
 * Provide three constructors Complex(a, b), Complex(a), and Complex().
 * Complex() creates a Complex object for number 0 and Complex(a) creates
 * a Complex object with 0 for b. Also provide the getRealPart() and
 * getImaginaryPart() methods for returning the real and imaginary part of the
 * complex number, respectively.
 *
 * Write a test program that prompts the user to enter two complex numbers and
 * displays the result of their addition, subtraction, multiplication, division, and absolute
 * value. Here is a sample run:
 *
 *      Enter the first complex number: 3.5 5.5 (enter)
 *      Enter the second complex number: -3.5 1 (enter)
 *      (3.5 + 5.5i) + (-3.5 + 1.0i) = 0.0 + 6.5i
 *      (3.5 + 5.5i) - (-3.5 + 1.0i) = 7.0 + 4.5i
 *      (3.5 + 5.5i) * (-3.5 + 1.0i) = -17.75 + -15.75i
 *      (3.5 + 5.5i) / (-3.5 + 1.0i) = -0.5094 + -1.7i
 *      |(3.5 + 5.5i)| = 6.519202405202649
 *
 * NOTE: Per the book'd errata at http://www.cs.armstrong.edu/liang/intro10e/errata.html,
 * on line 5 in the output box for Exercise 13.7, I've changed -13.75i to -15.75i.
 */
public class PE_13_17_Math_The_Complex_class {
    public static void main(String[] args) {

        Complex complex1 = promptComplexNumber("first");
        Complex complex2 = promptComplexNumber("second");
        Complex result;

        result = complex1.add(complex2);
        System.out.println(complex1 + " + " + complex2 + " = "
                + result.getRealPart() + " + " + result.getImaginaryPart());
        result = complex1.subtract(complex2);
        System.out.println(complex1 + " - " + complex2 + " = " +
                +result.getRealPart() + " + " + result.getImaginaryPart());
        result = complex1.multiply(complex2);
        System.out.println(complex1 + " * " + complex2 + " = " +
                +result.getRealPart() + " + " + result.getImaginaryPart());
        result = complex1.divide(complex2);
        System.out.println(complex1 + " / " + complex2 + " = " +
                +result.getRealPart() + " + " + result.getImaginaryPart());
        System.out.println("|" + complex1 + "| = " + complex1.abs());
    }

    private static Complex promptComplexNumber(String s) {
        Scanner scanner = new Scanner(System.in);
        boolean valid;
        double a = 0;
        double b = 0;
        do {
            valid = true;
            System.out.print("Enter the " + s + " complex number: ");
            if (scanner.hasNextDouble()) {
                a = scanner.nextDouble();
                if (scanner.hasNextDouble()) {
                    b = scanner.nextDouble();
                } else {
                    System.out.println("The second value is invalid\nTry again\n");
                    valid = false;
                    scanner.nextLine();
                }
            } else {
                System.out.println("The first value is invalid\nTry again\n");
                valid = false;
                scanner.nextLine();
            }
        } while (!valid);
        return new Complex(a, b);
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


