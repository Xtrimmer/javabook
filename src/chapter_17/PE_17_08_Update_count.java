package chapter_17;

import java.io.*;

/**
 * (Update count) Suppose you wish to track how many times a program has been
 * executed. You can store an int to count the file. Increase the count by 1 each
 * time this program is executed. Let the program be Exercise17_08 and store the
 * count in Exercise17_08.dat.
 */
public class PE_17_08_Update_count {
    public static void main(String[] args) {
        File file = new File("resources/data/Exercise17_08.dat");
        if (!file.exists()) {
            initializeDataFile(file);
        }
        int count = readIntFromFile(file);
        writeIntToFile(++count, file);
        System.out.println("This program has been ran " + count + " times.");
    }

    private static void initializeDataFile(File file) {
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file))) {
            outputStream.writeInt(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int readIntFromFile(File file) {
        int count = 0;
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(file))) {
            count = inputStream.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    private static void writeIntToFile(int value, File file) {
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file))) {
            outputStream.writeInt(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
