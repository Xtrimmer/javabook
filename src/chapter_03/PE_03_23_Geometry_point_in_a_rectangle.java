package chapter_03;

import java.util.Scanner;

/**
 * (Geometry: point in a rectangle?) Write a program that prompts the user to enter
 * a point (x, y) and checks whether the point is within the rectangle centered at
 * (0, 0) with width 10 and height 5. For example, (2, 2) is inside the rectangle and
 * (6, 4) is outside the rectangle, as shown in Figure 3.7b. (Hint: A point is in the
 * rectangle if its horizontal distance to (0, 0) is less than or equal to 10 / 2 and its
 * vertical distance to (0, 0) is less than or equal to 5.0 / 2. Test your program to
 * cover all cases.)
 */
public class PE_03_23_Geometry_point_in_a_rectangle {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        System.out.print("Enter a point with two coordinates: ");
        double x = SCANNER.nextDouble();
        double y = SCANNER.nextDouble();
        if (Math.abs(x - 0) <= 5 && Math.abs(y - 0) <= 2.5)
            System.out.println("Point (" + x + ", " + y + ") is in the rectangle");
        else System.out.println("Point (" + x + ", " + y + ") is NOT in the rectangle");
    }
}
