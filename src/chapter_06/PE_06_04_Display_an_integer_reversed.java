package chapter_06;

import java.util.Scanner;

/**
 * (Display an integer reversed) Write a method with the following header to display
 * an integer in reverse order:
 *
 *      public static void reverse(int number)
 *
 * For example, reverse(3456) displays 6543. Write a test program that prompts
 * the user to enter an integer and displays its reversal.
 */
public class PE_06_04_Display_an_integer_reversed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int input = scanner.nextInt();
        reverse(input);
    }

    public static void reverse(int number){
        int reverse = 0;
        while (number > 0){
            reverse = reverse * 10 + number % 10;
            number /= 10;
        }
        System.out.println("It's reversal is " + reverse);
    }
}
