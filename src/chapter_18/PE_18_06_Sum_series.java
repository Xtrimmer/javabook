package chapter_18;

/**
 * (Sum series) Write a recursive method to compute the following series:
 *
 *      m(i) = 1/2 + 2/3 + ... + i/(i + 1)
 *
 * Write a test program that displays m(i) for i = 1, 2, . . ., 10.
 */
public class PE_18_06_Sum_series {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println("i = " + i + ": " + sumSeries(i));
        }
    }

    private static double sumSeries(int i) {
        return sumSeries(i, 1.0 / 2);
    }

    private static double sumSeries(int i, double result) {
        if (i <= 0) return 0;
        if (i == 1) return result;
        return sumSeries(i - 1, result + i / (i + 1.0));
    }
}
