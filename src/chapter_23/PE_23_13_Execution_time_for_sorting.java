package chapter_23;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * (Execution time for sorting) Write a program that obtains the execution time of
 * selection sort, bubble sort, merge sort, quick sort, heap sort, and radix sort for
 * input size 50,000, 100,000, 150,000, 200,000, 250,000, and 300,000. Your program
 * should create data randomly and print a table like this:
 *
 *      Array    | Selection Bubble    Merge     Quick     Heap      Radix
 *      size     | Sort      Sort      Sort      Sort      Sort      Sort
 *      --------------------------------------------------------------------
 *       50,000  |
 *      100,000  |
 *      150,000  |
 *      200,000  |
 *      250,000  |
 *      300,000  |
 *
 * (Hint: You can use the following code template to obtain the execution time.)
 *
 *      long startTime = System.currentTimeMillis();
 *      perform the task;
 *      long endTime = System.currentTimeMillis();
 *      long executionTime = endTime - startTime;
 *
 * The text gives a recursive quick sort. Write a nonrecursive version in this exercise.
 */
public class PE_23_13_Execution_time_for_sorting {
    private static final int MAX_ARRAY_SIZE = 30000;
    private static final int MIN_ARRAY_SIZE = MAX_ARRAY_SIZE / 6;
    private static final Integer[] RANDOM_INTS = generateRandomInts(MAX_ARRAY_SIZE);

    public static void main(String[] args) {
        System.out.println("Array   | Selection Bubble    Merge     Quick     Heap      Radix");
        System.out.println("size    | Sort      Sort      Sort      Sort      Sort      Sort");
        System.out.println("--------------------------------------------------------------------");
        for (int arraySize = MIN_ARRAY_SIZE; arraySize <= MAX_ARRAY_SIZE; arraySize += MIN_ARRAY_SIZE) {
            System.out.printf("%,-7d | ", arraySize);
            printSortingResults(arraySize);
        }
    }

    private static Integer[] generateRandomInts(int size) {
        Integer[] ints = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            ints[i] = random.nextInt(size);
        }
        return ints;
    }

    private static <E extends Comparable<E>> void printSortingResult(E[] array, Sortable sorter) {
        long startTime = System.currentTimeMillis();
        sorter.sort(array);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.printf("%,-10d", executionTime);
    }

    private static void printSortingResults(int arraySize) {
        Integer[] array = new Integer[arraySize];
        Sortable[] sortables = {new SelectionSorter(), new BubbleSorter(), new MergeSorter(),
                new QuickSorter(), new HeapSorter(), new RadixSorter()};

        for (Sortable sortable : sortables) {
            System.arraycopy(RANDOM_INTS, 0, array, 0, arraySize);
            printSortingResult(array, sortable);
        }
        System.out.println();
    }

    private interface Sortable<E> {
        void sort(E[] array);
    }

    private static class SelectionSorter<E extends Comparable<E>> implements Sortable<E> {
        @Override
        public void sort(E[] array) {
            Comparator<E> comparator = Comparator.naturalOrder();
            int min;
            for (int i = 0; i < array.length; i++) {
                min = i;
                for (int j = i; j < array.length; j++) {
                    if (comparator.compare(array[j], array[min]) < 0) {
                        min = j;
                    }
                }
                E temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
    }

    private static class BubbleSorter<E extends Comparable<E>> implements Sortable<E> {
        @Override
        public void sort(E[] array) {
            Comparator<E> comparator = Comparator.naturalOrder();
            boolean needNextPass = true;
            for (int k = 1; k < array.length && needNextPass; k++) {
                needNextPass = false;
                for (int i = 0; i < array.length - k; i++) {
                    if (comparator.compare(array[i], (array[i + 1])) > 0) {
                        E temp = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = temp;
                        needNextPass = true;
                    }
                }
            }
        }
    }

    private static class MergeSorter<E extends Comparable<E>> implements Sortable<E> {
        private static <E> void merge(E[] list1, E[] list2, E[] temp, Comparator<? super E> comparator) {
            int current1 = 0;
            int current2 = 0;
            int current3 = 0;

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

        @Override
        public void sort(E[] array) {
            Comparator<E> comparator = Comparator.naturalOrder();
            if (array.length > 1) {
                E[] firstHalf = (E[]) new Comparable[array.length / 2];
                System.arraycopy(array, 0, firstHalf, 0, array.length / 2);
                sort(firstHalf);

                int secondHalfLength = array.length - array.length / 2;
                E[] secondHalf = (E[]) new Comparable[secondHalfLength];
                System.arraycopy(array, array.length / 2,
                        secondHalf, 0, secondHalfLength);
                sort(secondHalf);

                merge(firstHalf, secondHalf, array, comparator);
            }
        }
    }

    private static class QuickSorter<E extends Comparable<E>> implements Sortable<E> {
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

        private static <E> void quickSort(E[] list, int first, int last, Comparator<? super E> comparator) {
            if (last > first) {
                int pivotIndex = partition(list, first, last, comparator);
                quickSort(list, first, pivotIndex - 1, comparator);
                quickSort(list, pivotIndex + 1, last, comparator);
            }
        }

        @Override
        public void sort(E[] array) {
            Comparator<E> comparator = Comparator.naturalOrder();
            quickSort(array, 0, array.length - 1, comparator);
        }
    }

    private static class HeapSorter<E extends Comparable<E>> implements Sortable<E> {
        static class Heap<E> {
            final Comparator<? super E> comparator;
            private final ArrayList<E> list = new ArrayList<>();

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

        @Override
        public void sort(E[] array) {
            Comparator<E> comparator = Comparator.naturalOrder();
            Heap<E> heap = new Heap<>(array, comparator);
            for (int i = array.length - 1; i >= 0; i--) {
                array[i] = heap.remove();
            }
        }
    }

    private static class RadixSorter implements Sortable<Integer> {
        private static List<List<Integer>> ResetBuckets() {
            List<List<Integer>> buckets = new ArrayList<>(10);
            for (int i = 0; i < 10; i++) {
                buckets.add(new ArrayList<>());
            }
            return buckets;
        }

        private static void combineBuckets(Integer[] list, List<List<Integer>> buckets) {
            int i = 0;
            for (List<Integer> integers : buckets) {
                for (Integer integer : integers) {
                    list[i++] = integer;
                }
            }
        }

        private static void distributeToBuckets(Integer[] list, int radix, List<List<Integer>> buckets) {
            int divisorModulo = (int) Math.pow(10, radix);
            int divisorDivision = (int) Math.pow(10, radix - 1);
            int bucket;
            for (int integer : list) {
                bucket = integer % divisorModulo / divisorDivision;
                buckets.get(bucket).add(integer);
            }
        }

        @Override
        public void sort(Integer[] array) {
            int radixCount = 5;
            for (int radix = 1; radix <= radixCount; radix++) {
                List<List<Integer>> buckets = ResetBuckets();
                distributeToBuckets(array, radix, buckets);
                combineBuckets(array, buckets);
            }
        }
    }
}
