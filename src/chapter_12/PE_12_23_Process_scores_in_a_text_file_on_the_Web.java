package chapter_12;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * (Process scores in a text file on the Web) Suppose that the text file on the
 * Web http://cs.armstrong.edu/liang/data/Scores.txt contains an unspecified number
 * of scores. Write a program that reads the scores from the file and displays their
 * total and average. Scores are separated by blanks.
 */
public class PE_12_23_Process_scores_in_a_text_file_on_the_Web {
    public static void main(String[] args) {
        URL url = getURL("http://cs.armstrong.edu/liang/data/Scores.txt");
        ArrayList<Double> scores = getScores(url);
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

    private static ArrayList<Double> getScores(URL url) {
        ArrayList<Double> scores = new ArrayList<>();
        try (Scanner scanner = new Scanner(url.openStream())) {
            while (scanner.hasNext()) {
                scores.add(scanner.nextDouble());
            }
        } catch (IOException | InputMismatchException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return scores;
    }

    private static URL getURL(String s) {
        try {
            return new URL(s);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
