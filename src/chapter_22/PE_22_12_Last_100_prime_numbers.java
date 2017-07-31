package chapter_22;

import java.io.*;

/**
 * (Last 100 prime numbers) Programming Exercise 22.8 stores the prime numbers
 * in a file named PrimeNumbers.dat. Write an efficient program that reads
 * the last 100 numbers in the file. (Hint: Don’t read all numbers from the file.
 * Skip all numbers before the last 100 numbers in the file.)
 */
public class PE_22_12_Last_100_prime_numbers {
    private static final File file = new File("resources/data/PrimeNumbers.dat");

    public static void main(String[] args) throws FileNotFoundException {
        PrimeNumbers primeNumbers = new PrimeNumbers(file);
        for (long i = primeNumbers.size() - 100; i < primeNumbers.size(); i++) {
            System.out.printf("%7d ", primeNumbers.get(i));
        }
    }

    static class PrimeNumbers {
        private static final int LENGTH = 100;
        private static final int BYTES_IN_LONG = Long.SIZE / Byte.SIZE;
        private final long[] array = new long[LENGTH];
        private final long size;
        private final File file;
        private int page = -1;

        public PrimeNumbers(File file) {
            this.file = file;
            this.size = file.length() / (long) BYTES_IN_LONG;
        }

        public long get(long index) {
            if (index >= size || index < 0) throw new IndexOutOfBoundsException();
            if (index / LENGTH != page) {
                loadPage((int) (index / LENGTH));
            }
            return array[(int) (index % LENGTH)];
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
