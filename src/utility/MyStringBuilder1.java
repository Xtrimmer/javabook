package utility;

/**
 * Created for Chapter 10 Exercise 27.
 */
public class MyStringBuilder1 {
    private char[] values;
    private int size;

    public MyStringBuilder1(String s) {
        values = s.toCharArray();
        size = s.length();
    }

    public MyStringBuilder1 append(MyStringBuilder1 s) {
        while (values.length < this.size + s.size) expand();
        System.arraycopy(s.values, 0, values, size, s.size);
        size += s.size;
        return this;
    }

    public MyStringBuilder1 append(int i) {
        int intLength = Integer.toString(i).length();
        while (values.length < this.size + intLength) expand();
        System.arraycopy(Integer.toString(i).toCharArray(), 0, values, size, intLength);
        size += intLength;
        return this;
    }

    public int length() {
        return size;
    }

    public char charAt(int index) {
        return values[index];
    }

    public MyStringBuilder1 toLowerCase() {
        for (int i = 0; i < size; i++) {
            values[i] = Character.toLowerCase(values[i]);
        }
        return this;
    }

    public MyStringBuilder1 substring(int begin, int end) {
        char[] array = new char[end - begin + 1];
        System.arraycopy(values, begin, array, 0, end - begin + 1);
        return new MyStringBuilder1(new String(array, 0, array.length));
    }

    public String toString() {
        return new String(values, 0, size);
    }

    private void expand() {
        char[] newValues = new char[values.length * 2];
        System.arraycopy(values, 0, newValues, 0, values.length);
        values = newValues;
    }
}
