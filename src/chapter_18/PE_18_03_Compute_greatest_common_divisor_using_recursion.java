package chapter_18;

import java.util.Scanner;

/**
 * (Compute greatest common divisor using recursion) The gcd(m, n) can also
 * be defined recursively as follows:
 *
 * - If m % n is 0, gcd(m, n) is n.
 * - Otherwise, gcd(m, n) is gcd(n, m % n).
 *
 * Write a recursive method to find the GCD. Write a test program that prompts the
 * user to enter two integers and displays their GCD.
 */
public class PE_18_03_Compute_greatest_common_divisor_using_recursion {
    public static void main(String[] args) {
        int[] values = promptIntegerValue();
        System.out.println("The GCD is: " + gcd(values[0], values[1]));
    }

    private static int gcd(int m, int n) {
        if (m % n == 0) {
            return n;
        }
        return gcd(n, m % n);
    }

    private static int[] promptIntegerValue() {
        int[] values = new int[2];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter two integers: ");
        if (scanner.hasNextInt()) {
            values[0] = scanner.nextInt();
        }
        if (scanner.hasNextInt()) {
            values[1] = scanner.nextInt();
        }
        return values;
    }
}
