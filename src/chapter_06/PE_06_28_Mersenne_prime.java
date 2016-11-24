package chapter_06;

/**
 * (Mersenne prime) A prime number is called a Mersenne prime if it can be written
 * in the form 2p - 1 for some positive integer p. Write a program that finds all
 * Mersenne primes with p … 31 and displays the output as follows:
 *
 *      p     2^p –1
 *      ------------
 *      2     3
 *      3     7
 *      5     31
 *      ...
 */
public class PE_06_28_Mersenne_prime {
    public static void main(String[] args) {
        System.out.println("p     2^p –1");
        System.out.println("------------");
        for (int i = 2; i <= 31; i++) {
            if (isPrime(i) && isPrime((int)(Math.pow(2, i) - 1))) {
                System.out.printf("%-6d%d%n", i, (int)(Math.pow(2, i) - 1));
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
