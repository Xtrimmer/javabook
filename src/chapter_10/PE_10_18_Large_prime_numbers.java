package chapter_10;

import java.math.BigInteger;

/**
 * (Large prime numbers) Write a program that finds five prime numbers larger
 * than Long.MAX_VALUE.
 */
public class PE_10_18_Large_prime_numbers {
    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger(Long.toString(Long.MAX_VALUE));
        for (int i = 0; i < 5; i++) {
            bigInteger = bigInteger.nextProbablePrime();
            System.out.println(bigInteger.toString());
        }
    }
}
