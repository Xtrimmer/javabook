package chapter_11;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * (Algebra: perfect square) Write a program that prompts the user to enter an integer
 * m and find the smallest integer n such that m * n is a perfect square. (Hint:
 * Store all smallest factors of m into an array list. n is the product of the factors that
 * appear an odd number of times in the array list. For example, consider m = 90,
 * store the factors 2, 3, 3, 5 in an array list. 2 and 5 appear an odd number of times
 * in the array list. So, n is 10.) Here are sample runs:
 *
 *      Enter an integer m: 1500 (enter)
 *      The smallest number n for m * n to be a perfect square is 15
 *      m * n is 22500
 *
 *      Enter an integer m: 63
 *      The smallest number n for m * n to be a perfect square is 7
 *      m * n is 441
 */
public class PE_11_17_Algebra_perfect_square {
    public static void main(String[] args) {
        int m = promptInteger();
        int n = findN(m);
        System.out.println("The smallest number n for m * n to be a perfect square is " + n);
        System.out.println("m * n is " + (m * n));
    }

    private static int findN(int number) {
        ArrayList<Integer> factors = getFactors(number);
        ArrayList<Integer> oddCountFactors = getOddCountFactors(factors);
        return product(oddCountFactors);
    }

    private static int product(ArrayList<Integer> oddFactors) {
        int n = 1;
        for (Integer oddFactor : oddFactors) {
            n *= oddFactor;
        }
        return n;
    }

    private static ArrayList<Integer> getOddCountFactors(ArrayList<Integer> factors) {
        ArrayList<Integer> oddFactors = new ArrayList<>();
        for (Integer factor : factors) {
            int count = 0;
            for (Integer integer : factors) {
                if (integer.equals(factor)) count++;
            }
            if (count % 2 == 1 && !oddFactors.contains(factor)) oddFactors.add(factor);
        }
        return oddFactors;
    }

    public static ArrayList<Integer> getFactors(int number) {
        ArrayList<Integer> factors = new ArrayList<>();
        while(number > 1){
            int i = 2;
            while (number % i != 0) i++;
            factors.add(i);
            number /= i;
        }
        return factors;
    }

    public static int promptInteger() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer m: ");
        return scanner.nextInt();
    }
}
