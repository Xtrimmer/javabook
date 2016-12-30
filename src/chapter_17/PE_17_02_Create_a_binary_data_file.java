package chapter_17;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * (Create a binary data file) Write a program to create a file named
 * Exercise17_02.dat if it does not exist. Append new data to it if it already exists.
 * Write 100 integers created randomly into the file using binary I/O.
 */
public class PE_17_02_Create_a_binary_data_file {
    public static void main(String[] args) {
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("Exercise17_02.dat", true))) {
            for (int i = 0; i < 100; i++) {
                outputStream.writeInt((int) (Math.random() * 100));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
