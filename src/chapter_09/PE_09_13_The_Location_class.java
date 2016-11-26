package chapter_09;

import java.util.Scanner;

/**
 * (The Location class) Design a class named Location for locating a maximal
 * value and its location in a two-dimensional array. The class contains public data
 * fields row, column, and maxValue that store the maximal value and its indices
 * in a two-dimensional array with row and column as int types and maxValue as
 * a double type. Write the following method that returns the location of the largest element in a
 * two-dimensional array:
 *
 *      public static Location locateLargest(double[][] a)
 *
 * The return value is an instance of Location. Write a test program that prompts
 * the user to enter a two-dimensional array and displays the location of the largest
 * element in the array. Here is a sample run:
 *
 *      Enter the number of rows and columns in the array: 3 4 (enter)
 *      Enter the array:
 *      23.5 35 2 10 (enter)
 *      4.5 3 45 3.5 (enter)
 *      35 44 5.5 9.6 (enter)
 *      The location of the largest element is 45 at (1, 2)
 */
public class PE_09_13_The_Location_class {
    public static void main(String[] args) {
        final int ROWS = 0, COLUMNS = 1;
        int[] arraySize = promptArraySize();
        double[][] array = prompt2DDoubleArray(arraySize[ROWS], arraySize[COLUMNS]);
        Location location = Location.locateLargest(array);
        System.out.println(location.toString());
    }

    private static int[] promptArraySize() {
        int[] size = new int[2];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of rows and columns in the array: ");
        for (int i = 0; i < 2; i++) {
            size[i] = scanner.nextInt();
        }
        return size;
    }

    private static double[][] prompt2DDoubleArray(int rows, int columns) {
        double[][] m = new double[rows][columns];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the array:");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                m[r][c] = scanner.nextDouble();
            }
        }
        return m;
    }

    private static class Location {
        public final int row;
        public final int column;
        final double maxValue;

        Location(int row, int column, double maxValue) {
            this.row = row;
            this.column = column;
            this.maxValue = maxValue;
        }

        static Location locateLargest(double[][] a) {
            Location location = new Location(0, 0, a[0][0]);
            for (int r = 0; r < a.length; r++) {
                for (int c = 0; c < a[r].length; c++) {
                    if (a[r][c] > location.maxValue)
                        location = new Location(r, c, a[r][c]);
                }
            }
            return location;
        }

        @Override
        public String toString() {
            String formattedMaxValue;
            if (maxValue % 1.0 != 0)
                formattedMaxValue = String.format("%s", maxValue);
            else
                formattedMaxValue = String.format("%.0f", maxValue);
            return String.format("The location of the largest element is %s at (%d, %d)",
                    formattedMaxValue, row, column);
        }
    }
}
