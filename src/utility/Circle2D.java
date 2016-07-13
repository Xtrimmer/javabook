package utility;

/**
 * Added for Chapter 10 Exercise 11.
 */
public class Circle2D {
    private double x;
    private double y;
    private double radius;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public Circle2D() {
        this(0, 0, 1);
    }

    public Circle2D(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public boolean contains(double x, double y) {
        return distance(x, y) < radius;
    }

    public boolean contains(Circle2D circle) {
        return distance(circle.x, circle.y) <= Math.abs(radius - circle.radius);
    }

    public boolean overlaps(Circle2D circle) {
        return distance(circle.x, circle.y) <= radius + circle.radius
                && !contains(circle);
    }

    public double distance(double x, double y) {
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
    }
}
