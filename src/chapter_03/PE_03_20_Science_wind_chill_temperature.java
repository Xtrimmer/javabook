package chapter_03;

import java.util.Scanner;

/**
 * (Science: wind-chill temperature) Programming Exercise 2.17 gives a formula
 * to compute the wind-chill temperature. The formula is valid for temperatures in
 * the range between −58ºF and 41ºF and wind speed greater than or equal to 2.
 * Write a program that prompts the user to enter a temperature and a wind speed.
 * The program displays the wind-chill temperature if the input is valid; otherwise,
 * it displays a message indicating whether the temperature and/or wind speed is
 * invalid.
 */
public class PE_03_20_Science_wind_chill_temperature {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter the temperature in Fahrenheit: ");
        double ta = SCANNER.nextDouble();
        System.out.print("Enter the wind speed in miles per hour: ");
        double v = SCANNER.nextDouble();
        if (ta >= -58 && ta <= 41 && v >= 2) {
            double windChill = 35.74 + (0.6215 * ta) - (35.75 * Math.pow(v, 0.16)) + (0.4275 * ta * Math.pow(v, 0.16));
            System.out.println("The wind chill index is " + windChill);
        } else System.out.println("The temperature and/or wind speed is invalid.");
    }
}
