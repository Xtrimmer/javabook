package chapter_13;

import java.math.BigInteger;

/**
 * (Use the Rational class) Write a program that computes the following summation
 * series using the Rational class:
 *
 *      1/2 + 2/3 + 3/4 + ... + 98/99 + 99/100
 *
 * You will discover that the output is incorrect because of integer overflow (too
 * large). To fix this problem, see Programming Exercise 13.15.
 */
public class PE_13_18_Use_the_Rational_class {
    public static void main(String[] args) {
        PE_13_15_Rational sum = new PE_13_15_Rational(BigInteger.ZERO, BigInteger.ONE);
        BigInteger max = new BigInteger("99");
        for (BigInteger i = BigInteger.ONE; i.compareTo(max) <= 0; i = i.add(BigInteger.ONE)) {
            sum = sum.add(new PE_13_15_Rational(i, i.add(BigInteger.ONE)));
            System.out.println(i + ". " + sum);
        }
        System.out.println(sum);
    }
}
