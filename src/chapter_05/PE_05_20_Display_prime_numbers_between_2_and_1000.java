package chapter_05;

/**
 * (Display prime numbers between 2 and 1,000) Modify Listing 5.15 to display all
 * the prime numbers between 2 and 1,000, inclusive. Display eight prime numbers
 * per line. Numbers are separated by exactly one space.
 */
public class PE_05_20_Display_prime_numbers_between_2_and_1000 {
    public static void main(String[] args) {
        final int NUMBER_OF_PRIMES_PER_LINE = 8; // Display 10 per line
        int count = 0; // Count the number of prime numbers

        // Repeatedly find prime numbers
        for (int i = 2; i <= 1000; i++) {
            // Assume the number is prime
            boolean isPrime = true; // Is the current number prime?

            // Test whether number is prime
            for (int divisor = 2; divisor <= i / 2; divisor++) {
                if (i % divisor == 0) { // If true, number is not prime
                    isPrime = false; // Set isPrime to false
                    break; // Exit the for loop
                }
            }

            // Display the prime number and increase the count
            if (isPrime) {
                count++; // Increase the count

                if (count % NUMBER_OF_PRIMES_PER_LINE == 0) {
                    // Display the number and advance to the new line
                    System.out.printf(" %3d%n", i);
                } else
                    System.out.printf(" %3d", i);
            }
        }
    }
}
