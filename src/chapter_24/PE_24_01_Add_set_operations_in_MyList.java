package chapter_24;

/**
 * (Add set operations in MyList) Define the following methods in MyList and
 * implement them in MyAbstractList:
 *
 * // Adds the elements in otherList to this list.
 * // Returns true if this list changed as a result of the call
 * public boolean addAll(MyList<E> otherList);
 *
 * // Removes all the elements in otherList from this list
 * // Returns true if this list changed as a result of the call
 * public boolean removeAll(MyList<E> otherList);
 *
 * // Retains the elements in this list that are also in otherList
 * // Returns true if this list changed as a result of the call
 * public boolean retainAll(MyList<E> otherList);
 *
 * Write a test program that creates two MyArrayLists, list1 and list2, with
 * the initial values {"Tom", "George", "Peter", "Jean", "Jane"} and
 * {"Tom", "George", "Michael", "Michelle", "Daniel"}, then perform
 * the following operations:
 *
 * - Invokes list1.addAll(list2), and displays list1 and list2.
 * - Recreates list1 and list2 with the same initial values, invokes
 *   list1.removeAll(list2), and displays list1 and list2.
 * - Recreates list1 and list2 with the same initial values, invokes
 *   list1.retainAll(list2), and displays list1 and list2.
 */
public class PE_24_01_Add_set_operations_in_MyList {

    public static void main(String[] args) {
        String[] array1 = {"Tom", "George", "Peter", "Jean", "Jane"};
        String[] array2 = {"Tom", "George", "Michael", "Michelle", "Daniel"};

        MyArrayList<String> list1 = new MyArrayList<>(array1);
        MyArrayList<String> list2 = new MyArrayList<>(array2);

        list1.addAll(list2);
        System.out.println("list1.addAll(list2)");
        System.out.println(list1);
        System.out.println(list2);
        System.out.println();


        list1 = new MyArrayList<>(array1);
        list2 = new MyArrayList<>(array2);

        list1.removeAll(list2);
        System.out.println("list1.removeAll(list2)");
        System.out.println(list1);
        System.out.println(list2);
        System.out.println();

        list1 = new MyArrayList<>(array1);
        list2 = new MyArrayList<>(array2);

        list1.retainAll(list2);
        System.out.println("list1.retainAll(list2)");
        System.out.println(list1);
        System.out.println(list2);
        System.out.println();
    }

    public interface MyList<E> extends java.lang.Iterable<E> {
        /**
         * Add a new element at the end of this list
         */
        void add(E e);

        /**
         * Add a new element at the specified index in this list
         */
        void add(int index, E e);

        /**
         * Adds the elements in otherList to this list.
         * Returns true if this list changed as a result of the call
         */
        boolean addAll(MyList<E> otherList);

        /**
         * Clear the list
         */
        void clear();

        /**
         * Return true if this list contains the element
         */
        boolean contains(E e);

        /**
         * Return the element from this list at the specified index
         */
        E get(int index);

        /**
         * Return the index of the first matching element in this list.
         * Return -1 if no match.
         */
        int indexOf(E e);

        /**
         * Return true if this list doesn't contain any elements
         */
        boolean isEmpty();

        /**
         * Return the index of the last matching element in this list
         * Return -1 if no match.
         */
        int lastIndexOf(E e);

        /**
         * Remove the first occurrence of the element e from this list.
         * Shift any subsequent elements to the left.
         * Return true if the element is removed.
         */
        boolean remove(E e);

        /**
         * Remove the element at the specified position in this list.
         * Shift any subsequent elements to the left.
         * Return the element that was removed from the list.
         */
        E remove(int index);

        /**
         * Removes all the elements in otherList from this list
         * Returns true if this list changed as a result of the call
         */
        boolean removeAll(MyList<E> otherList);

        /**
         * Retains the elements in this list that are also in otherList
         * Returns true if this list changed as a result of the call
         */
        boolean retainAll(MyList<E> otherList);

        /**
         * Replace the element at the specified position in this list
         * with the specified element and return the old element.
         */
        Object set(int index, E e);

        /**
         * Return the number of elements in this list
         */
        int size();
    }

    public static abstract class MyAbstractList<E> implements MyList<E> {
        protected int size = 0; // The size of the list

        /**
         * Create a default list
         */
        protected MyAbstractList() {
        }

        /**
         * Create a list from an array of objects
         */
        protected MyAbstractList(E[] objects) {
            for (int i = 0; i < objects.length; i++)
                add(objects[i]);
        }

        @Override
        /** Add a new element at the end of this list */
        public void add(E e) {
            add(size, e);
        }

        /**
         * Adds the elements in otherList to this list.
         * Returns true if this list changed as a result of the call
         */
        @Override
        public boolean addAll(MyList<E> otherList) {
            if (otherList == null) throw new IllegalArgumentException("otherList cannot be null");
            int sizeBeforeAddition = size();
            for (E e : otherList) {
                add(e);
            }
            return size > sizeBeforeAddition;
        }

        /**
         * Removes all the elements in otherList from this list
         * Returns true if this list changed as a result of the call
         */
        @Override
        public boolean removeAll(MyList<E> otherList) {
            if (otherList == null) throw new IllegalArgumentException("otherList cannot be null");
            int sizeBeforeAddition = size();
            for (E e : otherList) {
                while (contains(e)) {
                    remove(e);
                }
            }
            return size < sizeBeforeAddition;
        }

        /**
         * Retains the elements in this list that are also in otherList
         * Returns true if this list changed as a result of the call
         */
        @Override
        public boolean retainAll(MyList<E> otherList) {
            if (otherList == null) throw new IllegalArgumentException("otherList cannot be null");
            int sizeBeforeAddition = size();
            int length = size() - 1;
            for (int i = length; i >= 0; i--) {
                E e = get(i);
                if (!otherList.contains(e)) {
                    remove(i);
                }
            }
            return size < sizeBeforeAddition;
        }


        @Override
        /** Return true if this list doesn't contain any elements */
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        /** Return the number of elements in this list */
        public int size() {
            return size;
        }

        @Override
        /** Remove the first occurrence of the element e
         * from this list. Shift any subsequent elements to the left.
         * Return true if the element is removed. */
        public boolean remove(E e) {
            if (indexOf(e) >= 0) {
                remove(indexOf(e));
                return true;
            } else
                return false;
        }
    }

    public static class MyArrayList<E> extends MyAbstractList<E> {
        public static final int INITIAL_CAPACITY = 16;
        private E[] data = (E[]) new Object[INITIAL_CAPACITY];

        /**
         * Create a default list
         */
        public MyArrayList() {
        }

        /**
         * Create a list from an array of objects
         */
        public MyArrayList(E[] objects) {
            for (int i = 0; i < objects.length; i++)
                add(objects[i]); // Warning: don't use super(objects)!
        }

        @Override
        /** Override iterator() defined in Iterable */
        public java.util.Iterator<E> iterator() {
            return new ArrayListIterator();
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder("[");

            for (int i = 0; i < size; i++) {
                result.append(data[i]);
                if (i < size - 1) result.append(", ");
            }

            return result.toString() + "]";
        }

        /**
         * Trims the capacity to current size
         */
        public void trimToSize() {
            if (size != data.length) {
                E[] newData = (E[]) (new Object[size]);
                System.arraycopy(data, 0, newData, 0, size);
                data = newData;
            } // If size == capacity, no need to trim
        }

        @Override
        /** Add a new element at the specified index */
        public void add(int index, E e) {
            ensureCapacity();

            // Move the elements to the right after the specified index
            for (int i = size - 1; i >= index; i--)
                data[i + 1] = data[i];

            // Insert new element to data[index]
            data[index] = e;

            // Increase size by 1
            size++;
        }

        private void checkIndex(int index) {
            if (index < 0 || index >= size)
                throw new IndexOutOfBoundsException
                        ("index " + index + " out of bounds");
        }

        /**
         * Create a new larger array, double the current size + 1
         */
        private void ensureCapacity() {
            if (size >= data.length) {
                E[] newData = (E[]) (new Object[size * 2 + 1]);
                System.arraycopy(data, 0, newData, 0, size);
                data = newData;
            }
        }

        private class ArrayListIterator
                implements java.util.Iterator<E> {
            private int current = 0; // Current index

            @Override
            public boolean hasNext() {
                return (current < size);
            }

            @Override
            public E next() {
                return data[current++];
            }

            @Override
            public void remove() {
                MyArrayList.this.remove(current);
            }
        }

        @Override
        /** Clear the list */
        public void clear() {
            data = (E[]) new Object[INITIAL_CAPACITY];
            size = 0;
        }

        @Override
        /** Return true if this list contains the element */
        public boolean contains(E e) {
            for (int i = 0; i < size; i++)
                if (e.equals(data[i])) return true;

            return false;
        }

        @Override
        /** Return the element at the specified index */
        public E get(int index) {
            checkIndex(index);
            return data[index];
        }


        @Override
        /** Return the index of the first matching element
         * in this list. Return -1 if no match. */
        public int indexOf(E e) {
            for (int i = 0; i < size; i++)
                if (e.equals(data[i])) return i;

            return -1;
        }

        @Override
        /** Return the index of the last matching element
         * in this list. Return -1 if no match. */
        public int lastIndexOf(E e) {
            for (int i = size - 1; i >= 0; i--)
                if (e.equals(data[i])) return i;

            return -1;
        }

        @Override
        /** Remove the element at the specified position
         * in this list. Shift any subsequent elements to the left.
         * Return the element that was removed from the list. */
        public E remove(int index) {
            checkIndex(index);

            E e = data[index];

            // Shift data to the left
            for (int j = index; j < size - 1; j++)
                data[j] = data[j + 1];

            data[size - 1] = null; // This element is now null

            // Decrement size
            size--;

            return e;
        }

        @Override
        /** Replace the element at the specified position
         * in this list with the specified element. */
        public E set(int index, E e) {
            checkIndex(index);
            E old = data[index];
            data[index] = e;
            return old;
        }








    }
}
