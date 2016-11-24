package chapter_06;

import java.util.Scanner;

/**
 * (Convert milliseconds to hours, minutes, and seconds) Write a method that converts
 * milliseconds to hours, minutes, and seconds using the following header:
 *
 *      public static String convertMillis(long millis)
 *
 * The method returns a string as hours:minutes:seconds. For example,
 * convertMillis(5500) returns a string 0:0:5, convertMillis(100000) returns
 * a string 0:1:40, and convertMillis(555550000) returns a string 154:19:10.
 */
public class PE_06_25_Convert_milliseconds_to_hours_minutes_and_seconds {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the milliseconds: ");
        long millis = scanner.nextLong();
        System.out.println(convertMillis(millis));
    }

    public static String convertMillis(long millis) {
        long totalSeconds = millis / 1000;
        long second = totalSeconds % 60;
        long totalMinutes = totalSeconds / 60;
        long minute = totalMinutes % 60;
        long totalHours = totalMinutes / 60;
        long hour = totalHours % 24;
        return totalHours + ":" + minute + ":" + second;
    }
}
