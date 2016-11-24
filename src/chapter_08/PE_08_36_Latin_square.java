package chapter_08;

import java.util.Scanner;

/**
 * (Latin square) A Latin square is an n-by-n array filled with n different Latin letters,
 * each occurring exactly once in each row and once in each column. Write a
 * program that prompts the user to enter the number n and the array of characters,
 * as shown in the sample output, and checks if the input array is a Latin square.
 * The characters are the first n characters starting from A.
 *
 *      Enter number n: 4 (enter)
 *      Enter 4 rows of letters separated by spaces:
 *      A B C D (enter)
 *      B A D C (enter)
 *      C D B A (enter)
 *      D C A B (enter)
 *      The input array is a Latin square
 *
 *      Enter number n: 3 (enter)
 *      Enter 3 rows of letters separated by spaces:
 *      A F D (enter)
 *      Wrong input: the letters must be from A to C
 */
public class PE_08_36_Latin_square {
    public static void main(String[] args) {
        int n = promptNumber();
        char[][] matrix= prompt2DCharArray(n);
        boolean isLatinSquare = validateLatinSquare(matrix);
        printResult(isLatinSquare);
    }

    private static void printResult(boolean isLatinSquare) {
        System.out.print("The input array is ");
        System.out.print(isLatinSquare ? "" : "not ");
        System.out.println("a Latin square");
    }

    private static boolean validateLatinSquare(char[][] matrix) {
        boolean[] rowCheck;
        boolean[] columnCheck;
        for (int i = 0; i < matrix.length; i++) {
            rowCheck = new boolean[matrix.length];
            columnCheck = new boolean[matrix.length];
            for (int j = 0; j < matrix.length; j++) {
                rowCheck[matrix[i][j] - 'A'] = true;
                columnCheck[matrix[j][i] - 'A'] = true;
            }
            for (int j = 0; j < matrix.length; j++) {
                if (!(rowCheck[j] && columnCheck[j])) return false;
            }
        }
        return true;
    }

    private static int promptNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number n: ");
        return scanner.nextInt();
    }

    private static char[][] prompt2DCharArray(int size) {
        Scanner scanner = new Scanner(System.in);
        char[][] matrix = new char[size][size];
        System.out.println("Enter 4 rows of letters separated by spaces:");
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                char ch = scanner.next().toUpperCase().charAt(0);
                if (ch >= 'A' && ch < 'A' + size) {
                    matrix[r][c] = ch;
                } else {
                    System.out.println("Wrong input: the letters must be " +
                            "from A to " + (char)('A' + (size - 1)));
                    System.exit(-1);
                }
            }
        }
        return matrix;
    }
}
