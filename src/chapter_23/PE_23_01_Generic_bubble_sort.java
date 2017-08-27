package chapter_23;

import java.util.Arrays;
import java.util.Comparator;

/**
 * (Generic bubble sort) Write the following two generic methods using bubble
 * sort. The first method sorts the elements using the Comparable interface and
 * the second uses the Comparator interface.
 *
 *      public static <E extends Comparable<E>>
 *          void bubbleSort(E[] list)
 *
 *      public static <E> void bubbleSort(E[] list,
 *          Comparator<? super E> comparator)
 */
public class PE_23_01_Generic_bubble_sort {
    public static void main(String[] args) {
        Integer[] list = {1, 3, 5, 7, 9, 0, 8, 6, 4, 2};
        System.out.println(Arrays.toString(list));

        bubbleSort(list);
        System.out.println(Arrays.toString(list));

        bubbleSort(list, Comparator.reverseOrder());
        System.out.println(Arrays.toString(list));
    }

    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        bubbleSort(list, Comparator.naturalOrder());
    }

    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        boolean needNextPass = true;

        for (int k = 1; k < list.length && needNextPass; k++) {
            needNextPass = false;
            for (int i = 0; i < list.length - k; i++) {
                if (comparator.compare(list[i], (list[i + 1])) > 0) {
                    E temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    needNextPass = true;
                }
            }
        }
    }
}
