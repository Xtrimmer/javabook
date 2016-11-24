package chapter_06;

/**
 * (Number of days in a year) Write a method that returns the number of days in a
 * year using the following header:
 *
 *      public static int numberOfDaysInAYear(int year)
 *
 * Write a test program that displays the number of days in year from 2000 to 2020.
 */
public class PE_06_16_Number_of_days_in_a_year {
    public static void main(String[] args) {
        System.out.println("Year     Days");
        System.out.println("-------------");
        for (int i = 2000; i <= 2020; i++) {
            System.out.printf("%-9d%d%n", i, numberOfDaysInAYear(i));
        }
    }

    public static int numberOfDaysInAYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0) ? 366 : 365;
    }
}
