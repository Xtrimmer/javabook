package Chapter_11;

import utility.MyPoint;
import utility.Triangle2D;

import java.util.Scanner;

/**
 * (Area of a convex polygon) A polygon is convex if it contains any line segments
 * that connects two points of the polygon. Write a program that prompts the user to
 * enter the number of points in a convex polygon, then enter the points clockwise,
 * and display the area of the polygon. Here is a sample run of the program:
 *
 *      Enter the number of the points: 7 (enter)
 *      Enter the coordinates of the points:
 *      -12 0 -8.5 10 0 11.4 5.5 7.8 6 -5.5 0 -7 -3.5 -13.5 (enter)
 *      The total area is 292.575
 *
 * NOTE: In the sample input data, I changed the last number from -3.5 to -13.5 and
 * changed the output area from 250.075 to 292.575 per the books errata.
 * ref: http://www.cs.armstrong.edu/liang/intro10e/errata.html
 */
public class PE_11_15_Area_of_a_convex_polygon {
    private static final int X = 0, Y = 1;

    public static void main(String[] args) {
        int numberOfPoints = promptIntegerValue();
        double[][] points = get2DDoubleArray(numberOfPoints, 2);
        double area = calculateArea(points);
        System.out.println("The total area is " + area);
    }

    private static double calculateArea(double[][] points) {
        Triangle2D[] triangles = getTriangles(points);
        return getSumOfTriangleAreas(triangles);
    }

    private static double getSumOfTriangleAreas(Triangle2D[] triangles) {
        double sum = 0;
        for (Triangle2D triangle : triangles) {
            sum += triangle.getArea();
        }
        return sum;
    }

    private static Triangle2D[] getTriangles(double[][] points) {
        Triangle2D[] triangles = new Triangle2D[points.length - 2];
        for (int i = 0; i < triangles.length; i++) {
            triangles[i] = new Triangle2D(
                    new MyPoint(points[0][X], points[0][Y]),
                    new MyPoint(points[i + 1][X], points[i + 1][Y]),
                    new MyPoint(points[i + 2][X], points[i + 2][Y])
            );
        }
        return triangles;
    }

    public static int promptIntegerValue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of the points: ");
        return scanner.nextInt();
    }

    private static double[][] get2DDoubleArray(int rows, int columns) {
        double[][] m = new double[rows ][columns];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the coordinates of the points:");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                m[r][c] = scanner.nextDouble();
            }
        }
        return m;
    }
}
