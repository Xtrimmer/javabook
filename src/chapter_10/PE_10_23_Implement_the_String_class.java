package chapter_10;

import java.util.Arrays;

/**
 * (Implement the String class) The String class is provided in the Java library.
 * Provide your own implementation for the following methods (name the new
 * class MyString2):
 *
 *      public MyString2(String s);
 *      public int compare(String s);
 *      public MyString2 substring(int begin);
 *      public MyString2 toUpperCase();
 *      public char[] toChars();
 *      public static MyString2 valueOf(boolean b);
 */
public class PE_10_23_Implement_the_String_class {
    public static void main(String[] args) {
        MyString2 string1 = new MyString2("JDoe23Go@Gmail.com");
        MyString2 string2 = MyString2.valueOf(true);

        // print string
        System.out.print("string1 = ");
        printMyString2(string1);
        System.out.println();

        //print compare string
        System.out.println("string1.compare(\"JDoe23Go@Gmail.com\") = " + string1.compare("JDoe23Go@Gmail.com"));
        System.out.println("string2.compare(\"true\") = " + string2.compare("true"));
        System.out.println("string1.compare(\"JDoe23Go@Gmail.com2\") = " + string1.compare("JDoe23Go@Gmail.com2"));
        System.out.println("string1.compare(\"JDoe23Go@Gmail\") = " + string1.compare("JDoe23Go@Gmail"));
        System.out.println("string1.compare(\"jdoe23go@gmail.com\") = " + string1.compare("jdoe23go@gmail.com"));
        System.out.println("string1.compare(\"Kill\") = " + string1.compare("Kill"));
        System.out.println("string1.compare(\"Idol\") = " + string1.compare("Idol"));

        // print substring
        System.out.print("string1.substring(9) = ");
        printMyString2(string1.substring(9));
        System.out.println();

        //print toUppercase
        System.out.print("string1.toUpperCase() = ");
        printMyString2(string1.toUpperCase());
        System.out.println();
        System.out.print("string2.toUpperCase() = ");
        printMyString2(string2.toUpperCase());
        System.out.println();

        //print toChars
        System.out.print("string1.toChars() = ");
        System.out.println(Arrays.toString(string1.toChars()));
        System.out.print("string2.toChars() = ");
        System.out.println(Arrays.toString(string2.toChars()));

        //print valueOf
        System.out.print("MyString2.valueOf(true) = ");
        printMyString2(MyString2.valueOf(true));
        System.out.println();
        System.out.print("MyString2.valueOf(false) = ");
        printMyString2(MyString2.valueOf(false));
        System.out.println();
    }

    private static void printMyString2(MyString2 s) {
        for (int i = 0; i < s.toChars().length; i++) {
            System.out.print(s.toChars()[i]);
        }
    }

    private static class MyString2 {
        char[] chars;

        MyString2(String s) {
            chars = s.toCharArray();
        }

        static MyString2 valueOf(boolean b) {
            return new MyString2(b ? "true" : "false");
        }

        int compare(String s) {
            char[] chars = s.toCharArray();
            int length = this.chars.length < chars.length
                    ? this.chars.length : chars.length;
            for (int i = 0; i < length; i++) {
                if (this.chars[i] != chars[i]) {
                    if (this.chars[i] < chars[i]) return -1;
                    else return 1;
                }
            }
            if (this.chars.length < chars.length) return -1;
            else if (this.chars.length > chars.length) return 1;
            else return 0;
        }

        MyString2 substring(int begin) {
            char[] chars = new char[this.chars.length - begin];
            System.arraycopy(this.chars, begin, chars, 0, chars.length);
            return new MyString2(new String(chars));
        }

        MyString2 toUpperCase() {
            char[] newChars = new char[chars.length];
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] >= 'a' && chars[i] <= 'z')
                    newChars[i] = (char) (chars[i] - ('a' - 'A'));
                else
                    newChars[i] = chars[i];
            }
            return new MyString2(new String(newChars));
        }

        char[] toChars() {
            return chars;
        }
    }
}
