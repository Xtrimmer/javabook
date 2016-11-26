package chapter_02;

import java.util.Scanner;

/**
 * (Financial application: calculate tips) Write a program that reads the subtotal and
 * the gratuity rate, then computes the gratuity and total. For example, if the user
 * enters 10 for subtotal and 15% for gratuity rate, the program displays $1.5 as gratuity
 * and $11.5 as total.
 */
public class PE_02_05_Financial_application_calculate_tips {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter the subtotal and gratuity rate: ");
        float subtotal = SCANNER.nextFloat();
        float gratuityRate = SCANNER.nextFloat();

        gratuityRate /= 100f;
        float gratuity = subtotal * gratuityRate;
        float total = subtotal + gratuity;

        System.out.println("The gratuity is $" + gratuity + " and total is $" + total);
    }
}
