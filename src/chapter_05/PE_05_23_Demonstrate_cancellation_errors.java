package chapter_05;

/**
 * (Demonstrate cancellation errors) A cancellation error occurs when you are
 * manipulating a very large number with a very small number. The large number
 * may cancel out the smaller number. For example, the result of 100000000.0 +
 * 0.000000001 is equal to 100000000.0. To avoid cancellation errors and obtain
 * more accurate results, carefully select the order of computation. For example, in
 * computing the following series, you will obtain more accurate results by computing
 * from right to left rather than from left to right:
 *
 *      1 + 1/2 + 1/3 + ... + 1/n
 *
 * Write a program that compares the results of the summation of the preceding
 * series, computing from left to right and from right to left with n = 50000.
 */
public class PE_05_23_Demonstrate_cancellation_errors {
    public static void main(String[] args) {
        float sum = 0;
        for (int i = 1; i <= 50000; i++) {
            sum += 1.0 / i;
        }
        System.out.println("The L to R sum is: " + sum);
        sum = 0;
        for (int i = 50000; i >= 1; i--) {
            sum += 1.0 / i;
        }
        System.out.println("The R to L sum is: " + sum);
    }
}
