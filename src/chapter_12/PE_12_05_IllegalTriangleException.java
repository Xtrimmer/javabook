package chapter_12;

import utility.IllegalTriangleException;
import utility.Triangle_12_5;

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
 *      }
 */
public class PE_12_05_IllegalTriangleException {
    public static void main(String[] args) throws IllegalTriangleException {
        // This triangle will throw an exception.
        Triangle_12_5 triangle = new Triangle_12_5(1, 2, 4);
    }
}
