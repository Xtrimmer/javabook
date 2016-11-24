package chapter_06;

import java.util.Scanner;

/**
 * (Display patterns) Write a method to display a pattern as follows:
 *
 *                    1
 *                  2 1
 *                3 2 1
 *                  ...
 *      n n-1 ... 3 2 1
 *
 * The method header is
 * public static void displayPattern(int n)
 */
public class PE_06_06_Display_patterns {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int num = scanner.nextInt();
        displayPattern(num);
    }
    public static void displayPattern(int n){
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n-1; j++) {
                System.out.print("  ");
            }
            for (int j = i; j >= 1; j--) {
                System.out.print(" " + j);
            }
            System.out.println();
        }
    }
}
