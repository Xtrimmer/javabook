package chapter_10;

/**
 * (Geometry: the Circle2D class) Define the Circle2D class that contains:
 *
 * - Two double data fields named x and y that specify the center of the circle
 *   with getter methods.
 * - A data field radius with a getter method.
 * - A no-arg constructor that creates a default circle with (0, 0) for (x, y) and 1
 *   for radius.
 * - A constructor that creates a circle with the specified x, y, and radius.
 * - A method getArea() that returns the area of the circle.
 * - A method getPerimeter() that returns the perimeter of the circle.
 * - A method contains(double x, double y) that returns true if the
 *   specified point (x, y) is inside this circle (see Figure 10.21a).
 * - A method contains(Circle2D circle) that returns true if the specified
 *   circle is inside this circle (see Figure 10.21b).
 * - A method overlaps(Circle2D circle) that returns true if the specified
 *   circle overlaps with this circle (see Figure 10.21c).
 *
 *      FIGURE 10.21 (p. 403)
 *      (a) A point is inside the circle.
 *      (b) A circle is inside another circle.
 *      (c) A circle overlaps another circle.
 *
 * Draw the UML diagram for the class and then implement the class. Write a test
 * program that creates a Circle2D object c1 (new Circle2D(2, 2, 5.5)),
 * displays its area and perimeter, and displays the result of c1.contains(3,
 * 3), c1.contains(new Circle2D(4, 5, 10.5)), and c1.overlaps(new
 * Circle2D(3, 5, 2.3)).
 */
public class PE_10_11_Geometry_the_Circle2D_class {
    public static void main(String[] args) {
        Circle2D c1 = new Circle2D(2, 2, 5.5);
        System.out.println("Circle2D c1:");
        System.out.println("   Area:      " + c1.getArea());
        System.out.println("   Perimeter: " + c1.getPerimeter());
        System.out.println("   c1.contains(3, 3):\n      "
                + c1.contains(3, 3)); //T
        System.out.println("   c1.contains(new Circle2D(4, 5, 10.5)):\n      "
                + c1.contains(new Circle2D(4, 5, 10.5))); //F
        System.out.println("   c1.overlaps(new Circle2D(3, 5, 2.3))):\n      "
                + c1.overlaps(new Circle2D(3, 5, 2.3))); //F
    }

    private static class Circle2D {
        private final double x;
        private final double y;
        private final double radius;

        Circle2D() {
            this(0, 0, 1);
        }

        Circle2D(double x, double y, double radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        double getX() {
            return x;
        }

        double getY() {
            return y;
        }

        double getRadius() {
            return radius;
        }

        double getArea() {
            return Math.PI * radius * radius;
        }

        double getPerimeter() {
            return 2 * Math.PI * radius;
        }

        boolean contains(double x, double y) {
            return distance(x, y) < radius;
        }

        boolean contains(Circle2D circle) {
            return distance(circle.x, circle.y) <= Math.abs(radius - circle.radius);
        }

        boolean overlaps(Circle2D circle) {
            return distance(circle.x, circle.y) <= radius + circle.radius
                    && !contains(circle);
        }

        double distance(double x, double y) {
            return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
        }
    }
}
