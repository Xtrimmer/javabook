package chapter_03;

import java.util.Scanner;

/**
 * (Geometry: two rectangles) Write a program that prompts the user to enter the
 * center x-, y-coordinates, width, and height of two rectangles and determines
 * whether the second rectangle is inside the first or overlaps with the first, as shown
 * in Figure 3.9. Test your program to cover all cases.
 */
public class PE_03_28_Geometry_two_rectangles {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter r1's center x-, y-coordinates, width, and height: ");
        double cx1 = SCANNER.nextDouble();
        double cy1 = SCANNER.nextDouble();
        double w1 = SCANNER.nextDouble();
        double h1 = SCANNER.nextDouble();

        System.out.print("Enter r2's center x-, y-coordinates, width, and height: ");
        double cx2 = SCANNER.nextDouble();
        double cy2 = SCANNER.nextDouble();
        double w2 = SCANNER.nextDouble();
        double h2 = SCANNER.nextDouble();

        double right1 = cx1 + w1 / 2.0;
        double left1 = cx1 - w1 / 2.0;
        double top1 = cy1 + h1 / 2.0;
        double bottom1 = cy1 - h1 / 2.0;
        double right2 = cx2 + w2 / 2.0;
        double left2 = cx2 - w2 / 2.0;
        double top2 = cy2 + h2 / 2.0;
        double bottom2 = cy2 - h2 / 2.0;

        if (right2 <= right1 && left2 >= left1 && top2 <= top1 && bottom2 >= bottom1) {
            System.out.println("r2 is inside r1.");
        } else if (right2 <= left1 || left2 > right1 || bottom2 >= top1 || top2 <= bottom1) {
            System.out.println("r2 does not overlap r1");
        } else {
            System.out.println("r2 overlaps r1");
        }
    }
}

