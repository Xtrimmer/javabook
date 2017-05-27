package chapter_21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * (Display nonduplicate words in ascending order) Write a program that reads
 * words from a text file and displays all the nonduplicate words in ascending order.
 * The text file is passed as a command-line argument.
 */
public class PE_21_02_Display_nonduplicate_words_in_ascending_order {

    private static final String PUNCTUATION = "[!\"#$%&'()*+,\\-./:;<=>?@\\[\\\\\\]^_`{|}~]";
    private static final String WHITESPACE = "\\s";

    public static void main(String[] args) {
        String filePath = args[0];
        validateArgs(args);
        String text = getTextFromFilePath(filePath);
        text = text.replaceAll(PUNCTUATION, "");
        text = text.toLowerCase();
        String[] words = text.split(WHITESPACE);
        TreeSet<String> wordSet = new TreeSet<>(Arrays.asList(words));
        wordSet.remove("");
        wordSet.forEach(System.out::println);
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

    private static void validateArgs(String[] args) {
        if (args.length != 1) {
            System.out.println("Wrong number of arguments.");
            System.exit(1);
        }
    }
}
