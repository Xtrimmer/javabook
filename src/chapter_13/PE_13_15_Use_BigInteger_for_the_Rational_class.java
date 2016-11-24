package chapter_13;

import java.math.BigInteger;

/**
 * (Use BigInteger for the Rational class) Redesign and implement the
 * Rational class in Listing 13.13 using BigInteger for the numerator and
 * denominator.
 */
public class PE_13_15_Use_BigInteger_for_the_Rational_class {
    public static void main(String[] args) {
        PE_13_15_Rational r1 = new PE_13_15_Rational(BigInteger.ONE, new BigInteger("2"));
        PE_13_15_Rational r2 = new PE_13_15_Rational(BigInteger.ONE, new BigInteger("3"));
        PE_13_15_Rational r3 = new PE_13_15_Rational(BigInteger.ONE, new BigInteger("4"));
        PE_13_15_Rational r4 = new PE_13_15_Rational(BigInteger.ONE, new BigInteger("5"));
        PE_13_15_Rational r5 = new PE_13_15_Rational(BigInteger.ONE, new BigInteger("6"));

        System.out.println(r1.add(r2.subtract(r3.multiply(r4.divide(r5)))));
    }
}

class PE_13_15_Rational extends Number implements Comparable<PE_13_15_Rational> {
    // Data fields for numerator and denominator
    private BigInteger numerator = BigInteger.ZERO;
    private BigInteger denominator = BigInteger.ONE;

    /** Construct a rational with default properties */
    public PE_13_15_Rational() {
        this(BigInteger.ZERO, BigInteger.ONE);
    }

    /** Construct a rational with specified numerator and denominator */
    public PE_13_15_Rational(BigInteger numerator, BigInteger denominator) {
        BigInteger gcd = gcd(numerator, denominator);
        this.numerator = (denominator.compareTo(BigInteger.ZERO) > 0 ? BigInteger.ONE : new BigInteger("-1")).multiply(numerator).divide(gcd);
        this.denominator = denominator.abs().divide(gcd);
    }

    private static BigInteger gcd(BigInteger a, BigInteger b){
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger temp = b;
            b = a.remainder(b);
            a = temp;
        }
        return a;
    }

    /** Return numerator */
    public BigInteger getNumerator() {
        return numerator;
    }

    /** Return denominator */
    public BigInteger getDenominator() {
        return denominator;
    }

    /** Add a rational number to this rational */
    public PE_13_15_Rational add(PE_13_15_Rational secondRational) {
        BigInteger n = (numerator.multiply(secondRational.denominator).add(denominator.multiply(secondRational.numerator)));
        BigInteger d = (denominator.multiply(secondRational.denominator));
        return new PE_13_15_Rational(n, d);
    }

    /** Subtract a rational number from this rational */
    public PE_13_15_Rational subtract(PE_13_15_Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.denominator).subtract(denominator.multiply(secondRational.numerator));
        BigInteger d = (denominator.multiply(secondRational.denominator));
        return new PE_13_15_Rational(n, d);
    }

    /** Multiply a rational number to this rational */
    public PE_13_15_Rational multiply(PE_13_15_Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.numerator);
        BigInteger d = (denominator.multiply(secondRational.denominator));
        return new PE_13_15_Rational(n, d);
    }

    /** Divide a rational number from this rational */
    public PE_13_15_Rational divide(PE_13_15_Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.denominator);
        BigInteger d = denominator.multiply(secondRational.numerator);
        return new PE_13_15_Rational(n, d);
    }

    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE))
            return numerator + "";
        else
            return numerator + "/" + denominator;
    }

    @Override // Override the equals method in the Object class
    public boolean equals(Object other) {
        if ((this.subtract((PE_13_15_Rational)(other))).getNumerator().equals(BigInteger.ZERO))
            return true;
        else
            return false;
    }

    @Override // Implement the abstract intValue method in Number
    public int intValue() {
        return (int)doubleValue();
    }

    @Override // Implement the abstract floatValue method in Number
    public float floatValue() {
        return (float)doubleValue();
    }

    @Override // Implement the doubleValue method in Number
    public double doubleValue() {
        return (numerator.divide(denominator)).doubleValue();
    }

    @Override // Implement the abstract longValue method in Number
    public long longValue() {
        return (long)doubleValue();
    }

    @Override // Implement the compareTo method in Comparable
    public int compareTo(PE_13_15_Rational o) {
        if (this.subtract(o).getNumerator().compareTo(BigInteger.ZERO) > 0)
            return 1;
        else if (this.subtract(o).getNumerator().compareTo(BigInteger.ZERO) < 0)
            return -1;
        else
            return 0;
    }
}