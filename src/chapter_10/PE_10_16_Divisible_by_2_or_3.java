package chapter_10;

import java.math.BigInteger;

/**
 * (Divisible by 2 or 3) Find the first ten numbers with 50 decimal digits that are
 divisible by 2 or 3.
 */
public class PE_10_16_Divisible_by_2_or_3 {
    public static void main(String[] args) {
        String value = getValue();
        BigInteger bigInteger = new BigInteger(value);
        printNumbersDivisibleBy2Or3(bigInteger, 10);

    }

    private static void printNumbersDivisibleBy2Or3(BigInteger start, int quantity) {
        int count = 0;
        BigInteger two = new BigInteger("2");
        BigInteger three = new BigInteger("3");
        while (count < quantity) {
            if (start.mod(two).equals(BigInteger.ZERO)
                    || start.mod(three).equals(BigInteger.ZERO)) {
                System.out.println(start.toString());
                count++;
            }
            start = start.add(BigInteger.ONE);
        }
    }

    private static String getValue() {
        StringBuilder value = new StringBuilder(50);
        value.append('1');
        for (int i = 0; i < 49; i++) {
            value.append('0');
        }
        return value.toString();
    }
}
