package chapter_02;

import java.util.Scanner;

/**
 * (Current time) Listing 2.6, ShowCurrentTime.java, gives a program that displays
 * the current time in GMT. Revise the program so that it prompts the user to enter
 * the time zone offset to GMT and displays the time in the specified time zone.
 */
public class PE_02_08_Current_time {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        System.out.println("Enter the time zone offset to GMT: ");
        int offset = SCANNER.nextInt();

        // Obtain the total milliseconds since midnight, Jan 1, 1970
        long totalMilliseconds = System.currentTimeMillis();

        // Obtain the total seconds since midnight, Jan 1, 1970
        long totalSeconds = totalMilliseconds / 1000;

        // Compute the current second in the minute in the hour
        long currentSecond = (int) (totalSeconds % 60);

        // Obtain the total minutes
        long totalMinutes = totalSeconds / 60;

        // Compute the current minute in the hour
        long currentMinute = (int) (totalMinutes % 60);

        // Obtain the total hours
        long totalHours = totalMinutes / 60;

        totalHours += offset;

        // Compute the current hour
        long currentHour = (int) (totalHours % 24);

        // Display results
        System.out.println("Current time is " + currentHour + ":"
                + currentMinute + ":" + currentSecond);
    }
}
