package chapter_02;

import java.util.Scanner;
/**
 * (Convert Celsius to Fahrenheit) Write a program that reads a Celsius degree in a
 * double value from the console, then converts it to Fahrenheit and displays the
 * result. The formula for the conversion is as follows:
 *
 *      fahrenheit = (9 / 5) * celsius + 32
 *
 * Hint: In Java, 9 / 5 is 1, but 9.0 / 5 is 1.8.
 */
public class PE_02_01_Convert_Celsius_to_Fahrenheit {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        System.out.print("Enter a degree in Celsius: ");
        double degreesC = SCANNER.nextDouble();
        double degreesF = (9.0 / 5) * degreesC + 32;
        System.out.println(degreesC + " Celsius is " + degreesF + " Fahrenheit");
    }
}
