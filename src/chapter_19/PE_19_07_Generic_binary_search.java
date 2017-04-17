package chapter_19;

import java.util.Arrays;

/**
 * (Generic binary search) Implement the following method using binary search.
 *
 *      public static <E extends Comparable<E>>
 *          int binarySearch(E[] list, E key)
 */
public class PE_19_07_Generic_binary_search {
    public static void main(String[] args) {
        String[] strings = {"Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliett"};
        Integer[] integers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println(Arrays.toString(strings));
        System.out.println("Index of Delta: " + binarySearch(strings, "Delta"));
        System.out.println();
        System.out.println(Arrays.toString(integers));
        System.out.println("Index of 5: " + binarySearch(integers, 5));
    }

    public static <E extends Comparable<E>> int binarySearch(E[] list, E key) {
        int low = 0;
        int high = list.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (key.compareTo(list[mid]) < 0) high = mid - 1;
            else if (key.compareTo(list[mid]) > 0) low = mid + 1;
            else return mid;
        }
        return -1;
    }
}
