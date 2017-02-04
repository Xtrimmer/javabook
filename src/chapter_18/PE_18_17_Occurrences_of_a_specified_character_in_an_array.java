package chapter_18;

import java.util.Scanner;

/**
 * (Occurrences of a specified character in an array) Write a recursive method that
 * finds the number of occurrences of a specified character in an array. You need to
 * define the following two methods. The second one is a recursive helper method.
 *
 *      public static int count(char[] chars, char ch)
 *      public static int count(char[] chars, char ch, int high)
 *
 * Write a test program that prompts the user to enter a list of characters in one line,
 * and a character, and displays the number of occurrences of the character in the list.
 */
public class PE_18_17_Occurrences_of_a_specified_character_in_an_array {
    public static void main(String[] args) {
        char[] chars = promptUserForCharArray();
        char ch = promptUserForCharacter();
        System.out.println("The count is: " + count(chars, ch));
    }

    private static int count(char[] chars, char ch) {
        return count(chars, ch, chars.length - 1);
    }

    private static int count(char[] chars, char ch, int high) {
        int count = chars[high] == ch ? 1 : 0;
        if (high == 0) return count;
        return count + count(chars, ch, high - 1);
    }

    private static char[] promptUserForCharArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter chars in one line:");
        return scanner.nextLine().toCharArray();
    }

    private static char promptUserForCharacter() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a character: ");
        return scanner.nextLine().charAt(0);
    }
}
