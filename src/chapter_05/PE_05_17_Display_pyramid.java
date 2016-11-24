package chapter_05;

import java.util.Scanner;

/**
 * (Display pyramid) Write a program that prompts the user to enter an integer from
 * 1 to 15 and displays a pyramid, as shown in the following sample run:
 *
 * Enter the number of lines: 7 (enter)
 *             1
 *           2 1 2
 *         3 2 1 2 3
 *       4 3 2 1 2 3 4
 *     5 4 3 2 1 2 3 4 5
 *   6 5 4 3 2 1 2 3 4 5 6
 * 7 6 5 4 3 2 1 2 3 4 5 6 7
 */
public class PE_05_17_Display_pyramid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of lines: ");
        int num = scanner.nextInt();
        for (int i = 1; i <= num ; i++) {
            for (int j = 0; j < num - i; j++) {
                System.out.print("   ");
            }
            for (int j = i; j > 1; j--) {
                System.out.printf("%-2d ", j);
            }
            for (int j = 1; j <= i ; j++) {
                System.out.printf("%-2d ", j);
            }
            System.out.println();
        }
    }
}
