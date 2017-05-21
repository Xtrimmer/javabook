package chapter_12;

import textbook_listings.GeometricObject;

/**
 * (IllegalTriangleException) Programming Exercise 11.1 defined the
 * Triangle class with three sides. In a triangle, the sum of any two sides is
 * greater than the other side. The Triangle class must adhere to this rule.
 * Create the IllegalTriangleException class, and modify the constructor
 * of the Triangle class to throw an IllegalTriangleException object if a
 * triangle is created with sides that violate the rule, as follows:
 *
 *      // Construct a triangle with the specified sides
 *      public Triangle(double side1, double side2, double side3)
 *              throws IllegalTriangleException {
 *              // Implement it
 *
 *      }
 */
public class PE_12_05_IllegalTriangleException {
    public static void main(String[] args) throws IllegalTriangleException {

        // This triangle will NOT throw an exception.
        Triangle triangle = new Triangle(3, 4, 5);

        // This triangle will throw an exception.
        Triangle triangle2 = new Triangle(1, 2, 4);
    }

    static class Triangle extends GeometricObject {
        private double side1;
        private double side2;
        private double side3;

        public Triangle() throws IllegalTriangleException {
            this(1.0, 1.0, 1.0);
        }

        public Triangle(double side1, double side2, double side3)
                throws IllegalTriangleException {
            if (side1 + side2 > side3
                    && side2 + side3 > side1
                    && side3 + side1 > side2) {
                this.side1 = side1;
                this.side2 = side2;
                this.side3 = side3;
            } else {
                throw new IllegalTriangleException(side1 + ", " + side2 + ", " + side3);
            }
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

    static class IllegalTriangleException extends Exception {

        public IllegalTriangleException(String message) {
            super("Invalid triangle: (" + message + ")");
        }
    }
}
