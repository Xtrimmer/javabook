package chapter_18;

/**
 * (Sum series) Write a recursive method to compute the following series:
 *
 *      m(i) = 1/3 + 2/5 + 3/7 + 4/9 + 5/11 + 6/13 + ... + i/(2i + 1)
 *
 * Write a test program that displays m(i) for i = 1, 2, . . ., 10.
 */
public class PE_18_05_Sum_series {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println("i = " + i + ": " + sumSeries(i));
        }
    }

    private static double sumSeries(int i) {
        return sumSeries(i, 1.0 / 3);
    }

    private static double sumSeries(int i, double result) {
        if (i <= 0) return 0;
        if (i == 1) return result;
        return sumSeries(i - 1, result + i / (2.0 * i + 1));
    }
}
