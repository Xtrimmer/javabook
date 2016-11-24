package chapter_07;

import java.util.Scanner;

/**
 * (Count occurrence of numbers) Write a program that reads the integers between 1
 * and 100 and counts the occurrences of each. Assume the input ends with 0. Here
 * is a sample run of the program:
 *
 *      Enter the integers between 1 and 100: 2 5 6 5 4 3 23 43 2 0
 *      2 occurs 2 times
 *      3 occurs 1 time
 *      4 occurs 1 time
 *      5 occurs 2 times
 *      6 occurs 1 time
 *      23 occurs 1 time
 *      43 occurs 1 time
 *
 * Note that if a number occurs more than one time, the plural word “times” is used
 * in the output.
 */
public class PE_07_03_Count_occurrence_of_numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] counts = new int[100];
        System.out.print("Enter the integers between 1 and 100: ");
        while (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            if (number == 0) break;
            counts[number - 1]++;
        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                String out = (i + 1) + " occurs " + counts[i] + " time";
                if (counts[i] > 1) out += "s";
                System.out.println(out);
            }
        }
    }
}
