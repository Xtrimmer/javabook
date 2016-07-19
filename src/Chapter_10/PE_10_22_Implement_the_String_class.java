package Chapter_10;

import utility.MyString1;

/**
 * (Implement the String class) The String class is provided in the Java library.
 * Provide your own implementation for the following methods (name the new
 * class MyString1):
 *
 *      public MyString1(char[] chars);
 *      public char charAt(int index);
 *      public int length();
 *      public MyString1 substring(int begin, int end);
 *      public MyString1 toLowerCase();
 *      public boolean equals(MyString1 s);
 *      public static MyString1 valueOf(int i);
 */
public class PE_10_22_Implement_the_String_class {
    public static void main(String[] args) {
        char[] chars = {'J','D','o','e','2','3','G','o','@','G','m','a','i','l','.','n','e','t'};
        MyString1 string1 = new MyString1(chars);
        MyString1 string2 = MyString1.valueOf(-4567);
        MyString1 string3 = new MyString1(chars);

        // print string
        System.out.print("string1 = ");
        printMyString1(string1);
        System.out.println();

        //print characters
        System.out.println("charAt(0) = " + string1.charAt(0));
        System.out.println("charAt(0) = " + string1.charAt(5));
        System.out.println("charAt(0) = " + string1.charAt(string1.length() - 1));

        //print length
        System.out.println("string1.length = " + string1.length());

        // print to lowercase
        System.out.print("string1.toLowerCase = ");
        printMyString1(string1.toLowerCase());
        System.out.println();

        // print substring
        System.out.print("string1.substring(9, 13) = ");
        printMyString1(string1.substring(9, 13));
        System.out.println();

        //print string.equals
        System.out.println("string1 = string2: " + string1.equals(string2));
        System.out.println("string1 = string3: " + string1.equals(string3));

        //print valueOf(-4567)
        System.out.print("MyString1.valueOf(-4567) = ");
        printMyString1(MyString1.valueOf(-4567));
    }

    private static void printMyString1(MyString1 s) {
        for (int i = 0; i < s.length(); i++) {
            System.out.print(s.charAt(i));
        }
    }
}
