package chapter_18;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * (Replace words) Write a program that replaces all occurrences of a word with a
 * new word in all the files under a directory, recursively. Pass the parameters from
 * the command line as follows:
 *
 *      java PE_18_31_Replace_words dirName oldWord newWord
 */
public class PE_18_31_Replace_words {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java PE_18_31_Replace_words dirName oldWord newWord");
            System.exit(0);
        }
        replaceInFile(new File(args[0]), args[1], args[2]);
    }

    private static String readFile(File file) {
        StringBuilder text = new StringBuilder();
        try (Scanner fileIn = new Scanner(file)) {
            while (fileIn.hasNext()) {
                text.append(fileIn.nextLine());
                text.append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            System.out.println("source file is not found");
            System.exit(0);
        }
        return text.toString();
    }

    private static void replaceInFile(File file, String oldWord, String newWord) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                replaceInFile(f, oldWord, newWord);
            }
        } else {
            replaceWordsInFile(file, oldWord, newWord);
        }
    }

    private static void replaceWordsInFile(File file, String oldWord, String newWord) {
        String text = readFile(file);
        text = text.replaceAll(oldWord, newWord);
        writeFile(file, text);
    }

    private static void writeFile(File file, String text) {
        try (PrintWriter fileOut = new PrintWriter(file)) {
            fileOut.print(text);
        } catch (FileNotFoundException e) {
            System.out.println("destination file is not found");
            System.exit(0);
        }
    }
}
