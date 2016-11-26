package chapter_03;

import java.util.Scanner;

/**
 * (Geometry: points in triangle?) Suppose a right triangle is placed in a plane as
 * shown below. The right-angle point is placed at (0, 0), and the other two points
 * are placed at (200, 0), and (0, 100). Write a program that prompts the user to enter
 * a point with x- and y-coordinates and determines whether the point is inside the
 * triangle.
 */
public class PE_03_27_Geometry_points_in_triangle {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        System.out.print("Enter a point's x- and y-coordinates: ");
        double x = SCANNER.nextDouble();
        double y = SCANNER.nextDouble();
        if (x >= 0 && y >= 0 && y <= -0.5 * x + 100) System.out.println("The point is in the triangle");
        else System.out.println("The point is NOT in the triangle");
    }
}
