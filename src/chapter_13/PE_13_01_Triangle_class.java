package chapter_13;

import java.util.Scanner;

/**
 * (Triangle class) Design a new Triangle class that extends the abstract
 * GeometricObject class. Draw the UML diagram for the classes Triangle
 * and GeometricObject and then implement the Triangle class. Write a test
 * program that prompts the user to enter three sides of the triangle, a color, and a
 * Boolean value to indicate whether the triangle is filled. The program should create
 * a Triangle object with these sides and set the color and filled properties using
 * the input. The program should display the area, perimeter, color, and true or false
 * to indicate whether it is filled or not.
 */
public class PE_13_01_Triangle_class {
    public static void main(String[] args) {
        PE_13_01_Triangle triangle = createUserTriangle();
        System.out.println(triangle);
    }

    private static PE_13_01_Triangle createUserTriangle() {
        double[] sides = promptDoubleValues(3);
        String color = promptStringValue("Enter the color: ");
        boolean isFilled = promptBooleanValue();
        PE_13_01_Triangle triangle = new PE_13_01_Triangle(sides[0], sides[1], sides[2]);
        triangle.setColor(color);
        triangle.setFilled(isFilled);
        return triangle;
    }

    private static boolean promptBooleanValue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter [T]rue' for filled, or [F]alse for unfilled: ");
        return scanner.nextLine().toUpperCase().charAt(0) == 'T';
    }

    private static String promptStringValue(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static double[] promptDoubleValues(int size) {
        double[] values = new double[size];
        boolean valid;
        do {
            valid = true;
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter " + size + " sides: ");
            for (int i = 0; i < size; i++) {
                if (scanner.hasNextDouble()) {
                    values[i] = scanner.nextDouble();
                } else {
                    System.out.println("One or more of the input values is invalid.\nTry again.");
                    valid = false;
                    break;
                }
            }
        } while (!valid);
        return values;
    }
}

class PE_13_01_Triangle extends PE_13_01_GeometricObject {
    private double side1;
    private double side2;
    private double side3;

    public PE_13_01_Triangle() {
        this(1.0, 1.0, 1.0);
    }

    public PE_13_01_Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }

    public double getArea() {
        double s = getPerimeter() / 2.0;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public String toString() {
        return "Triangle:\n" +
                "   Area:      " + getArea() + '\n' +
                "   Perimeter: " + getPerimeter() + '\n' +
                "   Color:     " + getColor() + '\n' +
                "   Filled:    " + isFilled();
    }
}

abstract class PE_13_01_GeometricObject {
    private String color = "white";
    private boolean filled;
    private java.util.Date dateCreated;

    /**
     * Construct a default geometric object
     */
    protected PE_13_01_GeometricObject() {
        dateCreated = new java.util.Date();
    }

    /**
     * Construct a geometric object with color and filled value
     */
    protected PE_13_01_GeometricObject(String color, boolean filled) {
        dateCreated = new java.util.Date();
        this.color = color;
        this.filled = filled;
    }

    /**
     * Return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Set a new color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Return filled. Since filled is boolean,
     * the get method is named isFilled
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * Set a new filled
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * Get dateCreated
     */
    public java.util.Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return "created on " + dateCreated + "\ncolor: " + color +
                " and filled: " + filled;
    }

    /**
     * Abstract method getArea
     */
    public abstract double getArea();

    /**
     * Abstract method getPerimeter
     */
    public abstract double getPerimeter();
}