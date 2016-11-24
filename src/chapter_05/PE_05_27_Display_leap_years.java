package chapter_05;

/**
 * (Display leap years) Write a program that displays all the leap years, ten per line,
 * from 101 to 2100, separated by exactly one space. Also display the number of
 * leap years in this period.
 */
public class PE_05_27_Display_leap_years {
    public static void main(String[] args) {
        /**
         * if (year is not divisible by 4) then (it is a common year)
         * else if (year is not divisible by 100) then (it is a leap year)
         * else if (year is not divisible by 400) then (it is a common year)
         * else (it is a leap year)
         */
        boolean leapYear = false;
        int count = 0;
        for (int i = 101; i <= 2100; i++) {
            if (i % 4 != 0) leapYear = false;
            else if (i % 100 != 0) leapYear = true;
            else if (i % 400 != 0) leapYear = false;
            else leapYear = true;
            if (leapYear){
                System.out.print(i + " ");
                count++;
            }
            if (count == 10){
                System.out.println();
                count = 0;
            }
        }
    }
}
