package chapter_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * (Process scores in a text file) Suppose that a text file contains an unspecified
 * number of scores separated by blanks. Write a program that prompts the user
 * to enter the file, reads the scores from the file, and displays their total and
 * average.
 */
public class PE_12_14_Process_scores_in_a_text_file {
    public static void main(String[] args) {
        String fileName = promptStringValue("Enter the file: ");
        File file = new File(fileName);
        validateFile(file);
        ArrayList<Double> scores = getScores(file);
        System.out.println("Total: " + calculateSum(scores));
        System.out.println("Average: " + calculateAverage(scores));
}

    private static double calculateAverage(ArrayList<Double> scores) {
        int count = 0;
        for (Double score : scores) {
            count++;
        }
        return calculateSum(scores) / count;
    }

    private static double calculateSum(ArrayList<Double> scores) {
        double sum = 0;
        for (Double score : scores) {
            sum += score;
        }
        return sum;
    }

    private static String promptStringValue(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static ArrayList<Double> getScores(File file) {
        ArrayList<Double> scores = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                scores.add(scanner.nextDouble());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        } catch (InputMismatchException e) {
            System.out.println("File contains mis-formatted or corrupt data.");
            System.exit(0);
        }
        return scores;
    }

    private static void validateFile(File file) {
        validateFileExists(file);
        validateFileIsFile(file);
        validateFileIsReadable(file);
    }

    private static void validateFileIsReadable(File file) {
        if (!file.canRead()) {
            System.out.println("The application cannot read the file denoted by this pathname.");
            System.exit(0);
        }
    }

    private static void validateFileIsFile(File file) {
        if (!file.isFile()) {
            System.out.println("The file denoted by this pathname is not a normal file.");
            System.exit(0);
        }
    }

    private static void validateFileExists(File file) {
        if (!file.exists()) {
            System.out.println("The file denoted by this pathname does not exist.");
            System.exit(0);
        }
    }
}
