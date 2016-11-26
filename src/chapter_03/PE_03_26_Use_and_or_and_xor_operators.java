package chapter_03;

import java.util.Scanner;

/**
 * (Use the &&, || and ^ operators) Write a program that prompts the user to enter
 * an integer and determines whether it is divisible by 5 and 6, whether it is divisible
 * by 5 or 6, and whether it is divisible by 5 or 6, but not both.
 */
public class PE_03_26_Use_and_or_and_xor_operators {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int number = SCANNER.nextInt();
        boolean bOr = false;
        boolean bAnd = false;
        boolean bXor = false;
        if (number % 5 == 0 || number % 6 == 0) bOr = true;
        if (number % 5 == 0 && number % 6 == 0) bAnd = true;
        if (number % 5 == 0 ^ number % 6 == 0) bXor = true;
        System.out.println("Is 10 divisible by 5 and 6? " + bAnd);
        System.out.println("Is 10 divisible by 5 or 6? " + bOr);
        System.out.println("Is 10 divisible by 5 or 6, but not both? " + bXor);
    }
}
