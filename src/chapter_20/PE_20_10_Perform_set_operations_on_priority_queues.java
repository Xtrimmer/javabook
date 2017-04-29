package chapter_20;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * (Perform set operations on priority queues) Create two priority queues,
 * {"George", "Jim", "John", "Blake", "Kevin", "Michael"} and
 * {"George", "Katie", "Kevin", "Michelle", "Ryan"}, and find their
 * union, difference, and intersection.
 */
public class PE_20_10_Perform_set_operations_on_priority_queues {
    public static void main(String[] args) {
        PriorityQueue<String> queue1 = new PriorityQueue<>(
                Arrays.asList("George", "Jim", "John", "Blake", "Kevin", "Michael")
        );
        PriorityQueue<String> queue2 = new PriorityQueue<>(
                Arrays.asList("George", "Katie", "Kevin", "Michelle", "Ryan")
        );

        System.out.println("Queue 1:\n" + queue1);
        System.out.println("Queue 2:\n" + queue2);
        System.out.println();

        PriorityQueue<String> union = new PriorityQueue<>(queue1);
        union.addAll(queue2);
        System.out.println("Union:\n" + union);

        PriorityQueue<String> difference = new PriorityQueue<>(queue1);
        difference.removeAll(queue2);
        System.out.println("Difference:\n" + difference);

        PriorityQueue<String> intersection = new PriorityQueue<>(queue1);
        intersection.retainAll(queue2);
        System.out.println("Intersection:\n" + intersection);
    }
}
