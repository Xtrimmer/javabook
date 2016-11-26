package chapter_02;

import java.util.Scanner;

/**
 * (Find the number of years) Write a program that prompts the user to enter the
 * minutes (e.g., 1 billion), and displays the number of years and days for the
 * minutes. For simplicity, assume a year has 365 days.
 */
public class PE_02_07_Find_the_number_of_years {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        final int MINUTES_PER_DAY = 24 * 60;
        final int MINUTES_PER_YEAR = MINUTES_PER_DAY * 365;

        System.out.print("Enter the number of minutes: ");
        int minutes = SCANNER.nextInt();

        int years = minutes / MINUTES_PER_YEAR;
        int days = minutes % MINUTES_PER_YEAR;
        days /= MINUTES_PER_DAY;

        System.out.println(minutes + " minutes is approximately " + years + " years and " + days + " days");
    }
}
