package chapter_22;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * (All prime numbers up to 10,000,000,000) Write a program that finds
 * all prime numbers up to 10,000,000,000. There are approximately
 * 455,052,511 such prime numbers. Your program should meet the following
 * requirements:
 *
 * - Your program should store the prime numbers in a binary data file, named
 *   PrimeNumbers.dat. When a new prime number is found, the number is
 *   appended to the file.
 * - To find whether a new number is prime, your program should load the
 *   prime numbers from the file to an array of the long type of size 10000.
 *   If no number in the array is a divisor for the new number, continue to read
 *   the next 10000 prime numbers from the data file, until a divisor is found
 *   or all numbers in the file are read. If no divisor is found, the new number
 *   is prime.
 * - Since this program takes a long time to finish, you should run it as a batch
 *   job from a UNIX machine. If the machine is shut down and rebooted, your
 *   program should resume by using the prime numbers stored in the binary data
 *   file rather than start over from scratch.
 */
public class PE_22_08_All_prime_numbers_up_to_10_billion {
    private static final long SEARCH_LIMIT = 10000000000L;
    private static final File file = new File("resources/data/PrimeNumbers.dat");

    public static void main(String[] args) throws FileNotFoundException {
        PrimeNumbers primeNumbers = new PrimeNumbers(file);
        long largestPrime = getLargestPrime(primeNumbers);
        while (++largestPrime <= SEARCH_LIMIT) {
            if (isPrime(largestPrime, primeNumbers)) primeNumbers.add(largestPrime);
        }
        System.out.printf("All primes up to %,d have been found.", SEARCH_LIMIT);
    }

    private static long getLargestPrime(PrimeNumbers primeNumbers) {
        if (primeNumbers.size() == 0) return 1;
        return primeNumbers.get(primeNumbers.size() - 1);
    }

    private static boolean isPrime(long primeCandidate, PrimeNumbers primeNumbers) {
        long squareRoot = (long) Math.sqrt(primeCandidate);
        for (Long primeNumber : primeNumbers) {
            if (primeNumber > squareRoot) break;
            if (primeCandidate % primeNumber == 0) return false;
        }
        return true;
    }

    static class PrimeNumbers implements Iterable<Long> {
        private static final int LENGTH = 10000;
        private static final int BYTES_IN_LONG = Long.SIZE / Byte.SIZE;
        private final long[] array = new long[LENGTH];
        private DataOutputStream outputStream;
        private long size;
        private int page = 0;
        private File file;

        public PrimeNumbers(File file) throws FileNotFoundException {
            outputStream = new DataOutputStream(new FileOutputStream(file, true));
            this.file = file;
            this.size = file.length() / (long) BYTES_IN_LONG;
            loadPage(45517);
        }

        public PrimeNumbers add(long prime) {
            try {
                outputStream.writeLong(prime);
                if (page == size / LENGTH) {
                    array[(int) (size % LENGTH)] = prime;
                }
                size++;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this;
        }

        public long get(long index) {
            if (index >= size || index < 0) throw new IndexOutOfBoundsException();
            if (index / LENGTH != page) {
                loadPage((int) (index / LENGTH));
            }
            return array[(int) (index % LENGTH)];
        }

        @Override
        public Iterator<Long> iterator() {
            return new Iterator<Long>() {
                private long cursor = 0;

                @Override
                public boolean hasNext() {
                    return cursor < size;
                }

                @Override
                public Long next() {
                    if (!this.hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return get(cursor++);
                }
            };
        }

        public long size() {
            return size;
        }

        private void loadPage(int page) {
            try (DataInputStream inputStream = new DataInputStream(new FileInputStream(file))) {
                inputStream.skip(page * LENGTH * (long) BYTES_IN_LONG);
                int i;
                try {
                    for (i = 0; i < array.length; i++) {
                        array[i] = inputStream.readLong();
                    }
                } catch (EOFException ignored) {
                }
                this.page = page;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
