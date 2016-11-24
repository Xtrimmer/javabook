package chapter_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * (Remove text) Write a program that removes all the occurrences of a specified
 * string from a text file. For example, invoking
 *
 *      java Exercise12_11 John filename
 *
 * removes the string John from the specified file. Your program should get the
 * arguments from the command line.
 */
public class PE_12_11_Remove_text {
    public static void main(String[] args) {
        validateArgsCount(args);
        File file = new File(args[1]);
        validateFile(file);
        ArrayList<StringBuilder> text = readFile(file);
        removeText(text, args[0]);
        writeFile(file, text);
    }

    private static void validateFile(File file) {
        validateFileExists(file);
        validateFileIsFile(file);
        validateFileIsReadable(file);
        validateFileIsWritable(file);
    }

    private static void validateFileIsWritable(File file) {
        if (!file.canWrite()) {
            System.out.println("The application cannot modify the file denoted by this pathname.");
            System.exit(0);
        }
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

    private static void writeFile(File file, ArrayList<StringBuilder> text) {
        try (PrintWriter fileOut = new PrintWriter(file)) {
            for (StringBuilder line : text) {
                fileOut.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("destination file is not found");
            System.exit(0);
        }
    }

    private static void removeText(ArrayList<StringBuilder> text, String s) {
        for (StringBuilder line : text) {
            while (line.indexOf(s) >= 0) {
                line.delete(line.indexOf(s), line.indexOf(s) + s.length());
            }
        }
    }

    private static ArrayList<StringBuilder> readFile(File file) {
        ArrayList<StringBuilder> text = new ArrayList<>();
        try (Scanner fileIn = new Scanner(file)){
            while (fileIn.hasNext()) {
                StringBuilder line = new StringBuilder(fileIn.nextLine());
                text.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("source file is not found");
            System.exit(0);
        }
        return text;
    }

    private static void validateArgsCount(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid parameter count");
            System.exit(0);
        }
    }
}
