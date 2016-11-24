package chapter_08;

import java.util.Scanner;

/**
 * (Geometry: same line?) Programming Exercise 6.39 gives a method for testing
 * whether three points are on the same line.
 * Write the following method to test whether all the points in the array points are
 * on the same line.
 *
 *      public static boolean sameLine(double[][] points)
 *
 * Write a program that prompts the user to enter five points and displays whether
 * they are on the same line. Here are sample runs:
 *
 *      Enter five points: 3.4 2 6.5 9.5 2.3 2.3 5.5 5 -5 4 (enter)
 *      The five points are not on the same line
 *
 *      Enter five points: 1 1 2 2 3 3 4 4 5 5 (enter)
 *      The five points are on the same line
 */
public class PE_08_15_Geometry_same_line {
    public static void main(String[] args) {
        double[][] points = getPoints(5);
        if (onTheSameLine(points)) {
            System.out.println("The five points are on the same line");
        } else {
            System.out.println("The five points are not on the same line");
        }
    }

    public static double[][] getPoints(int quantity) {
        Scanner scanner = new Scanner(System.in);
        double[][] points = new double[quantity][2];
        System.out.print("Enter " + quantity + " points: ");
        for (int i = 0; i < quantity; i++) {
            for (int j = 0; j < 2; j++) {
                points[i][j] = scanner.nextDouble();
            }
        }
        return points;
    }

    public static boolean onTheSameLine(double[][] points) {
        boolean isCollinear = true;
        for (int i = 0; i < points.length - 2; i++) {
            isCollinear &= (points[i + 1][0] - points[i][0]) * (points[i + 2][1] - points[i][1])
                    - (points[i + 2][0] - points[i][0]) * (points[i + 1][1] - points[i][1]) == 0;
        }
        return isCollinear;
    }
}
