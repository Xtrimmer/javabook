package chapter_08;

import java.util.Scanner;

/**
 * (Geometry: area of a triangle) Write a method that returns the area of a triangle
 * using the following header:
 *
 *      public static double getTriangleArea(double[][] points)
 *
 * The points are stored in a 3-by-2 two-dimensional array points with points[0][0]
 * and points[0][1] for (x1, y1). The triangle area can be computed using the
 * formula in Programming Exercise 2.19. The method returns 0 if the three points
 * are on the same line. Write a program that prompts the user to enter three points of
 * a triangle and displays the triangle's area. Here is a sample run of the program:
 *
 *      Enter x1, y1, x2, y2, x3, y3: 2.5 2 5 -1.0 4.0 2.0 (enter)
 *      The area of the triangle is 2.25
 *
 *      Enter x1, y1, x2, y2, x3, y3: 2 2 4.5 4.5 6 6 (enter)
 *      The three points are on the same line
 */
public class PE_08_32_Geometry_area_of_a_triangle {
    public static void main(String[] args) {
        double[][] points = get2DDoubleArray(3, 2);
        double area = getTriangleArea(points);
        printResults(area);
    }

    private static void printResults(double area) {
        System.out.printf(area == 0 ? "The three points are on the same line" :
                "The area of the triangle is %.2f", area);
    }

    private static double[][] get2DDoubleArray(int rows, int columns) {
        double[][] m = new double[rows ][columns];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter x1, y1, x2, y2, x3, y3: ");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                m[r][c] = scanner.nextDouble();
            }
        }
        return m;
    }

    private static double getTriangleArea(double[][] points) {
        final int X = 0,Y = 1;
        return Math.abs((points[0][X] * (points[1][Y] - points[2][Y]) + points[1][X] *
                (points[2][Y] - points[0][Y]) + points[2][X] *
                (points[0][Y] - points[1][Y])) / 2.0);
    }
}
