package chapter_06;

/**
 * (Sum series) Write a method to compute the following series:
 *
 *      m(i) = 1/2 + 2/3 + ... + i/(i + 1)
 *
 * Write a test program that displays the following table:
 *
 *      i          m(i)
 *      -------------------
 *      1           0.5000
 *      2           1.1667
 *      ...
 *      19         16.4023
 *      20         17.3546
 */
public class PE_06_13_Sum_series {
    public static void main(String[] args) {
        System.out.println("i          m(i)");
        System.out.println("-------------------");
        for (int i = 1; i <= 20; i++) {
            System.out.printf("%-2d%16.4f%n", i, sumSeries(i));
        }
    }

    public static double sumSeries(int num) {
        double sum = 0;
        for (int i = 1; i <= num; i++) {
            sum += i / (i + 1.0);
        }
        return sum;
    }
}
