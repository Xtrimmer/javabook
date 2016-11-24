package chapter_09;

import java.util.GregorianCalendar;

/**
 * (Use the GregorianCalendar class) Java API has the GregorianCalendar class
 * in the java.util package, which you can use to obtain the year, month, and day of a
 * date. The no-arg constructor constructs an instance for the current date, and the methods
 * get(GregorianCalendar.YEAR), get(GregorianCalendar.MONTH),
 * and get(GregorianCalendar.DAY_OF_MONTH) return the year, month, and day.
 * Write a program to perform two tasks:
 * - Display the current year, month, and day.
 * - The GregorianCalendar class has the setTimeInMillis(long), which
 * can be used to set a specified elapsed time since January 1, 1970. Set the value
 * to 1234567898765L and display the year, month, and day.
 */
public class PE_09_05_Use_the_GregorianCalendar_class {
    public static void main(String[] args) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        System.out.println("Current");
        printDate(gregorianCalendar);

        System.out.println("1234567898765L");
        gregorianCalendar.setTimeInMillis(1234567898765L);
        printDate(gregorianCalendar);
    }

    private static void printDate(GregorianCalendar gregorianCalendar) {
        System.out.printf("   Year:  %s%n", gregorianCalendar.get(GregorianCalendar.YEAR));
        System.out.printf("   Month: %s%n", gregorianCalendar.get(GregorianCalendar.MONTH));
        System.out.printf("   Day:   %s%n", gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH));
        System.out.println();
    }
}
