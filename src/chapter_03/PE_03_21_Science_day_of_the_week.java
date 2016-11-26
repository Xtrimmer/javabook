package chapter_03;

import java.util.Scanner;

/**
 * (Science: day of the week) Zeller’s congruence is an algorithm developed by
 * Christian Zeller to calculate the day of the week. The formula is
 *
 *      h = (q + (26 * (m + 1) / 10) + k + (k / 4) + (j / 4) + 5j) % 7
 *
 * where
 *
 *  - h is the day of the week (0: Saturday, 1: Sunday, 2: Monday, 3: Tuesday, 4: Wednesday, 5: Thursday, 6: Friday).
 *  - q is the day of the month.
 *  - m is the month (3: March, 4: April, …, 12: December). January and February
 *    are counted as months 13 and 14 of the previous year.
 *  - j is the century (i.e., year/100).
 *  - k is the year of the century (i.e., year % 100).
 *
 *  Note that the division in the formula performs an integer division. Write a program
 *  that prompts the user to enter a year, month, and day of the month, and
 *  displays the name of the day of the week.
 */
public class PE_03_21_Science_day_of_the_week {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        System.out.print("Enter year: (e.g., 2012): ");
        int year = SCANNER.nextInt();
        int j = year / 100;
        int k = year % 100;
        System.out.print("Enter month: 1-12: ");
        int m = SCANNER.nextInt();
        if (m < 3) m += 12;
        System.out.print("Enter the day of the month: 1-31: ");
        int q = SCANNER.nextInt();
        int h = (q + (26 * (m + 1) / 10) + k + (k / 4) + (j / 4) + (5 * j)) % 7;
        String day = null;
        switch (h) {
            case 0:
                day = "Saturday";
                break;
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
        }
        System.out.println("Day of the week is " + day);
    }
}
