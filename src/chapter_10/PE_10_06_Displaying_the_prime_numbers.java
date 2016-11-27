package chapter_10;

import textbook_listings.StackOfIntegers;

/**
 * (Displaying the prime numbers) Write a program that displays all the prime
 * numbers less than 120 in decreasing order. Use the StackOfIntegers class
 * to store the prime numbers (e.g., 2, 3, 5, ...) and retrieve and display them in
 * reverse order.
 */
public class PE_10_06_Displaying_the_prime_numbers {
    public static void main(String[] args) {
        final int NUMBER = 120;
        StackOfIntegers primes = getPrimes(NUMBER);
        printPrimes(primes);
    }

    private static void printPrimes(StackOfIntegers primeFactors) {
        int count = 0;
        while (primeFactors.getSize() > 1) {
            System.out.printf("%d, ", primeFactors.pop());
            count++;
            if (count == 10) {
                System.out.println();
                count = 0;
            }
        }
        System.out.print(primeFactors.pop());
    }

    private static StackOfIntegers getPrimes(int number) {
        StackOfIntegers primes = new StackOfIntegers(32);
        for (int i = number; i > 1; i--) {
            if (isPrime(i)) primes.push(i);
        }
        return primes;
    }

    public static boolean isPrime(int number) {
        for (int divisor = 2; divisor <= number / 2; divisor++) {
            if (number % divisor == 0) { // If true, number is not prime
                return false; // Number is not a prime
            }
        }
        return true; // Number is prime
    }
}
