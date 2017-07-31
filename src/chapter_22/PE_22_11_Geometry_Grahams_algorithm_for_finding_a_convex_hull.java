package chapter_22;

import java.awt.geom.Line2D;
import java.util.*;

/**
 * (Geometry: Graham’s algorithm for finding a convex hull) Section 22.10.2
 * introduced Graham’s algorithm for finding a convex hull for a set of points.
 * Assume that the Java’s coordinate system is used for the points. Implement the
 * algorithm using the following method:
 *
 *      // Return the points that form a convex hull
 *      public static ArrayList<MyPoint> getConvexHull(double[][] s)
 *
 * MyPoint is a static inner class defined as follows:
 *
 *      private static class MyPoint implements Comparable<MyPoint> {
 *          double x, y;
 *
 *          MyPoint rightMostLowestPoint;
 *
 *          MyPoint(double x, double y) {
 *              this.x = x; this.y = y;
 *          }
 *
 *          public void setRightMostLowestPoint(MyPoint p) {
 *              rightMostLowestPoint = p;
 *          }
 *
 *          @Override
 *          public int compareTo(MyPoint o) {
 *              // Implement it to compare this point with point o
 *              // angularly along the x-axis with rightMostLowestPoint
 *              // as the center, as shown in Figure 22.10b. By implementing
 *              // the Comparable interface, you can use the Array.sort
 *              // method to sort the points to simplify coding.
 *          }
 *      }
 *
 * Write a test program that prompts the user to enter the set size and the points
 * and displays the points that form a convex hull. Here is a sample run:
 *
 *      How many points are in the set? 6 (Enter)
 *      Enter 6 points: 1 2.4 2.5 2 1.5 34.5 5.5 6 6 2.4 5.5 9 (Enter)
 *      The convex hull is
 *      (1.5, 34.5) (5.5, 9.0) (6.0, 2.4) (2.5, 2.0) (1.0, 2.4)
 */
public class PE_22_11_Geometry_Grahams_algorithm_for_finding_a_convex_hull {

    public static void main(String[] args) {
        int pointCount = promptUserForInteger("How many points are in the set? ");
        double[][] points = prompt2DDoubleArray(pointCount, 2, "Enter %d points: ");
        GrahamsConvexHull grahamsConvexHull = new GrahamsConvexHull();
        List<MyPoint> convexHull = grahamsConvexHull.getConvexHull(points);
        print(convexHull);
    }

    private static void print(List<MyPoint> convexHull) {
        for (MyPoint point : convexHull) {
            System.out.print(point + " ");
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

    static class GrahamsConvexHull {
        private static final int X = 0;
        private static final int Y = 1;
        private static final int LEFT_SIDE_OF_LINE = -1;

        public List<MyPoint> getConvexHull(double[][] s) {
            // Step 1
            MyPoint p0 = getRightmostLowestPoint(s);
            List<MyPoint> points = parsePoints(s, p0);

            // Step 2
            Collections.sort(points);
            DiscardCloserPointWithSameAngle(points);

            // Step 3
            LinkedList<MyPoint> convexHull = new LinkedList<>();
            convexHull.push(points.get(0));
            convexHull.push(points.get(1));
            convexHull.push(points.get(2));

            // Step 4
            int i = 3;
            while (i < points.size()) {
                MyPoint t1 = convexHull.getFirst();
                MyPoint t2 = convexHull.get(1);
                MyPoint p = points.get(i);
                Line2D line = new Line2D.Double(t2.x, t2.y, t1.x, t1.y);
                if (line.relativeCCW(p.x, p.y) == LEFT_SIDE_OF_LINE) {
                    convexHull.push(p);
                    i++;
                } else {
                    convexHull.pop();
                }
            }
            return convexHull;
        }

        private void DiscardCloserPointWithSameAngle(List<MyPoint> points) {
            List<MyPoint> pointsCopy = new ArrayList<>(points);
            for (int i = 1; i < pointsCopy.size(); i++) {
                MyPoint p1 = pointsCopy.get(i - 1);
                MyPoint p2 = pointsCopy.get(i);
                MyPoint min = p1.rightMostLowestPoint;
                double p1Angle = Math.atan2(p1.y - min.y, p1.x - min.x);
                double p2Angle = Math.atan2(p2.y - min.y, p2.x - min.x);
                if (Double.compare(p1Angle, p2Angle) == 0) points.remove(p1);
            }
        }

        private MyPoint getRightmostLowestPoint(double[][] points) {
            double[] rightmostLowestPoint = points[0];
            for (double[] point : points) {
                if (point[Y] == rightmostLowestPoint[Y]) {
                    if (point[X] > rightmostLowestPoint[X]) {
                        rightmostLowestPoint = point;
                    }
                } else if (point[Y] < rightmostLowestPoint[Y]) {
                    rightmostLowestPoint = point;
                }
            }
            MyPoint newPoint = new MyPoint(rightmostLowestPoint[X], rightmostLowestPoint[Y]);
            newPoint.setRightMostLowestPoint(newPoint);
            return newPoint;
        }

        private ArrayList<MyPoint> parsePoints(double[][] pointArray, MyPoint rightmostLowestPoint) {
            ArrayList<MyPoint> pointList = new ArrayList<>();
            for (double[] point : pointArray) {
                MyPoint newPoint = new MyPoint(point[X], point[Y]);
                newPoint.setRightMostLowestPoint(rightmostLowestPoint);
                pointList.add(newPoint);
            }
            return pointList;
        }
    }

    private static class MyPoint implements Comparable<MyPoint> {
        final double x;
        final double y;
        MyPoint rightMostLowestPoint;

        MyPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(MyPoint o) {
            double thisAngle = Math.atan2(this.y - rightMostLowestPoint.y, this.x - rightMostLowestPoint.x);
            double thisDistance = Math.sqrt(Math.pow(this.y - rightMostLowestPoint.y, 2) + Math.pow(this.x - rightMostLowestPoint.x, 2));
            double thatAngle = Math.atan2(o.y - rightMostLowestPoint.y, o.x - rightMostLowestPoint.x);
            double thatDistance = Math.sqrt(Math.pow(o.y - rightMostLowestPoint.y, 2) + Math.pow(o.x - rightMostLowestPoint.x, 2));

            if (Double.compare(thisAngle, thatAngle) == 0) {
                return Double.compare(thisDistance, thatDistance);
            } else
                return Double.compare(thisAngle, thatAngle);
        }

        public void setRightMostLowestPoint(MyPoint p) {
            rightMostLowestPoint = p;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
