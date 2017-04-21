package chapter_20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * (Display words in ascending alphabetical order) Write a program that reads
 * words from a text file and displays all the words (duplicates allowed) in ascending
 * alphabetical order. The words must start with a letter. The text file is passed
 * as a command-line argument.
 */
public class PE_20_01_Display_words_in_ascending_alphabetical_order {
    public static void main(String[] args) {
        validateArgs(args);
        File file = new File(args[0]);
        List<String> words = getWordsFromFile(file);
        Collections.sort(words);
        printWords(words);
    }

    private static void printWords(List<String> words) {
        for (String word : words) {
            System.out.println(word);
        }
    }

    private static List<String> getWordsFromFile(File file) {
        List<String> words = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                words.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file");
            System.exit(2);
        }
        return words;
    }

    private static void validateArgs(String[] args) {
        if (args.length != 1) {
            System.out.println("Wrong number of arguments.");
            System.exit(1);
        }
    }
}
