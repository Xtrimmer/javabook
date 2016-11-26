package chapter_02;

import java.util.Scanner;

/**
 * (Science: calculating energy) Write a program that calculates the energy needed to
 * heat water from an initial temperature to a final temperature. Your program should
 * prompt the user to enter the amount of water in kilograms and the initial and final
 * temperatures of the water. The formula to compute the energy is
 *
 *      Q = M * (finalTemperature – initialTemperature) * 4184
 *
 * where M is the weight of water in kilograms, temperatures are in degrees Celsius,
 * and energy Q is measured in joules.
 */
public class PE_02_10_Science_calculating_energy {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter the amount of water in kilograms: ");
        double kilograms = SCANNER.nextDouble();
        System.out.print("Enter the initial temperature: ");
        double temp1 = SCANNER.nextDouble();
        System.out.print("Enter the final temperature: ");
        double temp2 = SCANNER.nextDouble();

        double q = kilograms * (temp2 - temp1) * 4184;

        System.out.println("The energy needed is: " + q);
    }
}
