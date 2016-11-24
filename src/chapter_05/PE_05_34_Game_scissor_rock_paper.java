package chapter_05;

import java.util.Scanner;

/**
 * (Game: scissor, rock, paper) Programming Exercise 3.17 gives a program that
 * plays the scissor-rock-paper game. Revise the program to let the user continuously
 * play until either the user or the computer wins more than two times than its
 * opponent.
 */
public class PE_05_34_Game_scissor_rock_paper {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        int score = 0;
        while (score <= 2 && score >= -2) {
            int computer = (int)(Math.random() * 3);
            System.out.print("scissor (0), rock (1), paper (2): ");
            int player = SCANNER.nextInt();
            String msg1 = "The computer is ";
            String msg2 = "You are ";
            String msg3;
            switch (computer){
                case 0:
                    msg1 += "scissor. ";
                    break;
                case 1:
                    msg1 += "rock. ";
                    break;
                case 2:
                    msg1 += "paper. ";
                    break;
            }
            switch (player){
                case 0:
                    msg2 += "scissor";
                    break;
                case 1:
                    msg2 += "rock";
                    break;
                case 2:
                    msg2 += "paper";
                    break;
            }
            if (computer == player){
                msg2 += " too. ";
                msg3 = "It is a draw";
            } else if (player == computer + 1 || player == computer - 2){
                msg2 += ". ";
                msg3 = "You won this round";
                score++;
            } else {
                msg2 += ". ";
                msg3 = "You lost this round";
                score--;
            }
            System.out.println(msg1 + msg2 + "\n" + msg3);
        }
        if (score > 0) System.out.println("You win the series");
        else System.out.println("The computer won the series");
    }
}
