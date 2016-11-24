package chapter_07;

import java.util.Scanner;

/**
 * (Average an array) Write two overloaded methods that return the average of an
 * array with the following headers:
 *
 *      public static int average(int[] array)
 *      public static double average(double[] array)
 *
 * Write a test program that prompts the user to enter ten double values, invokes this
 * method, and displays the average value.
 */
public class PE_07_08_Average_an_array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] numbers = new double[10];
        System.out.print("Enter ten numbers: ");
        for (int i = 0; i < 10; i++) {
            numbers[i] = scanner.nextDouble();
        }
        System.out.println("The average is " + average(numbers));
    }

    public static int average(int[] array){
        int sum = 0;
        for (int anArray : array) {
            sum += anArray;
        }
        return sum / array.length;
    }

    public static double average(double[] array){
        double sum = 0;
        for (double anArray : array) {
            sum += anArray;
        }
        return sum / array.length;
    }
}
