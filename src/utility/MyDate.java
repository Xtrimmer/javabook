package utility;

import java.util.GregorianCalendar;

/**
 * Created for Chapter 10 Exercise 14.
 */
public class MyDate {
    private int year;
    private int month;
    private int day;

    public MyDate() {
        this(System.currentTimeMillis());
    }

    public MyDate(long timeInMilliseconds) {
        setYear(timeInMilliseconds);
        setMonth(timeInMilliseconds);
        setDay(timeInMilliseconds);
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    private void setYear(long timeInMilliseconds) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(timeInMilliseconds);
        this.year = gregorianCalendar.get(GregorianCalendar.YEAR);
    }

    public int getMonth() {
        return month;
    }

    private void setMonth(long timeInMilliseconds) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(timeInMilliseconds);
        this.month = gregorianCalendar.get(GregorianCalendar.MONTH);
    }

    public int getDay() {
        return day;
    }

    private void setDay(long timeInMilliseconds) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(timeInMilliseconds);
        this.day = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    public void setDate(long elapsedTime) {
        setYear(elapsedTime);
        setMonth(elapsedTime);
        setDay(elapsedTime);
    }
}
