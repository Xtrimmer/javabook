package chapter_17;

import java.io.*;
import java.util.Scanner;

/**
 * (Encrypt files) Encode the file by adding 5 to every byte in the file. Write a program
 * that prompts the user to enter an input file name and an output file name and
 * saves the encrypted version of the input file to the output file.
 */
public class PE_17_14_Encrypt_files {
    private static File inputFile;
    private static File outputFile;

    public static void main(String[] args) {
        promptUserForFileNames();
        encryptFiles();
    }

    private static void encryptFiles() {
        try (
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile))
        ) {
            int value;
            while ((value = inputStream.read()) != -1) outputStream.write(value + 5);
        } catch (FileNotFoundException e) {
            System.out.println("The input file cannot be found");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void promptUserForFileNames() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the name of the file to encrypt: ");
        inputFile = new File(scanner.nextLine());
        System.out.println("Enter the output file name: ");
        outputFile = new File(scanner.nextLine());
    }
}
