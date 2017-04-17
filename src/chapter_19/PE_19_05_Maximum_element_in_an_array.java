package chapter_19;

import java.util.Arrays;

/**
 * (Maximum element in an array) Implement the following method that returns the
 * maximum element in an array.
 *
 *      public static <E extends Comparable<E>> E max(E[] list)
 */
public class PE_19_05_Maximum_element_in_an_array {
    public static void main(String[] args) {
        String[] strings = {"How", "now", "brown", "cow"};
        Integer[] ints = {1, 13, 5, 3, 2, 8, 1};

        System.out.println("strings : " + Arrays.toString(strings));
        System.out.println("Max string : " + max(strings));

        System.out.println("integers: " + Arrays.toString(ints));
        System.out.println("Max integer: " + max(ints));
    }

    public static <E extends Comparable<E>> E max(E[] list) {
        if (list == null || list.length == 0) return null;
        E max = list[0];
        for (int i = 1; i < list.length; i++) {
            if (list[i] != null && list[i].compareTo(max) > 0) max = list[i];
        }
        return max;
    }
}
