package chapter_24;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Iterator;

/**
 * (Implement MyLinkedList) The implementations of the methods
 * contains(E e), get(int index), indexOf(E e), lastIndexOf(E e),
 * and set(int index, E e) are omitted in the text. Implement these methods.
 */
public class PE_24_02_Implement_MyLinkedList {

    public static void main(String[] args) {
        String[] array1 = {"Tom", "George", "Peter", "Jean", "Jane"};
        String[] array2 = {"Tom", "George", "Michael", "Michelle", "Daniel"};

        MyLinkedList<String> list1 = new MyLinkedList<>(array1);
        MyLinkedList<String> list2 = new MyLinkedList<>(array2);

        System.out.println("list1.contains(\"Peter\"): " + list1.contains("Peter"));
        System.out.println("list2.contains(\"Peter\"): " + list2.contains("Peter"));
        System.out.println();
        System.out.println("list1.get(2): " + list1.get(4));
        System.out.println("list2.get(2): " + list2.get(4));
        System.out.println();
        System.out.println("list1.indexOf(\"Tom\"): " + list1.indexOf("Tom"));
        System.out.println("list1.indexOf(\"Peter\"): " + list1.indexOf("Peter"));
        System.out.println("list1.indexOf(\"Jane\"): " + list1.indexOf("Jane"));
        System.out.println("list1.indexOf(\"Daniel\"): " + list1.indexOf("Daniel"));
        System.out.println();
        list1.addAll(list2);
        System.out.println("list1.lastIndexOf(\"Tom\")" + list1.lastIndexOf("Tom"));
        System.out.println("list1.lastIndexOf(\"George\")" + list1.lastIndexOf("George"));
        System.out.println("list1.lastIndexOf(\"Peter\")" + list1.lastIndexOf("Peter"));
        System.out.println();
        System.out.println("list2.set(0, \"Bob\"): " + list2.set(0, "Bob"));
        System.out.println("list2.set(0, \"Bob\"): " + list2.set(0, "Bob"));
        System.out.println("list2.set(0, \"Bob\"): " + list2.set(4, "Bob"));
        System.out.println("list2.set(0, \"Bob\"): " + list2.set(4, "Bob"));
        System.out.println("list2.set(0, \"Bob\"): " + list2.set(2, "Bob"));
        System.out.println("list2.set(0, \"Bob\"): " + list2.set(2, "Bob"));
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
    }

    public static class MyLinkedList<E> extends MyAbstractList<E> {
        private Node<E> head, tail;

        /**
         * Create a default list
         */
        public MyLinkedList() {
        }

        /**
         * Create a list from an array of objects
         */
        public MyLinkedList(E[] objects) {
            super(objects);
        }

        /**
         * Return the head element in the list
         */
        public E getFirst() {
            if (size == 0) {
                return null;
            } else {
                return head.element;
            }
        }

        /**
         * Return the last element in the list
         */
        public E getLast() {
            if (size == 0) {
                return null;
            } else {
                return tail.element;
            }
        }

        /**
         * Add an element to the beginning of the list
         */
        public void addFirst(E e) {
            Node<E> newNode = new Node<>(e); // Create a new node
            newNode.next = head; // link the new node with the head
            head = newNode; // head points to the new node
            size++; // Increase list size

            if (tail == null) // The new node is the only node in list
                tail = head;
        }

        /**
         * Add an element to the end of the list
         */
        public void addLast(E e) {
            Node<E> newNode = new Node<>(e); // Create a new node for e

            if (tail == null) {
                head = tail = newNode; // The only node in list
            } else {
                tail.next = newNode; // Link the new node with the last node
                tail = tail.next; // tail now points to the last node
            }

            size++;
        }

        @Override
        /** Add a new element at the specified index
         * in this list. The index of the head element is 0 */
        public void add(int index, E e) {
            if (index == 0) addFirst(e); // Insert first
            else if (index >= size) addLast(e); // Insert last
            else { // Insert in the middle
                Node<E> current = head;
                for (int i = 1; i < index; i++)
                    current = current.next;
                Node<E> temp = current.next;
                current.next = new Node<E>(e);
                (current.next).next = temp;
                size++;
            }
        }

        /**
         * Remove the head node and
         * return the object that is contained in the removed node.
         */
        public E removeFirst() {
            if (size == 0) return null; // Nothing to delete
            else {
                Node<E> temp = head; // Keep the first node temporarily
                head = head.next; // Move head to point to next node
                size--; // Reduce size by 1
                if (head == null) tail = null; // List becomes empty
                return temp.element; // Return the deleted element
            }
        }

        /**
         * Remove the last node and
         * return the object that is contained in the removed node.
         */
        public E removeLast() {
            if (size == 0) return null; // Nothing to remove
            else if (size == 1) { // Only one element in the list
                Node<E> temp = head;
                head = tail = null; // list becomes empty
                size = 0;
                return temp.element;
            } else {
                Node<E> current = head;

                for (int i = 0; i < size - 2; i++)
                    current = current.next;

                Node<E> temp = tail;
                tail = current;
                tail.next = null;
                size--;
                return temp.element;
            }
        }

        @Override
        /** Remove the element at the specified position in this
         * list. Return the element that was removed from the list.
         */
        public E remove(int index) {
            if (index < 0 || index >= size) return null; // Out of range
            else if (index == 0) return removeFirst(); // Remove first
            else if (index == size - 1) return removeLast(); // Remove last
            else {
                Node<E> previous = head;

                for (int i = 1; i < index; i++) {
                    previous = previous.next;
                }

                Node<E> current = previous.next;
                previous.next = current.next;
                size--;
                return current.element;
            }
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder("[");

            Node<E> current = head;
            for (int i = 0; i < size; i++) {
                result.append(current.element);
                current = current.next;
                if (current != null) {
                    result.append(", "); // Separate two elements with a comma
                } else {
                    result.append("]"); // Insert the closing ] in the string
                }
            }

            return result.toString();
        }

        @Override
        /** Clear the list */
        public void clear() {
            size = 0;
            head = tail = null;
        }

        @Override
        /** Return true if this list contains the element e */
        public boolean contains(E e) {
            Iterator<E> iterator = this.iterator();
            while (iterator.hasNext()) {
                if (iterator.next() == e) {
                    return true;
                }
            }
            return false;
        }

        @Override
        /** Return the element at the specified index */
        public E get(int index) {
            checkIndex(index);
            Iterator<E> iterator = this.iterator();
            E e = null;
            for (int i = 0; i <= index; i++) {
                e = iterator.next();
            }
            return e;
        }

        @Override
        /** Return the index of the head matching element
         * in this list. Return -1 if no match. */
        public int indexOf(E e) {
            int index = 0;
            Iterator<E> iterator = this.iterator();
            while (iterator.hasNext()) {
                if (iterator.next() == e) {
                    return index;
                }
                index++;
            }
            return -1;
        }

        @Override
        /** Return the index of the last matching element
         * in this list. Return -1 if no match. */
        public int lastIndexOf(E e) {
            int index = 0;
            int lastIndex = -1;
            Iterator<E> iterator = this.iterator();
            while (iterator.hasNext()) {
                if (iterator.next() == e) {
                    lastIndex = index;
                }
                index++;
            }
            return lastIndex;
        }

        @Override
        /** Replace the element at the specified position
         * in this list with the specified element. */
        public E set(int index, E e) {
            checkIndex(index);
            E previousElement = null;
            if (index == size - 1) {
                previousElement = tail.element;
                tail.element = e;
            } else {
                Node<E> node = head;
                for (int i = 0; i < index; i++) {
                    node = node.next;
                }
                previousElement = node.element;
                node.element = e;
            }
            return previousElement;
        }

        @Override
        /** Override iterator() defined in Iterable */
        public java.util.Iterator<E> iterator() {
            return new LinkedListIterator();
        }

        private void checkIndex(int index) {
            if (index < 0 || index >= size)
                throw new IndexOutOfBoundsException
                        ("index " + index + " out of bounds");
        }

        private class LinkedListIterator
                implements java.util.Iterator<E> {
            private Node<E> current = head; // Current index

            @Override
            public boolean hasNext() {
                return (current != null);
            }

            @Override
            public E next() {
                E e = current.element;
                current = current.next;
                return e;
            }

            @Override
            public void remove() {
                System.out.println("Implementation left as an exercise");
            }
        }

        // This class is only used in LinkedList, so it is private.
        // This class does not need to access any
        // instance members of LinkedList, so it is defined static.
        private static class Node<E> {
            E element;
            Node<E> next;

            public Node(E element) {
                this.element = element;
            }
        }
    }
}
