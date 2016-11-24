package chapter_06;

import java.util.Scanner;

/**
 * (Format an integer) Write a method with the following header to format the integer
 * with the specified width.
 *
 *      public static String format(int number, int width)
 *
 * The method returns a string for the number with one or more prefix 0s. The size
 * of the string is the width. For example, format(34, 4) returns 0034 and format(
 * 34, 5) returns 00034. If the number is longer than the width, the method
 * returns the string representation for the number. For example, format(34, 1)
 * returns 34.
 *
 * Write a test program that prompts the user to enter a number and its width and
 * displays a string returned by invoking format(number, width).
 */
public class PE_06_37_Format_an_integer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        System.out.print("Enter a width: ");
        int width = scanner.nextInt();
        System.out.println(format(number, width));
    }

    public static String format(int number, int width) {
        String s = "";
        int size = getSize(number);
        for (int i = 0; i < width - size; i++) {
            s += '0';
        }
        return s + number;
    }

    /** Return the number of digits in d */
    public static int getSize(long d) {
        int count = 0;
        while (d > 0) {
            count++;
            d /= 10;
        }
        return count;
    }
}
