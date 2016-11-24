package chapter_05;

import java.util.Scanner;

/**
 * (Reverse a string) Write a program that prompts the user to enter a string and
 * displays the string in reverse order.
 *
 *      Enter a string: ABCD (enter)
 *      The reversed string is DCBA
 */
public class PE_05_46_Reverse_a_string {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String in;
        String out = "";
        System.out.print("Enter a string: ");
        in = scanner.next();
        for (int i = 0; i < in.length(); i++) {
            out = in.charAt(i) + out;
        }
        System.out.println("The reversed string is " + out);
    }
}
