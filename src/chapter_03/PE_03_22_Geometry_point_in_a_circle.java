package chapter_03;

import java.util.Scanner;

/**
 * (Geometry: point in a circle?) Write a program that prompts the user to enter a
 * point (x, y) and checks whether the point is within the circle centered at (0, 0)
 * with radius 10. For example, (4, 5) is inside the circle and (9, 9) is outside the
 * circle, as shown in Figure 3.7a.
 * (Hint: A point is in the circle if its distance to (0, 0) is less than or equal to 10.
 * The formula for computing the distance is
 *        _________________________
 *      √(x2 - x1)^2 + (y2 - y1)^2.
 *
 * Test your program to cover all cases.)
 */
public class PE_03_22_Geometry_point_in_a_circle {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        System.out.print("Enter a point with two coordinates: ");
        double x = SCANNER.nextDouble();
        double y = SCANNER.nextDouble();
        if (Math.sqrt(Math.pow(x - 0, 2) + Math.pow(y - 0, 2)) <= 10)
            System.out.println("Point (" + x + ", " + y + ") is in the circle.");
        else System.out.println("Point (" + x + ", " + y + ") is NOT in the circle.");
    }
}
