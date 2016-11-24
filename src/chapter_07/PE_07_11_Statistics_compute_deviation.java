package chapter_07;

import java.util.Scanner;

/**
 * (Statistics: compute deviation) Programming Exercise 5.45 computes the standard
 * deviation of numbers. This exercise uses a different but equivalent formula to
 * compute the standard deviation of n numbers.
 *
 *      mean = (x1 + x2 + ... + x(n-1) + x(n)) / n
 *      deviation = √((x1 - mean)^2 + (x2 - mean)^2 + ... + (x(n-1) - mean)^2 + (x(n) - mean)^2) / (n - 1)
 *      
 * To compute the standard deviation with this formula, you have to store the individual
 * numbers using an array, so that they can be used after the mean is obtained.
 * Your program should contain the following methods:
 *
 *      // Compute the deviation of double values
 *      public static double deviation(double[] x)
 *
 *      // Compute the mean of an array of double values
 *      public static double mean(double[] x)
 *
 * Write a test program that prompts the user to enter ten numbers and displays the
 * mean and standard deviation, as shown in the following sample run:
 *
 *      Enter ten numbers: 1.9 2.5 3.7 2 1 6 3 4 5 2 (enter)
 *      The mean is 3.11
 *      The standard deviation is 1.55738
 */
public class PE_07_11_Statistics_compute_deviation {
    public static void main(String[] args) {
        final int SIZE = 10;
        double[] array = new double[SIZE];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ten numbers: ");
        for (int i = 0; i < SIZE; i++) {
            array[i] = scanner.nextDouble();
        }
        System.out.printf("The mean is %.2f%n", mean(array));
        System.out.printf("The standard deviation is %.5f%n", deviation(array));
    }

    // Compute the deviation of double values
    public static double deviation(double[] x) {
        double mean = mean(x);
        double sum = 0;
        for (double v : x) {
            sum += Math.pow(v - mean, 2);
        }
        return Math.sqrt(sum / (x.length - 1));
    }

    // Compute the mean of an array of double values
    public static double mean(double[] x) {
        double sum = 0;
        for (double v : x) {
            sum += v;
        }
        return sum / x.length;
    }
}
