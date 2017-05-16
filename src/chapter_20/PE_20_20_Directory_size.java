package chapter_20;

import java.io.File;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

/**
 * (Directory size) Rewrite Programming Exercise 18.28 using a stack instead of
 * a queue.
 */
public class PE_20_20_Directory_size {
    public static void main(String[] args) {
        System.out.print("Enter a directory or a file: ");
        Scanner input = new Scanner(System.in);
        String directory = input.nextLine();
        System.out.println(getSize(new File(directory)) + " bytes");
    }

    private static long getSize(File root) {
        long size = 0;
        Stack<File> fileStack = new Stack<>();
        fileStack.push(root);

        while (!fileStack.isEmpty()) {
            File poppedFile = fileStack.pop();
            if (poppedFile.isFile()) {
                size += poppedFile.length();
            } else {
                File[] files = poppedFile.listFiles();
                if (files != null) Collections.addAll(fileStack, files);
            }
        }
        return size;
    }
}
