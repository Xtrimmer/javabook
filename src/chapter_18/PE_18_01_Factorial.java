package chapter_18;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * (Factorial) Using the BigInteger class introduced in Section 10.9, you can
 * find the factorial for a large number (e.g., 100!). Implement the factorial
 * method using recursion. Write a program that prompts the user to enter an integer
 * and displays its factorial.
 */
public class PE_18_01_Factorial {
    public static void main(String[] args) {
        BigInteger input = promptIntegerValue();
        System.out.print(factorial(input));
    }

    private static BigInteger factorial(BigInteger n) {
        return factorial(n, BigInteger.ONE);
    }

    private static BigInteger factorial(BigInteger n, BigInteger result) {
        if (n.equals(BigInteger.ZERO))
            return result;
        else
            return factorial(n.subtract(BigInteger.ONE), n.multiply(result));
    }

    private static BigInteger promptIntegerValue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        return new BigInteger(scanner.nextLine());
    }
}
