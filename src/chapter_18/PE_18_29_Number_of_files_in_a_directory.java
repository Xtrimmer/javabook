package chapter_18;

import java.io.File;
import java.util.Scanner;

/**
 * (Number of files in a directory) Write a program that prompts the user to enter a
 * directory and displays the number of the files in the directory.
 */
public class PE_18_29_Number_of_files_in_a_directory {
    public static void main(String[] args) {
        // Prompt the user to enter a directory or a file
        System.out.print("Enter a directory or a file: ");
        Scanner input = new Scanner(System.in);
        String directory = input.nextLine();

        // Display the size
        System.out.println(getCount(new File(directory)) + " files");
    }

    private static long getCount(File file) {
        long count = 0; // Store the total count of all files

        if (file.isDirectory()) {
            File[] subfiles = file.listFiles(); // All files and subdirectories
            for (File subfile : subfiles) {
                count += getCount(subfile); // Recursive call
            }
        } else { // Base case
            count++;
        }
        return count;
    }
}
