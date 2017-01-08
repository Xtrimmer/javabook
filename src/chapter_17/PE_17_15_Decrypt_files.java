package chapter_17;

import java.io.*;
import java.util.Scanner;

/**
 * (Decrypt files) Suppose a file is encrypted using the scheme in Programming
 * Exercise 17.14. Write a program to decode an encrypted file. Your program
 * should prompt the user to enter an input file name for the encrypted file and an
 * output file name for the unencrypted version of the input file.
 */
public class PE_17_15_Decrypt_files {
    private static File inputFile;
    private static File outputFile;

    public static void main(String[] args) {
        promptUserForFileNames();
        decryptFiles();
    }

    private static void decryptFiles() {
        try (
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile))
        ) {
            int value;
            while ((value = inputStream.read()) != -1) outputStream.write(value - 5);
        } catch (FileNotFoundException e) {
            System.out.println("The input file cannot be found");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void promptUserForFileNames() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the name of the file to decrypt: ");
        inputFile = new File(scanner.nextLine());
        System.out.println("Enter the output file name: ");
        outputFile = new File(scanner.nextLine());
    }
}
