package chapter_08;

import java.util.Scanner;

/**
 * (Geometry: intersecting point) Write a method that returns the intersecting point of
 * two lines. The intersecting point of the two lines can be found by using the formula
 * shown in Programming Exercise 3.25. Assume that (x1, y1) and (x2, y2) are the
 * two points on line 1 and (x3, y3) and (x4, y4) are on line 2. The method header is
 *
 *      public static double[] getIntersectingPoint(double[][] points)
 *
 * The points are stored in a 4-by-2 two-dimensional array points with
 * (points[0][0], points[0][1]) for (x1, y1). The method returns the intersecting
 * point or null if the two lines are parallel. Write a program that prompts
 * the user to enter four points and displays the intersecting point. Here is a sample run.
 *
 *      Enter x1, y1, x2, y2, x3, y3, x4, y4: 2 2 5 -1.0 4.0 2.0 -1.0 -2.0 (enter)
 *      The intersecting point is at (2.88889, 1.1111)
 *
 *      Enter x1, y1, x2, y2, x3, y3, x4, y4: 2 2 7 6.0 4.0 2.0 -1.0 -2.0 (enter)
 *      The two lines are parallel
 */
public class PE_08_31_Geometry_intersecting_point {
    public static void main(String[] args) {
        double[][] points = get2DDoubleArray(4, 2);
        double[] intersectingPoint = getIntersectingPoint(points);
        printResults(intersectingPoint);
    }

    private static void printResults(double[] intersectingPoint) {
        final int X = 0, Y = 1;
        if (intersectingPoint == null) System.out.println("The two lines are parallel");
        else System.out.printf("The intersecting point is at (%.5f, %.5f)",
                intersectingPoint[X], intersectingPoint[Y]);

    }

    private static double[][] get2DDoubleArray(int rows, int columns) {
        double[][] m = new double[rows ][columns];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter x1, y1, x2, y2, x3, y3, x4, y4: ");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                m[r][c] = scanner.nextDouble();
            }
        }
        return m;
    }

    private static double[] getIntersectingPoint(double[][] points) {
        final int X = 0, Y = 1;
        double a = points[0][Y] - points[1][Y];
        double b = points[0][X] - points[1][X];
        double c = points[2][Y] - points[3][Y];
        double d = points[2][X] - points[3][X];
        double e = (a * points[0][X]) - (b * points[0][Y]);
        double f = (c * points[2][X]) - (d * points[2][Y]);
        double determinant = (a * d) - (b * c);
        if (determinant == 0) return null;
        else {
            double x = (e * d - b * f) / determinant;
            double y = -((a * f) - (e * c)) / determinant;
            return new double[]{x, y};
        }
    }
}
