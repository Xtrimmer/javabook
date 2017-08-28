package chapter_23;

import java.util.Arrays;
import java.util.Comparator;

/**
 * (Generic heap sort) Write the following two generic methods using heap sort.
 * The first method sorts the elements using the Comparable interface and the
 * second uses the Comparator interface.
 *
 *      public static <E extends Comparable<E>>
 *          void heapSort(E[] list)
 *
 *      public static <E> void heapSort(E[] list,
 *          Comparator<? super E> comparator)
 */
public class PE_23_05_Generic_heap_sort {
    public static void main(String[] args) {
        Integer[] list = {1, 3, 5, 7, 9, 0, 8, 6, 4, 2};
        System.out.println(Arrays.toString(list));

        heapSort(list);
        System.out.println(Arrays.toString(list));

        heapSort(list, Comparator.reverseOrder());
        System.out.println(Arrays.toString(list));
    }

    public static <E extends Comparable<E>> void heapSort(E[] list) {
        heapSort(list, Comparator.naturalOrder());
    }

    public static <E> void heapSort(E[] list, Comparator<? super E> comparator) {
        Heap<E> heap = new Heap<>(list, comparator);
        for (int i = list.length - 1; i >= 0; i--)
            list[i] = heap.remove();
    }

    static class Heap<E> {
        final Comparator<? super E> comparator;
        private final java.util.ArrayList<E> list = new java.util.ArrayList<>();

        public Heap(E[] objects, Comparator<? super E> comparator) {
            this.comparator = comparator;
            for (E object : objects) add(object);
        }

        public void add(E newObject) {
            list.add(newObject);
            int currentIndex = list.size() - 1;

            while (currentIndex > 0) {
                int parentIndex = (currentIndex - 1) / 2;
                if (comparator.compare(list.get(currentIndex), list.get(parentIndex)) > 0) {
                    E temp = list.get(currentIndex);
                    list.set(currentIndex, list.get(parentIndex));
                    list.set(parentIndex, temp);
                } else break;
                currentIndex = parentIndex;
            }
        }

        public E remove() {
            if (list.size() == 0) return null;

            E removedObject = list.get(0);
            list.set(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);

            int currentIndex = 0;
            while (currentIndex < list.size()) {
                int leftChildIndex = 2 * currentIndex + 1;
                int rightChildIndex = 2 * currentIndex + 2;

                if (leftChildIndex >= list.size()) break; // The tree is a heap
                int maxIndex = leftChildIndex;
                if (rightChildIndex < list.size()) {
                    if (comparator.compare(list.get(maxIndex), list.get(rightChildIndex)) < 0) {
                        maxIndex = rightChildIndex;
                    }
                }
                if (comparator.compare(list.get(currentIndex), list.get(maxIndex)) < 0) {
                    E temp = list.get(maxIndex);
                    list.set(maxIndex, list.get(currentIndex));
                    list.set(currentIndex, temp);
                    currentIndex = maxIndex;
                } else break;
            }
            return removedObject;
        }
    }
}
