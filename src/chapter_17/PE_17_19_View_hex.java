package chapter_17;

import java.io.*;
import java.util.Scanner;

/**
 * (View hex) Write a program that prompts the user to enter a file name, reads bytes
 * from the file, and displays each byte’s hex representation. (Hint: You can first
 * convert the byte value into an 8-bit string, then convert the bit string into a two digit
 * hex string.)
 */
public class PE_17_19_View_hex {
    private static File file;

    public static void main(String[] args) {
        promptUserForFileName();
        printHexInFile();
    }

    public static String getHex(int value) {
        return String.format("%02X", value);
    }

    private static void printHexInFile() {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            int value;
            while ((value = inputStream.read()) != -1) {
                System.out.print(getHex(value));
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
