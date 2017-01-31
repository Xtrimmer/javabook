package chapter_18;

import java.util.Scanner;

/**
 * (Print the characters in a string reversely) Rewrite Programming Exercise 18.9
 * using a helper method to pass the substring high index to the method. The
 * helper method header is:
 *
 *      public static void reverseDisplay(String value, int high)
 */
public class PE_18_12_Print_the_characters_in_a_string_reversely {
    public static void main(String[] args) {
        String string = promptUserForString();
        reverseDisplay(string);
    }

    private static void reverseDisplay(String value) {
        reverseDisplay(value, value.length() - 1);
    }

    private static void reverseDisplay(String value, int high) {
        if (high == 0) {
            System.out.print(value.charAt(0));
        } else {
            System.out.print(value.charAt(high));
            reverseDisplay(value, high - 1);
        }
    }

    private static String promptUserForString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        return scanner.nextLine();
    }
}
