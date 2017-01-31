package chapter_18;

import java.util.Scanner;

/**
 * (Find the number of uppercase letters in an array) Write a recursive method
 * to return the number of uppercase letters in an array of characters. You need to
 * define the following two methods. The second one is a recursive helper method.
 *
 *      public static int count(char[] chars)
 *      public static int count(char[] chars, int high)
 *
 * Write a test program that prompts the user to enter a list of characters in one line
 * and displays the number of uppercase letters in the list.
 */
public class PE_18_16_Find_the_number_of_uppercase_letters_in_an_array {

    private static int count = 0;

    public static void main(String[] args) {
        char[] chars = promptUserForCharArray();
        System.out.println("Uppercase count: " + count(chars));
    }

    private static char[] promptUserForCharArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter chars in one line:");
        return scanner.nextLine().toCharArray();
    }

    private static int count(char[] chars) {
        return count(chars, chars.length - 1);
    }

    private static int count(char[] chars, int high) {
        if (high < 0) return count;
        if (Character.isUpperCase(chars[high])) count++;
        return count(chars, high - 1);
    }
}
