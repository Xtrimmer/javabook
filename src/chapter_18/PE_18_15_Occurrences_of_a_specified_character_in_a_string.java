package chapter_18;

import java.util.Scanner;

/**
 * (Occurrences of a specified character in a string) Rewrite Programming Exercise
 * 18.10 using a helper method to pass the substring high index to the method.
 * The helper method header is:
 *
 *      public static int count(String str, char a, int high)
 */
public class PE_18_15_Occurrences_of_a_specified_character_in_a_string {
    private static int count = 0;

    public static void main(String[] args) {
        String string = promptUserForString();
        char ch = promptUserForCharacter();
        System.out.println("Number of occurrences: " + count(string, ch));
    }

    private static char promptUserForCharacter() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a character: ");
        return scanner.nextLine().charAt(0);
    }

    private static int count(String str, char a) {
        return count(str, a, str.length() - 1);
    }

    private static int count(String str, char a, int high) {
        if (high < 0) return count;
        if (a == str.charAt(high)) {
            count++;
        }
        return count(str, a, high - 1);
    }

    private static String promptUserForString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        return scanner.nextLine();
    }
}
