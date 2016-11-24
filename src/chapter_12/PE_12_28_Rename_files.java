package chapter_12;

import java.io.File;

/**
 * (Rename files) Suppose you have a lot of files in a directory named Exercisei_j,
 * where i and j are digits. Write a program that pads a 0 before i if i is a single
 * digit. For example, a file named Exercise2_1 in a directory will be renamed to
 * Exercise02_1. In Java, when you pass the symbol * from the command line,
 * it refers to all files in the directory (see Supplement III.V). Use the following
 * command to run your program.
 *
 *      java Exercise12_28 *
 */
public class PE_12_28_Rename_files {
    public static void main(String[] args) {
        renameFiles(args);
    }

    private static void renameFiles(String[] fileNames) {
        for (String fileName : fileNames) {
            if (fileName.matches("Exercise\\d_\\d+\\.\\w+")) {
                StringBuilder stringBuilder = new StringBuilder(fileName);
                stringBuilder.insert(fileName.indexOf('_') - 1, "0");
                new File(fileName).renameTo(new File(stringBuilder.toString()));
            }
        }
    }
}

