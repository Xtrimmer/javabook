package chapter_02;

import java.util.Scanner;

/**
 * (Cost of driving) Write a program that prompts the user to enter the distance to
 * drive, the fuel efficiency of the car in miles per gallon, and the price per gallon,
 * and displays the cost of the trip.
 */
public class PE_02_23_Cost_of_driving {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter the driving distance: ");
        double distance = SCANNER.nextDouble();
        System.out.print("Enter miles per gallon: ");
        double miles = SCANNER.nextDouble();
        System.out.print("Enter price per gallon: ");
        double ppg = SCANNER.nextDouble();

        double cost = (Math.round(distance / miles * ppg * 100)) / 100d;

        System.out.print("The cost of driving is $" + cost);
    }
}
