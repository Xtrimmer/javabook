package utility;

import java.util.GregorianCalendar;

/**
 * Created for Chapter 11 Exercise 02.
 */
public class Employee extends Person {
    private String office = "default office";
    private double salary = 40000.0;
    private MyDate dateHired = new MyDate();

    public Employee() {
    }

    public Employee(String name) {
        super(name);
    }

    public Employee(String name, String office, double salary, MyDate dateHired) {
        super(name);
        this.office = office;
        this.salary = salary;
        this.dateHired = dateHired;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public MyDate getDateHired() {
        return dateHired;
    }

    public void setDateHired(MyDate dateHired) {
        this.dateHired = dateHired;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + getName() + '\'' +
                '}';
    }

    private static class MyDate {
        private int year;
        private int month;
        private int day;

        MyDate() {
            this(System.currentTimeMillis());
        }

        MyDate(long timeInMilliseconds) {
            setYear(timeInMilliseconds);
            setMonth(timeInMilliseconds);
            setDay(timeInMilliseconds);
        }

        MyDate(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        int getYear() {
            return year;
        }

        void setYear(long timeInMilliseconds) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTimeInMillis(timeInMilliseconds);
            this.year = gregorianCalendar.get(GregorianCalendar.YEAR);
        }

        int getMonth() {
            return month;
        }

        void setMonth(long timeInMilliseconds) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTimeInMillis(timeInMilliseconds);
            this.month = gregorianCalendar.get(GregorianCalendar.MONTH);
        }

        int getDay() {
            return day;
        }

        void setDay(long timeInMilliseconds) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTimeInMillis(timeInMilliseconds);
            this.day = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
        }

        void setDate(long elapsedTime) {
            setYear(elapsedTime);
            setMonth(elapsedTime);
            setDay(elapsedTime);
        }
    }
}
