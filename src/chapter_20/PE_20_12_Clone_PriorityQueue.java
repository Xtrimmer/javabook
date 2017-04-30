package chapter_20;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * (Clone PriorityQueue) Define MyPriorityQueue class that extends
 * PriorityQueue to implement the Cloneable interface and implement the
 * clone() method to clone a priority queue.
 */
public class PE_20_12_Clone_PriorityQueue {
    public static void main(String[] args) throws CloneNotSupportedException {
        MyPriorityQueue<Integer> queue1 = new MyPriorityQueue<>();
        queue1.addAll(Arrays.asList(1, 11, 111));
        MyPriorityQueue<Integer> queue2 = (MyPriorityQueue<Integer>) queue1.clone();
        queue2.offer(1111);
        System.out.println("Queue 1:\n" + queue1);
        System.out.println("Queue 2:\n" + queue2);
    }

    private static class MyPriorityQueue<E> extends PriorityQueue<E> implements Cloneable {
        @Override
        protected Object clone() throws CloneNotSupportedException {
            MyPriorityQueue<E> myPriorityQueueCopy = new MyPriorityQueue<>();
            myPriorityQueueCopy.addAll(this);
            return myPriorityQueueCopy;
        }
    }
}
