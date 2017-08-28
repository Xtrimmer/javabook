package chapter_23;

/**
 * (Improve quick sort) The quick sort algorithm presented in the book selects the
 * first element in the list as the pivot. Revise it by selecting the median among the
 * first, middle, and last elements in the list.
 */
public class PE_23_04_Improve_quick_sort {

    /** A test method */
    public static void main(String[] args) {
        int[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        quickSort(list);
        for (int aList : list) System.out.print(aList + " ");
    }

    private static Pivot getPivot(int[] list, int first, int last) {
        Pivot pivot = new Pivot();
        int middle = (first + last) / 2;
        pivot.value = Math.min(Math.max(list[first], list[middle]), Math.max(list[middle], list[last]));
        if (pivot.value == list[first]) pivot.index = first;
        else if (pivot.value == list[last]) pivot.index = last;
        else pivot.index = middle;
        return pivot;
    }

    /** Partition the array list[first..last] */
    private static int partition(int[] list, int first, int last) {
        Pivot pivot = getPivot(list, first, last);
        list[pivot.index] = list[first];
        list[first] = pivot.value;
        int low = first + 1;
        int high = last;

        while (high > low) {
            while (low <= high && list[low] <= pivot.value)
                low++;
            while (low <= high && list[high] > pivot.value)
                high--;
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && list[high] >= pivot.value)
            high--;
        if (pivot.value > list[high]) {
            list[first] = list[high];
            list[high] = pivot.value;
            return high;
        } else {
            return first;
        }
    }

    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    private static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    static class Pivot {
        int value;
        int index;
    }
}
