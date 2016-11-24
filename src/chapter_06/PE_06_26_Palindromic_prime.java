package chapter_06;

/**
 * (Palindromic prime) A palindromic prime is a prime number and also palindromic.
 * For example, 131 is a prime and also a palindromic prime, as are 313 and
 * 757. Write a program that displays the first 100 palindromic prime numbers. Display
 * 10 numbers per line, separated by exactly one space, as follows:
 *
 *      2 3 5 7 11 101 131 151 181 191
 *      313 353 373 383 727 757 787 797 919 929
 *      ...
 */
public class PE_06_26_Palindromic_prime {
    public static void main(String[] args) {
        int primeCount = 0;
        int number = 2;
        do {
            if (isPrime(number) && isPalindrome(number)) {
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
