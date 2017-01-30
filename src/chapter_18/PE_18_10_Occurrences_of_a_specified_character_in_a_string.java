package chapter_18;

import java.util.Scanner;

/**
 * (Occurrences of a specified character in a string) Write a recursive method that
 * finds the number of occurrences of a specified letter in a string using the following
 * method header:
 *
 *      public static int count(String str, char a)
 *
 * For example, count("Welcome", 'e') returns 2. Write a test program that
 * prompts the user to enter a string and a character, and displays the number of
 * occurrences for the character in the string.
 */
public class PE_18_10_Occurrences_of_a_specified_character_in_a_string {
    public static void main(String[] args) {
        String string = promptUserForString();
        char ch = promptUserForCharacter();
        System.out.println("Number of occurrences: " + count(string, ch));
    }

    private static char promptUserForCharacter() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a character: ");
        return scanner.nextLine().charAt(0);
    }

    private static int count(String str, char a) {
        return count(str, a, str.length() - 1, 0);
    }

    private static int count(String str, char a, int index, int count) {
        if (index < 0) return count;
        if (a == str.charAt(index)) {
            count++;
        }
        return count(str, a, index - 1, count);
    }

    private static String promptUserForString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        return scanner.nextLine();
    }
}
