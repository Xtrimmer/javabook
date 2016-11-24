package chapter_06;

import java.util.Scanner;

/**
 * (Occurrences of a specified character) Write a method that finds the number of
 * occurrences of a specified character in a string using the following header:
 *
 *      public static int count(String str, char a)
 *
 * For example, count("Welcome", 'e') returns 2. Write a test program that
 * prompts the user to enter a string followed by a character and displays the number
 * of occurrences of the character in the string.
 */
public class PE_06_23_Occurrences_of_a_specified_character {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s =scanner.nextLine();
        System.out.print("Enter a character: ");
        char ch = scanner.next().charAt(0);
        System.out.println("The count of " + ch + " in the string is: " + count(s, ch));
    }

    public static int count(String str, char a) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == a) count++;
        }
        return count;
    }
}
