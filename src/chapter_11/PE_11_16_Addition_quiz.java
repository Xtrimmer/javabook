package chapter_11;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * (Addition quiz) Rewrite Listing 5.1 RepeatAdditionQuiz.java to alert the user
 * if an answer is entered again. Hint: use an array list to store answers. Here is a
 * sample run:
 *
 *      What is 5 + 9? 12 (enter)
 *      Wrong answer. Try again. What is 5 + 9? 34 (enter)
 *      Wrong answer. Try again. What is 5 + 9? 12 (enter)
 *      You already entered 12
 *      Wrong answer. Try again. What is 5 + 9? 14 (enter)
 *      You got it!
 */
public class PE_11_16_Addition_quiz {
    public static void main(String[] args) {
        int number1 = (int) (Math.random() * 10);
        int number2 = (int) (Math.random() * 10);
        ArrayList<Integer> answers = new ArrayList<>();
        // Create a Scanner
        Scanner input = new Scanner(System.in);
        System.out.print(
                "What is " + number1 + " + " + number2 + "? ");
        int answer = input.nextInt();
        while (number1 + number2 != answer) {
            if (answers.contains(answer)) {
                System.out.println("You already entered " + answer);
            } else {
                answers.add(answer);
            }
            System.out.print("Wrong answer. Try again. What is "
                    + number1 + " + " + number2 + "? ");
            answer = input.nextInt();
        }
        System.out.println("You got it!");
    }
}
