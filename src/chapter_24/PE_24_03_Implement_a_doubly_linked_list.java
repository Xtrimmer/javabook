package chapter_24;

import java.util.AbstractSequentialList;
import java.util.ListIterator;

/**
 * (Implement a doubly linked list) The MyLinkedList class used in Listing 24.6
 * is a one-way directional linked list that enables one-way traversal of the list.
 * Modify the Node class to add the new data field name previous to refer to the
 * previous node in the list, as follows:
 *
 *      public class Node<E> { *
 *          E element;
 *          Node<E> next;
 *          Node<E> previous;
 *
 *          public Node(E e) {
 *              element = e;
 *          }
 *      }
 *
 * Implement a new class named TwoWayLinkedList that uses a doubly
 * linked list to store elements. The MyLinkedList class in the text
 * extends MyAbstractList. Define TwoWayLinkedList to extend the
 * java.util.AbstractSequentialList class. You need to implement all the
 * methods defined in MyLinkedList as well as the methods listIterator()
 * and listIterator(int index). Both return an instance of java.util.
 * ListIterator<E>. The former sets the cursor to the head of the list and the
 * latter to the element at the specified index.
 */
public class PE_24_03_Implement_a_doubly_linked_list {
    public static void main(String[] args) {

    }

    private static class TwoWayLinkedList<E> extends AbstractSequentialList<E>{
        private Node<E> head, tail;

        @Override
        public ListIterator<E> listIterator(int index) {
            return new ListIterator<E>() {

                private Node<E> current = head;
                private int index = 0;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public E next() {
                    E e = current.element;
                    current = current.next;
                    index++;
                    return e;
                }

                @Override
                public boolean hasPrevious() {
                    return current.previous != null;
                }

                @Override
                public E previous() {
                    current = current.previous;
                    E e = current.element;
                    return e;
                }

                @Override
                public int nextIndex() {
                    return index + 1;
                }

                @Override
                public int previousIndex() {
                    return 0;
                }

                @Override
                public void remove() {

                }

                @Override
                public void set(E e) {

                }

                @Override
                public void add(E e) {

                }
            };
        }

        @Override
        public int size() {
            return 0;
        }

        private static class Node<E> {
            E element;
            Node<E> next;
            Node<E> previous;
            public Node(E e) {
                element = e;
            }
        }
    }
}
