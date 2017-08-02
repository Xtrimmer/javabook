package chapter_22;

import java.util.List;

/**
 * (Execution time for prime numbers) Write a program that obtains the execution
 * time for finding all the prime numbers less than 8,000,000, 10,000,000,
 * 12,000,000, 14,000,000, 16,000,000, and 18,000,000 using the algorithms in
 * Listings 22.5–22.7. Your program should print a table like this:
 *
 *              | 8000000 10000000 12000000 14000000 16000000 18000000
 * --------------------------------------------------------------------
 * Listing 22.5 |
 * Listing 22.6 |
 * Listing 22.7 |
 */
public class PE_22_14_Execution_time_for_prime_numbers {

    public static void main(String[] args) {
        System.out.println("             | 8000000 10000000 12000000 14000000 16000000 18000000");
        System.out.print("--------------------------------------------------------------------");

        PrimeScanner[] PrimeScanners = {new Listing22_5(), new Listing22_6(), new Listing22_7()};
        for (int i = 0; i < 3; i++) {
            System.out.printf("\nListing 22.%d |", i + 5);
            PrimeScanners[i].scan(18000000);
        }
    }

    interface PrimeScanner {
        void scan(int n);
    }

    static class Listing22_5 implements PrimeScanner {
        @Override
        public void scan(int n) {
            long startTime = System.currentTimeMillis();
            int threshold = 8000000;
            int number = 2;

            // Repeatedly find prime numbers
            while (number <= n) {

                // ClosestPair if number is prime
                for (int divisor = 2; divisor <= (int) (Math.sqrt(number));
                     divisor++) {
                    if (number % divisor == 0) { // If true, number is not prime
                        break; // Exit the for loop
                    }
                }

                if (number == threshold) {
                    System.out.printf("%8d ", System.currentTimeMillis() - startTime);
                    threshold += 2000000;
                }

                // Check if the next number is prime
                number++;
            }
        }
    }

    static class Listing22_6 implements PrimeScanner {
        @Override
        public void scan(int n) {
            long startTime = System.currentTimeMillis();
            int threshold = 8000000;
            // A list to hold prime numbers
            List<Integer> list = new java.util.ArrayList<>();

            int number = 2; // A number to be tested for primeness
            int squareRoot = 1; // Check whether number <= squareRoot

            // Repeatedly find prime numbers
            while (number <= n) {
                // Assume the number is prime
                boolean isPrime = true; // Is the current number prime?

                if (squareRoot * squareRoot < number) squareRoot++;

                // ClosestPair if number is prime
                for (int k = 0; k < list.size()
                        && list.get(k) <= squareRoot; k++) {
                    if (number % list.get(k) == 0) { // If true, not prime
                        isPrime = false; // Set isPrime to false
                        break; // Exit the for loop
                    }
                }

                if (isPrime) {
                    list.add(number); // Add a new prime to the list
                }

                if (number == threshold) {
                    System.out.printf("%8d ", System.currentTimeMillis() - startTime);
                    threshold += 2000000;
                }

                // Check if the next number is prime
                number++;
            }
        }
    }

    static class Listing22_7 implements PrimeScanner {
        @Override
        public void scan(int n) {
            long startTime = System.currentTimeMillis();
            int threshold = 8000000;
            boolean[] primes = new boolean[n + 1]; // Prime number sieve

            // Initialize primes[i] to true
            for (int i = 0; i < primes.length; i++) {
                primes[i] = true;
            }

            for (int k = 2; k <= n / k; k++) {
                if (primes[k]) {
                    for (int i = k; i <= n / k; i++) {
                        primes[k * i] = false; // k * i is not prime
                        if (k * i == threshold) {
                            System.out.printf("%8d ", System.currentTimeMillis() - startTime);
                            threshold += 2000000;
                        }
                    }
                }
            }
        }
    }
}