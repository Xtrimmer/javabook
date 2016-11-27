package chapter_10;

/**
 * (Implement the StringBuilder class) The StringBuilder class is provided
 * in the Java library. Provide your own implementation for the following methods
 * (name the new class MyStringBuilder2):
 *
 *      public MyStringBuilder2();
 *      public MyStringBuilder2(char[] chars);
 *      public MyStringBuilder2(String s);
 *      public MyStringBuilder2 insert(int offset, MyStringBuilder2 s);
 *      public MyStringBuilder2 reverse();
 *      public MyStringBuilder2 substring(int begin);
 *      public MyStringBuilder2 toUpperCase();
 */
public class PE_10_28_Implement_the_StringBuilder_class {
    public static void main(String[] args) {
        MyStringBuilder2 sb1 = new MyStringBuilder2(new char[]{'A', 'b', 'C'});
        MyStringBuilder2 sb2 = new MyStringBuilder2("dEfGh");
        System.out.println("sb1.toString() = " + sb1.toString());
        System.out.println("sb2.toString() = " + sb2.toString());
        System.out.println("sb1.insert(1, sb2) = " + sb1.insert(1, sb2));
        System.out.println("sb1.reverse() = " + sb1.reverse());
        System.out.println("sb1.substring(2) = " + sb1.substring(2));
        System.out.println("sb1.toUpperCase() = " + sb1.toUpperCase());
    }

    private static class MyStringBuilder2 {
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

        @Override
        public String toString() {
            return new String(values, 0, size);
        }

        private void expand() {
            char[] newValues = new char[values.length * 2];
            System.arraycopy(values, 0, newValues, 0, values.length);
            values = newValues;
        }
    }
}
