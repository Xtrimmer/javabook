package chapter_03;

import java.util.Scanner;

/**
 * (Algebra: solve 2 X 2 linear equations) You can use Cramer's rule to solve the
 * following 2 X 2 system of linear equation:
 *
 *      ax + by = e
 *      cx + dy = f
 *      x = (ed - bf) / (ad - bc)
 *      y = (af - ec) / (ad - bc)
 *
 * Write a program that prompts the user to enter a, b, c, d, e, and f and displays the
 * result. If ad - bc is 0, report that “The equation has no solution”.
 *
 *      Enter a, b, c, d, e, f: 9.0 4.0 3.0 -5.0 -6.0 -21.0 (enter)
 *      x is -2.0 and y is 3.0
 *
 *      Enter a, b, c, d, e, f: 1.0 2.0 2.0 4.0 4.0 5.0 (enter)
 *      The equation has no solution
 */
public class PE_03_03_Algebra_solve_2X2_linear_equations {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter a, b, c, d, e, f: ");
        double a = SCANNER.nextDouble();
        double b = SCANNER.nextDouble();
        double c = SCANNER.nextDouble();
        double d = SCANNER.nextDouble();
        double e = SCANNER.nextDouble();
        double f = SCANNER.nextDouble();
        double denominator = a * d - b * c;

        if (denominator == 0) {
            System.out.println("The equation has no solution.");
        } else {
            double x = (e * d - b * f) / denominator;
            double y = (a * f - e * c) / denominator;
            System.out.println("x is " + x + " and y is " + y);
        }

    }
}
