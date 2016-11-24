package chapter_05;

import java.util.Scanner;

/**
 * (Count positive and negative numbers and compute the average of numbers) Write
 * a program that reads an unspecified number of integers, determines how many
 * positive and negative values have been read, and computes the total and average of
 * the input values (not counting zeros). Your program ends with the input 0. Display
 * the average as a floating-point number.
 */
public class PE_05_01_Count_positive_and_negative_numbers_and_compute_the_average_of_numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;
        int countPositive = 0;
        int countNegative = 0;
        int sum = 0;
        System.out.print("Enter an integer, the input ends if it is 0: ");
        number = scanner.nextInt();
        while (number != 0) {
            if (number >= 0) {
                countPositive++;
            } else {
                countNegative++;
            }
            sum += number;
            number = scanner.nextInt();
        }
        System.out.println("The number of positives is " + countPositive);
        System.out.println("The number of negatives is " + countNegative);
        System.out.println("The total is " + sum);
        System.out.println("The average is " + sum /(float)(countNegative + countPositive));
    }
}
