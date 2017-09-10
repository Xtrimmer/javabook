package chapter_23;

import java.util.ArrayList;

/**
 * (Heap clone and equals) Implement the clone and equals method in the
 * Heap class.
 */
public class PE_23_11_Heap_clone_and_equals {
    public static void main(String[] args) {
        Heap<Character> heap = new Heap<>();
        heap.add('A').add('B').add('C');

        System.out.println("x.clone() != x: " + (heap.clone() != heap));
        System.out.println("x.clone().getClass() == x.getClass(): " + (heap.clone().getClass() == heap.getClass()));
        System.out.println("x.clone().equals(x): " + (heap.clone().equals(heap)));

        Heap<Character> heapClone = (Heap<Character>) heap.clone();
        heapClone.add('D').add('E').add('F');

        print("Original:", heap);
        print("Clone:", heapClone);
    }

    private static void print(String message, Heap<Character> heap) {
        System.out.println(message);
        while (heap.size() > 0) {
            System.out.print(heap.remove() + " ");
        }
        System.out.println();
    }

    static class Heap<E extends Comparable<E>> implements Cloneable {
        private java.util.ArrayList<E> list = new java.util.ArrayList<>();

        public Heap() {
        }

        public Heap<E> add(E newObject) {
            list.add(newObject);
            int currentIndex = list.size() - 1;

            while (currentIndex > 0) {
                int parentIndex = (currentIndex - 1) / 2;
                if (list.get(currentIndex).compareTo(
                        list.get(parentIndex)) > 0) {
                    E temp = list.get(currentIndex);
                    list.set(currentIndex, list.get(parentIndex));
                    list.set(parentIndex, temp);
                } else
                    break;

                currentIndex = parentIndex;
            }
            return this;
        }

        @Override
        public boolean equals(Object o) {
            return o == this || o instanceof Heap && list.equals(((Heap) o).list);
        }

        @Override
        public Object clone() {
            Heap<E> clone = null;
            try {
                clone = (Heap<E>) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            if (clone != null) {
                clone.list = (ArrayList<E>) list.clone();
            }
            return clone;
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
                    if (list.get(maxIndex).compareTo(
                            list.get(rightChildIndex)) < 0) {
                        maxIndex = rightChildIndex;
                    }
                }

                if (list.get(currentIndex).compareTo(
                        list.get(maxIndex)) < 0) {
                    E temp = list.get(maxIndex);
                    list.set(maxIndex, list.get(currentIndex));
                    list.set(currentIndex, temp);
                    currentIndex = maxIndex;
                } else
                    break;
            }

            return removedObject;
        }

        public int size() {
            return list.size();
        }
    }
}
