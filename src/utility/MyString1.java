package utility;

/**
 * Created for Chapter 10 Exercise 22.
 */
public class MyString1 {
    private char[] chars;

    public MyString1(char[] chars) {
        this.chars = new char[chars.length];
        System.arraycopy(chars, 0, this.chars, 0, chars.length);
    }

    public char charAt(int index) {
        return chars[index];
    }

    public int length() {
        return chars.length;
    }

    public MyString1 substring(int begin, int end) {
        char[] chars = new char[end - begin + 1];
        System.arraycopy(this.chars, begin, chars, 0, chars.length);
        return new MyString1(chars);
    }

    public MyString1 toLowerCase() {
        char[] newChars = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z')
                newChars[i] = (char)(chars[i] + ('a' - 'A'));
            else
                newChars[i] = chars[i];
        }
        return new MyString1(newChars);
    }

    public boolean equals(MyString1 s) {
        if (s.chars.length != this.chars.length) return false;
        for (int i = 0; i < chars.length; i++) {
            if (s.chars[i] != this.chars[i]) return false;
        }
        return true;
    }

    public static MyString1 valueOf(int number) {
        int temp = number;
        int numberLength = 0;
        boolean isNegative = number < 0;
        while (temp != 0) {
            numberLength++;
            temp /= 10;
        }
        char[] chars;
        if (isNegative) {
            chars = new char[numberLength + 1];
            chars[0] = '-';
            number = -number;
        } else {
            chars = new char[numberLength];
        }
        int offset = chars.length - numberLength;
        for (int i = chars.length - 1; i >= offset; i--) {
            chars[i] = (char)((number % 10) + '0');
            number /= 10;
        }
        return new MyString1(chars);
    }
}
