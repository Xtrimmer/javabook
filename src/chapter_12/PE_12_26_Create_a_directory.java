package chapter_12;

import java.io.File;
import java.util.Scanner;

/**
 * (Create a directory) Write a program that prompts the user to enter a directory
 * name and creates a directory using the File’s mkdirs method. The program
 * displays the message “Directory created successfully” if a directory is created
 * or “Directory already exists” if the directory already exists.
 */
public class PE_12_26_Create_a_directory {
    public static void main(String[] args) {
        String directory = promptStringValue("Enter a directory name: ");
        createDirectory(directory);
    }

    private static void createDirectory(String directory) {
        File file = new File(directory);
        if (file.exists()) {
            System.out.println("Directory already exists");
        } else if (file.mkdir()) {
            System.out.println("Directory created successfully");
        } else {
            System.out.println("The program encountered a problem creating the directory.");
        }
    }

    private static String promptStringValue(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
