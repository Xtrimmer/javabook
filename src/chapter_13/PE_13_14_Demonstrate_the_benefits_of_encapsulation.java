package chapter_13;

/**
 * (Demonstrate the benefits of encapsulation) Rewrite the Rational class in
 * Listing 13.13 using a new internal representation for the numerator and denominator.
 * Create an array of two integers as follows:
 *
 *      private long[] r = new long[2];
 *
 * Use r[0] to represent the numerator and r[1] to represent the denominator.
 * The signatures of the methods in the Rational class are not changed, so a client
 * application that uses the previous Rational class can continue to use this new
 * Rational class without being recompiled.
 */
public class PE_13_14_Demonstrate_the_benefits_of_encapsulation {
    public static void main(String[] args) {
        PE_13_14_Rational r1 = new PE_13_14_Rational(1, 2);
        PE_13_14_Rational r2 = new PE_13_14_Rational(1, 3);
        PE_13_14_Rational r3 = new PE_13_14_Rational(1, 4);
        PE_13_14_Rational r4 = new PE_13_14_Rational(1, 5);
        PE_13_14_Rational r5 = new PE_13_14_Rational(1, 6);

        System.out.println(r1.add(r2.subtract(r3.multiply(r4.divide(r5)))));
    }
}

class PE_13_14_Rational extends Number implements Comparable<PE_13_14_Rational> {
    // Data fields for numerator and denominator
    private static final int NUMERATOR = 0;
    private static final int DENOMINATOR = 1;
    private long[] r = new long[2];

    /** Construct a rational with default properties */
    public PE_13_14_Rational() {
        this(0, 1);
    }

    /** Construct a rational with specified numerator and denominator */
    public PE_13_14_Rational(long numerator, long denominator) {
        long gcd = gcd(numerator, denominator);
        this.r[NUMERATOR] = ((denominator > 0) ? 1 : -1) * numerator / gcd;
        this.r[DENOMINATOR] = Math.abs(denominator) / gcd;
    }

    /** Find GCD of two numbers */
    private static long gcd(long n, long d) {
        long n1 = Math.abs(n);
        long n2 = Math.abs(d);
        int gcd = 1;

        for (int k = 1; k <= n1 && k <= n2; k++) {
            if (n1 % k == 0 && n2 % k == 0)
                gcd = k;
        }

        return gcd;
    }

    /** Return numerator */
    public long getNumerator() {
        return r[NUMERATOR];
    }

    /** Return denominator */
    public long getDenominator() {
        return r[DENOMINATOR];
    }

    /** Add a rational number to this rational */
    public PE_13_14_Rational add(PE_13_14_Rational secondRational) {
        long n = r[NUMERATOR] * secondRational.getDenominator() +
                r[DENOMINATOR] * secondRational.getNumerator();
        long d = r[DENOMINATOR] * secondRational.getDenominator();
        return new PE_13_14_Rational(n, d);
    }

    /** Subtract a rational number from this rational */
    public PE_13_14_Rational subtract(PE_13_14_Rational secondRational) {
        long n = r[NUMERATOR] * secondRational.getDenominator()
                - r[DENOMINATOR] * secondRational.getNumerator();
        long d = r[DENOMINATOR] * secondRational.getDenominator();
        return new PE_13_14_Rational(n, d);
    }

    /** Multiply a rational number to this rational */
    public PE_13_14_Rational multiply(PE_13_14_Rational secondRational) {
        long n = r[NUMERATOR] * secondRational.getNumerator();
        long d = r[DENOMINATOR] * secondRational.getDenominator();
        return new PE_13_14_Rational(n, d);
    }

    /** Divide a rational number from this rational */
    public PE_13_14_Rational divide(PE_13_14_Rational secondRational) {
        long n = r[NUMERATOR] * secondRational.getDenominator();
        long d = r[DENOMINATOR] * secondRational.r[NUMERATOR];
        return new PE_13_14_Rational(n, d);
    }

    @Override
    public String toString() {
        if (r[DENOMINATOR] == 1)
            return r[NUMERATOR] + "";
        else
            return r[NUMERATOR] + "/" + r[DENOMINATOR];
    }

    @Override // Override the equals method in the Object class
    public boolean equals(Object other) {
        if ((this.subtract((PE_13_14_Rational)(other))).getNumerator() == 0)
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
        return r[NUMERATOR] * 1.0 / r[DENOMINATOR];
    }

    @Override // Implement the abstract longValue method in Number
    public long longValue() {
        return (long)doubleValue();
    }

    @Override // Implement the compareTo method in Comparable
    public int compareTo(PE_13_14_Rational o) {
        if (this.subtract(o).getNumerator() > 0)
            return 1;
        else if (this.subtract(o).getNumerator() < 0)
            return -1;
        else
            return 0;
    }
}