package chapter_02;

import java.util.Scanner;

/**
 * (Sum the digits in an integer) Write a program that reads an integer between 0 and
 * 1000 and adds all the digits in the integer. For example, if an integer is 932, the
 * sum of all its digits is 14.
 *
 * Hint: Use the % operator to extract digits, and use the / operator to remove the
 * extracted digit. For instance, 932 % 10 = 2 and 932 / 10 = 93.
 */
public class PE_02_06_Sum_the_digits_in_an_integer {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter a number between 0 an 1000: ");
        int num = SCANNER.nextInt();

        int sum = 0;
        sum += num % 10;
        num /= 10;
        sum += num % 10;
        num /= 10;
        sum += num % 10;

        System.out.println("The sum of the digits is " + sum);
    }
}
