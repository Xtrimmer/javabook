package chapter_20;

import java.util.Arrays;
import java.util.Comparator;

/**
 * (Sort points in a plane) Write a program that meets the following requirements:
 *
 * - Define a class named Point with two data fields x and y to represent a
 *   point’s x- and y-coordinates. Implement the Comparable interface for comparing
 *   the points on x-coordinates. If two points have the same x-coordinates,
 *   compare their y-coordinates.
 *
 * - Define a class named CompareY that implements Comparator<Point>.
 *   Implement the compare method to compare two points on their y-coordinates.
 *   If two points have the same y-coordinates, compare their x-coordinates.
 *
 * - Randomly create 100 points and apply the Arrays.sort method to display
 *   the points in increasing order of their x-coordinates and in increasing order
 *   of their y-coordinates, respectively.
 */
public class PE_20_04_Sort_points_in_a_plane {

    public static void main(String[] args) {
        Point[] points = generateRandomPoints(100);

        Arrays.sort(points);
        System.out.println(Arrays.toString(points));
        System.out.println();
        Arrays.sort(points, new CompareY());
        System.out.println(Arrays.toString(points));
    }

    private static Point[] generateRandomPoints(int size) {
        Point[] points = new Point[size];
        for (int i = 0; i < size; i++) {
            points[i] = new Point((int) (Math.random() * size), (int) (Math.random() * size));
        }
        return points;
    }

    private static class Point implements Comparable<Point> {

        private final Integer x;
        private final Integer y;

        public Point(int x, int y) {
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
}
