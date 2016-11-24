package chapter_04;

import java.util.Scanner;

/**
 * (Check SSN) Write a program that prompts the user to enter a Social Security
 * number in the format DDD-DD-DDDD, where D is a digit. Your program should
 * check whether the input is valid.
 */
public class PE_04_21_Check_SSN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a SSN: ");
        String input = scanner.nextLine();
        if (input.length() == 11 ) {
            if (input.charAt(3) == '-' && input.charAt(6) == '-'){
                System.out.println(input + " is a valid social security number");
                return;
            }
        }
        System.out.println(input + " is an invalid social security number");
    }
}
