package chapter_05;

/**
 * (Find numbers divisible by 5 and 6) Write a program that displays all the numbers
 * from 100 to 1,000, ten per line, that are divisible by 5 and 6. Numbers are
 * separated by exactly one space.
 */
public class PE_05_10_Find_numbers_divisible_by_5_and_6 {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 100; i <= 1000; i++) {
            if (i % 5 == 0 && i % 6 == 0){
                System.out.print(i + " ");
                count++;
                if (count == 10){
                    count = 0;
                    System.out.println();
                }
            }
        }
    }
}
