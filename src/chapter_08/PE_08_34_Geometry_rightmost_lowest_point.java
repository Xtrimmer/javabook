package chapter_08;

import java.util.Scanner;

/**
 * (Geometry: rightmost lowest point) In computational geometry, often you need
 * to find the rightmost lowest point in a set of points. Write the following method
 * that returns the rightmost lowest point in a set of points.
 *
 *      public static double[] getRightmostLowestPoint(double[][] points)
 *
 * Write a test program that prompts the user to enter the coordinates of six points
 * and displays the rightmost lowest point. Here is a sample run:
 *
 *      Enter 6 points: 1.5 2.5 -3 4.5 5.6 -7 6.5 -7 8 1 10 2.5 (enter)
 *      The rightmost lowest point is (6.5, -7.0)
 */
public class PE_08_34_Geometry_rightmost_lowest_point {
    public static void main(String[] args) {
        double[][] points = get2DDoubleArray(6, 2);
        double[] rightmostLowestPoint = getRightmostLowestPoint(points);
        printRightmostLowestPoint(rightmostLowestPoint);
    }

    private static void printRightmostLowestPoint(double[] rightmostLowestPoint) {
        final int X = 0, Y = 1;
        System.out.print("The rightmost lowest point is (" +
                rightmostLowestPoint[X] + ", " +  rightmostLowestPoint[Y] + ")");
    }

    private static double[][] get2DDoubleArray(int rows,
                                               int columns) {
        double[][] m = new double[rows ][columns];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 6 points: ");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                m[r][c] = scanner.nextDouble();
            }
        }
        return m;
    }

    private static double[] getRightmostLowestPoint(double[][] points) {
        final int X = 0, Y = 1;
        double[] rightmostLowestPoint = points[0];
        for (double[] point : points) {
            if (point[Y] < rightmostLowestPoint[Y]) rightmostLowestPoint = point;
            else if (point[Y] == rightmostLowestPoint[Y] && point[X] > rightmostLowestPoint[X])
                rightmostLowestPoint = point;
        }
        return rightmostLowestPoint;
    }
}
