package chapter_05;

/**
 * (Compute e) You can approximate e using the following series:
 *
 *      e = 1 + 1/1! + 1/2! + 1/3! + 1/4! + ... + 1/i!
 *
 * Write a program that displays the e value for i = 10000, 20000, ..., and
 * 100000. (Hint: Because i! = i * (i - 1) * ... * 2 * 1, then
 *
 *      1/i! is 1/i(i - 1)!
 *
 * Initialize e and item to be 1 and keep adding a new item to e. The new item is
 * the previous item divided by i for i = 2, 3, 4, . . . .)
 */
public class PE_05_26_Compute_e {
    public static void main(String[] args) {
        double e = 1;
        double item = 1;
        for (int i = 2; i <= 100000; i++) {
            item /= (i - 1);
            e += item;
            if (i == 10000 || i == 20000 || i == 100000)
                System.out.printf("e @ i =  %6d: %.16f%n", i, e);
            // e == 2.7182818284590452353602874713527
        }
    }
}
