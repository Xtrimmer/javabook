package chapter_03;

import java.util.Scanner;

/**
 * (Geometry: two circles) Write a program that prompts the user to enter the center
 * coordinates and radii of two circles and determines whether the second circle is
 * inside the first or overlaps with the first, as shown in Figure 3.10. (Hint: circle2 is
 * inside circle1 if the distance between the two centers <= |r1 - r2| and circle2
 * overlaps circle1 if the distance between the two centers <= r1 + r2. Test your
 * program to cover all cases.)
 */
public class PE_03_29_Geometry_two_circles {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter circle1's center x-, y-coordinates, and radius: ");
        double x1 = SCANNER.nextDouble();
        double y1 = SCANNER.nextDouble();
        double r1 = SCANNER.nextDouble();

        System.out.print("Enter circle2's center x-, y-coordinates, and radius: ");
        double x2 = SCANNER.nextDouble();
        double y2 = SCANNER.nextDouble();
        double r2 = SCANNER.nextDouble();

        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        if (distance <= Math.abs(r1 - r2)) {
            System.out.println("circle2 is inside circle1");
        } else if (distance <= r1 + r2) {
            System.out.println("circle2 overlaps circle1");
        } else {
            System.out.println("circle2 does not overlap circle1");
        }
    }
}
