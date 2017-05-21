package chapter_06;

import java.util.Scanner;

/**
 * (The MyTriangle class) Create a class named MyTriangle that contains the
 * following two methods:
 *
 *      //Return true if the sum of any two sides is greater than the third side.
 *      public static boolean isValid(double side1, double side2, double side3)
 *
 *      //Return the area of the triangle.
 *      public static double area(double side1, double side2, double side3)
 *
 * Write a test program that reads three sides for a triangle and computes the area if
 * the input is valid. Otherwise, it displays that the input is invalid. The formula for
 * computing the area of a triangle is given in Programming Exercise 2.19.
 */
public class PE_06_19_The_MyTriangle_class {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 3 sides of a triangle: ");
        double side1 = scanner.nextDouble();
        double side2 = scanner.nextDouble();
        double side3 = scanner.nextDouble();
        System.out.println(MyTriangle.isValid(side1, side2, side3) ?
                MyTriangle.area(side1, side2, side3) : "The input is invalid");
    }

    static class MyTriangle {

        //Return the area of the triangle.
        public static double area(double side1, double side2, double side3) {
            double p = (side1 + side2 + side3) / 2d;
            return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
        }

        //Return true if the sum of any two sides is greater than the third side.
        public static boolean isValid(double side1, double side2, double side3) {
            return (side1 + side2 > side3) && (side1 + side3 > side2) && (side2 + side3 > side1);
        }
    }
}
