package chapter_06;

import java.util.Scanner;

/**
 * (Geometry: area of a pentagon) The area of a pentagon can be computed using the
 * following formula:
 *
 *      Area = (5 * s^2) /  4 * tan(Pi / 5)
 *
 * Write a method that returns the area of a pentagon using the following header:
 *
 *      public static double area(double side)
 *
 * Write a main method that prompts the user to enter the side of a pentagon and
 * displays its area. Here is a sample run:
 *
 *      Enter the side: 5.5 (enter)
 *      The area of the pentagon is 52.04444136781625
 */
public class PE_06_35_Geometry_area_of_a_pentagon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the side: ");
        double side = scanner.nextDouble();
        System.out.println("The area of the pentagon is " + area(side));
    }

    public static double area(double side) {
        return (5 * Math.pow(side, 2)) /  (4 * Math.tan(Math.PI / 5));
    }
}
