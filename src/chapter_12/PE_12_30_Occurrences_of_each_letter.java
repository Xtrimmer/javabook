package chapter_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * (Occurrences of each letter) Write a program that prompts the user to enter
 * a file name and displays the occurrences of each letter in the file. Letters are
 * case-insensitive. Here is a sample run:
 *
 *      Enter a filename: Lincoln.txt
 *      Number of A's: 56
 *      Number of B's: 134
 *      ...
 *      Number of Z's: 9
 */
public class PE_12_30_Occurrences_of_each_letter {
    public static void main(String[] args) {
        String fileName = promptStringValue("Enter a filename: ");
        File file = validateFile(fileName);
        StringBuilder fileText = readFile(file);
        int[] letterOccurrenceCounts = countLetters(fileText);
        displayLetterOccurrenceCounts(letterOccurrenceCounts);
    }

    private static void displayLetterOccurrenceCounts(int[] letterOccurrenceCounts) {
        char letter = 'A';
        for (int letterOccurrenceCount : letterOccurrenceCounts) {
            System.out.println("Number of " + letter + "'s: " + letterOccurrenceCount);
            letter++;
        }
    }

    private static int[] countLetters(StringBuilder fileText) {
        int[] letterCounts = new int[26];
        for (int i = 0; i < fileText.length(); i++) {
            char thisChar = fileText.charAt(i);
            if (Character.isAlphabetic(thisChar)) {
                thisChar = Character.toUpperCase(thisChar);
                letterCounts[thisChar - 'A']++;
            }
        }
        return letterCounts;
    }

    private static StringBuilder readFile(File file) {
        StringBuilder text = new StringBuilder();
        try (Scanner fileIn = new Scanner(file)){
            while (fileIn.hasNext()) {
                text.append(fileIn.nextLine());
                text.append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            System.out.println("source file is not found");
            System.exit(0);
        }
        return text;
    }

    private static File validateFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("The file denoted by this pathname does not exist.");
            System.exit(0);
        }
        if (!file.isFile()) {
            System.out.println("The file denoted by this pathname is not a normal file.");
            System.exit(0);
        }
        if (!file.canRead()) {
            System.out.println("The application cannot read the file denoted by this pathname.");
            System.exit(0);
        }
        return file;
    }

    private static String promptStringValue(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
