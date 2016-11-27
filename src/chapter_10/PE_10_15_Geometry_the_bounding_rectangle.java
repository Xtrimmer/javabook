package chapter_10;

import java.util.Scanner;

/**
 * (Geometry: the bounding rectangle) A bounding rectangle is the minimum rectangle
 * that encloses a set of points in a two-dimensional plane, as shown in
 * Figure 10.24d (p. 405). Write a method that returns a bounding rectangle for a set of
 * points in a two-dimensional plane, as follows:
 *
 *      public static MyRectangle2D getRectangle(double[][] points)
 *
 * The Rectangle2D class is defined in Programming Exercise 10.13. Write a
 * test program that prompts the user to enter five points and displays the bounding
 * rectangle’s center, width, and height. Here is a sample run:
 *
 *      Enter five points: 1.0 2.5 3 4 5 6 7 8 9 10 (enter)
 *      The bounding rectangle's center (5.0, 6.25), width 8.0, height 7.5
 */
public class PE_10_15_Geometry_the_bounding_rectangle {
    public static void main(String[] args) {
        double[][] points = promptPoints(5);
        MyRectangle2D boundingRectangle = getRectangle(points);
        printRectangle(boundingRectangle);
    }

    private static void printRectangle(MyRectangle2D boundingRectangle) {
        System.out.printf("The bounding rectangle's center (%s, %s), width %s, height %s",
                boundingRectangle.getX(), boundingRectangle.getY(),
                boundingRectangle.getWidth(), boundingRectangle.getHeight());
    }

    private static double[][] promptPoints(int size) {
        double[][] points = new double[size][2];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter " + size + " points: ");
        for (int i = 0; i < size; i++) {
            points[i][0] = scanner.nextDouble();
            points[i][1] = scanner.nextDouble();
        }
        return points;
    }

    private static MyRectangle2D getRectangle(double[][] points) {
        final int X = 0, Y = 1;
        double xMin = points[0][X];
        double yMin = points[0][Y];
        double xMax = points[0][X];
        double yMax = points[0][Y];
        for (int i = 1; i < points.length; i++) {
            if (points[i][X] > xMax) xMax = points[i][X];
            if (points[i][X] < xMin) xMin = points[i][X];
            if (points[i][Y] > yMax) yMax = points[i][Y];
            if (points[i][Y] < yMin) yMin = points[i][Y];
        }
        double height = yMax - yMin;
        double width = xMax - xMin;
        double centerX = xMin + width / 2.0;
        double centerY = yMin + height / 2.0;
        return new MyRectangle2D(centerX, centerY, width, height);
    }

    private static class MyRectangle2D {
        private double x;
        private double y;
        private double width;
        private double height;

        MyRectangle2D() {
            this(0, 0, 1, 1);
        }

        MyRectangle2D(double x, double y, double width, double height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        double getX() {
            return x;
        }

        void setX(double x) {
            this.x = x;
        }

        double getY() {
            return y;
        }

        void setY(double y) {
            this.y = y;
        }

        double getTop() {
            return this.y + height / 2.0;
        }

        double getLeft() {
            return this.x - width / 2.0;
        }

        double getBottom() {
            return this.y - height / 2.0;
        }

        double getWidth() {
            return width;
        }

        void setWidth(double width) {
            this.width = width;
        }

        double getHeight() {
            return height;
        }

        void setHeight(double height) {
            this.height = height;
        }

        double getArea() {
            return width * height;
        }

        double getPerimeter() {
            return 2 * (width + height);
        }

        boolean contains(double x, double y) {
            double right = this.x + width / 2.0;
            double left = getLeft();
            double top = getTop();
            double bottom = getBottom();
            return x <= right && x >= left && y <= top && y >= bottom;
        }

        boolean contains(MyRectangle2D r) {
            double right = r.x + r.width / 2.0;
            double left = r.x - r.width / 2.0;
            double top = r.y + r.height / 2.0;
            double bottom = r.y - r.height / 2.0;
            return contains(left, top) && contains(left, bottom)
                    && contains(right, top) && contains(right, bottom);
        }

        boolean overlaps(MyRectangle2D r) {
            double right = r.x + r.width / 2.0;
            double left = r.x - r.width / 2.0;
            double top = r.y + r.height / 2.0;
            double bottom = r.y - r.height / 2.0;
            return contains(r) || r.contains(this)
                    || contains(left, top) || contains(left, bottom)
                    || contains(right, top) || contains(right, bottom);
        }
    }
}
