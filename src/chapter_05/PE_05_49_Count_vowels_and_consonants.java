package chapter_05;

import java.util.Scanner;

/**
 * (Count vowels and consonants) Assume letters A, E, I, O, and U as the vowels.
 * Write a program that prompts the user to enter a string and displays the number
 * of vowels and consonants in the string.
 *
 *      Enter a string: Programming is fun (enter)
 *      The number of vowels is 5
 *      The number of consonants is 11
 */
public class PE_05_49_Count_vowels_and_consonants {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int vowels = 0;
        int consonants = 0;
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
                switch (ch){
                    case 'a':
                    case 'e':
                    case 'i':
                    case 'o':
                    case 'u':
                    case 'A':
                    case 'E':
                    case 'I':
                    case 'O':
                    case 'U':
                        vowels++;
                        break;
                    default:
                        consonants++;
                }
            }
        }
        System.out.println("The number of vowels is " + vowels);
        System.out.println("The number of consonants is " + consonants);
    }
}
