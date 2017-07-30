package chapter_22;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * (Number of prime numbers) Programming Exercise 22.8 stores the prime numbers
 * in a file named PrimeNumbers.dat. Write a program that finds the number
 * of prime numbers that are less than or equal to 10, 100, 1,000, 10,000,
 * 100,000, 1,000,000, 10,000,000, 100,000,000, 1,000,000,000, and
 * 10,000,000,000. Your program should read the data from PrimeNumbers.dat.
 */
public class PE_22_10_Number_of_prime_numbers {
    private static final String FILE_PATH = "resources/data/PrimeNumbers.dat";

    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_PATH, "r")) {
            long primesInFile = file.length() / 8;
            long primeLimit = 10;
            for (int i = 0; i < primesInFile; i++) {
                long prime = file.readLong();
                if (prime > primeLimit) {
                    System.out.printf("There are %d primes less than or equal to %d%n", i, primeLimit);
                    primeLimit *= 10;
                }
            }
            System.out.printf("There are %d primes less than or equal to %d%n", primesInFile, primeLimit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
