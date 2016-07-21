package Chapter_10;

import utility.MyStringBuilder1;

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
}
