package Chapter_12;

import java.io.File;
import java.util.ArrayList;

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
        ArrayList<File> files = getFilesFromArguments(args);
        renameFiles(files);
    }

    private static void renameFiles(ArrayList<File> files) {
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.indexOf('_') >= 0) {
                if (fileName.matches("Exercise\\d_\\d+\\.\\w+")) {
                    String prefix = "Exercise";
                    String suffix = fileName.substring(8);
                    file.renameTo(new File(prefix + '0' + suffix));
                }
            }
        }
    }

    private static ArrayList<File> getFilesFromArguments(String[] args) {
        ArrayList<File> files = new ArrayList<>();
        for (String arg : args) {
            File file = new File(arg);
            if (file.isFile() && file.canRead() && file.canWrite()) {
                files.add(file);
            }
        }
        return files;
    }
}
