package chapter_17;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * (Sum all the integers in a binary data file) Suppose a binary data file named
 * Exercise17_03.dat has been created and its data are created using
 * writeInt(int) in DataOutputStream. The file contains an unspecified
 * number of integers. Write a program to find the sum of the integers.
 */
public class PE_17_03_Sum_all_the_integers_in_a_binary_data_file {
    public static void main(String[] args) {
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream("resources/data/Exercise17_03.dat"))) {
            int data;
            int sum = 0;
            while ((data = inputStream.read()) != -1) {
                sum += data;
            }
            System.out.println("The sum is: " + sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
