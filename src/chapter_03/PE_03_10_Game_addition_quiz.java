package chapter_03;

import java.util.Scanner;

/**
 * (Game: addition quiz) Listing 3.3, SubtractionQuiz.java, randomly generates a
 * subtraction question. Revise the program to randomly generate an addition question
 * with two integers less than 100.
 */
public class PE_03_10_Game_addition_quiz {
    public static void main(String[] args) {
        int number1 = (int) (Math.random() * 100);
        int number2 = (int) (Math.random() * 100);

        System.out.print("What is " + number1 + " + " + number2 + "? ");
        Scanner input = new Scanner(System.in);
        int answer = input.nextInt();

        System.out.println(number1 + " + " + number2 + " = " + answer + " is "
                + (number1 + number2 == answer));
    }
}
