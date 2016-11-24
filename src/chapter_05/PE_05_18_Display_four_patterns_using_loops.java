package chapter_05;

/**
 * (Display four patterns using loops) Use nested loops that display the following
 * patterns in four separate programs:
 *
 *    Pattern A      Pattern B      Pattern C      Pattern D
 *    1              1 2 3 4 5 6              1    1 2 3 4 5 6
 *    1 2            1 2 3 4 5              2 1      1 2 3 4 5
 *    1 2 3          1 2 3 4              3 2 1        1 2 3 4
 *    1 2 3 4        1 2 3              4 3 2 1          1 2 3
 *    1 2 3 4 5      1 2              5 4 3 2 1            1 2
 *    1 2 3 4 5 6    1              6 5 4 3 2 1              1
 */
public class PE_05_18_Display_four_patterns_using_loops {
    public static void main(String[] args) {
        // Program A
        System.out.println("Pattern A");
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println("");
        }
        System.out.println("");

        // Program B
        System.out.println("Pattern B");
        for (int i = 6; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println("");
        }
        System.out.println("");

        // Program C
        System.out.println("Pattern C");
        for (int i = 1; i <= 6; i++) {
            String line = "";
            for (int j = 1; j <= i; j++) {
                line += j + " ";
            }
            System.out.printf("%12s%n", line);
        }
        System.out.println("");

        // Program D
        System.out.println("Pattern D");
        for (int i = 6; i >= 1; i--) {
            String line = "";
            for (int j = 1; j <= i; j++) {
                line += j + " ";
            }
            System.out.printf("%12s%n", line);
        }

    }
}
