package chapter_18;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * (Nonrecursive directory size) Rewrite Listing 18.7, DirectorySize.java, without
 * using recursion.
 */
public class PE_18_28_Nonrecursive_directory_size {
    public static void main(String[] args) {
        // Prompt the user to enter a directory or a file
        System.out.print("Enter a directory or a file: ");
        Scanner input = new Scanner(System.in);
        String directory = input.nextLine();

        // Display the size
        System.out.println(getSize(new File(directory)) + " bytes");
    }

    private static long getSize(File file) {
        long size = 0;
        List<File> directories = new ArrayList<>();
        if (file.isFile()) return file.length();
        else if (file.isDirectory()) directories.add(file);
        else {
            System.out.println("Not a file nor directory!");
            System.exit(0);
        }
        while (!directories.isEmpty()) {
            File directory = directories.get(directories.size() - 1);
            File[] subFiles = directory.listFiles();
            for (File subfile : subFiles) {
                if (subfile.isDirectory()) directories.add(subfile);
                else size += subfile.length();
            }

            directories.remove(directory);
        }
        return size;
    }
}
