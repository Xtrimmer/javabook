package chapter_18;

import java.io.File;
import java.util.Scanner;

/**
 * (Find words) Write a program that finds all occurrences of a word in all the files
 * under a directory, recursively. Pass the parameters from the command line as
 * follows:
 *
 *      java PE_18_30_Find_words dirName word
 */
public class PE_18_30_Find_words {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java PE_18_30_Find_words directoryName word");
            System.exit(1);
        }
        findInFile(new File(args[0]), args[1]);
    }

    private static void findInFile(File file, String word) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                findInFile(f, word);
            }
        } else {
            findWord(file, word);
        }
    }

    private static void findWord(File file, String word) {
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String line = input.nextLine();
                if (line.contains(word)) {
                    System.out.println(file + ": " + line);
                }
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
