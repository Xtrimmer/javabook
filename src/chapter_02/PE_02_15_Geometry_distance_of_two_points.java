package chapter_02;

import java.util.Scanner;

/**
 * (Geometry: distance of two points) Write a program that prompts the user to enter
 * two points (x1, y1) and (x2, y2) and displays their distance between them.
 * The formula for computing the distance is
 *        __________________________
 *      √(x2 - x1)^2 + (y2 - y1)^2
 *
 * Note that you can use Math.pow(a, 0.5) to compute 2a.
 */
public class PE_02_15_Geometry_distance_of_two_points {
    public static void main(String args[]) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter x1 and y1: ");
        double x1 = SCANNER.nextDouble();
        double y1 = SCANNER.nextDouble();
        System.out.print("Enter x2 and y2: ");
        double x2 = SCANNER.nextDouble();
        double y2 = SCANNER.nextDouble();

        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        System.out.println("The distance between the two points is " + distance);
    }
}
