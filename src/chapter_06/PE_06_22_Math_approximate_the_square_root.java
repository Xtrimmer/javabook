package chapter_06;

import java.util.Scanner;

/**
 * (Math: approximate the square root) There are several techniques for implementing
 * the sqrt method in the Math class. One such technique is known as the
 * Babylonian method. It approximates the square root of a number, n, by repeatedly
 * performing a calculation using the following formula:
 *
 *      nextGuess = (lastGuess + n / lastGuess) / 2
 *
 * When nextGuess and lastGuess are almost identical, nextGuess is the
 * approximated square root. The initial guess can be any positive value (e.g., 1).
 * This value will be the starting value for lastGuess. If the difference between
 * nextGuess and lastGuess is less than a very small number, such as 0.0001,
 * you can claim that nextGuess is the approximated square root of n. If not, next-
 * Guess becomes lastGuess and the approximation process continues. Implement
 * the following method that returns the square root of n.
 *
 *      public static double sqrt(long n)
 */
public class PE_06_22_Math_approximate_the_square_root {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        long num = scanner.nextLong();
        System.out.printf("The square root is: %.3f", sqrt(num));
    }

    public static double sqrt(long n) {
        double lastGuess = 1;
        double nextGuess = (lastGuess + n / lastGuess) / 2;
        while (Math.abs(nextGuess - lastGuess) > 0.0001) {
            lastGuess = nextGuess;
            nextGuess = (lastGuess + n / lastGuess) / 2;
        }
        return nextGuess;
    }
}
