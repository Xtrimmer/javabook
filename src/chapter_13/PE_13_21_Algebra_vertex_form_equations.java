package chapter_13;

import java.util.Scanner;

/**
 * (Algebra: vertex form equations) The equation of a parabola can be expressed
 * in either standard form (y = ax2 + bx + c) or vertex form (y = a(x - h)2 + k).
 * Write a program that prompts the user to enter a, b, and c as integers in standard
 * form and displays h and k in the vertex form. Here are some sample runs.
 */
public class PE_13_21_Algebra_vertex_form_equations {
    public static void main(String[] args) {
        double[] values = promptDoubleValue("Enter a, b, c: ", 3);
        PE_13_21_QuadraticEquation quadraticEquation = new PE_13_21_QuadraticEquation(values[0], values[1], values[2]);
        System.out.println("h is " + new PE_13_21_Rational(quadraticEquation.getH())
                + " k is " + new PE_13_21_Rational(quadraticEquation.getK()));
    }

    private static double[] promptDoubleValue(String s, int size) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(s);
        boolean valid;
        double[] input = new double[size];
        do {
            valid = true;
            for (int i = 0; i < size; i++) {
                if (scanner.hasNextDouble()) {
                    input[i] = scanner.nextDouble();
                } else {
                    System.out.println("One or more values are invalid.");
                    valid = false;
                    break;
                }
            }
        } while (!valid);
        return input;
    }
}

class PE_13_21_QuadraticEquation {
    private final double a;
    private final double b;
    private final double c;

    public PE_13_21_QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getH() {
        return -b / (2 * a);
    }

    public double getK() {
        return (a * Math.pow(getH(), 2)) + (b * getH()) + c;
    }

    public double getDiscriminant() {
        return Math.pow(b, 2) - (4 * a * c);
    }

    public double getRoot1() {
        double discriminant = getDiscriminant();
        if (discriminant < 0) return 0;
        else return (-b + Math.sqrt(discriminant)) / (2 * a);
    }

    public double getRoot2() {
        double discriminant = getDiscriminant();
        if (discriminant < 0) return 0;
        else return (-b - Math.sqrt(discriminant)) / (2 * a);
    }

    public String toVertexString() {
        return "y = " + a + "(x - " + getH() + ")^2 + " + getK();
    }

    @Override
    public String toString() {
        return "y = " + a + "x^2 + " + b + "x + " + c;
    }
}

class PE_13_21_Rational extends Number implements Comparable<PE_13_21_Rational> {
    // Data fields for numerator and denominator
    private long numerator = 0;
    private long denominator = 1;

    /** Construct a rational with default properties */
    public PE_13_21_Rational() {
        this(0, 1);
    }

    /** Construct a rational with specified numerator and denominator */
    public PE_13_21_Rational(long numerator, long denominator) {
        long gcd = gcd(numerator, denominator);
        this.numerator = ((denominator > 0) ? 1 : -1) * numerator / gcd;
        this.denominator = Math.abs(denominator) / gcd;
    }

    public PE_13_21_Rational(double decimal) {
        String input = decimal + "";
        String[] number = input.split("\\.");
        int decimalPlaces = number[1].length();
        boolean isNegative = extractNegativity(number);
        long denominator = (long)Math.pow(10, decimalPlaces);
        long integerPart = Long.valueOf(number[0]) * denominator;
        long numerator = Long.valueOf(number[1]) + integerPart;
        if (isNegative) numerator = -numerator;
        long gcd = gcd(numerator, denominator);
        this.numerator = ((denominator > 0) ? 1 : -1) * numerator / gcd;
        this.denominator = Math.abs(denominator) / gcd;
    }

    /** Return numerator */
    public long getNumerator() {
        return numerator;
    }

    /** Return denominator */
    public long getDenominator() {
        return denominator;
    }

    /** Add a rational number to this rational */
    public PE_13_21_Rational add(PE_13_21_Rational secondRational) {
        long n = numerator * secondRational.getDenominator() +
                denominator * secondRational.getNumerator();
        long d = denominator * secondRational.getDenominator();
        return new PE_13_21_Rational(n, d);
    }

    /** Subtract a rational number from this rational */
    public PE_13_21_Rational subtract(PE_13_21_Rational secondRational) {
        long n = numerator * secondRational.getDenominator()
                - denominator * secondRational.getNumerator();
        long d = denominator * secondRational.getDenominator();
        return new PE_13_21_Rational(n, d);
    }

    /** Multiply a rational number to this rational */
    public PE_13_21_Rational multiply(PE_13_21_Rational secondRational) {
        long n = numerator * secondRational.getNumerator();
        long d = denominator * secondRational.getDenominator();
        return new PE_13_21_Rational(n, d);
    }

    /** Divide a rational number from this rational */
    public PE_13_21_Rational divide(PE_13_21_Rational secondRational) {
        long n = numerator * secondRational.getDenominator();
        long d = denominator * secondRational.numerator;
        return new PE_13_21_Rational(n, d);
    }

    @Override
    public String toString() {
        if (denominator == 1)
            return numerator + "";
        else
            return numerator + "/" + denominator;
    }

    @Override // Override the equals method in the Object class
    public boolean equals(Object other) {
        if ((this.subtract((PE_13_21_Rational)(other))).getNumerator() == 0)
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
        return numerator * 1.0 / denominator;
    }

    @Override // Implement the abstract longValue method in Number
    public long longValue() {
        return (long)doubleValue();
    }

    @Override // Implement the compareTo method in Comparable
    public int compareTo(PE_13_21_Rational o) {
        if (this.subtract(o).getNumerator() > 0)
            return 1;
        else if (this.subtract(o).getNumerator() < 0)
            return -1;
        else
            return 0;
    }

    private static boolean extractNegativity(String[] s) {
        if (s[0].charAt(0) == '-') {
            s[0] = s[0].substring(1);
            return true;
        }
        return false;
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
}