package chapter_22;

import java.util.Scanner;

/**
 * (Pattern matching) Write a program that prompts the user to enter two strings
 * and tests whether the second string is a substring of the first string. Suppose
 * the neighboring characters in the string are distinct. (Don’t use the indexOf
 * method in the String class.) Analyze the time complexity of your algorithm.
 * Your algorithm needs to be at least O(n) time. Here is a sample run of the
 * program:
 *
 *      Enter a string s1: Welcome to Java (Enter)
 *      Enter a string s2: come (Enter)
 *      matched at index 3
 */
public class PE_22_03_Pattern_matching {
    public static void main(String[] args) {
        String str1 = promptUserForString("s1");
        String str2 = promptUserForString("s2");
        int index = matchPattern(str1, str2);
        System.out.println(index >= 0 ? "matched at index " + index : "no match");
    }

    private static boolean isCompleteMatch(String string, String pattern, int i) {
        for (int j = 1; j < pattern.length(); j++) {
            if (!(string.charAt(i + j) == pattern.charAt(j))) return false;
        }
        return true;
    }

    private static boolean isInvalidArguments(String... elements) {
        for (String string : elements) {
            if (string == null || string.length() == 0) return true;
        }
        return false;
    }

    private static int matchPattern(String string, String pattern) {
        if (isInvalidArguments(string, pattern)) return -1;
        for (int i = 0; i <= string.length() - pattern.length(); i++) {
            if (string.charAt(i) == pattern.charAt(0)) {
                if (isCompleteMatch(string, pattern, i)) return i;
            }
        }
        return -1;
    }

    private static String promptUserForString(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string " + message + ": ");
        return scanner.nextLine();
    }
}
