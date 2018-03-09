package chapter_23;

import java.util.Arrays;

/**
 * (Modify merge sort) Rewrite the mergeSort method to recursively sort the first
 * half of the array and the second half of the array without creating new temporary
 * arrays, and then merge the two into a temporary array and copy its contents to the
 * original array, as shown in Figure 23.6b.
 */
public class PE_23_20_Modify_merge_sort {
    public static void main(String[] args) {
        int[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        mergeSort(list);
        System.out.println(Arrays.toString(list));
    }

    public static void merge(int[] list, int start1, int end1, int start2, int end2) {
        int firstListIndex = start1;
        int secondListIndex = start2;
        int tempIndex = 0;
        int[] temp = new int[(end1 - start1) + (end2 - start2) + 2];

        while (firstListIndex <= end1 && secondListIndex <= end2) {
            if (list[firstListIndex] < list[secondListIndex])
                temp[tempIndex++] = list[firstListIndex++];
            else
                temp[tempIndex++] = list[secondListIndex++];
        }

        while (firstListIndex <= end1)
            temp[tempIndex++] = list[firstListIndex++];

        while (secondListIndex <= end2)
            temp[tempIndex++] = list[secondListIndex++];

        System.arraycopy(temp, 0, list, start1, end2 - start1 + 1);
    }

    public static void mergeSort(int[] list, int start, int end) {
        if (end - start > 0) {
            int end1 = (end - start) / 2 + start;
            int start2 = end1 + 1;
            mergeSort(list, start, end1);
            mergeSort(list, start2, end);
            merge(list, start, end1, start2, end);
        }
    }

    public static void mergeSort(int[] list) {
        mergeSort(list, 0, list.length - 1);
    }
}
