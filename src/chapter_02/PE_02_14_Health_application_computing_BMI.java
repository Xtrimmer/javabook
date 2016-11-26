package chapter_02;

import java.util.Scanner;

/**
 * (Health application: computing BMI) Body Mass Index (BMI) is a measure of
 * health on weight. It can be calculated by taking your weight in kilograms and
 * dividing by the square of your height in meters. Write a program that prompts the
 * user to enter a weight in pounds and height in inches and displays the BMI. Note
 * that one pound is 0.45359237 kilograms and one inch is 0.0254 meters.
 */
public class PE_02_14_Health_application_computing_BMI {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter weight in pounds: ");
        double weightLbs = SCANNER.nextDouble();
        System.out.print("Enter height in inches: ");
        double heightIn = SCANNER.nextDouble();

        double weightKg = weightLbs * 0.45359237d;
        double heightM = heightIn * 0.0254d;
        double bmi = weightKg / Math.pow(heightM, 2);

        System.out.println("BMI is " + bmi);
    }
}
