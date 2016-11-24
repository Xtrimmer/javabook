package chapter_12;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * (InputMismatchException) Write a program that prompts the user to read
 * two integers and displays their sum. Your program should prompt the user to
 * read the number again if the input is incorrect.
 */
public class PE_12_02_InputMismatchException {
    public static void main(String[] args) {
        boolean isValid = false;
        int num1 = 0;
        int num2 = 0;
        do {
            try {
                num1 = promptIntegerValue("1st");
                num2 = promptIntegerValue("2nd");
                isValid = true;
            } catch (InputMismatchException ex) {
                System.out.println("One or both of the operands are not integer values.\nPlease Try again");
            }
        } while (!isValid);
        System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
    }

    public static int promptIntegerValue(String s) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the " + s + " integer: ");
        return scanner.nextInt();
    }
}
