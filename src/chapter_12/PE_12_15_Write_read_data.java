package chapter_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * (Write/read data) Write a program to create a file named Exercise12_15.txt if
 * it does not exist. Write 100 integers created randomly into the file using text
 * I/O. Integers are separated by spaces in the file. Read the data back from the
 * file and display the data in increasing order.
 */
public class PE_12_15_Write_read_data {
    public static void main(String[] args) {
        File file = createFile("resources\\text\\Exercise12_15.txt");
        writeRandomIntegersToFile(file, 100);
        ArrayList<Integer> data = readFile(file);
        displayDataIncreasingOrder(data);
    }

    private static void displayDataIncreasingOrder(ArrayList<Integer> data) {
        Collections.sort(data);
        for (Integer integer : data) {
            System.out.println(integer);
        }
    }

    private static ArrayList<Integer> readFile(File file) {
        ArrayList<Integer> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                data.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        } catch (InputMismatchException e) {
            System.out.println("File contains mis-formatted or corrupt data.");
            System.exit(0);
        }
        return data;
    }

    private static void writeRandomIntegersToFile(File file, int count) {
        Random random = new Random();
        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (int i = 0; i < count - 1; i++) {
                printWriter.print(random.nextInt() + " ");
            }
            printWriter.print(random.nextInt());
        } catch (FileNotFoundException e) {
            System.out.println("The output file cannot be found.");
        }
    }

    public static File createFile(String path) {
        File file = new File(path);
        try {
            if(!file.createNewFile()) {
                System.out.println("File already exists and cannot be created.");
                System.exit(0);
            }
        } catch (IOException e) {
            System.out.println("An I/O error occurred creating the file.");
            System.exit(0);
        }
        return file;
    }
}
