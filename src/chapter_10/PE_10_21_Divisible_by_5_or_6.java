package chapter_10;

import java.math.BigInteger;

/**
 * (Divisible by 5 or 6) Find the first ten numbers greater than Long.MAX_VALUE
 * that are divisible by 5 or 6.
 */
public class PE_10_21_Divisible_by_5_or_6 {
    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger(Long.toString(Long.MAX_VALUE)).add(BigInteger.ONE);
        System.out.println(Long.MAX_VALUE + "\n");
        printNumbersDivisibleBy5Or6(bigInteger, 10);
    }

    private static void printNumbersDivisibleBy5Or6(BigInteger start, int quantity) {
        int count = 0;
        BigInteger five = new BigInteger("5");
        BigInteger six = new BigInteger("6");
        while (count < quantity) {
            if (start.mod(five).equals(BigInteger.ZERO)
                    || start.mod(six).equals(BigInteger.ZERO)) {
                System.out.println(start.toString());
                count++;
            }
            start = start.add(BigInteger.ONE);
        }
    }
}
