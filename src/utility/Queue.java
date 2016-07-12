package utility;

/**
 * Added for Chapter 10 Exercise 10.
 */
public class Queue {
    private int[] elements;
    private int size;
    private int head;

    public Queue() {
        this(8);
    }

    public Queue(int initialCapacity) {
        elements = new int[initialCapacity];
    }

    public void enqueue(int v) {
        if (size >= elements.length) {
            int[] temp = new int[elements.length * 2];
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
        }
        elements[size++] = v;
    }

    public int dequeue() {
        int element = elements[0];
        size--;
        System.arraycopy(elements, 1, elements, 0, size);
        return element;
    }

    public boolean empty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
}
