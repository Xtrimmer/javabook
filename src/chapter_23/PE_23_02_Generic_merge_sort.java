package chapter_23;

import java.util.Arrays;
import java.util.Comparator;

/**
 * (Generic merge sort) Write the following two generic methods using merge sort.
 * The first method sorts the elements using the Comparable interface and the
 * second uses the Comparator interface.
 *
 *      public static <E extends Comparable<E>>
 *          void mergeSort(E[] list)
 *
 *      public static <E> void mergeSort(E[] list,
 *          Comparator<? super E> comparator)
 */
public class PE_23_02_Generic_merge_sort {
    public static void main(String[] args) {

        Integer[] list = {1, 3, 5, 7, 9, 0, 8, 6, 4, 2};
        System.out.println(Arrays.toString(list));

        mergeSort(list);
        System.out.println(Arrays.toString(list));

        mergeSort(list, Comparator.reverseOrder());
        System.out.println(Arrays.toString(list));
    }

    /** Merge two sorted lists */
    private static <E> void merge(E[] list1, E[] list2, E[] temp, Comparator<? super E> comparator) {
        int current1 = 0; // Current index in list1
        int current2 = 0; // Current index in list2
        int current3 = 0; // Current index in temp

        while (current1 < list1.length && current2 < list2.length) {
            if (comparator.compare(list1[current1], list2[current2]) < 0)
                temp[current3++] = list1[current1++];
            else
                temp[current3++] = list2[current2++];
        }

        while (current1 < list1.length)
            temp[current3++] = list1[current1++];

        while (current2 < list2.length)
            temp[current3++] = list2[current2++];
    }

    public static <E> void mergeSort(E[] list, Comparator<? super E> comparator) {
        if (list.length > 1) {
            E[] firstHalf = (E[]) new Object[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf, comparator);

            // Merge sort the second half
            int secondHalfLength = list.length - list.length / 2;
            E[] secondHalf = (E[]) new Object[secondHalfLength];
            System.arraycopy(list, list.length / 2,
                    secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf, comparator);

            // Merge firstHalf with secondHalf into list
            merge(firstHalf, secondHalf, list, comparator);
        }
    }

    public static <E extends Comparable<E>> void mergeSort(E[] list) {
        mergeSort(list, Comparator.naturalOrder());
    }
}
