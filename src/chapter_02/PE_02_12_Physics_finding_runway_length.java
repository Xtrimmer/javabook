package chapter_02;

import java.util.Scanner;

/**
 * (Physics: finding runway length) Given an airplane’s acceleration a and take-off
 * speed v, you can compute the minimum runway length needed for an airplane to
 * take off using the following formula:
 *
 *      length = v^2 / 2a
 *
 * Write a program that prompts the user to enter v in meters/second (m/s) and the
 * acceleration a in meters/second squared (m/s^2)and displays the minimum runway
 * length.
 */
public class PE_02_12_Physics_finding_runway_length {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter speed and acceleration: ");
        double v = SCANNER.nextDouble();
        double a = SCANNER.nextDouble();

        double length = Math.pow(v, 2) / (2 * a);

        System.out.println("The minimum runway length for this airplane is " + length);
    }
}
