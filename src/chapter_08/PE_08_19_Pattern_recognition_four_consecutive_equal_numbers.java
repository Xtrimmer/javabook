package chapter_08;

/**
 * (Pattern recognition: four consecutive equal numbers) Write the following
 * method that tests whether a two-dimensional array has four consecutive numbers
 * of the same value, either horizontally, vertically, or diagonally.
 *
 *      public static boolean isConsecutiveFour(int[][] values)
 *
 * Write a test program that prompts the user to enter the number of rows and columns
 * of a two-dimensional array and then the values in the array and displays
 * true if the array contains four consecutive numbers with the same value. Otherwise,
 * display false. Here are some examples of the true cases:
 *
 *      0 1 0 3 1 6 1
 *      0 1 6 8 6 0 1
 *      5 6 2 1 8 2 9
 *      6 5 6 1 1 9 1
 *      1 3 6 1 4 0 7
 *     ③ ③ ③ ③ 4 0 7
 *
 *      0 1 0 3 1 6 1
 *      0 1 6 8 6 0 1
 *      5 ⑤ 2 1 8 2 9
 *      6 ⑤ 6 1 1 9 1
 *      1 ⑤ 6 1 4 0 7
 *      3 ⑤ 3 3 4 0 7
 *
 *      0 1 0 3 1 6 1
 *      0 1 6 8 6 0 1
 *      5 6 2 1 ⑥ 2 9
 *      6 5 6 ⑥ 1 9 1
 *      1 3 ⑥ 1 4 0 7
 *      3 ⑥ 3 3 4 0 7
 *
 *      0 1 0 3 1 6 1
 *      0 1 6 8 6 0 1
 *      ⑨ 6 2 1 8 2 9
 *      6 ⑨ 6 1 1 9 1
 *      1 3 ⑨ 1 4 0 7
 *      3 3 3 ⑨ 4 0 7
 */
public class PE_08_19_Pattern_recognition_four_consecutive_equal_numbers {
    public static void main(String[] args) {
        /*int[][] matrix1 = {{0,1,0,3,1,6,1},{0,1,6,8,6,0,1},{5,6,2,1,8,2,9},
                {6,5,6,1,1,9,1},{1,3,6,1,4,0,7},{3,3,3,3,4,0,7}};
        int[][] matrix2 = {{0,1,0,3,1,6,1},{0,1,6,8,6,0,1},{5,5,2,1,8,2,9},
                {6,5,6,1,1,9,1},{1,5,6,1,4,0,7},{3,5,3,3,4,0,7}};
        int[][] matrix3 = {{0,1,0,3,1,6,1},{0,1,6,8,6,0,1},{5,6,2,1,6,2,9},
                {6,5,6,6,1,9,1},{1,3,6,1,4,0,7},{3,6,3,3,4,0,7}};
        int[][] matrix4 = {{0,1,0,3,1,6,1},{0,1,6,8,6,0,1},{9,6,2,1,8,2,9},
                {6,9,6,1,1,9,1},{1,3,9,1,4,0,7},{3,3,3,9,4,0,7}};*/
        int[][] matrix5 = new int[4][4];
        for (int i = 0; i < matrix5.length; i++) {
            for (int j = 0; j < matrix5[i].length; j++) {
                matrix5[i][j] = (int)(Math.random() * 2);
            }
        }
        printMatrix(matrix5);
        System.out.println(isConsecutiveFour(matrix5));
    }

    public static boolean isConsecutiveFour(int[][] values) {

        boolean isConsecutiveFour;
        //Check horizontal.
        for (int[] value : values) {
            for (int j = 0; j < value.length - 3; j++) {
                isConsecutiveFour = true;
                for (int k = 1; k < 4; k++) {
                    isConsecutiveFour &= value[j] == value[j + k];
                }
                if (isConsecutiveFour) return true;
            }
        }
        //Check vertical.
        for (int i = 0; i < values.length - 3; i++) {
            for (int j = 0; j < values[i].length; j++) {
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
                isConsecutiveFour = true;
                for (int k = 1; k < 4; k++) {
                    isConsecutiveFour &= values[i][j] == values[i - k][j + k];
                }
                if (isConsecutiveFour) return true;
            }
        }
        return false;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] aMatrix : matrix) {
            for (int anAMatrix : aMatrix) {
                System.out.print(anAMatrix);
            }
            System.out.println();
        }
    }
}
