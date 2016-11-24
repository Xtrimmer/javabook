package chapter_04;

import java.util.Scanner;
/**
 * (Find the character of an ASCII code) Write a program that receives an ASCII code
 * (an integer between 0 and 127) and displays its character.
 */
public class PE_04_08_Find_the_character_of_an_ASCII_code {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an ASCII code: ");
        int ascii = scanner.nextInt();
        if (ascii >= 0 && ascii <= 127) {
            char ch = (char)ascii;
            System.out.println("The character for ASCII code " + ascii + " is " + ch);
        } else System.out.println("Invalid input.");
    }
}
