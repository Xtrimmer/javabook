package Chapter_13;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * (Display calendars) Rewrite the PrintCalendar class in Listing 6.12 to display
 * a calendar for a specified month using the Calendar and GregorianCalendar
 * classes. Your program receives the month and year from the command line. For
 * example:
 *
 *      java Exercise13_04 5 2016
 *
 * This displays the calendar shown in Figure 13.9.
 *
 *      FIGURE 13.9(p.529) The program displays a calendar for May 2016.
 *
 * You also can run the program without the year. In this case, the year is the current
 * year. If you run the program without specifying a month and a year, the month is
 * the current month.
 */
public class PE_13_04_Display_calendars {
    private static final String[] MONTH_NAMES = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};


    public static void main(String[] args) {
        int month = getMonth(args);
        int year = getYear(args);
        Calendar calendar = new GregorianCalendar(year, month, 1);
        printMonth(calendar);
    }

    private static void printMonth(Calendar calendar) {
        printMonthTitle(calendar);
        printMonthBody(calendar);
    }

    private static void printMonthBody(Calendar calendar) {
        int startDay = calendar.get(Calendar.DAY_OF_WEEK);
        int numberOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Pad space before the first day of the month
        int i;
        for (i = Calendar.SUNDAY; i < startDay; i++)
            System.out.print("    ");

        for (i = 1; i <= numberOfDaysInMonth; i++) {
            System.out.printf("%4d", i);

            if ((i + startDay - 1) % 7 == 0)
                System.out.println();
        }

        System.out.println();
    }

    public static void printMonthTitle(Calendar calendar) {
        System.out.println(" " + MONTH_NAMES[calendar.get(Calendar.MONTH)]
                + " " + calendar.get(Calendar.YEAR));
        System.out.println("-----------------------------");
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
    }

    private static int getYear(String[] args) {
        GregorianCalendar calendar = new GregorianCalendar();
        int year = -1;
        try {
            year = Integer.valueOf(args[1]);
            if (year >= calendar.getActualMinimum(Calendar.YEAR)
                    && year <= calendar.getActualMaximum(Calendar.YEAR)) {
                return year;
            } else {
                throw new NumberFormatException();
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            year = Calendar.YEAR;
        } catch (NumberFormatException ex) {
            System.out.println("Invalid Year Value:");
            System.exit(0);
        }
        return year;
    }

    private static int getMonth(String[] args) {
        GregorianCalendar calendar = new GregorianCalendar();
        int month = -1;
        try {
            month = Integer.valueOf(args[0]);
            if (month >= calendar.getActualMinimum(Calendar.MONTH) + 1
                    && month <= calendar.getActualMaximum(Calendar.MONTH) + 1) {
                month -= 1;
            } else {
                throw new NumberFormatException();
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            month = Calendar.MONTH;
        } catch (NumberFormatException ex) {
            System.out.println("Invalid Month Value:");
            System.exit(0);
        }
        return month;
    }
}