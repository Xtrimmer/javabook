package chapter_06;

/**
 * (Twin primes) Twin primes are a pair of prime numbers that differ by 2. For example,
 * 3 and 5 are twin primes, 5 and 7 are twin primes, and 11 and 13 are twin primes.
 * Write a program to find all twin primes less than 1,000. Display the output as follows:
 *
 *      (3, 5)
 *      (5, 7)
 *      ...
 */
public class PE_06_29_Twin_primes {
    public static void main(String[] args) {
        for (int i = 3; i <= 998; i++) {
            if (isPrime(i) && isPrime(i + 2)) {
                System.out.println("(" + i + ", " + (i + 2) + ")");
            }
        }
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
