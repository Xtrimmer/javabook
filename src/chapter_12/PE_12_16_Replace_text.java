package chapter_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * (Replace text) Listing 12.16, ReplaceText.java, gives a program that replaces
 * text in a source file and saves the change into a new file. Revise the program to
 * save the change into the original file. For example, invoking
 *
 *      java Exercise12_16 file oldString newString
 *
 * replaces oldString in the source file with newString.
 */
public class PE_12_16_Replace_text {
    public static void main(String[] args) {
        validateArgsCount(args);
        File file = new File(args[0]);
        validateFile(file);
        ArrayList<String> text = readFile(file);
        replaceText(args[1], args[2], text);
        writeFile(file, text);
    }

    private static void replaceText(String oldString, String newString, ArrayList<String> text) {
        for (int i = 0; i < text.size(); i++) {
            String line = text.remove(i);
            line = line.replaceAll(oldString, newString);
            text.add(i, line);
        }
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

    private static void writeFile(File file, ArrayList<String> text) {
        try (PrintWriter fileOut = new PrintWriter(file)) {
            for (String line : text) {
                fileOut.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("destination file is not found");
            System.exit(0);
        }
    }

    private static ArrayList<String> readFile(File file) {
        ArrayList<String> text = new ArrayList<>();
        try (Scanner fileIn = new Scanner(file)){
            while (fileIn.hasNext()) {
                String line = fileIn.nextLine();
                text.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("source file is not found");
            System.exit(0);
        }
        return text;
    }

    private static void validateArgsCount(String[] args) {
        if (args.length != 3) {
            System.out.println("Invalid parameter count");
            System.exit(0);
        }
    }
}
