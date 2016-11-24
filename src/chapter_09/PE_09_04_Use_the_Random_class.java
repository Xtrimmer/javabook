package chapter_09;

import java.util.Random;

/**
 * (Use the Random class) Write a program that creates a Random object with seed
 * 1000 and displays the first 50 random integers between 0 and 100 using the
 * nextInt(100) method.
 */
public class PE_09_04_Use_the_Random_class {
    public static void main(String[] args) {
        Random random = new Random(1000);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.printf("%2d ", random.nextInt(100));
            }
            System.out.println();
        }
    }
}
