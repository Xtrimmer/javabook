package chapter_19;

import java.util.ArrayList;

/**
 * (Largest element in ArrayList) Write the following method that returns the largest
 * element in an ArrayList:
 *
 *      public static <E extends Comparable<E>> E max(ArrayList<E> list)
 */
public class PE_19_10_Largest_element_in_ArrayList {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>() {{
            add("Delta");
            add("Juliett");
            add("Charlie");
            add("Foxtrot");
            add("India");
            add("Bravo");
            add("Alfa");
            add("Hotel");
            add("Golf");
            add("Echo");
        }};

        ArrayList<Integer> integers = new ArrayList<Integer>() {{
            add(5);
            add(0);
            add(6);
            add(2);
            add(1);
            add(9);
            add(3);
            add(4);
            add(7);
            add(8);
        }};

        System.out.println(strings);
        System.out.println("Max string:  " + max(strings));
        System.out.println();
        System.out.println(integers);
        System.out.println("Max integer: " + max(integers));
    }

    public static <E extends Comparable<E>> E max(ArrayList<E> list) {
        E max = list.get(0);
        for (E element : list) {
            if (max.compareTo(element) < 0) {
                max = element;
            }
        }
        return max;
    }
}
