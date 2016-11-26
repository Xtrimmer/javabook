package chapter_02;

import java.util.Scanner;

/**
 * (Geometry: area of a hexagon) Write a program that prompts the user to enter the side
 * of a hexagon and displays its area. The formula for computing the area of a hexagon is
 *
 *      area = (3 * √3 / 2) * s^2
 *
 * where s is the length of a side. Here is a sample run:
 *
 *      Enter the side: 5.5
 *      The area of the hexagon is 78.5918
 *
 * NOTE: Per the book's erata at http://www.cs.armstrong.edu/liang/intro10e/errata.html,
 * In the output box for Programming Exercise 2.16, change 78.5895 to 78.5918.
 */
public class PE_02_16_Geometry_area_of_a_hexagon {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter the side: ");
        double side = SCANNER.nextDouble();

        double area = ((3 * Math.sqrt(3)) / 2) * Math.pow(side, 2);

        System.out.println("The area of the hexagon is " + area);
    }
}
