package chapter_06;

/**
 * (Emirp) An emirp (prime spelled backward) is a nonpalindromic prime number
 * whose reversal is also a prime. For example, 17 is a prime and 71 is a prime, so 17
 * and 71 are emirps. Write a program that displays the first 100 emirps. Display 10
 * numbers per line, separated by exactly one space, as follows:
 *
 *      13 17 31 37 71 73 79 97 107 113
 *      149 157 167 179 199 311 337 347 359 389
 *      ...
 */
public class PE_06_27_Emirp {
    public static void main(String[] args) {
        int primeCount = 0;
        int number = 2;
        do {
            if (isPrime(number) && isPrime(reverse(number)) && !isPalindrome(number)) {
                System.out.print(number + " ");
                primeCount++;
                if (primeCount % 10 == 0) System.out.println();
            }
            number++;
        } while (primeCount < 100);
    }

    public static boolean isPrime(int number) {
        for (int divisor = 2; divisor <= number / 2; divisor++) {
            if (number % divisor == 0) { // If true, number is not prime
                return false; // Number is not a prime
            }
        }
        return true; // Number is prime
    }

    public static int reverse(int number){
        int reverse = 0;
        while (number > 0){
            reverse = reverse * 10 + number % 10;
            number /= 10;
        }
        return reverse;
    }

    public static boolean isPalindrome(int number){
        return number == reverse(number);
    }
}
