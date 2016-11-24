package chapter_06;

/**
 * (Financial application: compute commissions) Write a method that computes the
 * commission, using the scheme in Programming Exercise 5.39. The header of the
 * method is as follows:
 *
 *      public static double computeCommission(double salesAmount)
 *
 * Write a test program that displays the following table:
 *
 *       Sales Amount     Commission
 *      -----------------------------
 *       10000               900.0
 *       15000              1500.0
 *       ...
 *       95000             11100.0
 *       100000            11700.0
 */
public class PE_06_11_Financial_application_compute_commissions {
    public static void main(String[] args) {
        System.out.println(" Sales Amount     Commission");
        System.out.println("-----------------------------");
        for (int i = 10000; i <= 100000; i += 5000) {
            System.out.printf(" %-17d%8.1f%n", i, computeCommission(i));
        }
    }

    public static double computeCommission(double salesAmount) {
        if (salesAmount <= 5000){
            return salesAmount * 0.08;
        }
        else if (salesAmount <= 10000){
            return ((salesAmount - 5000) * 0.1) + (5000 * 0.08);
        }
        else{
            return ((salesAmount - 10000) * 0.12) + (5000 * 0.1) + (5000 * 0.08);
        }
    }
}
