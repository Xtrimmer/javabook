package chapter_05;

/**
 * (Find the smallest n such that n^2 is greater than 12,000) Use a while loop to find the smallest
 * integer n such that n^2 is greater than 12,000.
 */
public class PE_05_13_Find_the_largest_n_such_that_n_cubed_is_less_than_12000 {
    public static void main(String[] args) {
        int n = 1;
        while (n * n * n < 12000){
            n++;
        }
        System.out.println("The smallest integer n such that n^2 > 12000: " + (n - 1));
    }
}
