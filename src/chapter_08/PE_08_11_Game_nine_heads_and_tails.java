package chapter_08;

import java.util.Scanner;

/**
 * (Game: nine heads and tails) Nine coins are placed in a 3-by-3 matrix with some
 * face up and some face down. You can represent the state of the coins using a
 * 3-by-3 matrix with values 0 (heads) and 1 (tails). Here are some examples:
 *
 *      0 0 0   1 0 1   1 1 0   1 0 1   1 0 0
 *      0 1 0   0 0 1   1 0 0   1 1 0   1 1 1
 *      0 0 0   1 0 0   0 0 1   1 0 0   1 1 0
 *
 * Each state can also be represented using a binary number. For example, the preceding
 * matrices correspond to the numbers
 *
 *      000010000 101001100 110100001 101110100 100111110
 *
 * There are a total of 512 possibilities, so you can use decimal numbers 0, 1, 2, 3,
 * . . . , and 511 to represent all states of the matrix. Write a program that prompts
 * the user to enter a number between 0 and 511 and displays the corresponding
 * matrix with the characters H and T. Here is a sample run:
 *
 *      Enter a number between 0 and 511: 7 (enter)
 *      H H H
 *      H H H
 *      T T T
 */
public class PE_08_11_Game_nine_heads_and_tails {
    public static void main(String[] args) {
        int number = getNumber();
        char[][] matrix = makeMatrix(number);
        printMatrix(matrix);
    }

    public static int getNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number between 0 and 511: ");
        return scanner.nextInt();
    }

    public static void printMatrix(char[][] matrix) {
        for (char[] aMatrix : matrix) {
            for (char anAMatrix : aMatrix) {
                System.out.print(anAMatrix + " ");
            }
            System.out.println();
        }
    }

    public static char[][] makeMatrix(int number) {
        char[][] matrix = new char[3][3];
        for (int i = 2; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                matrix[i][j] = number % 2 == 1 ? 'T' : 'H';
                number /= 2;
            }
        }
        return matrix;
    }
}
