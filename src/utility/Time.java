package utility;

public class Time {
    private int hour;
    private int minute;
    private int second;

    public Time() {
        this(System.currentTimeMillis());
    }

    public Time(long timeInMilliseconds) {
        setTime(timeInMilliseconds);
    }

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void setTime(long timeInMilliseconds) {
        hour = (int)((timeInMilliseconds / (1000 * 60 * 60)) % 24);
        minute = (int)((timeInMilliseconds / (1000 * 60)) % 60);
        second = (int)((timeInMilliseconds / 1000) % 60);
    }
}
