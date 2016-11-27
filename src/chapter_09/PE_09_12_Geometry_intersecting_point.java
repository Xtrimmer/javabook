package chapter_09;

import javafx.geometry.Point2D;

import java.util.Scanner;

/**
 * (Geometry: intersecting point) Suppose two line segments intersect. The two endpoints
 * for the first line segment are (x1, y1) and (x2, y2) and for the second line
 * segment are (x3, y3) and (x4, y4). Write a program that prompts the user to enter
 * these four endpoints and displays the intersecting point. As discussed in Programming
 * Exercise 3.25, the intersecting point can be found by solving a linear equation.
 * Use the LinearEquation class in Programming Exercise 9.11 to solve this
 * equation. Here are some sample runs:
 *
 *      Enter x1, y1, x2, y2, x3, y3, x4, y4: 2 2 5 -1.0 4.0 2.0 -1.0 -2.0 (enter)
 *      The intersecting point is at (2.88889, 1.1111)
 *
 *      Enter x1, y1, x2, y2, x3, y3, x4, y4: 2 2 7 6.0 4.0 2.0 -1.0 -2.0 (enter)
 *      The two lines are parallel
 */
public class PE_09_12_Geometry_intersecting_point {
    public static void main(String[] args) {
        Point2D[] point2Ds = promptPoint2Ds(4);
        double a = point2Ds[0].getY() - point2Ds[1].getY();
        double b = point2Ds[0].getX() - point2Ds[1].getX();
        double c = point2Ds[2].getY() - point2Ds[3].getY();
        double d = point2Ds[2].getX() - point2Ds[3].getX();
        double e = (a * point2Ds[0].getX()) - (b * point2Ds[0].getY());
        double f = (c * point2Ds[2].getX()) - (d * point2Ds[2].getY());
        LinearEquation linearEquation = new LinearEquation(a, b, c, d, e, f);
        printResults(linearEquation);
    }

    private static Point2D[] promptPoint2Ds(int numberOfPoints) {
        Point2D[] point2Ds = new Point2D[numberOfPoints];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < numberOfPoints; i++) {
            System.out.print("Enter X and Y values for point-" + i + ": ");
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            point2Ds[i] = new Point2D(x, y);
        }
        return point2Ds;
    }

    private static void printResults(LinearEquation linearEquation) {
        if (linearEquation.isSolvable()) {
            System.out.printf("The intersecting point is at (%f, %f)",
                    linearEquation.getX(), linearEquation.getY());
        } else {
            System.out.println("The lines are parallel.");
        }
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
