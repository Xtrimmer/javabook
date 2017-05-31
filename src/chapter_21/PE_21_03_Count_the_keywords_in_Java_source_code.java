package chapter_21;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * (Count the keywords in Java source code) Revise the program in Listing 21.7. If
 * a keyword is in a comment or in a string, don’t count it. Pass the Java file name
 * from the command line. Assume that the Java source code is correct and line
 * comments and paragraph comments do not overlap.
 */
public class PE_21_03_Count_the_keywords_in_Java_source_code {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a Java source file: ");
        String filename = input.nextLine();

        File file = new File(filename);
        if (file.exists()) {
            System.out.println("The number of keywords in " + filename
                    + " is " + countKeywords(file));
        } else {
            System.out.println("File " + filename + " does not exist");
        }
    }

    public static int countKeywords(File file) throws Exception {
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

        Set<String> keywordSet =
                new HashSet<>(Arrays.asList(keywordString));
        int count = 0;

        Scanner input = new Scanner(file);

        while (input.hasNext()) {
            String word = input.next();
            if (word.contains("//")) {
                word = input.nextLine();
            } else if (word.contains("\"")) {
                word = word.replaceFirst("\"", "");
                while (input.hasNext() && !word.contains("\"")) {
                    word = input.next();
                }
            } else if (word.contains("/*")) {
                while (input.hasNext() && !word.contains("*/")) {
                    word = input.next();
                }
            } else if (keywordSet.contains(word))
                count++;
        }

        return count;
    }
}
