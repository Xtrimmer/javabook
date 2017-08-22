package chapter_22;

import java.util.Arrays;
import java.util.Random;

/**
 * (Find the smallest number) Write a method that uses the divide-and-conquer
 * approach to find the smallest number in a list.
 */
public class PE_22_24_Find_the_smallest_number {
    public static void main(String[] args) {
        int[] list = generateIntArray(25);
        System.out.println(Arrays.toString(list));
        System.out.println("Min: " + findSmallestNumber(list));
    }

    private static int findSmallestNumber(int[] list) {
        return findSmallestNumber(0, list.length - 1, list);
    }

    private static int findSmallestNumber(int low, int high, int[] list) {
        if (high == low) return list[low];
        if (high - low == 1) return Math.min(list[low], list[high]);
        int mid = (high + low) / 2;
        return Math.min(
                findSmallestNumber(low, mid, list),
                findSmallestNumber(mid + 1, high, list)
        );
    }

    private static int[] generateIntArray(int size) {
        int[] numbers = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            numbers[i] = random.nextInt();
        }
        return numbers;
    }
}
