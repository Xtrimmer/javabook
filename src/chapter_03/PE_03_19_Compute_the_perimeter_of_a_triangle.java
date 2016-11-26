package chapter_03;

import java.util.Scanner;

/**
 * (Compute the perimeter of a triangle) Write a program that reads three edges for
 * a triangle and computes the perimeter if the input is valid. Otherwise, display that
 * the input is invalid. The input is valid if the sum of every pair of two edges is
 * greater than the remaining edge.
 */
public class PE_03_19_Compute_the_perimeter_of_a_triangle {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        System.out.println("Enter the 3 sides of a triangle.");
        System.out.print("Side 1: ");
        double side1 = SCANNER.nextDouble();
        System.out.print("Side 2: ");
        double side2 = SCANNER.nextDouble();
        System.out.print("Side 3: ");
        double side3 = SCANNER.nextDouble();

        if (side1 < side2 + side3 && side2 < side1 + side3 && side3 < side1 + side2) {
            double perimeter = side1 + side2 + side3;
            System.out.println("Perimeter = " + perimeter);
        } else System.out.println("The input is invalid.");
    }
}
