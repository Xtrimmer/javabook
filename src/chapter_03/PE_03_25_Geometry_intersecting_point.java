package chapter_03;

import java.util.Scanner;

/**
 * (Geometry: intersecting point) Two points on line 1 are given as (x1, y1) and (x2, y2)
 * and on line 2 as (x3, y3) and (x4, y4), as shown in Figure 3.8a–b.
 *
 * The intersecting point of the two lines can be found by solving the following linear equation:
 *
 *      (y1 - y2)x - (x1 - x2)y = (y1 - y2)x1 - (x1 - x2)y1
 *      (y3 - y4)x - (x3 - x4)y = (y3 - y4)x3 - (x3 - x4)y3
 *
 * This linear equation can be solved using Cramer’s rule. (see Programming Exercise
 * 3.3). If the equation has no solutions, the two lines are parallel. (Figure 3.8c).
 * Write a program that prompts the user to enter four points and displays the intersecting point.
 *
 *      Enter x1, y1, x2, y2, x3, y3, x4, y4: 2 2 5 -1.0 4.0 2.0 -1.0 -2.0 (enter)
 *      The intersecting point is at (2.88889, 1.1111)
 *
 *      Enter x1, y1, x2, y2, x3, y3, x4, y4: 2 2 7 6.0 4.0 2.0 -1.0 -2.0 (enter)
 *      The two lines are parallel
 */
public class PE_03_25_Geometry_intersecting_point {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter x1, y1, x2, y2, x3, y3, x4, y4: ");
        double x1 = SCANNER.nextDouble();
        double y1 = SCANNER.nextDouble();
        double x2 = SCANNER.nextDouble();
        double y2 = SCANNER.nextDouble();
        double x3 = SCANNER.nextDouble();
        double y3 = SCANNER.nextDouble();
        double x4 = SCANNER.nextDouble();
        double y4 = SCANNER.nextDouble();

        double a = y1 - y2;
        double b = x1 - x2;
        double c = y3 - y4;
        double d = x3 - x4;
        double e = (a * x1) - (b * y1);
        double f = (c * x3) - (d * y3);
        double determinant = (a * d) - (b * c);

        if (determinant == 0) {
            System.out.println("The lines are parallel.");
        } else {
            double x = (e * d - b * f) / determinant;
            double y = -((a * f) - (e * c)) / determinant;
            x = Math.round(x * 100000) / 100000.0;
            y = Math.round(y * 100000) / 100000.0;
            System.out.println("The intersecting point is at (" + x + ", " + y + ")");
        }
    }
}
