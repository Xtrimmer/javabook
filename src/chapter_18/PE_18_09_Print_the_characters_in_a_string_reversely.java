package chapter_18;

import java.util.Scanner;

/**
 * (Print the characters in a string reversely) Write a recursive method that displays
 * a string reversely on the console using the following header:
 *
 *      public static void reverseDisplay(String value)
 *
 * For example, reverseDisplay("abcd") displays dcba. Write a test program
 * that prompts the user to enter a string and displays its reversal.
 */
public class PE_18_09_Print_the_characters_in_a_string_reversely {
    public static void main(String[] args) {
        String string = promptUserForString();
        reverseDisplay(string);
    }

    private static void reverseDisplay(String value) {
        reverseDisplay(value, value.length() - 1);
    }

    private static void reverseDisplay(String value, int index) {
        if (index == 0) {
            System.out.print(value.charAt(0));
        } else {
            System.out.print(value.charAt(index));
            reverseDisplay(value, index - 1);
        }
    }

    private static String promptUserForString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        return scanner.nextLine();
    }
}
