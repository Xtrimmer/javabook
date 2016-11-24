package chapter_12;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * (Count words) Write a program that counts the number of words in President
 * Abraham Lincoln’s Gettysburg address from http://cs.armstrong.edu/liang/data/
 * Lincoln.txt.
 */
public class PE_12_19_Count_words {
    public static void main(String[] args) {
        URL url = getURL("http://cs.armstrong.edu/liang/data/Lincoln.txt");
        StringBuilder text = getTextFromURL(url);
        int wordcount = getWordCount(text);
        System.out.println("The wordcount is: " + wordcount);
    }

    private static int getWordCount(StringBuilder text) {
        String[] words = text.toString().split("\\s+");
        return words.length;
    }

    private static StringBuilder getTextFromURL(URL url) {
        StringBuilder text = new StringBuilder();
        try (Scanner scanner = new Scanner(url.openStream())) {
            while (scanner.hasNext()) {
                text.append(scanner.nextLine());
                text.append('\n');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return text;
    }

    private static URL getURL(String s) {
        try {
            return new URL(s);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
