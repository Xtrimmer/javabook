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

    }

    public interface MyList<E> extends java.lang.Iterable<E> {
        /**
         * Add a new element at the end of this list
         */
        public void add(E e);

        /**
         * Add a new element at the specified index in this list
         */
        public void add(int index, E e);

        /**
         * Clear the list
         */
        public void clear();

        /**
         * Return true if this list contains the element
         */
        public boolean contains(E e);

        /**
         * Return the element from this list at the specified index
         */
        public E get(int index);

        /**
         * Return the index of the first matching element in this list.
         * Return -1 if no match.
         */
        public int indexOf(E e);

        /**
         * Return true if this list doesn't contain any elements
         */
        public boolean isEmpty();

        /**
         * Return the index of the last matching element in this list
         * Return -1 if no match.
         */
        public int lastIndexOf(E e);

        /**
         * Remove the first occurrence of the element e from this list.
         * Shift any subsequent elements to the left.
         * Return true if the element is removed.
         */
        public boolean remove(E e);

        /**
         * Remove the element at the specified position in this list.
         * Shift any subsequent elements to the left.
         * Return the element that was removed from the list.
         */
        public E remove(int index);

        /**
         * Replace the element at the specified position in this list
         * with the specified element and return the old element.
         */
        public Object set(int index, E e);

        /**
         * Return the number of elements in this list
         */
        public int size();

        /** Adds the elements in otherList to this list.
         * Returns true if this list changed as a result of the call
         */
        public boolean addAll(MyList<E> otherList);

        /** Removes all the elements in otherList from this list
         * Returns true if this list changed as a result of the call
         */
        public boolean removeAll(MyList<E> otherList);

        /** Retains the elements in this list that are also in otherList
         * Returns true if this list changed as a result of the call
         */
        public boolean retainAll(MyList<E> otherList);
    }

    public abstract class MyAbstractList<E> implements MyList<E> {
        protected int size = 0; // The size of the list

        /**
         * Create a default list
         */
        protected MyAbstractList() {
        }

        /** Adds the elements in otherList to this list.
         * Returns true if this list changed as a result of the call
         */
        @Override
        public boolean addAll(MyList<E> otherList){
            for (E e : otherList) {
                add(e);
            }
            return true;
        }

        /** Removes all the elements in otherList from this list
         * Returns true if this list changed as a result of the call
         */
        @Override
        public boolean removeAll(MyList<E> otherList){
            for (E e : otherList) {
                while (contains(e)){
                    remove(e);
                }
            }
            return true;
        }

        /** Retains the elements in this list that are also in otherList
         * Returns true if this list changed as a result of the call
         */
        @Override
        public boolean retainAll(MyList<E> otherList){
            for (E e : otherList) {

            }
            return true;
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
}
