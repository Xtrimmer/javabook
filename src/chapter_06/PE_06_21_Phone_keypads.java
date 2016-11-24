package chapter_06;

import java.util.Scanner;

/**
 * (Phone keypads) The international standard letter/number mapping for telephones
 * is shown in Programming Exercise 4.15. Write a method that returns a number,
 * given an uppercase letter, as follows:
 *
 *      int getNumber(char uppercaseLetter)
 *
 * Write a test program that prompts the user to enter a phone number as a string.
 * The input number may contain letters. The program translates a letter (uppercase
 * or lowercase) to a digit and leaves all other characters intact. Here is a sample run
 * of the program:
 *
 *      Enter a string: 1-800-Flowers (enter)
 *      1-800-3569377
 *
 *      Enter a string: 1800flowers (enter)
 *      18003569377
 */
public class PE_06_21_Phone_keypads {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a phone number: ");
        String s = scanner.nextLine().toUpperCase();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                System.out.print(getNumber(ch));
            } else {
                System.out.print(ch);
            }
        }
    }

    public static int getNumber(char uppercaseLetter) {
        int num = 0;
        if (uppercaseLetter < 'D') {
            num = 2;
        } else if (uppercaseLetter < 'G') {
            num = 3;
        } else if (uppercaseLetter < 'I') {
            num = 4;
        } else if (uppercaseLetter < 'M') {
            num = 5;
        } else if (uppercaseLetter < 'P') {
            num = 6;
        } else if (uppercaseLetter < 'T') {
            num = 7;
        } else if (uppercaseLetter < 'W') {
            num = 8;
        } else if (uppercaseLetter <= 'Z') {
            num = 9;
        }
        return num;
    }
}
