package chapter_22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * (Closest pair of points) Section 22.8 introduced an algorithm for finding the
 * closest pair of points using a divide-and-conquer approach. Implement the
 * algorithm to meet the following requirements:
 *
 * + Define the classes Point and CompareY in the same way as in Programming
 *   Exercise 20.4.
 * + Define a class named Pair with the data fields p1 and p2 to represent two
 *   points, and a method named getDistance() that returns the distance
 *   between the two points.
 * + Implement the following methods:
 *
 *      // Return the distance of the closest pair of points
 *      public static Pair getClosestPair(double[][] points)
 *
 *      // Return the distance of the closest pair of points
 *      public static Pair getClosestPair(Point[] points)
 *
 *      // Return the distance of the closest pair of points
 *      // in pointsOrderedOnX[low..high]. This is a recursive
 *      // method. pointsOrderedOnX and pointsOrderedOnY are
 *      // not changed in the subsequent recursive calls
 *      public static Pair distance(Point[] pointsOrderedOnX,
 *      int low, int high, Point[] pointsOrderedOnY)
 *
 *      // Compute the distance between two points p1 and p2
 *      public static double distance(Point p1, Point p2)
 *
 *      Compute the distance between points (x1, y1) and (x2, y2)
 *      public static double distance(double x1, double y1,
 *      double x2, double y2)
 */
public class PE_22_07_Closest_pair_of_points {
    public static void main(String[] args) {
        double[][] pointsArray = generateRandomPoints(100, -100, 100);
        Pair closestPairOfPoints = getClosestPair(pointsArray);
        System.out.printf("The closest pair of points are:%n%s and %s.%nTheir distance is %f",
                closestPairOfPoints.p1, closestPairOfPoints.p2, closestPairOfPoints.getDistance());
    }

    /** Return the distance of the closest pair of points
     * in pointsOrderedOnX[low..high]. This is a recursive
     * method. pointsOrderedOnX and pointsOrderedOnY are
     * not changed in the subsequent recursive calls.
     */
    public static Pair distance(Point[] pointsOrderedOnX,
                                int low, int high, Point[] pointsOrderedOnY) {
        if (low >= high) {
            return null;
        } else if (low + 1 == high) {
            return new Pair(pointsOrderedOnX[low], pointsOrderedOnX[high]);
        }

        int midpoint = (low + high) / 2;
        Pair pair1 = distance(pointsOrderedOnX, low, midpoint, pointsOrderedOnY);
        Pair pair2 = distance(pointsOrderedOnX, midpoint + 1, high, pointsOrderedOnY);

        double distance;
        Pair closestPair = null;

        if (pair1 == null && pair2 == null) {
            distance = Double.MAX_VALUE;
        } else if (pair1 == null) {
            distance = pair2.getDistance();
            closestPair = pair2;
        } else if (pair2 == null) {
            distance = pair1.getDistance();
            closestPair = pair1;
        } else {
            distance = Math.min(pair1.getDistance(), pair2.getDistance());
            closestPair = ((pair1.getDistance() <= pair2.getDistance()) ? pair1 : pair2);
        }

        ArrayList<Point> stripL = new ArrayList<>();
        ArrayList<Point> stripR = new ArrayList<>();
        for (Point point : pointsOrderedOnY) {
            if ((point.x <= pointsOrderedOnX[midpoint].x) &&
                    (point.x >= pointsOrderedOnX[midpoint].x - distance)) {
                stripL.add(point);
            } else {
                stripR.add(point);
            }
        }

        double d3 = distance;
        int r = 0;
        for (Point point : stripL) {
            while (r < stripR.size() && point.y > stripR.get(r).y + distance) {
                r++;
            }

            int r1 = r;
            while (r1 < stripR.size() && stripR.get(r1).y <= point.y + distance) {
                if (d3 > distance(point, stripR.get(r1))) {
                    d3 = distance(point, stripR.get(r1));
                    closestPair.p1 = point;
                    closestPair.p2 = stripR.get(r1);
                }
                r1++;
            }
        }

        return closestPair;
    }

    /** Compute the distance between two points p1 and p2 */
    public static double distance(Point p1, Point p2) {
        return distance(p1.x, p1.y, p2.x, p2.y);
    }

    /** Compute the distance between points (x1, y1) and (x2, y2) */
    public static double distance(double x1, double y1,
                                  double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private static double[][] generateRandomPoints(int count, double lowerBound, double upperBound) {
        double[][] array = new double[100][2];
        double range = upperBound - lowerBound;
        for (int i = 0; i < array.length; i++) {
            array[i][0] = Math.random() * range + lowerBound;
            array[i][1] = Math.random() * range + lowerBound;
        }
        return array;
    }

    /** Return the distance of the closest pair of points */
    public static Pair getClosestPair(Point[] points) {
        Point[] pointsOrderedOnX = new Point[points.length];
        Point[] pointsOrderedOnY = new Point[points.length];
        System.arraycopy(points, 0, pointsOrderedOnX, 0, points.length);
        System.arraycopy(points, 0, pointsOrderedOnY, 0, points.length);
        Arrays.sort(pointsOrderedOnX);
        Arrays.sort(pointsOrderedOnY, new CompareY());
        return distance(pointsOrderedOnX, 0, points.length - 1, pointsOrderedOnY);
    }

    /** Return the distance of the closest pair of points */
    public static Pair getClosestPair(double[][] points) {
        Point[] pointArray = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            pointArray[i] = new Point(points[i][0], points[i][1]);
        }
        return getClosestPair(pointArray);
    }

    private static class Point implements Comparable<Point> {

        private final Double x;
        private final Double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point that) {
            int firstComparison = this.x.compareTo(that.x);
            return firstComparison == 0 ? this.y.compareTo(that.y) : firstComparison;
        }

        @Override
        public String toString() {
            return "[X:" + x + ", Y:" + y + "]";
        }
    }

    private static class CompareY implements Comparator<Point> {

        @Override
        public int compare(Point point1, Point point2) {
            int firstComparison = point1.y.compareTo(point2.y);
            return firstComparison == 0 ? point1.x.compareTo(point2.x) : firstComparison;
        }
    }

    private static class Pair {
        private Point p1;
        private Point p2;

        public Pair(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public double getDistance() {
            return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
        }
    }
}
