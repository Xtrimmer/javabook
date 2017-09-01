package chapter_23;

/**
 * (Min-heap) The heap presented in the text is also known as a max-heap, in which
 * each node is greater than or equal to any of its children. A min-heap is a heap
 * in which each node is less than or equal to any of its children. Min-heaps are
 * often used to implement priority queues. Revise the Heap class in Listing 23.9 to
 * implement a min-heap.
 */
public class PE_23_07_Min_heap {
    public static void main(String[] args) {

        MinHeap<Integer> heap = new MinHeap<>();

        heap.add(5).add(4).add(3).add(30).add(1).add(6).add(2);

        while (heap.size() > 0) {
            System.out.print(heap.remove() + " ");
        }
    }

    static class MinHeap<E extends Comparable<E>> {
        private final java.util.ArrayList<E> list = new java.util.ArrayList<>();

        public MinHeap<E> add(E newObject) {
            list.add(newObject);
            int currentIndex = list.size() - 1;

            while (currentIndex > 0) {
                int parentIndex = (currentIndex - 1) / 2;
                if (list.get(currentIndex).compareTo(list.get(parentIndex)) < 0) {
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

                if (leftChildIndex >= list.size()) break; // The tree is a heap
                int maxIndex = leftChildIndex;
                if (rightChildIndex < list.size()) {
                    if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) > 0) {
                        maxIndex = rightChildIndex;
                    }
                }
                if (list.get(currentIndex).compareTo(list.get(maxIndex)) > 0) {
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
