package chapter_08;

import java.util.Scanner;

/**
 * (Locate the largest element) Write the following method that returns the location
 * of the largest element in a two-dimensional array.
 *
 *      public static int[] locateLargest(double[][] a)
 *
 * The return value is a one-dimensional array that contains two elements. These
 * two elements indicate the row and column indices of the largest element in the
 * two-dimensional array. Write a test program that prompts the user to enter a two dimensional
 * array and displays the location of the largest element in the array.
 * Here is a sample run:
 *
 *      Enter the number of rows and columns of the array: 3 4
 *      Enter the array:
 *      23.5 35 2 10
 *      4.5 3 45 3.5
 *      35 44 5.5 9.6
 *      The location of the largest element is at (1, 2)
 */
public class PE_08_13_Locate_the_largest_element {
    public static void main(String[] args) {
        int[] indices = getSize();
        double[][] array = getArray(indices);
        indices = locateLargest(array);
        System.out.println("The location of the largest element is at (" + indices[0] + ", " + indices[1] + ")");
    }

    public static double[][] getArray(int[] indices) {
        Scanner scanner = new Scanner(System.in);
        double[][] array = new double[indices[0]][indices[1]];
        for (int i = 0; i < indices[0]; i++) {
            for (int j = 0; j < indices[1]; j++) {
                array[i][j] = scanner.nextDouble();
            }

        }
        return array;
    }

    public static int[] getSize() {
        Scanner scanner = new Scanner(System.in);
        int[] indices = new int[2];
        System.out.print("Enter the number of rows and columns of the array: ");
        indices[0] = scanner.nextInt();
        indices[1] = scanner.nextInt();
        return indices;
    }

    public static int[] locateLargest(double[][] a) {
        int[] indices = new int[2];
        double max = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] > max) {
                    max = a[i][j];
                    indices[0] = i;
                    indices[1] = j;
                }
            }
        }
        return indices;
    }
}
