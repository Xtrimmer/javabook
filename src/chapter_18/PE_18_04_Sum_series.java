package chapter_18;

/**
 * (Sum series) Write a recursive method to compute the following series:
 *
 *      m(i) = 1 + 1/2 + 1/3 + ... + 1/i
 *
 * Write a test program that displays m(i) for i = 1, 2, . . ., 10.
 */
public class PE_18_04_Sum_series {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println("i = " + i + ": " + sumSeries(i));
        }
    }

    private static double sumSeries(int i) {
        return sumSeries(i, 1);
    }

    private static double sumSeries(int i, double result) {
        if (i <= 0) return 0;
        if (i == 1) return result;
        return sumSeries(i - 1, result + 1.0 / i);
    }
}
