package chapter_11;

import utility.MyPoint;

import java.awt.geom.Line2D;
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

    private static double[][] get2DDoubleArray(int rows, int columns) {
        double[][] m = new double[rows][columns];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the coordinates of the points:");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                m[r][c] = scanner.nextDouble();
            }
        }
        return m;
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

    static class Triangle2D {
        private MyPoint p1;
        private MyPoint p2;
        private MyPoint p3;

        public Triangle2D() {
            this(new MyPoint(0, 0),
                    new MyPoint(1, 1),
                    new MyPoint(2, 5));
        }

        public Triangle2D(MyPoint p1, MyPoint p2, MyPoint p3) {
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
        }

        public boolean contains(MyPoint p) {
            double area1 = new Triangle2D(p, p1, p2).getArea();
            double area2 = new Triangle2D(p, p2, p3).getArea();
            double area3 = new Triangle2D(p, p3, p1).getArea();
            return Math.abs(area1 + area2 + area3 - getArea()) < 0.00000000001;
        }

        public boolean contains(Triangle2D t) {
            return contains(t.p1) && contains(t.p2) && contains(t.p3);
        }

        public double getArea() {
            return Math.abs(
                    (p1.getX() * (p2.getY() - p3.getY())
                            + p2.getX() * (p3.getY() - p1.getY())
                            + p3.getX() * (p1.getY() - p2.getY())) / 2d);
        }

        public MyPoint getP1() {
            return p1;
        }

        public void setP1(MyPoint p1) {
            this.p1 = p1;
        }

        public MyPoint getP2() {
            return p2;
        }

        public void setP2(MyPoint p2) {
            this.p2 = p2;
        }

        public MyPoint getP3() {
            return p3;
        }

        public void setP3(MyPoint p3) {
            this.p3 = p3;
        }

        public double getPerimeter() {
            return p1.distance(p2) + p2.distance(p3) + p3.distance(p1);
        }

        public boolean overlaps(Triangle2D t) {
            Line2D[] linesT1 = {
                    new Line2D.Double(p1.getX(), p1.getY(), p2.getX(), p2.getY()),
                    new Line2D.Double(p2.getX(), p2.getY(), p3.getX(), p3.getY()),
                    new Line2D.Double(p3.getX(), p3.getY(), p1.getX(), p1.getY())
            };
            Line2D[] linesT2 = {
                    new Line2D.Double(t.p1.getX(), t.p1.getY(), t.p2.getX(), t.p2.getY()),
                    new Line2D.Double(t.p2.getX(), t.p2.getY(), t.p3.getX(), t.p3.getY()),
                    new Line2D.Double(t.p3.getX(), t.p3.getY(), t.p1.getX(), t.p1.getY())
            };
            for (int i = 0; i < linesT1.length; i++) {
                for (int j = 0; j < linesT2.length; j++) {
                    if (linesT1[i].intersectsLine(linesT2[j])) return true;
                }
            }
            return contains(t) || t.contains(this);
        }
    }
}
