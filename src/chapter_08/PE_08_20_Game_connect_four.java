package chapter_08;

import java.util.Scanner;

/**
 * (Game: connect four) Connect four is a two-player board game in which the
 * players alternately drop colored disks into a seven-column, six-row vertically
 * suspended grid, as shown int the figure on page 313.
 *
 * The objective of the game is to connect four same-colored disks in a row, a column,
 * or a diagonal before your opponent can do likewise. The program prompts
 * two players to drop a red or yellow disk alternately. In the preceding figure, the
 * red disk is shown in a dark color and the yellow in a light color. Whenever a disk
 * is dropped, the program re-displays the board on the console and determines the
 * status of the game (win, draw, or continue). Here is a sample run:
 *
 *      | | | | | | | |
 *      | | | | | | | |
 *      | | | | | | | |
 *      | | | | | | | |
 *      | | | | | | | |
 *      | | | | | | | |
 *      —————————
 *      Drop a red disk at column (0–6): 0
 *      | | | | | | | |
 *      | | | | | | | |
 *      | | | | | | | |
 *      | | | | | | | |
 *      | | | | | | | |
 *      |R| | | | | | |
 *      —————————
 *      Drop a yellow disk at column (0–6): 3
 *      | | | | | | | |
 *      | | | | | | | |
 *      | | | | | | | |
 *      | | | | | | | |
 *      | | | | | | | |
 *      |R| | |Y| | | |
 *      —————————
 *      . . .
 *      . . .
 *      . . .
 *      Drop a yellow disk at column (0–6): 6
 *      | | | | | | | |
 *      | | | | | | | |
 *      | | | |R| | | |
 *      | | | |Y|R|Y| |
 *      | | |R|Y|Y|Y|Y|
 *      |R|Y|R|Y|R|R|R|
 *      —————————
 *      The yellow player won
 */
public class PE_08_20_Game_connect_four {
    public static void main(String[] args) {
        playConnectFour();
    }

    private static void playConnectFour() {
        char[][] board = initializeBoard(6, 7);
        char player = 'Y';
        while (hasPlay(board)) {
            if (player == 'Y') player = 'R';
            else player = 'Y';
            printBoard(board);
            takeTurn(board, player);
        }
        printBoard(board);
        if (isConsecutiveFour(board)) System.out.println("The " + getPlayerString(player) + " player won");
        else System.out.println("It's a draw");
    }

    private static void takeTurn(char[][] board, char player) {
        int column = 0;
        boolean validPlay = false;
        while (!validPlay) {
            column = getPlayColumn(player);
            //column = (int)(Math.random() * 7);
            if (board[0][column] == ' ') validPlay = true;
            else System.out.println("That column is full. Try again.");
        }
        for (int i = board.length - 1; i >= 0 ; i--) {
            if (board[i][column] == ' ') {
                board[i][column] = player;
                break;
            }
        }
    }

    private static int getPlayColumn(char player) {
        Scanner scanner = new Scanner(System.in);
        String color = getPlayerString(player);
        System.out.print("Drop a " + color + " disk at column (0–6): ");
        return scanner.nextInt();
    }

    private static String getPlayerString(char player) {
        return player == 'Y' ? "Yellow" : "Red";
    }

    private static boolean hasPlay(char[][] board) {
        if (isConsecutiveFour(board)) return false;
        for (int i = 0; i < board[0].length; i++) {
             if (board[0][i] == ' ') return true;
        }
        return false;
    }

    private static void printBoard(char[][] board) {
        for (char[] aBoard : board) {
            System.out.print("|");
            for (char anABoard : aBoard) {
                System.out.print(anABoard + "|");
            }
            System.out.println();
        }
        System.out.println("---------------");
    }

    private static char[][] initializeBoard(int rows, int columns) {
        char[][] board = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = ' ';
            }
        }
        return board;
    }

    public static boolean isConsecutiveFour(char[][] values) {
        boolean isConsecutiveFour;
        //Check horizontal.
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length - 3; j++) {
                if (values[i][j] == ' ') continue;
                isConsecutiveFour = true;
                for (int k = 1; k < 4; k++) {
                    isConsecutiveFour &= values[i][j] == values[i][j + k];
                }
                if (isConsecutiveFour) return true;
            }
        }
        //Check vertical.
        for (int i = 0; i < values.length - 3; i++) {
            for (int j = 0; j < values[i].length; j++) {
                if (values[i][j] == ' ') continue;
                isConsecutiveFour = true;
                for (int k = 1; k < 4; k++) {
                    isConsecutiveFour &= values[i][j] == values[i + k][j];
                }
                if (isConsecutiveFour) return true;
            }
        }
        //check major diagonals
        for (int i = 0; i < values.length - 3; i++) {
            for (int j = 0; j < values[i].length - 3; j++) {
                if (values[i][j] == ' ') continue;
                isConsecutiveFour = true;
                for (int k = 1; k < 4; k++) {
                    isConsecutiveFour &= values[i][j] == values[i + k][j + k];
                }
                if (isConsecutiveFour) return true;
            }
        }
        //check sub diagonals
        for (int i = 4; i < values.length; i++) {
            for (int j = 0; j < values[i].length - 3; j++) {
                if (values[i][j] == ' ') continue;
                isConsecutiveFour = true;
                for (int k = 1; k < 4; k++) {
                    isConsecutiveFour &= values[i][j] == values[i - k][j + k];
                }
                if (isConsecutiveFour) return true;
            }
        }
        return false;
    }

}
