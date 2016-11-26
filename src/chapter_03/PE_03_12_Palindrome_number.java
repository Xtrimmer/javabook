package chapter_03;

import java.util.Scanner;

/**
 * (Palindrome number) Write a program that prompts the user to enter a three-digit
 * integer and determines whether it is a palindrome number. A number is palindrome
 * if it reads the same from right to left and from left to right.
 */
public class PE_03_12_Palindrome_number {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        System.out.print("Enter a three-digit integer: ");
        int number = SCANNER.nextInt();
        if (number / 100 == number % 10) {
            System.out.println(number + " is a palindrome");
        } else {
            System.out.println(number + " is not a palindrome");
        }
    }
}
