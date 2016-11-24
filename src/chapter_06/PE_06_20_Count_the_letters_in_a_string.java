package chapter_06;

import java.util.Scanner;

/**
 * (Count the letters in a string) Write a method that counts the number of letters in
 * a string using the following header:
 *
 *      public static int countLetters(String s)
 *
 * Write a test program that prompts the user to enter a string and displays the number
 * of letters in the string.
 */
public class PE_06_20_Count_the_letters_in_a_string {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = scanner.nextLine();
        System.out.println("The number of letters: " + countLetters(s));
    }

    public static int countLetters(String s) {
        int length = s.length();
        int letterCount = 0;
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
                letterCount++;
        }
        return letterCount;
    }
}
