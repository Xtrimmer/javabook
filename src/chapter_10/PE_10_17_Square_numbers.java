package chapter_10;

import java.math.BigInteger;

/**
 * (Square numbers) Find the first ten square numbers that are greater than
 * Long.MAX_VALUE. A square number is a number in the form of n^2. For example,
 * 4, 9, and 16 are square numbers. Find an efficient approach to run your
 * program fast.
 */
public class PE_10_17_Square_numbers {
    public static void main(String[] args) {
        Double aDouble = Math.sqrt((double)Long.MAX_VALUE);
        Long firstSquareRoot = aDouble.longValue();
        BigInteger bigInteger = new BigInteger(firstSquareRoot.toString()).add(BigInteger.ONE);
        System.out.println(Long.MAX_VALUE);
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println(bigInteger.multiply(bigInteger).toString());
            bigInteger = bigInteger.add(BigInteger.ONE);
        }
    }
}
