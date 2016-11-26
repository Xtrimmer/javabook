package chapter_02;

import java.util.Scanner;

/**
 * (Convert feet into meters) Write a program that reads a number in feet, converts it
 * to meters, and displays the result. One foot is 0.305 meter.
 */
public class PE_02_03_Convert_feet_into_meters {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter a value for feet: ");
        double feet = SCANNER.nextDouble();

        double meters = feet * 0.305d;

        System.out.println(feet + " feet is " + meters + " meters.");
    }
}