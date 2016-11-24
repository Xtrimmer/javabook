package chapter_05;

import java.util.Scanner;

/**
 * (Display calendars) Write a program that prompts the user to enter the year and
 * first day of the year and displays the calendar table for the year on the console. For
 * example, if the user entered the year 2013, and 2 for Tuesday, January 1, 2013,
 * your program should display the calendar for each month in the year, as follows:
 *
 *             January 2013
 *      Sun Mon Tue Wed Thu Fri Sat
 *               1   2   3   4   5
 *       6   7   8   9  10  11  12
 *      13  14  15  16  17  18  19
 *      20  21  22  23  24  25  26
 *      27  28  29  30  31
 *
 *             December 2013
 *      Sun Mon Tue Wed Thu Fri Sat
 *       1   2   3   4   5   6   7
 *       8   9  10  11  12  13  14
 *      15  16  17  18  19  20  21
 *      22  23  24  25  26  27  28
 *      29  30  31
 */
public class PE_05_29_Display_calendars {
    public static void main(String[] args) {
        // Declare variables
        Scanner scanner = new Scanner(System.in);
        int year;
        int day;
        int febDays;
        String monthName = "unknown";
        int monthDays = 0;

        // Get input from user
        System.out.print("Enter the year: ");
        year = scanner.nextInt();
        System.out.print("Enter the first day of the year: ");
        day = scanner.nextInt();

        // Check if leap year
        if (year % 4 != 0) febDays = 28;
        else if (year % 100 != 0) febDays = 29;
        else if (year % 400 != 0) febDays = 28;
        else febDays = 29;

        // Calculate month, day and output to console
        for (int i = 1; i <= 12; i++) {
            switch (i){
                case 1:
                    monthName = "January";
                    monthDays = 31;
                    break;
                case 2:
                    monthName = "February";
                    monthDays = febDays;
                    break;
                case 3:
                    monthName = "March";
                    monthDays = 31;
                    break;
                case 4:
                    monthName = "April";
                    monthDays = 30;
                    break;
                case 5:
                    monthName = "May";
                    monthDays = 31;
                    break;
                case 6:
                    monthName = "June";
                    monthDays = 30;
                    break;
                case 7:
                    monthName = "July";
                    monthDays = 31;
                    break;
                case 8:
                    monthName = "August";
                    monthDays = 31;
                    break;
                case 9:
                    monthName = "September";
                    monthDays = 30;
                    break;
                case 10:
                    monthName = "October";
                    monthDays = 31;
                    break;
                case 11:
                    monthName = "November";
                    monthDays = 30;
                    break;
                case 12:
                    monthName = "December";
                    monthDays = 31;
                    break;
            }
            System.out.println("       " + monthName + " " + year);
            System.out.println("___________________________");
            System.out.println("Sun Mon Tue Wed Thu Fri Sat");
            for (int j = 0; j < day; j++) {
                System.out.print("    ");
            }
            int count = day;
            for (int j = 1; j <= monthDays; j++) {
                if (count == 7) {
                    count = 0;
                    System.out.println();
                }
                System.out.printf("%2d  ", j);
                count++;
            }

            System.out.println();
            day = (day + monthDays) % 7;
            System.out.println();
        }
    }
}
