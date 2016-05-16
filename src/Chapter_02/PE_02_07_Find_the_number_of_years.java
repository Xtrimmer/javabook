package Chapter_02;

import java.util.Scanner;
/**
 * (Find the number of years) Write a program that prompts the user to enter the
 * minutes (e.g., 1 billion), and displays the number of years and days for the
 * minutes. For simplicity, assume a year has 365 days.
 */
public class PE_02_07_Find_the_number_of_years {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        int minutes;
        int minutesPerDay = 24 * 60;
        int minutesPerYear = minutesPerDay * 365;
        int years;
        int days;

        System.out.print("Enter the number of minutes: ");
        minutes = SCANNER.nextInt();

        years = minutes / minutesPerYear;
        days = minutes % minutesPerYear;
        days /= minutesPerDay;

        System.out.println(minutes + " minutes is aproximately " + years + " years and " + days + " days.");

    }
}
