package chapter_05;

import java.util.Scanner;

/**
 * (Count uppercase letters) Write a program that prompts the user to enter a string
 * and displays the number of the uppercase letters in the string.
 *
 *      Enter a string: Welcome to Java (enter)
 *      The number of uppercase letters is 2
 */
public class PE_05_50_Count_uppercase_letters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int uppercase = 0;
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch >= 'A' && ch <= 'Z') uppercase++;
        }
        System.out.println("The number of uppercase letters is " + uppercase);
    }
}
