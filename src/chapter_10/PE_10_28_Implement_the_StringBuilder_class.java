package chapter_10;

import utility.MyStringBuilder2;

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
}
