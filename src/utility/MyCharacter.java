package utility;

/**
 * Created for Chapter 10 Exercise 24.
 */
public class MyCharacter {
    private char value;

    public MyCharacter(char value) {
        this.value = value;
    }

    public char charValue() {
        return value;
    }

    public static int compare(char x, char y) {
        if (x < y) return -1;
        if (x > y) return 1;
        return 0;
    }

    public int compareTo(MyCharacter anotherCharacter) {
        if (this.value < anotherCharacter.value) return -1;
        if (this.value > anotherCharacter.value) return 1;
        return 0;
    }

    public boolean equals(MyCharacter anotherCharacter) {
        return this.value == anotherCharacter.value;
    }

    public int getNumericValue() {
        if (isDigit()) return (this.value - '0');
        else return -1;
    }

    public boolean isLetter() {
        return isUpperCase() || isLowerCase();
    }

    public boolean isDigit() {
        return value >= '0' && value <= '9';
    }

    public boolean isLetterOrDigit() {
        return isLetter() || isDigit();
    }

    public boolean isLowerCase() {
        return value >= 'a' && value <= 'z';
    }

    public boolean isUpperCase() {
        return value >= 'A' && value <= 'Z';
    }

    public char toLowerCase() {
        if (isUpperCase()) return (char) (value + ('a' - 'A'));
        else return value;
    }

    public char toUpperCase() {
        if (isLowerCase()) return (char) (value - ('a' - 'A'));
        else return value;
    }

    public String toString() {
        return value + "";
    }

    public static MyCharacter valueOf(char c) {
        return new MyCharacter(c);
    }
}
