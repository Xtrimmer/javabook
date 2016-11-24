package chapter_06;

import java.util.Scanner;

/**
 * (Geometry: point position) Given a directed line from point p0(x0, y0) to p1(x1,
 * y1), you can use the following condition to decide whether a point p2(x2, y2) is
 * on the left of the line, on the right, or on the same line (see Figure 3.11):
 *
 *      p = (x1 - x0)*(y2 - y0) - (x2 - x0)*(y1 - y0)
 *      if p >0 then p2 is on the left side of the line
 *      if p =0 then p2 is on the same line
 *      if p <0 p2 is on the right side of the line
 *
 * Write the methods with the following headers:
 *
 *      // Return true if point (x2, y2) is on the left side of the
 *      // directed line from (x0, y0) to (x1, y1)
 *      public static boolean leftOfTheLine(double x0, double y0,
 *      double x1, double y1, double x2, double y2)
 *
 *      // Return true if point (x2, y2) is on the same
 *      // line from (x0, y0) to (x1, y1)
 *      public static boolean onTheSameLine(double x0, double y0,
 *      double x1, double y1, double x2, double y2)
 *
 *      // Return true if point (x2, y2) is on the
 *      // line segment from (x0, y0) to (x1, y1)
 *      public static boolean onTheLineSegment(double x0, double y0,
 *      double x1, double y1, double x2, double y2)
 *
 * Write a program that prompts the user to enter the three points for p0, p1, and p2
 * and displays whether p2 is on the left of the line from p0 to p1, right, the same
 * line, or on the line segment. Here are some sample runs:
 *
 *      Enter three points for p0, p1, and p2: 1 1 2 2 1.5 1.5 (enter)
 *      (1.5, 1.5) is on the line segment from (1.0, 1.0) to (2.0, 2.0)
 *
 *      Enter three points for p0, p1, and p2: 1 1 2 2 3 3 (enter)
 *      (3.0, 3.0) is on the same line from (1.0, 1.0) to (2.0, 2.0)
 *
 *      Enter three points for p0, p1, and p2: 1 1 2 2 1 1.5 (enter)
 *      (1.0, 1.5) is on the left side of the line from (1.0, 1.0) to (2.0, 2.0)
 *
 *      Enter three points for p0, p1, and p2: 1 1 2 2 1 -1 (enter)
 *      (1.0, -1.0) is on the right side of the line from (1.0, 1.0) to (2.0, 2.0)
 */
public class PE_06_39_Geometry_point_position {

    final static double EPSILON = 0.000001;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter three points for p0, p1, and p2: ");
        double x0 = scanner.nextDouble();
        double y0 = scanner.nextDouble();
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        String result;
        if (leftOfTheLine(x0, y0, x1, y1, x2, y2)) result = " left side of the line ";
        else if (onTheLineSegment(x0, y0, x1, y1, x2, y2)) result = " line segment ";
        else if (onTheSameLine(x0, y0, x1, y1, x2, y2)) result = " same line ";
        else result = " right side of the line ";
        System.out.println("(" + x2 + ", " + y2 + ") is on the"
                + result
                + "from (" + x0 + ", " + y0 + ") to (" + x1 + ", " + y1 + ")");
    }

    /**
     * Return true if point (x2, y2) is on the left side of the directed line from (x0, y0) to (x1, y1)
     */
    public static boolean leftOfTheLine(double x0, double y0,
                                        double x1, double y1, double x2, double y2) {
        double p = (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);
        return p > 0;
    }

    /**
     * Return true if point (x2, y2) is on the same line from (x0, y0) to (x1, y1)
     */
    public static boolean onTheSameLine(double x0, double y0,
                                        double x1, double y1, double x2, double y2) {
        double p = (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);
        return Math.abs(p) < EPSILON;
    }

    /**
     * Return true if point (x2, y2) is on the line segment from (x0, y0) to (x1, y1)
     */
    public static boolean onTheLineSegment(double x0, double y0,
                                           double x1, double y1, double x2, double y2) {
        double p = (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);
        if (Math.abs(p) > EPSILON) return false;

        double dotproduct = (x2 - x0) * (x1 - x0) + (y2 - y0) * (y1 - y0);
        if (dotproduct < 0) return false;

        double squaredlengthba = (x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0);
        return dotproduct <= squaredlengthba;

    }
}
