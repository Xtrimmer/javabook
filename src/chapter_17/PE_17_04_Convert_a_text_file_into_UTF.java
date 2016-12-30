package chapter_17;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * (Convert a text file into UTF) Write a program that reads lines of characters from
 * a text file and writes each line as a UTF-8 string into a binary file. Display the
 * sizes of the text file and the binary file. Use the following command to run the
 * program:
 * <p>
 * java Exercise17_04 Welcome.java Welcome.utf
 */
public class PE_17_04_Convert_a_text_file_into_UTF {
    public static void main(String[] args) {
        validateArguments(args);
        File sourceFile = new File(args[0]);
        verifyFileExists(sourceFile);
        File targetFile = new File(args[1]);

        try (
                Scanner scanner = new Scanner(sourceFile);
                DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(targetFile))
        ) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                outputStream.writeUTF(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Input  Size: " + sourceFile.length());
        System.out.println("Output Size: " + targetFile.length());
    }

    private static void verifyFileExists(File file) {
        if (!file.exists()) {
            System.out.println("Source file " + file.getName() + " does not exist");
            System.exit(2);
        }
    }

    private static void validateArguments(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Copy sourceFile targetFile");
            System.exit(1);
        }
    }
}
