package chapter_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * (Data sorted?) Write a program that reads the strings from file SortedStrings.
 * txt and reports whether the strings in the files are stored in increasing order.
 * If the strings are not sorted in the file, displays the first two strings that are out
 * of the order.
 */
public class PE_12_21_Data_sorted {
    public static void main(String[] args) {
        ArrayList<String> strings = loadStringsFromFile("resources\\text\\SortedStrings.txt");
        reportStringSortCheckResults(strings);
    }

    private static void reportStringSortCheckResults(ArrayList<String> strings) {
        ArrayList<String> sortedStrings = new ArrayList<>(strings);
        Collections.sort(sortedStrings);
        if (strings.equals(sortedStrings)) {
            System.out.println("The strings are sorted");
        } else {
            System.out.println("The strings are not sorted");
            printUnsortedStrings(strings, 2);
        }
    }

    private static void printUnsortedStrings(ArrayList<String> strings, int count) {
        int index = 0;
        for (int i = 0; i < strings.size() - 1; i++) {
            if (strings.get(i).compareToIgnoreCase(strings.get(i + 1)) > 0) {
                index = i;
            }
        }
        try {
            for (int i = 0; i < count - 1; i++) {
                System.out.print(strings.get(index + i) + ", ");
            }
            System.out.print(strings.get(index + count - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.print("[EOF]");
        }
    }

    private static ArrayList<String> loadStringsFromFile(String filePath) {
        File file = new File(filePath);
        validateFile(file);
        ArrayList<String> words = new ArrayList<>();
        try (Scanner fileIn = new Scanner(file)){
            while (fileIn.hasNext()) {
                String line = fileIn.next();
                words.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("source file is not found");
            System.exit(0);
        }
        return words;
    }

    private static void validateFile(File file) {
        validateFileExists(file);
        validateFileIsFile(file);
        validateFileIsReadable(file);
    }

    private static void validateFileIsReadable(File file) {
        if (!file.canRead()) {
            System.out.println("The application cannot read the file denoted by this pathname.");
            System.exit(0);
        }
    }

    private static void validateFileIsFile(File file) {
        if (!file.isFile()) {
            System.out.println("The file denoted by this pathname is not a normal file.");
            System.exit(0);
        }
    }

    private static void validateFileExists(File file) {
        if (!file.exists()) {
            System.out.println("The file denoted by this pathname does not exist.");
            System.exit(0);
        }
    }
}
