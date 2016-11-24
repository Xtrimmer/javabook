package chapter_13;

/**
 * (The Octagon class) Write a class named Octagon that extends
 * GeometricObject and implements the Comparable and Cloneable interfaces.
 * Assume that all eight sides of the octagon are of equal length. The area can
 * be computed using the following formula:
 *
 *      area = (2 + 4 / √2) * side * side
 *
 * Draw the UML diagram that involves Octagon, GeometricObject,
 * Comparable, and Cloneable. Write a test program that creates an Octagon
 * object with side value 5 and displays its area and perimeter. Create a new object
 * using the clone method and compare the two objects using the compareTo
 * method.
 */
public class PE_13_11_The_Octagon_class {
    public static void main(String[] args) {
        PE_13_11_Octagon octagon1 = new PE_13_11_Octagon(5);
        PE_13_11_Octagon octagon2 = (PE_13_11_Octagon) octagon1.clone();

        System.out.println("octagon1");
        System.out.println("   Perimeter = " + octagon1.getPerimeter());
        System.out.println("   Area =      " + octagon1.getArea());
        System.out.println("octagon2");
        System.out.println("   Perimeter = " + octagon2.getPerimeter());
        System.out.println("   Area =      " + octagon2.getArea());
        System.out.println();
        System.out.println("octagon1.compareTo(octagon2) = " + octagon1.compareTo(octagon2));
    }
}

abstract class PE_13_11_GeometricObject {
    private String color = "white";
    private boolean filled;
    private java.util.Date dateCreated;

    /** Construct a default geometric object */
    protected PE_13_11_GeometricObject() {
        dateCreated = new java.util.Date();
    }

    /** Construct a geometric object with color and filled value */
    protected PE_13_11_GeometricObject(String color, boolean filled) {
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

class PE_13_11_Octagon extends PE_13_11_GeometricObject implements Comparable<PE_13_11_Octagon>, Cloneable{
    double side = 1;

    public PE_13_11_Octagon() {
    }

    public PE_13_11_Octagon(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return (2 + 4 / Math.sqrt(2)) * side * side;
    }

    @Override
    public double getPerimeter() {
        return side * 8;
    }

    @Override
    public int compareTo(PE_13_11_Octagon o) {
        if (this.equals(o)) {
            return 0;
        } else if (this.side > o.side) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o){
        return o instanceof PE_13_11_Octagon && this.side == ((PE_13_11_Octagon)o).side;
    }

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}