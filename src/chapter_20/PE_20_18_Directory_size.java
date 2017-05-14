package chapter_20;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * (Directory size) Listing 20.7, DirectorySize.java, gives a recursive method for
 * finding a directory size. Rewrite this method without using recursion. Your
 * program should use a queue to store the subdirectories under a directory. The
 * algorithm can be described as follows:
 *
 *   long getSize(File directory) {
 *     long size = 0;
 *     add directory to the queue;
 *
 *     while (queue is not empty) {
 *       Remove an item from the queue into t;
 *       if (t is a file)
 *         size += t.length();
 *       else
 *         add all the files and subdirectories under t into the queue;
 *     }
 *
 *     return size;
 *   }
 */
public class PE_20_18_Directory_size {
    public static void main(String[] args) {
        System.out.print("Enter a directory or a file: ");
        Scanner input = new Scanner(System.in);
        String directory = input.nextLine();
        System.out.println(getSize(new File(directory)) + " bytes");
    }

    private static long getSize(File root) {
        long size = 0;
        List<File> fileQueue = new LinkedList<>();
        fileQueue.add(root);

        while (!fileQueue.isEmpty()) {
            File polledFile = fileQueue.remove(0);
            if (polledFile.isFile()) {
                size += polledFile.length();
            } else {
                File[] files = polledFile.listFiles();
                if (files != null) Collections.addAll(fileQueue, files);
            }
        }
        return size;
    }
}
