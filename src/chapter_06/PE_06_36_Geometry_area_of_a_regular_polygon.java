package chapter_06;

import java.util.Scanner;

/**
 * (Geometry: area of a regular polygon) A regular polygon is an n-sided polygon
 * in which all sides are of the same length and all angles have the same degree (i.e.,
 * the polygon is both equilateral and equiangular). The formula for computing the
 * area of a regular polygon is
 *
 *      Area = (n * s^2) / (4 * tan(PI / n))
 *
 * Write a method that returns the area of a regular polygon using the following header:
 *
 *      public static double area(int n, double side)
 *
 * Write a main method that prompts the user to enter the number of sides and the
 * side of a regular polygon and displays its area. Here is a sample run:
 *
 *      Enter the number of sides: 5 (enter)
 *      Enter the side: 6.5 (enter)
 *      The area of the polygon is 72.69017017488385
 */
public class PE_06_36_Geometry_area_of_a_regular_polygon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of sides: ");
        int n = scanner.nextInt();
        System.out.print("Enter the side: ");
        double side = scanner.nextDouble();
        System.out.println("The area of the polygon is " + area(n, side));
    }

    public static double area(int n, double side) {
        return (n * Math.pow(side, 2)) /  (4 * Math.tan(Math.PI / n));
    }
}
