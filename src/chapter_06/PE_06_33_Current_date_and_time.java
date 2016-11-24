package chapter_06;

/**
 * (Current date and time) Invoking System.currentTimeMillis() returns the
 * elapsed time in milliseconds since midnight of January 1, 1970. Write a program
 * that displays the date and time. Here is a sample run:
 *
 *      Current date and time is May 16, 2012 10:34:23
 */
public class PE_06_33_Current_date_and_time {
    public static void main(String[] args) {
        System.out.println(getDateTime());
    }

    public static String getDateTime() {
        // Obtain the total milliseconds since midnight, Jan 1, 1970
        long totalMilliseconds = System.currentTimeMillis();

        // Obtain the total seconds since midnight, Jan 1, 1970
        long totalSeconds = totalMilliseconds / 1000;

        // Compute the current second in the minute in the hour
        long currentSecond = totalSeconds % 60;
        // Obtain the total minutes
        long totalMinutes = totalSeconds / 60;

        // Compute the current minute in the hour
        long currentMinute = totalMinutes % 60;

        // Obtain the total hours
        long totalHours = totalMinutes / 60;

        // Compute the current hour
        long currentHour = totalHours % 24;

        // Obtain the total days
        long totalDays = totalHours / 24;

        // Display results
        return getDate((int)totalDays) + " " + currentHour + ":" + currentMinute + ":" + currentSecond + " GMT";
    }

    public static String getMonthName(int month) {
        String monthName = "unknown";
        switch (month){
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }
        return monthName;
    }

    public static String getDate(int days) {
        int year = 1970;
        int month = 1;
        int day = 1;
        int monthdays = getMonthDays(year, month);
        for (int i = 0; i < days; i++) {
            day++;
            if (day > monthdays) {
                month++;
                day = 1;
            }
            if (month > 12) {
                year++;
                month = 1;
            }
            monthdays = getMonthDays(year, month);
        }
        return getMonthName(month) + " " + day + ", " + year;
    }

    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    public static int getMonthDays(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 ||
                month == 8 || month == 10 || month == 12)
            return 31;

        if (month == 4 || month == 6 || month == 9 || month == 11)
            return 30;

        if (month == 2) return isLeapYear(year) ? 29 : 28;
        return 0; // If month is incorrect

    }
}
