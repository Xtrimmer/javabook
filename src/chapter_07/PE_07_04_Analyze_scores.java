package chapter_07;

import java.util.Scanner;

/**
 * (Analyze scores) Write a program that reads an unspecified number of scores and
 * determines how many scores are above or equal to the average and how many
 * scores are below the average. Enter a negative number to signify the end of the
 * input. Assume that the maximum number of scores is 100.
 */
public class PE_07_04_Analyze_scores {
    public static void main(String[] args) {
        final int MAX_NUMBER_OF_SCORES = 100;
        Scanner scanner = new Scanner(System.in);
        int[] scores = new int[MAX_NUMBER_OF_SCORES];
        int sum = 0;
        double average;
        int i;
        int aboveAverage = 0;
        int belowAverage = 0;
        System.out.print("Enter the scores ");
        for (i = 0; i < MAX_NUMBER_OF_SCORES; i++) {
            int score = scanner.nextInt();
            if (score < 0) break;
            scores[i] = score;
            sum += score;
        }
        average = sum / i + 0.0;
        for (int j = 0; j < i; j++) {
            if (scores[j] >= average) aboveAverage++;
            else belowAverage++;
        }
        System.out.println(aboveAverage + " scores are above or equal to the average.");
        System.out.println(belowAverage + " scores are below the average.");
    }
}
