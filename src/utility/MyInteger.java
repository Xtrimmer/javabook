package utility;

/**
 * Added for Chapter 10 Exercise 03.
 */
public class MyInteger {
    private int value;

    public MyInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static boolean isEven(int value) {
        return value % 2 == 0;
    }

    public static boolean isEven(MyInteger value) {
        return isEven(value.getValue());
    }

    public boolean isEven() {
        return isEven(value);
    }

    public static boolean isOdd(int value) {
        return !isEven(value);
    }

    public static boolean isOdd(MyInteger value) {
        return isOdd(value.getValue());
    }

    public boolean isOdd() {
        return isOdd(value);
    }

    public static boolean isPrime(int value) {
        for (int divisor = 2; divisor <= value / 2; divisor++) {
            if (value % divisor == 0) { // If true, value is not prime
                return false; // Value is not a prime
            }
        }
        return true; // Value is prime
    }

    public static boolean isPrime(MyInteger value) {
        return isPrime(value.getValue());
    }

    public boolean isPrime() {
        return isPrime(value);
    }

    public boolean equals(int value) {
        return this.value == value;
    }

    public boolean equals(MyInteger value) {
        return equals(value.getValue());
    }

    public static int parseInt(char[] array) {
        int value = 0;
        for (int i = 0; i < array.length; i++) {
            value += (array[i] - '0') * Math.pow(10, array.length - i - 1);
        }
        return value;
    }

    public static int parseInt(String value) {
        return Integer.parseInt(value);
    }
}
