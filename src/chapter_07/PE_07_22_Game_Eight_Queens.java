package chapter_07;

/**
 * (Game: Eight Queens) The classic Eight Queens puzzle is to place eight queens
 * on a chessboard such that no two queens can attack each other (i.e., no two queens
 * are on the same row, same column, or same diagonal). There are many possible
 * solutions. Write a program that displays one such solution. A sample output is
 * shown below:
 *
 *      |Q| | | | | | | |
 *      | | | | |Q| | | |
 *      | | | | | | | |Q|
 *      | | | | | |Q| | |
 *      | | |Q| | | | | |
 *      | | | | | | |Q| |
 *      | |Q| | | | | | |
 *      | | | |Q| | | | |
 */
public class PE_07_22_Game_Eight_Queens {
    public static void main(String[] args) {
        int[] board = {2, 5, 7, 0, 3, 6, 4, 1};
        for (int aBoard : board) {
            System.out.print("|");
            for (int j = 0; j < 8; j++) {
                if (aBoard == j) System.out.print("Q|");
                else System.out.print(" |");
            }
            System.out.println();
        }
    }
}
