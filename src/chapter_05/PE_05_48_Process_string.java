package chapter_05;

import java.util.Scanner;

/**
 * (Process string) Write a program that prompts the user to enter a string and displays
 * the characters at odd positions. Here is a sample run:
 *
 *      Enter a string: Beijing Chicago (enter)
 *      BiigCiao
 */
public class PE_05_48_Process_string {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            output += i % 2 == 0 ? input.charAt(i) : "";
        }
        System.out.println(output);
    }
}
