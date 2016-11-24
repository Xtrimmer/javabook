package chapter_05;

/**
 * (Display numbers in a pyramid pattern) Write a nested for loop that prints the
 * following output:
 *                             1
 *                         1   2   1
 *                     1   2   4   2   1
 *                 1   2   4   8   4   2   1
 *             1   2   4   8  16   8   4   2   1
 *         1   2   4   8  16  32  16   8   4   2   1
 *     1   2   4   8  16  32  64  32  16   8   4   2   1
 * 1   2   4   8  16  32  64 128  64  32  16   8   4   2   1
 */
public class PE_05_19_Display_numbers_in_a_pyramid_pattern {
    public static void main(String[] args) {
        final int NUMBER_OF_LINES = 8;
        for (int i = 1; i <= NUMBER_OF_LINES ; i++) {
            for (int j = 0; j < NUMBER_OF_LINES - i; j++) {
                System.out.print("    ");
            }
            for (int j = 1; j <= i ; j++) {
                System.out.printf("%3d ", (int)Math.pow(2, j-1));
            }
            for (int j = i-1; j >= 1; j--) {
                System.out.printf("%3d ", (int)Math.pow(2, j-1));
            }
            System.out.println();
        }
    }
}
