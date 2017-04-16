package chapter_19;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * (Revising Listing 19.1) Revise the GenericStack class in Listing 19.1 to implement
 * it using an array rather than an ArrayList. You should check the array size
 * before adding a new element to the stack. If the array is full, create a new array that
 * doubles the current array size and copy the elements from the current array to the
 * new array.
 */
public class PE_19_01_Revising_Listing_19_1 {
    public static void main(String[] args) {
        GenericStack<String> list1 = new GenericStack<>();
        GenericStack<Integer> list2 = new GenericStack<>();

        System.out.println(list1.toString());
        list1.push("AAA");
        list1.push("BBB");
        list1.push("CCC");
        System.out.println("size = " + list1.getSize());
        System.out.println(list1.toString());

        System.out.println(list2.toString());
        list2.push(111);
        list2.push(222);
        list2.push(333);
        System.out.println("size = " + list2.getSize());
        System.out.println(list2.toString());

        System.out.println("pop " + list1.pop());
        System.out.println("pop " + list1.pop());
        System.out.println("size = " + list1.getSize());
        System.out.println(list1.toString());

        System.out.println("pop " + list2.pop());
        System.out.println("size = " + list2.getSize());
        System.out.println(list2.toString());

        System.out.println("peek " + list1.peek());
        System.out.println(list1.toString());

        System.out.println("pop " + list1.pop());
        System.out.println("size = " + list1.getSize());
    }

    static class GenericStack<E> {
        private E[] list = (E[]) new Object[1];
        private int size = 0;

        public int getSize() {
            return size;
        }

        public E peek() {
            if (isEmpty()) throw new EmptyStackException();
            return list[size - 1];
        }

        public E pop() {
            if (isEmpty()) throw new EmptyStackException();
            return list[--size];
        }

        public void push(E o) {
            if (size == list.length) {
                list = Arrays.copyOf(list, list.length * 2);
            }
            list[size++] = o;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("stack: [");
            if (!isEmpty()) {
                stringBuilder.append(list[0]);
                for (int i = 1; i < size; i++) {
                    stringBuilder.append(", ");
                    stringBuilder.append(list[i]);
                }
            }
            stringBuilder.append("]");
            return stringBuilder.toString();
        }

        private boolean isEmpty() {
            return size == 0;
        }
    }
}