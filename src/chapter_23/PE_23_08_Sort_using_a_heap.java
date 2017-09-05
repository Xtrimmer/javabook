package chapter_23;

import java.util.Arrays;
import java.util.Comparator;

/**
 * (Sort using a heap) Implement the following sort method using a heap.
 *
 *      public static <E extends Comparable<E>> void sort(E[] list)
 */
public class PE_23_08_Sort_using_a_heap {
    public static void main(String[] args) {
        String[] strings = generateRandomStringArray(3, 15, "ABC");

        System.out.println("Before Sort");
        System.out.println(Arrays.toString(strings));

        sort(strings);
        System.out.println("After Sort");
        System.out.println(Arrays.toString(strings));
    }

    private static String[] generateRandomStringArray(int stringLength, int arraySize, String characters) {
        String[] strings = new String[arraySize];
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arraySize; i++) {
            stringBuilder.delete(0, stringBuilder.length());
            for (int j = 0; j < stringLength; j++) {
                stringBuilder.append(characters.charAt((int) (Math.random() * characters.length())));
            }
            strings[i] = stringBuilder.toString();
        }
        return strings;
    }

    public static <E extends Comparable<E>> void sort(E[] list) {
        Heap<E> heap = new Heap<>(list, Comparator.naturalOrder());
        for (int i = list.length - 1; i >= 0; i--) {
            list[i] = heap.remove();
        }
    }

    static class Heap<E> {
        private final Comparator<? super E> comparator;
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
