package chapter_05;

/**
 * (Perfect number) A positive integer is called a perfect number if it is equal to
 * the sum of all of its positive divisors, excluding itself. For example, 6 is the first
 * perfect number because 6 = 3 + 2 + 1. The next is 28 = 14 + 7 + 4 + 2
 * + 1. There are four perfect numbers less than 10,000. Write a program to find all
 * these four numbers.
 */
public class PE_05_33_Perfect_number {
    public static void main(String[] args) {

        for (int i = 1; i <= 10000 ; i++) {
            int sum = 0;
            for (int j = i/2; j >0 ; j--) {
                if (i % j == 0) sum += j;
            }
            if (i == sum) System.out.println(i + " is a perfect number");
        }
    }
}
