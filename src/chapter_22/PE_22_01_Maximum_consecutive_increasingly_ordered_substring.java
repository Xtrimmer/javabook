package chapter_22;

import java.util.Scanner;

/**
 * (Maximum consecutive increasingly ordered substring) Write a program that
 * prompts the user to enter a string and displays the maximum consecutive
 * increasingly ordered substring. Analyze the time complexity of your program.
 * Here is a sample run:
 *
 *      Enter a string:abcabcdgabxy
 *      abcdg
 *
 *      Enter a string: abcabcdgabmnsxy
 *      abmnsxy
 */
public class PE_22_01_Maximum_consecutive_increasingly_ordered_substring {
    public static void main(String[] args) {
        String input = promptUserForString();
        String substring = getMaximumConsecutiveIncreasinglyOrderedSubstring(input);
        System.out.println(substring);
    }

    private static int compareStringLength(String string1, String string2) {
        return string1.length() - string2.length();
    }

    private static String getMaximumConsecutiveIncreasinglyOrderedSubstring(String input) {
        String maxLengthString = "";
        int beginIndex = 0;
        for (int endIndex = 1; endIndex <= input.length(); endIndex++) {
            if (endIndex == input.length() || input.charAt(endIndex) <= input.charAt(endIndex - 1)) {
                String substring = input.substring(beginIndex, endIndex);
                if (compareStringLength(maxLengthString, substring) < 0) {
                    maxLengthString = substring;
                }
                beginIndex = endIndex;
            }
        }
        return maxLengthString;
    }

    private static String promptUserForString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        return scanner.nextLine();
    }
}
