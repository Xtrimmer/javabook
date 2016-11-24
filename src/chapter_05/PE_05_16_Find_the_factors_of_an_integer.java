package chapter_05;

import java.util.Scanner;

/**
 * (Find the factors of an integer) Write a program that reads an integer and displays
 * all its smallest factors in increasing order. For example, if the input integer is
 * 120, the output should be as follows: 2, 2, 2, 3, 5.
 */
public class PE_05_16_Find_the_factors_of_an_integer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a positive Integer: ");
        int num = scanner.nextInt();
        while(num > 1){
            int i = 2;
            while (num % i != 0) i++;
            System.out.print(i);
            if (num != i) System.out.print(", ");
            num /= i;
        }
    }
}
