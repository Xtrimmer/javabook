package chapter_02;

import java.util.Scanner;

/**
 * (Geometry: area of a triangle) Write a program that prompts the user to enter
 * three points (x1, y1), (x2, y2), (x3, y3) of a triangle and displays its area.
 * The formula for computing the area of a triangle is
 *
 *      s = (side1 + side2 + side3)/2;
 *               __________________________________
 *      area = √s(s - side1)(s - side2)(s - side3)
 *
 * Here is a sample run:
 *
 *      Enter three points for a triangle: 1.5 -3.4 4.6 5 9.5 -3.4 (enter)
 *      The area of the triangle is 33.6
 */
public class PE_02_19_Geometry_area_of_a_triangle {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter three points for a triangle: ");
        double x1 = SCANNER.nextDouble();
        double y1 = SCANNER.nextDouble();
        double x2 = SCANNER.nextDouble();
        double y2 = SCANNER.nextDouble();
        double x3 = SCANNER.nextDouble();
        double y3 = SCANNER.nextDouble();

        double area = Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2d);

        System.out.print("The area of the triangle is " + area);
    }
}
