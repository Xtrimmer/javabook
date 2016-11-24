package chapter_10;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * (Approximate e) Programming Exercise 5.26 approximates e using the following
 * series:
 *
 *      e = 1 + 1/1! + 1/2! + 1/3! + 1/4! + ... + 1/i!
 *
 * In order to get better precision, use BigDecimal with 25 digits of precision in
 * the computation. Write a program that displays the e value for i = 100, 200, . . . ,
 * and 1000.
 */
public class PE_10_20_Approximate_e {
    public static void main(String[] args) {
        BigDecimal e = new BigDecimal(BigInteger.ONE);
        BigDecimal item = new BigDecimal(BigInteger.ONE);
        for (BigDecimal i = new BigDecimal("2"); i.compareTo(new BigDecimal("1000")) < 1; i = i.add(BigDecimal.ONE)) {
            item = item.divide(i.subtract(BigDecimal.ONE), 25, BigDecimal.ROUND_HALF_UP);
            e = e.add(item);
            if (i.compareTo(new BigDecimal("100")) == 0
                    || i.compareTo(new BigDecimal("200")) == 0
                    || i.compareTo(new BigDecimal("1000")) == 0)
                System.out.printf("e @ i = %4s: %s%n", i.toString(), e.toString());
            // e == 2.7182818284590452353602874713527
        }
    }
}
