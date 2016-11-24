package chapter_07;

import java.util.Arrays;

/**
 * (Execution time) Write a program that randomly generates an array of 100,000
 * integers and a key. Estimate the execution time of invoking the linearSearch
 * method in Listing 7.6. Sort the array and estimate the execution time of invoking
 * the binarySearch method in Listing 7.7. You can use the following code
 * template to obtain the execution time:
 *
 *      long startTime = System.currentTimeMillis();
 *      perform the task;
 *      long endTime = System.currentTimeMillis();
 *      long executionTime = endTime - startTime;
 */
public class PE_07_16_Execution_time {
    public static void main(String[] args) {
        int key = (int) (Math.random() * 100000 );
        int[] array = generateRandomIntArray(100000);

        // Time and display linear search
        long startTime = System.nanoTime();
        int i = linearSearch(array, key);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println(i + " The linear search time is: " + executionTime);

        // sort array
        Arrays.sort(array);

        // time and display binary search
        startTime = System.nanoTime();
        i = binarySearch(array, key);
        endTime = System.nanoTime();
        executionTime = endTime - startTime;
        System.out.println(i + " The binary search time is: " + executionTime);
    }

    public static int[] generateRandomIntArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 100000);
        }
        return array;
    }

    public static int linearSearch(int[] list, int key) {
        for (int i = 0; i < list.length; i++) {
            if (key == list[i])
                return i;
        }
        return -1;
    }

    public static int binarySearch(int[] list, int key) {
        int low = 0;
        int high = list.length - 1;

        while (high >= low) {
            int mid = (low + high) / 2;
            if (key < list[mid])
                high = mid - 1;
            else if (key == list[mid])
                return mid;
            else
                low = mid + 1;
        }
        return -low - 1; // Now high < low, key not found
    }
}
