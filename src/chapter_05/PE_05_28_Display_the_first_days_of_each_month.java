package chapter_05;

import java.util.Scanner;

/**
 * (Display the first days of each month) Write a program that prompts the user to
 * enter the year and first day of the year, and displays the first day of each month
 * in the year. For example, if the user entered the year 2013, and 2 for Tuesday,
 * January 1, 2013, your program should display the following output:
 *
 *      January 1, 2013 is Tuesday
 *      ...
 *      December 1, 2013 is Sunday
 */
public class PE_05_28_Display_the_first_days_of_each_month {
    public static void main(String[] args) {
        // Declare variables
        Scanner scanner = new Scanner(System.in);
        int year;
        int day;
        int febDays;
        String monthName = "unknown";
        String dayName = "unknown";
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
            switch (day){
                case 0:
                    dayName = "Sunday";
                    break;
                case 1:
                    dayName = "Monday";
                    break;
                case 2:
                    dayName = "Tuesday";
                    break;
                case 3:
                    dayName = "Wednesday";
                    break;
                case 4:
                    dayName = "Thursday";
                    break;
                case 5:
                    dayName = "Friday";
                    break;
                case 6:
                    dayName = "Saturday";
                    break;
            }
            System.out.println(monthName + " 1, " + year + " is " + dayName);
            day = (day + monthDays) % 7;
        }
    }
}
