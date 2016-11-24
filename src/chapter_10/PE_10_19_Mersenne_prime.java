package chapter_10;

import java.math.BigInteger;

/**
 * (Mersenne prime) A prime number is called a Mersenne prime if it can be written
 * in the form 2^p - 1 for some positive integer p. Write a program that finds
 * all Mersenne primes with p … 100 and displays the output as shown below.
 * (Hint: You have to use BigInteger to store the number, because it is too big to
 * be stored in long. Your program may take several hours to run.)
 *
 *      p 2^p – 1
 *      2 3
 *      3 7
 *      5 31
 *      ...
 */
public class PE_10_19_Mersenne_prime {
    public static void main(String[] args) {
        BigInteger two = new BigInteger("2");
        BigInteger mersennePrime;
        System.out.println("p     2^p - 1");
        for (int i = 2; i <= 100; i++) {
            mersennePrime = two.pow(i).subtract(BigInteger.ONE);
            System.out.printf("%-3d  %s%n", i, mersennePrime.toString());
        }
    }
}
