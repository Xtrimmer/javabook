package utility;

/**
 * Created for Chapter 10 Exercise 28.
 */
public class MyStringBuilder2 {
    private char[] values;
    private int size;

    public MyStringBuilder2(String s) {
        values = s.toCharArray();
        size = s.length();
    }

    public MyStringBuilder2() {
		values = new char[16];
        size = 0;
	}

    public MyStringBuilder2(char[] chars) {
        size = chars.length;
		values = new char[size];
        System.arraycopy(chars, 0, values, 0, size);
	}

    public MyStringBuilder2 insert(int offset, MyStringBuilder2 s) {
        while (values.length < this.size + s.size) expand();
        System.arraycopy(values, offset, values, offset + s.size, size - offset);
        System.arraycopy(s.values, 0, values, offset, s.size);
        size += s.size;
        return this;
	}

    public MyStringBuilder2 reverse() {
        for (int beg = 0, end = size - 1; beg < size / 2; beg++, end--) {
            char temp = values[beg];
            values[beg] = values[end];
            values[end] = temp;
        }
        return this;
	}

    public MyStringBuilder2 substring(int begin) {
        System.arraycopy(values, begin, values, 0, size - begin);
        size -= begin;
        return this;
	}

    public MyStringBuilder2 toUpperCase() {
        for (int i = 0; i < size; i++) {
            values[i] = Character.toUpperCase(values[i]);
        }
        return this;
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
