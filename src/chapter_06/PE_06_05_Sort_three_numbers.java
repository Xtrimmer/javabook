package chapter_06;

import java.util.Scanner;

/**
 * (Sort three numbers) Write a method with the following header to display three
 * numbers in increasing order:
 *
 *      public static void displaySortedNumbers(
 *      double num1, double num2, double num3)
 *
 * Write a test program that prompts the user to enter three numbers and invokes the
 * method to display them in increasing order.
 */
public class PE_06_05_Sort_three_numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 3 numbers: ");
        double d1 = scanner.nextDouble();
        double d2 = scanner.nextDouble();
        double d3 = scanner.nextDouble();
        displaySortedNumbers(d1, d2, d3);
    }

    public static void displaySortedNumbers(
            double num1, double num2, double num3){
        double temp;
        if (num1 > num2) {
            temp = num2;
            num2 = num1;
            num1 = temp;
        }
        if (num2 > num3) {
            temp = num3;
            num3 = num2;
            num2 = temp;
        }
        if (num1 > num2) {
            temp = num2;
            num2 = num1;
            num1 = temp;
        }
        System.out.print("The numbers in increasing order are: " + num1 + " " +  num2 + " " +  num3);
    }
}
