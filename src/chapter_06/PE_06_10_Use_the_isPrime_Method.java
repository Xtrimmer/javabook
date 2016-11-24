package chapter_06;

/**
 * (Use the isPrime Method) Listing 6.7, PrimeNumberMethod.java, provides the
 * isPrime(int number) method for testing whether a number is prime. Use this
 * method to find the number of prime numbers less than 10000.
 */
public class PE_06_10_Use_the_isPrime_Method {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 2; i <= 10000; i++) {
            if (isPrime(i)) count++;
        }
        System.out.println("The number of primes less than 10,000 is " + count);
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
