package chapter_21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * (Count consonants and vowels) Write a program that prompts the user to enter a
 * text file name and displays the number of vowels and consonants in the file. Use
 * a set to store the vowels A, E, I, O, and U.
 */
public class PE_21_04_Count_consonants_and_vowels {

    private static final int CONSONANTS = 0;
    private static final int VOWELS = 1;
    private static final Set<Character> vowels = new HashSet<>(Arrays.asList('A', 'E', 'I', 'O', 'U'));
    private static int[] counts;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a text source file: ");
        String filePath = scanner.nextLine();
        String text = getTextFromFilePath(filePath);
        counts = countLetters(text);

        System.out.println("Consonants = " + counts[CONSONANTS]);
        System.out.println("Vowels = " + counts[VOWELS]);
    }

    private static int[] countLetters(String text) {
        counts = new int[2];
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isAlphabetic(ch)) {
                if (vowels.contains(Character.toUpperCase(ch))) {
                    counts[VOWELS]++;
                } else {
                    counts[CONSONANTS]++;
                }
            }
        }
        return counts;
    }

    private static String getTextFromFilePath(String filePath) {
        Path path = Paths.get(filePath);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }
}
