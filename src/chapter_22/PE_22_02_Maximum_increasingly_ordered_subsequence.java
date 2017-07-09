package chapter_22;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * (Maximum increasingly ordered subsequence) Write a program that prompts
 * the user to enter a string and displays the maximum increasingly ordered subsequence
 * of characters. Analyze the time complexity of your program. Here is
 * a sample run:
 *
 *      Enter a string: Welcome (Enter)
 *      Welo
 */
public class PE_22_02_Maximum_increasingly_ordered_subsequence {
    public static void main(String[] args) {
        String input = promptUserForString();
        String maxSubstring = getMaximumIncreasinglyOrderedSubsequence(input);
        System.out.println(maxSubstring);
    }

    private static void appendCharToEligibleSubsequences(List<StringBuilder> subsequences, char character) {
        for (StringBuilder subsequence : subsequences) {
            if (character > subsequence.charAt(subsequence.length() - 1)) {
                subsequence.append(character);
            }
        }
    }

    private static List<StringBuilder> getAllIncreasinglyOrderedSubsequences(String input) {
        List<StringBuilder> subsequences = new ArrayList<>();
        subsequences.add(new StringBuilder(input.charAt(0) + ""));
        for (int i = 1; i < input.length(); i++) {
            char character = input.charAt(i);
            if (character > input.charAt(i - 1)) {
                appendCharToEligibleSubsequences(subsequences, character);
            } else if (character < input.charAt(i - 1)) {
                subsequences.add(new StringBuilder(character + ""));
            }
        }
        return subsequences;
    }

    private static String getFirstMaxLengthString(List<StringBuilder> subsequences) {
        String maxSubsequence = "";
        for (StringBuilder subsequence : subsequences) {
            if (maxSubsequence.length() < subsequence.length()) {
                maxSubsequence = subsequence.toString();
            }
        }
        return maxSubsequence;
    }

    private static String getMaximumIncreasinglyOrderedSubsequence(String input) {
        List<StringBuilder> subsequences = getAllIncreasinglyOrderedSubsequences(input);
        return getFirstMaxLengthString(subsequences);
    }

    private static String promptUserForString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        return scanner.nextLine();
    }
}
