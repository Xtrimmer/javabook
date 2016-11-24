package chapter_13;

/**
 * (Enable Rectangle comparable) Rewrite the Rectangle class in Listing 13.3 to
 * extend GeometricObject and implement the Comparable interface. Override
 * the equals method in the Object class. Two Rectangle objects are equal
 * if their areas are the same. Draw the UML diagram that involves Rectangle,
 * GeometricObject, and Comparable.
 */
public class PE_13_10_Enable_Rectangle_comparable {
    public static void main(String[] args) {
        PE_13_10_Rectangle rectangle1 = new PE_13_10_Rectangle(1, 2);
        PE_13_10_Rectangle rectangle2 = new PE_13_10_Rectangle(2, 3);
        PE_13_10_Rectangle rectangle3 = new PE_13_10_Rectangle(3, 2);
        PE_13_10_Rectangle rectangle4 = new PE_13_10_Rectangle(3, 4);

        System.out.println("rectangle1.compareTo(rectangle2) = " + rectangle1.compareTo(rectangle2));
        System.out.println("rectangle2.compareTo(rectangle3) = " + rectangle2.compareTo(rectangle3));
        System.out.println("rectangle3.compareTo(rectangle4) = " + rectangle3.compareTo(rectangle4));
        System.out.println("rectangle4.compareTo(rectangle1) = " + rectangle4.compareTo(rectangle1));
    }
}

abstract class PE_13_10_GeometricObject {
    private String color = "white";
    private boolean filled;
    private java.util.Date dateCreated;

    /** Construct a default geometric object */
    protected PE_13_10_GeometricObject() {
        dateCreated = new java.util.Date();
    }

    /** Construct a geometric object with color and filled value */
    protected PE_13_10_GeometricObject(String color, boolean filled) {
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

class PE_13_10_Rectangle extends PE_13_10_GeometricObject implements Comparable<PE_13_10_Rectangle>{
    private double width;
    private double height;

    public PE_13_10_Rectangle() {
    }

    public PE_13_10_Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    /** Return width */
    public double getWidth() {
        return width;
    }

    /** Set a new width */
    public void setWidth(double width) {
        this.width = width;
    }

    /** Return height */
    public double getHeight() {
        return height;
    }

    /** Set a new height */
    public void setHeight(double height) {
        this.height = height;
    }

    @Override /** Return area */
    public double getArea() {
        return width * height;
    }

    @Override /** Return perimeter */
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public int compareTo(PE_13_10_Rectangle o) {
        if (this.equals(o)) {
            return 0;
        } else if (this.getArea() > o.getArea()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals (Object o){
        return o instanceof PE_13_10_Rectangle && this.getArea() == ((PE_13_10_Rectangle)o).getArea();
    }
}