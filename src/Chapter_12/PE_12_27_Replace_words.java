package Chapter_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * (Replace words) Suppose you have a lot of files in a directory that contain
 * words Exercisei_j, where i and j are digits. Write a program that pads a 0
 * before i if i is a single digit and 0 before j if j is a single digit. For example,
 * the word Exercise2_1 in a file will be replaced by Exercise02_01. In Java,
 * when you pass the symbol * from the command line, it refers to all files in
 * the directory (see Supplement III.V). Use the following command to run your
 * program.
 *
 *      java Exercise12_27 *
 */
public class PE_12_27_Replace_words {
    public static void main(String[] args) {
        ArrayList<File> files = getFilesFromArguments(args);
        replaceWordsInFiles(files);
    }

    private static void replaceWordsInFiles(ArrayList<File> files) {
        for (File file : files) {
            StringBuilder text = readFile(file);
            replaceWordsInText(text);
            writeFile(file, text);
        }
    }

    private static void writeFile(File file, StringBuilder text) {
        try (PrintWriter fileOut = new PrintWriter(file)) {
            fileOut.print(text);
        } catch (FileNotFoundException e) {
            System.out.println("destination file is not found");
            System.exit(0);
        }
    }

    private static void replaceWordsInText(StringBuilder text) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                String oldText = "Exercise" + i + "_" + j;
                String newText = "Exercise0" + i + "_0" + j;
                while (text.indexOf(oldText) > 0) {
                    int start = text.indexOf(oldText);
                    int end = start + oldText.length();
                    text.replace(start, end, newText);
                }
            }
        }
    }

    private static StringBuilder readFile(File file) {
        StringBuilder text = new StringBuilder();
        try (Scanner fileIn = new Scanner(file)){
            while (fileIn.hasNext()) {
                text.append(fileIn.nextLine());
                text.append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            System.out.println("source file is not found");
            System.exit(0);
        }
        return text;
    }

    private static ArrayList<File> getFilesFromArguments(String[] args) {
        ArrayList<File> files = new ArrayList<>();
        for (String arg : args) {
            File file = new File(arg);
            if (file.isFile() && file.canRead() && file.canWrite()) {
                files.add(file);
            }
        }
        return files;
    }
}
