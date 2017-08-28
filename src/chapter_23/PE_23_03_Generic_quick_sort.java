package chapter_23;

import java.util.Arrays;
import java.util.Comparator;

/**
 * (Generic quick sort) Write the following two generic methods using quick sort.
 * The first method sorts the elements using the Comparable interface and the
 * second uses the Comparator interface.
 *
 *      public static <E extends Comparable<E>>
 *          void quickSort(E[] list)
 *
 *      public static <E> void quickSort(E[] list,
 *          Comparator<? super E> comparator)
 */
public class PE_23_03_Generic_quick_sort {
    public static void main(String[] args) {

        Integer[] list = {1, 3, 5, 7, 9, 0, 8, 6, 4, 2};
        System.out.println(Arrays.toString(list));

        quickSort(list);
        System.out.println(Arrays.toString(list));

        quickSort(list, Comparator.reverseOrder());
        System.out.println(Arrays.toString(list));

    }

    private static <E> int partition(E[] list, int first, int last, Comparator<? super E> comparator) {
        E pivot = list[first];
        int low = first + 1;
        int high = last;

        while (high > low) {
            while (low <= high && comparator.compare(list[low], pivot) <= 0)
                low++;
            while (low <= high && comparator.compare(list[high], pivot) > 0)
                high--;
            if (high > low) {
                E temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && comparator.compare(list[high], pivot) >= 0)
            high--;
        if (comparator.compare(pivot, list[high]) > 0) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return first;
        }
    }

    public static <E> void quickSort(E[] list, Comparator<? super E> comparator) {
        quickSort(list, 0, list.length - 1, comparator);
    }

    private static <E> void quickSort(E[] list, int first, int last, Comparator<? super E> comparator) {
        if (last > first) {
            int pivotIndex = partition(list, first, last, comparator);
            quickSort(list, first, pivotIndex - 1, comparator);
            quickSort(list, pivotIndex + 1, last, comparator);
        }
    }

    public static <E extends Comparable<E>> void quickSort(E[] list) {
        quickSort(list, Comparator.naturalOrder());
    }
}
