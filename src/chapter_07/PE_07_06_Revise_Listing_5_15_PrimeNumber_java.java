package chapter_07;

/**
 * (Revise Listing 5.15, PrimeNumber.java) Listing 5.15 determines whether a number
 * n is prime by checking whether 2, 3, 4, 5, 6, . . . , n/2 is a divisor. If a divisor
 * is found, n is not prime. A more efficient approach is to check whether any of the
 * prime numbers less than or equal to √n can divide n evenly. If not, n is prime.
 * Rewrite Listing 5.15 to display the first 50 prime numbers using this approach.
 * You need to use an array to store the prime numbers and later use them to check
 * whether they are possible divisors for n.
 */
public class PE_07_06_Revise_Listing_5_15_PrimeNumber_java {
    public static void main(String[] args) {
        printNumbers(generatePrimes(100), 5);
    }

    public static void printNumbers(int[] array, int numbersPerRow) {
        for (int i = 0; i < array.length; i++) {
            if ((i+1) % numbersPerRow == 0) System.out.println(array[i]);
            else System.out.print(array[i] + " ");
        }
    }

    public static int[] generatePrimes(int numberOfPrimesToGenerate) {
        int primeCount = 1;
        int[] primes = new int[numberOfPrimesToGenerate];
        int n = 3;
        primes[0] = 2;
        while (primeCount < numberOfPrimesToGenerate) {
            int sqrtN = (int)Math.sqrt(n);
            for (int j = 0; j < primeCount; j++) {
                if (primes[j] > sqrtN){
                    primes[primeCount] = n;
                    primeCount++;
                    break;
                }
                if (n % primes[j] == 0) break;
            }
            n++;
        }
        return primes;
    }
}
