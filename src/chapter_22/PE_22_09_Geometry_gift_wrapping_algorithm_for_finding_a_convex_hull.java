package chapter_22;

import javafx.geometry.Point2D;

import java.awt.geom.Line2D;
import java.util.*;

/**
 * (Geometry: gift-wrapping algorithm for finding a convex hull) Section 22.10.1
 * introduced the gift-wrapping algorithm for finding a convex hull for a set of
 * points. Assume that the Java’s coordinate system is used for the points. Implement
 * the algorithm using the following method:
 *
 *      // Return the points that form a convex hull
 *      public static ArrayList<Point2D> getConvexHull(double[][] s)
 *
 * Point2D is defined in Section 9.6.
 * Write a test program that prompts the user to enter the set size and the points
 * and displays the points that form a convex hull. Here is a sample run:
 *
 *      How many points are in the set? 6 (Enter)
 *      Enter 6 points: 1 2.4 2.5 2 1.5 34.5 5.5 6 6 2.4 5.5 9 (Enter)
 *      The convex hull is
 *      (1.5, 34.5) (5.5, 9.0) (6.0, 2.4) (2.5, 2.0) (1.0, 2.4)
 */
public class PE_22_09_Geometry_gift_wrapping_algorithm_for_finding_a_convex_hull {

    public static void main(String[] args) {
        int pointCount = promptUserForInteger("How many points are in the set? ");
        double[][] points = prompt2DDoubleArray(pointCount, 2, "Enter %d points: ");
        GiftWrappingConvexHull giftWrappingConvexHull = new GiftWrappingConvexHull();
        ArrayList<Point2D> convexHull = giftWrappingConvexHull.getConvexHull(points);
        print(convexHull);
    }

    private static void print(ArrayList<Point2D> convexHull) {
        final StringJoiner points = new StringJoiner(" ");
        for (Point2D point2D : convexHull) {
            String point = "(" + point2D.getX() + ", " + point2D.getY() + ") ";
            System.out.print(point);
        }
    }

    private static double[][] prompt2DDoubleArray(int rows, int columns, String message) {
        double[][] m = new double[rows][columns];
        Scanner scanner = new Scanner(System.in);
        System.out.printf(message, rows);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                m[r][c] = scanner.nextDouble();
            }
        }
        return m;
    }

    private static int promptUserForInteger(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            System.out.println("Error parsing Integer Value!");
            System.exit(1);
        }
        return 0;
    }

    static class GiftWrappingConvexHull {
        private static final int X = 0;
        private static final int Y = 1;

        public ArrayList<Point2D> getConvexHull(double[][] s) {

            ArrayList<Point2D> convexHull = new ArrayList<>();
            ArrayList<Point2D> points = parsePoints(s);

            Point2D rightmostLowestPoint = getRightmostLowestPoint(points);
            convexHull.add(rightmostLowestPoint);

            Point2D hullEndPoint = rightmostLowestPoint;
            Point2D nextHullPoint = getNextHullPoint(hullEndPoint, points);
            while (!nextHullPoint.equals(rightmostLowestPoint)) {
                convexHull.add(nextHullPoint);
                hullEndPoint = nextHullPoint;
                nextHullPoint = getNextHullPoint(hullEndPoint, points);
            }

            return convexHull;
        }

        private Point2D getNextHullPoint(Point2D hullEndPoint, ArrayList<Point2D> points) {
            final int RIGHT_SIDE_OF_LINE = 1;
            Line2D line = new Line2D.Double(hullEndPoint.getX(), hullEndPoint.getY(), points.get(0).getX(), points.get(0).getY());
            for (Point2D point : points) {
                int sideOfLine = line.relativeCCW(point.getX(), point.getY());
                if (sideOfLine == RIGHT_SIDE_OF_LINE) {
                    line.setLine(line.getX1(), line.getY1(), point.getX(), point.getY());
                }
            }
            points.remove(new Point2D(line.getX2(), line.getY2()));
            return new Point2D(line.getX2(), line.getY2());
        }

        private Point2D getRightmostLowestPoint(ArrayList<Point2D> points) {
            return Collections.min(
                    points,
                    Comparator.comparing(Point2D::getY).thenComparing(Point2D::getX, Comparator.reverseOrder())
            );
        }

        private ArrayList<Point2D> parsePoints(double[][] pointArray) {
            ArrayList<Point2D> pointList = new ArrayList<>();
            for (double[] point : pointArray) {
                pointList.add(new Point2D(point[X], point[Y]));
            }
            return pointList;
        }
    }
}
