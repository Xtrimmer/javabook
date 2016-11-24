package chapter_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * (Replace text) Revise Programming Exercise 12.16 to replace a string in a file
 * with a new string for all files in the specified directory using the command:
 *
 *      java Exercise12_22 dir oldString newString
 */
public class PE_12_22_Replace_text {
    public static void main(String[] args) {
        validateArgsCount(args, 3);
        File directory = getDirectory(args[0]);
        ArrayList<File> files = getFilesInDirectory(directory);
        replaceTextInFiles(files, args[1], args[2]);
    }

    private static void replaceTextInFiles(ArrayList<File> files, String oldText, String newText) {
        for (File file : files) {
            ArrayList<String> text = readFile(file);
            replaceText(oldText, newText, text);
            writeFile(file, text);
        }
    }

    private static ArrayList<File> getFilesInDirectory(File directory) {
        File[] listing = directory.listFiles();
        ArrayList<File> files = new ArrayList<>();
        if (listing != null) {
            for (File file : listing) {
                if (file.isFile() && file.canRead() && file.canWrite()) {
                    files.add(file);
                }
            }
        }
        return files;
    }

    private static void replaceText(String oldString, String newString, ArrayList<String> text) {
        for (int i = 0; i < text.size(); i++) {
            String line = text.remove(i);
            line = line.replaceAll(oldString, newString);
            text.add(i, line);
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

    private static File getDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            System.out.println("Invalid directory.");
            System.exit(0);
        }
        return directory;
    }

    private static void validateArgsCount(String[] args, int argCount) {
        if (args.length != argCount) {
            System.out.println("Invalid parameter count");
            System.exit(0);
        }
    }
}
