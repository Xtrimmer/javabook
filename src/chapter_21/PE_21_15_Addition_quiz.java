package chapter_21;

import java.util.HashSet;
import java.util.Scanner;

/**
 * (Addition quiz) Rewrite Programming Exercise 11.16 to store the answers in a
 * set rather than a list.
 */
public class PE_21_15_Addition_quiz {
    public static void main(String[] args) {
        int number1 = (int) (Math.random() * 10);
        int number2 = (int) (Math.random() * 10);
        HashSet<Integer> answers = new HashSet<>();
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
