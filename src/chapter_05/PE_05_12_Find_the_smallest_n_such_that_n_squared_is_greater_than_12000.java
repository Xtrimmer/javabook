package chapter_05;

/**
 * (Find the smallest n such that n^2 is greater than 12,000) Use a while loop to find the smallest
 * integer n such that n^2 is greater than 12,000.
 */
public class PE_05_12_Find_the_smallest_n_such_that_n_squared_is_greater_than_12000 {
    public static void main(String[] args) {
        int n = 1;
        while (n * n < 12000){
            n++;
        }
        System.out.println("The smallest integer n such that n^2 > 12000: " + n);
    }
}
