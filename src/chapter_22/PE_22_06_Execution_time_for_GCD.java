package chapter_22;

/**
 * (Execution time for GCD) Write a program that obtains the execution time for
 * finding the GCD of every two consecutive Fibonacci numbers from the index
 * 40 to index 45 using the algorithms in Listings 22.3 and 22.4. Your program
 * should print a table like this:
 * <p>
 * | 40      41      42      43      44      45
 * ----------------------------------------------------------------------
 * Listing 22.3 GCD       |
 * Listing 22.4 GCDEuclid |
 * <p>
 * (Hint: You can use the following code template to obtain the execution time.)
 * <p>
 * long startTime = System.currentTimeMillis();
 * perform the task;
 * long endTime = System.currentTimeMillis();
 * long executionTime = endTime - startTime;
 */
public class PE_22_06_Execution_time_for_GCD {
    public static void main(String[] args) {
        int[] fibNumbers = getFibonacciRange(40, 46);
        printGcdTable(fibNumbers);
    }

    private static int[] getFibonacciRange(int startIndex, int endIndex) {
        int[] fibonacciNumbers = new int[endIndex + 1];
        fibonacciNumbers[0] = 0;
        fibonacciNumbers[1] = 1;
        fibonacciNumbers[2] = 1;

        for (int i = 3; i <= endIndex; i++) {
            fibonacciNumbers[i] = fibonacciNumbers[i - 2] + fibonacciNumbers[i - 1];
        }

        int[] subArray = new int[endIndex - startIndex + 1];
        System.arraycopy(fibonacciNumbers, startIndex, subArray, 0, subArray.length);
        return subArray;
    }

    private static void printGCDTimes(int[] fibNumbers, GreatestCommonDivisor greatestCommonDivisor) {
        for (int i = 1; i < fibNumbers.length; i++) {
            long startTime = System.currentTimeMillis();
            greatestCommonDivisor.gcd(fibNumbers[i - 1], fibNumbers[i]);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.printf("%-8d", executionTime);
        }
        System.out.println();
    }

    private static void printGcdTable(int[] fibNumbers) {
        System.out.println("                       | 40      41      42      43      44      45");
        System.out.println("----------------------------------------------------------------------");
        System.out.print("Listing 22.3 GCD       | ");
        printGCDTimes(fibNumbers, new GCD());
        System.out.print("Listing 22.4 GCDEuclid | ");
        printGCDTimes(fibNumbers, new GCDEuclid());

    }

    static class GCD implements GreatestCommonDivisor {
        @Override
        public int gcd(int m, int n) {
            int gcd = 1;
            if (m % n == 0) return n;
            for (int k = n / 2; k >= 1; k--) {
                if (m % k == 0 && n % k == 0) {
                    gcd = k;
                    break;
                }
            }
            return gcd;
        }
    }

    static class GCDEuclid implements GreatestCommonDivisor {
        @Override
        public int gcd(int m, int n) {
            if (m % n == 0)
                return n;
            else
                return gcd(n, m % n);
        }
    }

    interface GreatestCommonDivisor {
        int gcd(int m, int n);
    }
}