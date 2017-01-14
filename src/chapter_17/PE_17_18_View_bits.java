package chapter_17;

import java.io.*;
import java.util.Scanner;

/**
 * (View bits) Write the following method that displays the bit representation for the
 * last byte in an integer:
 *
 *      public static String getBits(int value)
 *
 * For a hint, see Programming Exercise 5.44. Write a program that prompts the
 * user to enter a file name, reads bytes from the file, and displays each byte’s binary
 * representation.
 */
public class PE_17_18_View_bits {
    private static File file;

    public static void main(String[] args) {
        promptUserForFileName();
        printBytesInFile();
    }

    public static String getBits(int value) {
        int mask = 0b1;
        String binaryString = "";
        for (int i = 0; i < 8; i++) {
            int bit = value & mask;
            binaryString = bit + binaryString;
            value = value >> 1;
        }
        return binaryString;
    }

    private static void printBytesInFile() {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            int value;
            while ((value = inputStream.read()) != -1) {
                System.out.print(getBits(value));
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file " + file.getName() + " cannot be found");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("There was a problem reading the file " + file.getName());
            System.exit(2);
        }
    }

    private static void promptUserForFileName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of a file: ");
        file = new File(scanner.nextLine());
    }
}
