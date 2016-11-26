package chapter_02;

import java.util.Scanner;

/**
 * (Science: wind-chill temperature) How cold is it outside? The temperature alone is
 * not enough to provide the answer. Other factors including wind speed, relative
 * humidity, and sunshine play important roles in determining coldness outside. In
 * 2001, the National Weather Service (NWS) implemented the new wind-chill temperature
 * to measure the coldness using temperature and wind speed. The formula is:
 *
 *      twc = 35.74 + 0.6215ta - 35.75v^0.16 + 0.4275tav^0.16
 *
 * where ta is the outside temperature measured in degrees Fahrenheit and v is the speed
 * measured in miles per hour. twc is the wind-chill temperature. The formula cannot be
 * used for wind speeds below 2 mph or temperatures below or above
 * Write a program that prompts the user to enter a temperature between and
 * and a wind speed greater than or equal to 2 and displays the wind-chill temperature.
 * Use Math.pow(a, b) to compute v0.16.
 */
public class PE_02_17_Science_wind_chill_temperature {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter the temperature in Fahrenheit: ");
        double ta = SCANNER.nextDouble();
        System.out.print("Enter the wind speed in miles per hour: ");
        double v = SCANNER.nextDouble();

        double windChill = 35.74 + (0.6215 * ta) - (35.75 * Math.pow(v, 0.16)) + (0.4275 * ta * Math.pow(v, 0.16));

        System.out.println("The wind chill index is " + windChill);
    }
}
