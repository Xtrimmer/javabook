package chapter_08;

import java.util.Arrays;
import java.util.Scanner;

/**
 * (Geometry: polygon subareas) A convex 4-vertex polygon is divided into four
 * triangles, as shown in Figure 8.9. (p. 318)
 *
 * Write a program that prompts the user to enter the coordinates of four vertices and
 * displays the areas of the four triangles in increasing order. Here is a sample run:
 *
 *      Enter x1, y1, x2, y2, x3, y3, x4, y4:
 *      -2.5 2 4 4 3 -2 -2 -3.5 (enter)
 *      The areas are 6.17 7.96 8.08 10.42
 */
public class PE_08_33_Geometry_polygon_subareas {
    public static void main(String[] args) {
        double[][] edgePoints = get2DDoubleArray(4, 2);
        double[] intersectionPoint = getIntersectingPoint(edgePoints);
        double[] triangleAreas = getTriangleAreas(edgePoints, intersectionPoint);
        Arrays.sort(triangleAreas);
        printTriangleAreas(triangleAreas);
    }

    private static void printTriangleAreas(double[] triangleAreas) {
        System.out.print("The areas are ");
        for (double area : triangleAreas) {
            System.out.printf("%.2f ", area);
        }
    }

    private static double[] getTriangleAreas(double[][] edgePoints,
                                             double[] intersectionPoint) {
        double[] triangleAreas = new double[4];
        for (int i = 0; i < 4; i++) {
            double[][] trianglePoints = new double[3][2];
            trianglePoints[0] = edgePoints[i % 4];
            trianglePoints[1] = edgePoints[(i + 1) % 4];
            trianglePoints[2] = intersectionPoint;
            triangleAreas[i] = getTriangleArea(trianglePoints);
        }
        return triangleAreas;
    }

    private static double[][] get2DDoubleArray(int rows,
                                               int columns) {
        double[][] m = new double[rows ][columns];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter x1, y1, x2, y2, x3, y3, x4, y4: ");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                m[r][c] = scanner.nextDouble();
            }
        }
        return m;
    }

    private static double[] getIntersectingPoint(double[][] points) {
        final int X = 0, Y = 1;
        double a = points[0][Y] - points[2][Y];
        double b = points[0][X] - points[2][X];
        double c = points[1][Y] - points[3][Y];
        double d = points[1][X] - points[3][X];
        double e = (a * points[0][X]) - (b * points[0][Y]);
        double f = (c * points[1][X]) - (d * points[1][Y]);
        double determinant = (a * d) - (b * c);
        if (determinant == 0) return null;
        else {
            double x = (e * d - b * f) / determinant;
            double y = -((a * f) - (e * c)) / determinant;
            return new double[]{x, y};
        }
    }

    private static double getTriangleArea(double[][] points) {
        final int X = 0,Y = 1;
        return Math.abs((points[0][X] * (points[1][Y] - points[2][Y]) + points[1][X] *
                (points[2][Y] - points[0][Y]) + points[2][X] *
                (points[0][Y] - points[1][Y])) / 2.0);
    }
}
