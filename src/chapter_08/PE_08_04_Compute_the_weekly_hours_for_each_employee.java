package chapter_08;

/**
 * (Compute the weekly hours for each employee) Suppose the weekly hours for all
 * employees are stored in a two-dimensional array. Each row records an employee’s
 * seven-day work hours with seven columns. For example, the following
 * array stores the work hours for eight employees. Write a program that displays
 * employees and their total hours in decreasing order of the total hours.
 *
 *                  Su  M  T  W Th  F Sa
 *      Employee0    2  4  3  4  5  8  8
 *      Employee1    7  3  4  3  3  4  4
 *      Employee2    3  3  4  3  3  2  2
 *      Employee3    9  3  4  7  3  4  1
 *      Employee4    3  5  4  3  6  3  8
 *      Employee5    3  4  4  6  3  4  4
 *      Employee6    3  7  4  8  3  8  4
 *      Employee7    6  3  5  9  2  7  9
 */
public class PE_08_04_Compute_the_weekly_hours_for_each_employee {
    public static void main(String[] args) {
        int[][] hours = {{2, 4, 3, 4, 5, 8, 8},
                {7, 3, 4, 3, 3, 4, 4},
                {3, 3, 4, 3, 3, 2, 2},
                {9, 3, 4, 7, 3, 4, 1},
                {3, 5, 4, 3, 6, 3, 8},
                {3, 4, 4, 6, 3, 4, 4},
                {3, 7, 4, 8, 3, 8, 4},
                {6, 3, 5, 9, 2, 7, 9}};
        int[][] sums = sumRows(hours);
        sortArrayByColumn(sums, 1);
        System.out.println();
        for (int i = sums.length - 1; i >= 0; i--) {
            System.out.println("Employee" + sums[i][0] + " has " + sums[i][1] + " hours.");
        }

    }

    public static int[][] sumRows(int[][] matrix) {
        int[][] sums = new int[matrix.length][2];
        int sum;
        for (int i = 0; i < matrix.length; i++) {
            sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
            sums[i][0] = i;
            sums[i][1] = sum;
        }
        return sums;
    }

    public static void sortArrayByColumn(int[][] array, int column) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min][column] > array[j][column]) {
                    min = j;
                }
            }
            int temp = array[i][column];
            array[i][column] = array[min][column];
            array[min][column] = temp;
            for (int j = 0; j < array[i].length; j++) {
                if (j != column) {
                    temp = array[i][j];
                    array[i][j] = array[min][j];
                    array[min][j] = temp;
                }
            }
        }
    }
}
