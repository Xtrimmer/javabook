package chapter_05;

/**
 * (Compute Pi) You can approximate p by using the following series:
 * Pi = 4 * (1 - 1/3 + 1/5 - 1/7 + 1/9 - 1/11 + ... + (-1)^(i + 1)/(2i - 1))
 * Write a program that displays the p value for i = 10000, 20000, …, and 100000.
 */
public class PE_05_25_Compute_Pi {
    public static void main(String[] args) {
        double pi = 0;
        for (int i = 1; i <= 100000; i++) {
            pi += Math.pow(-1, i + 1) / (2 * i - 1.0);
            if (i == 10000 || i == 20000 || i == 100000)
                System.out.printf("Pi @ i =  %6d: %.16f%n", i, (pi * 4));
        }
    }
}
