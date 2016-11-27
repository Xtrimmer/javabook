package chapter_10;

/**
 * (Implement the StringBuilder class) The StringBuilder class is provided
 * in the Java library. Provide your own implementation for the following methods
 * (name the new class MyStringBuilder1):
 *
 *      public MyStringBuilder1(String s);
 *      public MyStringBuilder1 append(MyStringBuilder1 s);
 *      public MyStringBuilder1 append(int i);
 *      public int length();
 *      public char charAt(int index);
 *      public MyStringBuilder1 toLowerCase();
 *      public MyStringBuilder1 substring(int begin, int end);
 *      public String toString();
 */
public class PE_10_27_Implement_the_StringBuilder_class {
    public static void main(String[] args) {
        MyStringBuilder1 sb1 = new MyStringBuilder1("AbC");
        MyStringBuilder1 sb2 = new MyStringBuilder1("dEfG");
        System.out.println("sb1.toString() = " + sb1.toString());
        System.out.println("sb2.toString() = " + sb2.toString());

        sb1.append(sb2);
        System.out.println("sb1.append(sb2) = " + sb1.toString());

        sb1.append(12345);
        System.out.println("sb1.append(12345) = " + sb1.toString());

        System.out.println("sb1.length() = " + sb1.length());
        System.out.println("sb1.charAt(4) = " + sb1.charAt(4));
        System.out.println("sb1.toLowerCase() = " + sb1.toLowerCase());
        System.out.println("sb1.substring(4, 9) = " + sb1.substring(4, 9));
    }

    private static class MyStringBuilder1 {
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
