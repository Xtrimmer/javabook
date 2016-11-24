package chapter_05;

import java.util.Scanner;

/**
 * (Statistics: compute mean and standard deviation) In business applications, you
 * are often asked to compute the mean and standard deviation of data. The mean is
 * simply the average of the numbers. The standard deviation is a statistic that tells
 * you how tightly all the various data are clustered around the mean in a set of data.
 * For example, what is the average age of the students in a class? How close are the
 * ages? If all the students are the same age, the deviation is 0.
 * Write a program that prompts the user to enter ten numbers, and displays the
 * mean and standard deviations of these numbers.
 * Here is a sample run:
 *
 *      Enter ten numbers: 1 2 3 4.5 5.6 6 7 8 9 10 (enter)
 *      The mean is 5.61
 *      The standard deviation is 2.99794
 */
public class PE_05_45_Statistics_compute_mean_and_standard_deviation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double mean;
        double standardDeviation;
        double sum = 0;
        double sumOfSquares = 0;
        System.out.print("Enter ten numbers: ");
        for (int i = 0; i < 10; i++) {
            double num = scanner.nextDouble();
            sum += num;
            sumOfSquares += Math.pow(num, 2);
        }
        mean = sum / 10.0;
        standardDeviation = Math.sqrt((sumOfSquares - (Math.pow(sum, 2) / 10.0)) / 9.0);
        System.out.printf("The mean is %.2f%n", mean);
        System.out.printf("The standard deviation is %.5f%n", standardDeviation);
    }
}
