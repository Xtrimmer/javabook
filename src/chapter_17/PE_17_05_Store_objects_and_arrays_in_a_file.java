package chapter_17;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * (Store objects and arrays in a file) Write a program that stores an array of the five
 * int values 1, 2, 3, 4, and 5, a Date object for the current time, and the double
 * value 5.5 into the file named Exercise17_05.dat.
 */
public class PE_17_05_Store_objects_and_arrays_in_a_file {
    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5};
        Date date = new Date();
        double value = 5.5;

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("resources/data/Exercise17_05.dat"))) {
            outputStream.writeObject(ints);
            outputStream.writeObject(date);
            outputStream.writeDouble(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
