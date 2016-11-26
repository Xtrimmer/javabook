package chapter_03;

import java.util.Scanner;

/**
 * (Game: add three numbers) The program in Listing 3.1 generates two integers and
 * prompts the user to enter the sum of these two integers. Revise the program to
 * generate three single-digit integers and prompt the user to enter the sum of these
 * three integers.
 */
public class PE_03_02_Game_add_three_numbers {

    public static void main(String[] args) {
        int number1 = (int) (System.currentTimeMillis() % 10);
        int number2 = (int) (System.currentTimeMillis() * 7 % 10);
        int number3 = (int) (System.currentTimeMillis() / 7 % 10);

        // Create a Scanner
        Scanner input = new Scanner(System.in);

        System.out.print(
                "What is " + number1 + " + " + number2 + " + " + number3 + "? ");

        int answer = input.nextInt();

        System.out.println(
                number1 + " + " + number2 + " + " + number3 + " = " + answer + " is "
                        + (number1 + number2 + number3 == answer)
        );
    }
}
