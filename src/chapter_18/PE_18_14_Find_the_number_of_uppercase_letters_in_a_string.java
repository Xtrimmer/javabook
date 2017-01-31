package chapter_18;

import java.util.Scanner;

/**
 * (Find the number of uppercase letters in a string) Write a recursive method
 * to return the number of uppercase letters in a string. Write a test program that
 * prompts the user to enter a string and displays the number of uppercase letters in
 * the string.
 */
public class PE_18_14_Find_the_number_of_uppercase_letters_in_a_string {
    public static void main(String[] args) {
        String string = promptUserForString();
        System.out.println("uppercase count: " + uppercaseCount(string));
    }

    private static int uppercaseCount(String string) {
        return uppercaseCount(string, string.length() - 1, 0);
    }

    private static int uppercaseCount(String string, int index, int count) {
        if (index < 0) return count;
        char character = string.charAt(index);
        if (character >= 'A' && character <= 'Z') count++;
        return uppercaseCount(string, index - 1, count);
    }

    private static String promptUserForString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        return scanner.nextLine();
    }
}
