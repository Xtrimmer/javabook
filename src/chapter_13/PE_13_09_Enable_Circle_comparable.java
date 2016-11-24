package chapter_13;

/**
 * (Enable Circle comparable) Rewrite the Circle class in Listing 13.2 to extend
 * GeometricObject and implement the Comparable interface. Override the
 * equals method in the Object class. Two Circle objects are equal if their radii
 * are the same. Draw the UML diagram that involves Circle, GeometricObject,
 * and Comparable.
 */
public class PE_13_09_Enable_Circle_comparable {
    public static void main(String[] args) {
        PE_13_09_Circle circle1 = new PE_13_09_Circle(4);
        PE_13_09_Circle circle2 = new PE_13_09_Circle(5);
        PE_13_09_Circle circle3 = new PE_13_09_Circle(5);
        PE_13_09_Circle circle4 = new PE_13_09_Circle(6);

        System.out.println("circle1.compareTo(circle2) = " + circle1.compareTo(circle2));
        System.out.println("circle2.compareTo(circle3) = " + circle2.compareTo(circle3));
        System.out.println("circle3.compareTo(circle4) = " + circle3.compareTo(circle4));
        System.out.println("circle4.compareTo(circle1) = " + circle4.compareTo(circle1));
    }
}

abstract class PE_13_09_GeometricObject {
    private String color = "white";
    private boolean filled;
    private java.util.Date dateCreated;

    /** Construct a default geometric object */
    protected PE_13_09_GeometricObject() {
        dateCreated = new java.util.Date();
    }

    /** Construct a geometric object with color and filled value */
    protected PE_13_09_GeometricObject(String color, boolean filled) {
        dateCreated = new java.util.Date();
        this.color = color;
        this.filled = filled;
    }

    /** Return color */
    public String getColor() {
        return color;
    }

    /** Set a new color */
    public void setColor(String color) {
        this.color = color;
    }

    /** Return filled. Since filled is boolean,
     *  the get method is named isFilled */
    public boolean isFilled() {
        return filled;
    }

    /** Set a new filled */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /** Get dateCreated */
    public java.util.Date getDateCreated() {
        return dateCreated;
    }

    /** Return a string representation of this object */
    public String toString() {
        return "created on " + dateCreated + "\ncolor: " + color +
                " and filled: " + filled;
    }

    /** Abstract method getArea */
    public abstract double getArea();

    /** Abstract method getPerimeter */
    public abstract double getPerimeter();
}

class PE_13_09_Circle extends PE_13_09_GeometricObject implements Comparable<PE_13_09_Circle>{
    private double radius;

    public PE_13_09_Circle() {
    }

    public PE_13_09_Circle(double radius) {
        this.radius = radius;
    }

    /** Return radius */
    public double getRadius() {
        return radius;
    }

    /** Set a new radius */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override /** Return area */
    public double getArea() {
        return radius * radius * Math.PI;
    }

    /** Return diameter */
    public double getDiameter() {
        return 2 * radius;
    }

    @Override /** Return perimeter */
    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }

    /* Print the circle info */
    public void printCircle() {
        System.out.println("The circle is created " + getDateCreated() +
                " and the radius is " + radius);
    }

    @Override
    public int compareTo(PE_13_09_Circle o) {
        if (this.equals(o)) {
            return 0;
        } else if (this.radius > o.radius) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o){
        return o instanceof PE_13_09_Circle && this.radius == ((PE_13_09_Circle) o).radius;
    }
}
