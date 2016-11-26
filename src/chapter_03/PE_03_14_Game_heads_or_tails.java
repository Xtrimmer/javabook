package chapter_03;

import java.util.Scanner;

/**
 * (Game: heads or tails) Write a program that lets the user guess whether the flip of
 * a coin results in heads or tails. The program randomly generates an integer 0 or 1,
 * which represents head or tail. The program prompts the user to enter a guess and
 * reports whether the guess is correct or incorrect.
 */
public class PE_03_14_Game_heads_or_tails {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        System.out.print("Enter [0] for heads or [1] for tails: ");
        int guess = SCANNER.nextInt();
        int flip = (int) (Math.random() * 2);
        String result;
        if (flip == 0) {
            result = "Heads";
        } else {
            result = "Tails";
        }
        if (guess == flip) {
            System.out.println("The coin flip resulted in " + result + ". You win!");
        } else {
            System.out.println("The coin flip resulted in " + result + ". You lose!");
        }
    }
}
