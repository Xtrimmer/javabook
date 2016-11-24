package chapter_08;

import java.util.Scanner;

/**
 * (Sort two-dimensional array) Write a method to sort a two-dimensional array
 * using the following header:
 *
 *      public static void sort(int m[][])
 *
 * The method performs a primary sort on rows and a secondary sort on columns.
 * For example, the following array
 *
 *      {{4, 2},{1, 7},{4, 5},{1, 2},{1, 1},{4, 1}}
 *
 * will be sorted to
 *
 *      {{1, 1},{1, 2},{1, 7},{4, 1},{4, 2},{4, 5}}.
 */
public class PE_08_16_Sort_two_dimensional_array {
    public static void main(String[] args) {
        int[][] points = getPoints(6);   //4 2 1 7 4 5 1 2 1 1 4 1
        printArray(points);
        sort(points);
        printArray(points);
    }

    public static int[][] getPoints(int quantity) {
        Scanner scanner = new Scanner(System.in);
        int[][] points = new int[quantity][2];
        System.out.print("Enter " + quantity + " points: ");
        for (int i = 0; i < quantity; i++) {
            for (int j = 0; j < 2; j++) {
                points[i][j] = scanner.nextInt();
            }
        }
        return points;
    }

    public static void sort(int m[][]) {
        for (int i = 0; i < m.length - 1; i++) {
            for (int j = i; j < m.length; j++) {
                if ((m[i][0] * 10 + m[i][1]) > (m[j][0] * 10 + m[j][1])) {
                    int[] temp = m[i];
                    m[i] = m[j];
                    m[j] = temp;
                }
            }
        }
    }

    public static void printArray(int[][] m) {
        System.out.print("{");
        for (int i = 0; i < m.length; i++) {
            System.out.print("{");
            for (int j = 0; j < m[i].length; j++) {
                if (j == m[i].length - 1) {
                    System.out.print(m[i][j]);
                } else {
                    System.out.print(m[i][j] + ", ");
                }
            }
            if (i == m.length - 1) {
                System.out.print("}");
            } else {
                System.out.print("},");
            }
        }
        System.out.println("}");
    }
}
