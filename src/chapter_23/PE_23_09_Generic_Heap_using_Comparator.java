package chapter_23;

import java.util.Comparator;

public class PE_23_09_Generic_Heap_using_Comparator {
    public static void main(String[] args) {
        Heap<String> heap = new Heap<>(Comparator.reverseOrder());
        heap.add("ABC").add("DEF").add("CBA").add("FED").add("BBC");

        int size = heap.size();
        for (int i = 0; i < size; i++) {
            System.out.println(heap.remove());
        }
    }

    static class Heap<E> {
        private final Comparator<? super E> comparator;
        private final java.util.ArrayList<E> list = new java.util.ArrayList<>();

        public Heap(Comparator<? super E> comparator) {
            this.comparator = comparator;
        }

        public Heap<E> add(E newObject) {
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
            return this;
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

                if (leftChildIndex >= list.size()) break;
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

        public int size() {
            return list.size();
        }
    }
}
