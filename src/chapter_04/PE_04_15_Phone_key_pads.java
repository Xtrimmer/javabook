package chapter_04;

import java.util.Scanner;
/**
 * (Phone key pads) Write a program that prompts the user to enter a letter and displays its corresponding
 * number.
 */
public class PE_04_15_Phone_key_pads {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        System.out.print("Enter a letter: ");
        char ch = SCANNER.nextLine().toUpperCase().charAt(0);
        int num = -1;
        if (ch >= 'A' && ch <= 'Z') {
            if (ch < 'D') {
                num = 2;
            } else if (ch < 'G') {
                num = 3;
            } else if (ch < 'I') {
                num = 4;
            } else if (ch < 'M') {
                num = 5;
            } else if (ch < 'P') {
                num = 6;
            } else if (ch < 'T') {
                num = 7;
            } else if (ch < 'W') {
                num = 8;
            } else if (ch <= 'Z') {
                num = 9;
            }
        } else {
            System.out.println(ch + " is an invalid input");
            System.exit(1);
        }
        System.out.println("The corresponding number is " + num);
    }
}
