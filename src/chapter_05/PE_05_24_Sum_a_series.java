package chapter_05;

/**
 * (Sum a series) Write a program to sum the following series:
 * 1/3 + 3/5 + 5/7 + 7/9 + 9/11 + 11/13 + ... + 95/97 + 97/99
 */
public class PE_05_24_Sum_a_series {
    public static void main(String[] args) {
        double sum = 0;
        for (int i = 1; i <= 97; i += 2) {
            sum += i / (i + 2.0);
        }
        System.out.println("The sum is: " + sum);
        // sum == 45.124450303050196
    }
}