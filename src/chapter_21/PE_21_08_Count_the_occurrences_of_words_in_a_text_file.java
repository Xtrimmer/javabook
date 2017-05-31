package chapter_21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * (Count the occurrences of words in a text file) Rewrite Listing 21.9 to read the
 * text from a text file. The text file is passed as a command-line argument. Words
 * are delimited by whitespace characters, punctuation marks (,;.:?), quotation
 * marks ('"), and parentheses. Count words in case-insensitive fashion (e.g., consider
 * Good and good to be the same word). The words must start with a letter.
 * Display the output in alphabetical order of words, with each word preceded by
 * its occurrence count.
 */
public class PE_21_08_Count_the_occurrences_of_words_in_a_text_file {
    public static void main(String[] args) {
        validateArgs(args, 1);
        String text = getTextFromFilePath(args[0]);
        Map<String, Integer> map = new TreeMap<>();

        String[] words = text.split("[\\s+\\p{P}]");
        for (String word : words) {
            String key = word.toLowerCase();

            if (key.matches("[a-z].*")) {
                if (!map.containsKey(key)) {
                    map.put(key, 1);
                } else {
                    int value = map.get(key);
                    value++;
                    map.put(key, value);
                }
            }
        }
        map.forEach((k, v) -> System.out.println(k + "\t" + v));
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

    private static void validateArgs(String[] args, int expectedLength) {
        if (args.length != expectedLength) {
            System.out.println("Wrong number of arguments.");
            System.exit(1);
        }
    }
}
