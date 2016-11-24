package chapter_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * (Reformat Java source code) Write a program that converts the Java source
 * code from the next-line brace style to the end-of-line brace style. For example,
 * the following Java source in (a) uses the next-line brace style. Your program
 * converts it to the end-of-line brace style in (b).
 *
 *      (a) Next-line brace style
 *      public class Test
 *      {
 *          public static void main(String[] args)
 *          {
 *              // Some statements
 *          }
 *      }
 *
 *      (b) End-of-line brace style
 *      public class Test {
 *          public static void main(String[] args) {
 *              // Some statements
 *          }
 *      }
 *
 * Your program can be invoked from the command line with the Java sourcecode
 * file as the argument. It converts the Java source code to a new format. For
 * example, the following command converts the Java source-code file Test.java
 * to the end-of-line brace style.
 *
 *      java Exercise12_12 Test.java
 */
public class PE_12_12_Reformat_Java_source_code {
    public static void main(String[] args) {
        validateArgsCount(args);
        File file = new File(args[0]);
        validateFile(file);
        StringBuilder text = readFile(file);
        reformatSourceCode(text);
        writeFile(file, text);
    }

    private static void reformatSourceCode(StringBuilder text) {
        int lastChar = text.length() - 1;
        for (int startIndex = 0; startIndex < text.length(); startIndex++) {
            if (Character.isWhitespace(text.charAt(startIndex))
                    && startIndex < lastChar) {
                int endIndex = startIndex + 1;
                while (Character.isWhitespace(text.charAt(endIndex))
                        && endIndex < lastChar) {
                    endIndex++;
                }
                if (text.charAt(endIndex) == '{') {
                    endIndex++;
                    while (text.charAt(endIndex - 1) != '\n'
                            && endIndex < lastChar) {
                        endIndex++;
                    }
                    text.replace(startIndex, endIndex, " {\n");
                    startIndex += 2;
                }
            }
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

    private static void writeFile(File file, StringBuilder text) {
        try (PrintWriter fileOut = new PrintWriter(file)) {
            fileOut.print(text);
        } catch (FileNotFoundException e) {
            System.out.println("destination file is not found");
            System.exit(0);
        }
    }

    private static StringBuilder readFile(File file) {
        StringBuilder text = new StringBuilder();
        try (Scanner fileIn = new Scanner(file)) {
            while (fileIn.hasNext()) {
                text.append(fileIn.nextLine());
                if (fileIn.hasNext()) text.append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("source file is not found");
            System.exit(0);
        }
        return text;
    }

    private static void validateArgsCount(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid parameter count");
            System.exit(0);
        }
    }
}