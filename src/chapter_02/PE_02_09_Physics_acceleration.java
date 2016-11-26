package chapter_02;

import java.util.Scanner;

/**
 * (Physics: acceleration) Average acceleration is defined as the change of velocity
 * divided by the time taken to make the change, as shown in the following formula:
 *
 *      a = ( v1 - v0 ) / t
 *
 * Write a program that prompts the user to enter the starting velocity v0 in
 * meters/second, the ending velocity v1 in meters/second, and the time span t in
 * seconds, and displays the average acceleration.
 */
public class PE_02_09_Physics_acceleration {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter v0, v1, and t: ");
        double v0 = SCANNER.nextDouble();
        double v1 = SCANNER.nextDouble();
        double t = SCANNER.nextDouble();

        double a = (v1 - v0) / t;

        System.out.println("The average acceleration is " + a);
    }
}
