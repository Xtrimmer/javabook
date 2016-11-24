package chapter_07;

import java.util.Arrays;
import java.util.Scanner;

/**
 * (Sort characters in a string) Write a method that returns a sorted string using the
 * following header:
 *
 *      public static String sort(String s)
 *
 * For example, sort("acb") returns abc.
 * Write a test program that prompts the user to enter a string and displays the sorted
 * string.
 */
public class PE_07_34_Sort_characters_in_a_string {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = scanner.nextLine();
        s = sort(s);
        System.out.println(s);

    }

    public static String sort(String s) {
        char[] chars = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            chars[i] = s.charAt(i);
        }
        Arrays.sort(chars);
        String out = "";
        for (char aChar : chars) {
            out += aChar;
        }
        return out;
    }
}
