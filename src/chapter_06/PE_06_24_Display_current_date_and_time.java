package chapter_06;

/**
 * (Display current date and time) Listing 2.7, ShowCurrentTime.java, displays the
 * current time. Improve this example to display the current date and time. The calendar
 * example in Listing 6.12, PrintCalendar.java, should give you some ideas on
 * how to find the year, month, and day.
 */
public class PE_06_24_Display_current_date_and_time {
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
    
    public static String getDayName(int day) {
        String dayName;
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
            default:
                dayName = "Unknown";
        }
        return dayName;
    }

    public static String getDate(int days) {
        int year = 1970;
        int month = 1;
        int day = 1;
        int weekday = 4;
        int monthdays = getMonthDays(year, month);
        for (int i = 0; i < days; i++) {
            weekday = (weekday + 1) % 7;
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
        return getDayName(weekday) + ", " + month + "-" + day + "-" + year;
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
