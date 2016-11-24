package chapter_08;

import java.util.Scanner;

/**
 * (Game: play a tic-tac-toe game) In a game of tic-tac-toe, two players take turns
 * marking an available cell in a 3 * 3 grid with their respective tokens (either
 * X or O). When one player has placed three tokens in a horizontal, vertical, or
 * diagonal row on the grid, the game is over and that player has won. A draw (no
 * winner) occurs when all the cells on the grid have been filled with tokens and
 * neither player has achieved a win. Create a program for playing tic-tac-toe.
 * The program prompts two players to enter an X token and O token alternately.
 * Whenever a token is entered, the program redisplays the board on the
 * console and determines the status of the game (win, draw, or continue). Here
 * is a sample run:
 *
 *      ——————-——————
 *      |     |      |     |
 *      ——————-——————
 *      |     |      |     |
 *      ——————-——————
 *      |     |      |     |
 *      ——————-——————
 *
 *      Enter a row (0, 1, or 2) for player X: 1
 *      Enter a column (0, 1, or 2) for player X: 1
 *
 *      ——————-——————
 *      |     |      |     |
 *      ——————-——————
 *      |     |  X   |     |
 *      ——————-——————
 *      |     |      |     |
 *      ——————-——————
 *
 *      Enter a row (0, 1, or 2) for player O: 1
 *      Enter a column (0, 1, or 2) for player O: 2
 *
 *      ——————-——————
 *      |     |      |     |
 *      ——————-——————
 *      |     |  X   |  O  |
 *      ——————-——————
 *      |     |      |     |
 *      ——————-——————
 *
 *      Enter a row (0, 1, or 2) for player X:
 *      . . .
 *
 *      ——————-——————
 *      |  X  |      |     |
 *      ——————-——————
 *      |  O  |  X   |  O  |
 *      ——————-——————
 *      |     |      |  X  |
 *      ——————-——————
 *
 *      X player won
 */
public class PE_08_09_Game_play_a_tic_tac_toe_game {
    public static void main(String[] args) {
        playTicTacToe();
    }

    public static boolean hasWinner(int[][] board) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) ||
                    (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i])) {
                return true;
            }
        }
        return board[1][1] != 0 && ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                board[0][2] == board[1][1] && board[1][1] == board[2][0]);
    }

    public static boolean hasNextPlay(int[][] board) {
        if (hasWinner(board)) return false;
        for (int[] aBoard : board) {
            for (int anABoard : aBoard) {
                if (anABoard == 0) return true;
            }
        }
        return false;
    }

    public static void printBoard(int[][] board) {
        System.out.println("-------------");
        for (int[] aBoard : board) {
            System.out.print("|");
            for (int anABoard : aBoard) {
                System.out.print(" ");
                if (anABoard == -1) System.out.print("O");
                if (anABoard == 0) System.out.print(" ");
                if (anABoard == 1) System.out.print("X");
                System.out.print(" |");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public static void playTicTacToe() {
        int[][] board = new int[3][3];
        int player = -1;
        char token;
        do {
            player = -player;
            token = player == 1 ? 'X' : 'O';
            printBoard(board);

            boolean isValidMove;
            do {
                isValidMove = true;
                int[] location = getPlayLocation(token);
                if (board[location[0]][location[1]] == 0) {
                    board[location[0]][location[1]] = player;
                } else {
                    System.out.println("Invalid play. That location has already been played");
                    isValidMove = false;
                }
            } while (!isValidMove);
        } while(hasNextPlay(board));
        printBoard(board);
        if (hasWinner(board)) {
            System.out.println(token + " player won");
        } else {
            System.out.println("It's a draw");
        }
    }

    public static int[] getPlayLocation(char token){
        Scanner scanner = new Scanner(System.in);
        int[] location = new int[2];
        System.out.print("Enter a row (0, 1, or 2) for player " + token + ": ");
        location[0] = scanner.nextInt();
        System.out.print("Enter a column (0, 1, or 2) for player " + token + ": ");
        location[1] = scanner.nextInt();
        return location;
    }
}
