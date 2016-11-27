package chapter_10;

import textbook_listings.StackOfIntegers;

import java.util.Scanner;

/**
 * (Displaying the prime factors) Write a program that prompts the user to enter
 * a positive integer and displays all its smallest factors in decreasing order. For
 * example, if the integer is 120, the smallest factors are displayed as 5, 3, 2, 2,
 * 2. Use the StackOfIntegers class to store the factors (e.g., 2, 2, 2, 3, 5) and
 * retrieve and display them in reverse order.
 */
public class PE_10_05_Displaying_the_prime_factors {
    public static void main(String[] args) {
        int number = promptInteger();
        StackOfIntegers primeFactors = getFactors(number);
        printFactors(primeFactors);
    }

    private static void printFactors(StackOfIntegers primeFactors) {
        System.out.print(primeFactors.pop());
        while (primeFactors.getSize() > 0) {
            System.out.printf(", %d", primeFactors.pop());
        }
    }

    public static int promptInteger() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a positive Integer: ");
        return scanner.nextInt();
    }

    public static StackOfIntegers getFactors(int number) {
        StackOfIntegers stackOfIntegers = new StackOfIntegers();
        while(number > 1){
            int i = 2;
            while (number % i != 0) i++;
            stackOfIntegers.push(i);
            number /= i;
        }
        return stackOfIntegers;
    }
}
