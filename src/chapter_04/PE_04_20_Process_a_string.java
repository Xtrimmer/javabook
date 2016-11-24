package chapter_04;

import java.util.Scanner;
/**
 * (Process a string) Write a program that prompts the user to enter a string and displays
 * its length and its first character.
 */
public class PE_04_20_Process_a_string {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        System.out.println("The length is: " + input.length());
        System.out.println("The first char is: " + input.charAt(0));
    }
}
