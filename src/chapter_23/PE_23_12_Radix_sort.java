package chapter_23;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * (Radix sort) Write a program that randomly generates 1,000,000 integers and
 * sorts them using radix sort.
 */
public class PE_23_12_Radix_sort {
    private static final int UPPER_BOUND = 1000000;

    public static void main(String[] args) {
        int[] integers = generateRandomInts(UPPER_BOUND);
        radixSort(integers, 6);
        System.out.println("isSorted: " + isSorted(integers));
    }

    private static List<List<Integer>> ResetBuckets() {
        List<List<Integer>> buckets = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<>());
        }
        return buckets;
    }

    private static void combineBuckets(int[] list, List<List<Integer>> buckets) {
        int i = 0;
        for (List<Integer> integers : buckets) {
            for (Integer integer : integers) {
                list[i++] = integer;
            }
        }
    }

    private static void distributeToBuckets(int[] list, int radix, List<List<Integer>> buckets) {
        int divisorModulo = (int) Math.pow(10, radix);
        int divisorDivision = (int) Math.pow(10, radix - 1);
        int bucket;
        for (int integer : list) {
            bucket = integer % divisorModulo / divisorDivision;
            buckets.get(bucket).add(integer);
        }
    }

    private static int[] generateRandomInts(int size) {
        int[] ints = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            ints[i] = random.nextInt(UPPER_BOUND);
        }
        return ints;
    }

    private static boolean isSorted(int[] integers) {
        for (int i = 0; i < UPPER_BOUND - 1; i++) {
            if (integers[i] > integers[i + 1]) return false;
        }
        return true;
    }

    private static void radixSort(int[] list, int radixCount) {
        for (int radix = 1; radix <= radixCount; radix++) {
            List<List<Integer>> buckets = ResetBuckets();
            distributeToBuckets(list, radix, buckets);
            combineBuckets(list, buckets);
        }
    }
}
