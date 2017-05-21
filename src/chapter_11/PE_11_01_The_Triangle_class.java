package chapter_11;

import textbook_listings.GeometricObject;

import java.util.Scanner;

/**
 * (The Triangle class) Design a class named Triangle that extends
 * GeometricObject. The class contains:
 *
 * - Three double data fields named side1, side2, and side3 with default
 *   values 1.0 to denote three sides of the triangle.
 * - A no-arg constructor that creates a default triangle.
 * - A constructor that creates a triangle with the specified side1, side2, and
 *   side3.
 * - The accessor methods for all three data fields.
 * - A method named getArea() that returns the area of this triangle.
 * - A method named getPerimeter() that returns the perimeter of this triangle.
 * - A method named toString() that returns a string description for the triangle.
 *
 * For the formula to compute the area of a triangle, see Programming Exercise 2.19.
 * The toString() method is implemented as follows:
 *
 *      return "Triangle: side1 = " + side1 + " side2 = " + side2 +
 *      " side3 = " + side3;
 *
 * Draw the UML diagrams for the classes Triangle and GeometricObject and
 * implement the classes. Write a test program that prompts the user to enter three
 * sides of the triangle, a color, and a Boolean value to indicate whether the triangle
 * is filled. The program should create a Triangle object with these sides and set
 * the color and filled properties using the input. The program should display
 * the area, perimeter, color, and true or false to indicate whether it is filled or not.
 */
public class PE_11_01_The_Triangle_class {
    public static void main(String[] args) {
        double[] sides = promptDoubleValues(3);
        String color = promptStringValue();
        boolean filled = promptBooleanValue();
        Triangle triangle = new Triangle(sides[0], sides[1], sides[2]);
        triangle.setColor(color);
        triangle.setFilled(filled);
        System.out.println("Area:      " + triangle.getArea());
        System.out.println("Perimeter: " + triangle.getPerimeter());
        System.out.println("Color:     " + triangle.getColor());
        System.out.println("Filled:    " + triangle.isFilled());
    }

    private static boolean promptBooleanValue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter [T]rue' for filled, or [F]alse for unfilled: ");
        return scanner.nextLine().toUpperCase().charAt(0) == 'T';
    }

    private static double[] promptDoubleValues(int size) {
        double[] values = new double[size];
        final Scanner SCANNER = new Scanner(System.in);
        System.out.print("Enter " + size + " sides: ");
        for (int i = 0; i < size; i++) {
            values[i] = SCANNER.nextDouble();
        }
        return values;
    }

    private static String promptStringValue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a color: ");
        return scanner.nextLine();
    }

    static class Triangle extends GeometricObject {
        private final double side1;
        private final double side2;
        private final double side3;

        public Triangle() {
            this(1.0, 1.0, 1.0);
        }

        public Triangle(double side1, double side2, double side3) {
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

        @Override
        public String toString() {
            return "Triangle: side1 = " + side1 + " side2 = " + side2 +
                    " side3 = " + side3;
        }

        public double getArea() {
            double s = getPerimeter() / 2.0;
            return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
        }

        public double getPerimeter() {
            return side1 + side2 + side3;
        }
    }
}
