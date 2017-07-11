package chapter_22;

import java.util.Scanner;

/**
 * (Same-number subsequence) Write an O(n) program that prompts the user to
 * enter a sequence of integers ending with 0 and finds the longest subsequence
 * with the same number. Here is a sample run of the program:
 *
 *      Enter a series of numbers ending with 0:
 *      2 4 4 8 8 8 8 2 4 4 0 (Enter)
 *      The longest same number sequence starts at index 3 with 4 values of 8
 */
public class PE_22_05_Same_number_subsequence {
    private static final Scanner scanner = new Scanner(System.in);
    private static int index = 0;

    public static void main(String[] args) {
        System.out.println("Enter a series of numbers ending with 0:");
        SequenceData maxSequenceData = new SequenceData();
        SequenceData currentSequenceData = new SequenceData(scanner.nextInt(), index++, 1);
        int currentNumber;
        while ((currentNumber = scanner.nextInt()) != 0) {
            if (currentNumber == currentSequenceData.number) {
                currentSequenceData.count++;
            } else {
                currentSequenceData = new SequenceData(currentNumber, index, 1);
            }
            if (maxSequenceData.count < currentSequenceData.count) {
                maxSequenceData = currentSequenceData;
            }
            index++;
        }
        System.out.printf("The longest same number sequence starts at index %d with %d values of %d%n",
                maxSequenceData.index, maxSequenceData.count, maxSequenceData.number);


    }

    static class SequenceData {
        public int number = 0;
        public int index = 0;
        public int count = 0;

        public SequenceData(int number, int index, int count) {
            this.number = number;
            this.index = index;
            this.count = count;
        }

        public SequenceData() {
        }
    }
}
