package chapter_10;

/**
 * (The MyPoint class) Design a class named MyPoint to represent a point with
 * x- and y-coordinates. The class contains:
 *
 * - The data fields x and y that represent the coordinates with getter
 *   methods.
 * - A no-arg constructor that creates a point (0, 0).
 * - A constructor that constructs a point with specified coordinates.
 * - A method named distance that returns the distance from this point to a
 *   specified point of the MyPoint type.
 * - A method named distance that returns the distance from this point to
 *   another point with specified x- and y-coordinates.
 *
 * Draw the UML diagram for the class and then implement the class. Write a
 * test program that creates the two points (0, 0) and (10, 30.5) and displays the
 * distance between them.
 */
public class PE_10_04_The_MyPoint_class {
    public static void main(String[] args) {
        MyPoint myPoint1 = new MyPoint();
        MyPoint myPoint2 = new MyPoint(10, 30.5);

        System.out.println("The distance between the two points is:");
        System.out.println(myPoint1.distance(myPoint2));
    }

    private static class MyPoint {
        private final double x;
        private final double y;

        MyPoint() {
            this(0, 0);
        }

        MyPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }

        double getX() {
            return x;
        }

        double getY() {
            return y;
        }

        double distance(double x, double y) {
            return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
        }

        double distance(MyPoint point) {
            return this.distance(point.x, point.y);
        }
    }
}
