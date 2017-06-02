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
 * (Count the occurrences of each keyword) Rewrite Listing 21.7 CountKeywords.
 * java to read in a Java source code file and count the occurrence of each keyword
 * in the file, but don’t count the keyword if it is in a comment or in a string literal.
 */
public class PE_21_10_Count_the_occurrences_of_each_keyword {
    private static final String REGEX_STRING_LITERAL = "\"[^\"\\\\]*(\\\\.[^\"\\\\]*)*\"";
    private static final String REGEX_COMMENT_LINE = "//.*";
    private static final String REGEX_COMMENT_BLOCK = "/\\*[\\s\\S]*\\*/";
    private static final String REGEX_DELIMITERS = "[\\s+\\p{P}]";

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a Java source file: ");
        String filename = input.nextLine();

        String text = getTextFromFilePath(filename);
        text = text.replaceAll(REGEX_STRING_LITERAL, "");
        text = text.replaceAll(REGEX_COMMENT_LINE, "");
        text = text.replaceAll(REGEX_COMMENT_BLOCK, "");
        System.out.println("The number of keywords in " + filename
                + " is " + countKeywords(text));
    }

    public static int countKeywords(String text) {
        String[] keywordString = {"abstract", "assert", "boolean",
                "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "enum",
                "extends", "for", "final", "finally", "float", "goto",
                "if", "implements", "import", "instanceof", "int",
                "interface", "long", "native", "new", "package", "private",
                "protected", "public", "return", "short", "static",
                "strictfp", "super", "switch", "synchronized", "this",
                "throw", "throws", "transient", "try", "void", "volatile",
                "while", "true", "false", "null"};
        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));

        int count = 0;
        String[] words = text.split(REGEX_DELIMITERS);
        for (String word : words) {
            if (keywordSet.contains(word))
                count++;
        }
        return count;
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
