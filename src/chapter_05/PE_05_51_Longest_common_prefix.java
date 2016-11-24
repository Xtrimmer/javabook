package chapter_05;

import java.util.Scanner;

/**
 * (Longest common prefix) Write a program that prompts the user to enter two
 * strings and displays the largest common prefix of the two strings. Here are some
 * sample runs:
 *
 *      Enter the first string: Welcome to C++ (enter)
 *      Enter the second string: Welcome to programming (enter)
 *      The common prefix is Welcome to
 *
 *      Enter the first string: Atlanta (enter)
 *      Enter the second string: Macon (enter)
 *      Atlanta and Macon have no common prefix
 */
public class PE_05_51_Longest_common_prefix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first string: ");
        String input1 = scanner.nextLine();
        System.out.print("Enter the second string: ");
        String input2 = scanner.nextLine();
        int minLength = input1.length() < input2.length() ? input1.length() : input2.length();
        if (input1.charAt(0) != input2.charAt(0)) {
            System.out.printf("%s and %s have no common prefix", input1, input2);
            return;
        }
        int i;
        for (i = 1; i < minLength; i++) {
            if (input1.charAt(i) != input2.charAt(i)) break;
        }
        System.out.print("The common prefix is " + input1.substring(0, i));
    }
}
