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
        String hexString = "";
        do {
            int remainder = value % 16;
            switch (remainder) {
                case 0:
                case 1:
                case 2:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    hexString = remainder + hexString;
                    break;
                case 10:
                    hexString = 'A' + hexString;
                    break;
                case 11:
                    hexString = 'B' + hexString;
                    break;
                case 12:
                    hexString = 'C' + hexString;
                    break;
                case 13:
                    hexString = 'D' + hexString;
                    break;
                case 14:
                    hexString = 'E' + hexString;
                    break;
                case 15:
                    hexString = 'F' + hexString;
                    break;
            }
        } while ((value /= 16) > 0);
        return hexString;
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
