package chapter_17;

import java.io.*;

/**
 * (Create a text file) Write a program to create a file named Exercise17_01.txt if
 * it does not exist. Append new data to it if it already exists. Write 100 integers
 * created randomly into the file using text I/O. Integers are separated by a space.
 */
public class PE_17_01_Create_a_text_file {
    public static void main(String[] args) {
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream("Exercise17_01.txt", true))) {
            for (int i = 0; i < 100; i++) {
                printWriter.append((int) (Math.random() * 100) + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
