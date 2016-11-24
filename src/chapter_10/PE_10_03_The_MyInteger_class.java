package chapter_10;

import utility.MyInteger;

/**
 * (The MyInteger class) Design a class named MyInteger. The class contains:
 *
 * - An int data field named value that stores the int value represented by this
 *   object.
 * - A constructor that creates a MyInteger object for the specified int value.
 * - A getter method that returns the int value.
 * - The methods isEven(), isOdd(), and isPrime() that return true if the
 *   value in this object is even, odd, or prime, respectively.
 * - The static methods isEven(int), isOdd(int), and isPrime(int) that
 *   return true if the specified value is even, odd, or prime, respectively.
 * - The static methods isEven(MyInteger), isOdd(MyInteger), and
 *   isPrime(MyInteger) that return true if the specified value is even, odd,
 *   or prime, respectively.
 * - The methods equals(int) and equals(MyInteger) that return true if
 *   the value in this object is equal to the specified value.
 * - A static method parseInt(char[]) that converts an array of numeric
 *   characters to an int value.
 * - A static method parseInt(String) that converts a string into an int
 *   value.
 *
 * Draw the UML diagram for the class and then implement the class. Write a client
 * program that tests all methods in the class.
 */
public class PE_10_03_The_MyInteger_class {
    public static void main(String[] args) {
        MyInteger myInteger = new MyInteger(13);
        System.out.println("The value of myInteger is " + myInteger.getValue());
        System.out.println("myInteger is even: " + myInteger.isEven());
        System.out.println("myInteger is odd: " + myInteger.isOdd());
        System.out.println("myInteger is prime: " + myInteger.isPrime());

        System.out.println("13 == 13: " + myInteger.equals(13));
        System.out.println("13 == 14: " + myInteger.equals(new MyInteger(14)));

        System.out.println("Parse int 123 string value == 123: "
                + MyInteger.parseInt("123"));
        System.out.println("Parse int 234 char array value == 234: "
                + MyInteger.parseInt(new char[]{'2', '3', '4'}));
    }
}
