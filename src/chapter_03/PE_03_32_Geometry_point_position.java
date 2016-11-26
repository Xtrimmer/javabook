package chapter_03;

import java.util.Scanner;

/**
 * (Geometry: point position) Given a directed line from point p0(x0, y0) to p1(x1,
 * y1), you can use the following condition to decide whether a point p2(x2, y2) is
 * on the left of the line, on the right, or on the same line (see Figure 3.11):
 *
 *      p = (x1 - x0)*(y2 - y0) - (x2 - x0)*(y1 - y0) =
 *      {
 *          if p >0 then p2 is on the left side of the line
 *          if p =0 then p2 is on the same line
 *          if p <0 p2 is on the right side of the line
 *      }
 *
 * Write a program that prompts the user to enter the three points for p0, p1, and p2
 * and displays whether p2 is on the left of the line from p0 to p1, on the right, or on
 * the same line.
 */
public class PE_03_32_Geometry_point_position {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter three points for p0, p1, and p2: ");
        double p0x = SCANNER.nextDouble();
        double p0y = SCANNER.nextDouble();
        double p1x = SCANNER.nextDouble();
        double p1y = SCANNER.nextDouble();
        double p2x = SCANNER.nextDouble();
        double p2y = SCANNER.nextDouble();

        double pointCheck = (p1x - p0x) * (p2y - p0y) - (p2x - p0x) * (p1y - p0y);

        if (pointCheck > 0) {
            System.out.println("(" + p2x + ", " + p2y + ") is on the left side of the line from ("
                    + p0x + ", " + p0y + ") to (" + p1x + ", " + p1y + ")");
        } else if (pointCheck == 0) {
            System.out.println("(" + p2x + ", " + p2y + ") is on the  line from ("
                    + p0x + ", " + p0y + ") to (" + p1x + ", " + p1y + ")");
        } else {
            System.out.println("(" + p2x + ", " + p2y + ") is on the right side of the line from ("
                    + p0x + ", " + p0y + ") to (" + p1x + ", " + p1y + ")");
        }
    }
}
