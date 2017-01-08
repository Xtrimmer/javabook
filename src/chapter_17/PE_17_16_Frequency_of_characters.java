package chapter_17;

import java.io.*;
import java.util.Scanner;

/**
 * (Frequency of characters) Write a program that prompts the user to enter the
 * name of an ASCII text file and displays the frequency of the characters in the file.
 */
public class PE_17_16_Frequency_of_characters {
    private static final int[] charCount = new int[Byte.MAX_VALUE];
    private static File file;

    public static void main(String[] args) {
        promptUserForFileName();
        countCharactersInFile();
        displayCharacterFrequency();
    }

    private static void countCharactersInFile() {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            int value;
            while ((value = inputStream.read()) != -1) {
                charCount[(byte) value]++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayCharacterFrequency() {
        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] != 0 && i >= 32)
                System.out.println("Character " + i + "(" + (char) i + ") count: " + charCount[i]);
        }
    }

    private static void promptUserForFileName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of an ASCII text file: ");
        file = new File(scanner.nextLine());
    }
}
