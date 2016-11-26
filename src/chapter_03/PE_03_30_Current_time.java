package chapter_03;

import java.util.Scanner;

/**
 * (Current time) Revise Programming Exercise 2.8 to display the hour using a
 * 12-hour clock.
 */
public class PE_03_30_Current_time {
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
        String period = currentHour >= 12 ? "PM" : "AM";
        currentHour = (int) (totalHours % 12);

        // Display results
        System.out.println("Current time is " + currentHour + ":"
                + currentMinute + ":" + currentSecond + " " + period);
    }
}
