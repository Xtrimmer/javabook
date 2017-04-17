package chapter_19;

import java.util.ArrayList;

/**
 * (Sort ArrayList) Write the following method that sorts an ArrayList:
 *
 *      public static <E extends Comparable<E>>
 *          void sort(ArrayList<E> list)
 */
public class PE_19_09_Sort_ArrayList {
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

        System.out.println("strings before sort:\n" + strings);
        sort(strings);
        System.out.println("strings after sort:\n" + strings);
        System.out.println();
        System.out.println("integers before sort:\n" + integers);
        sort(integers);
        System.out.println("integers after sort:\n" + integers);
    }

    public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
        boolean change = true;
        while (change) {
            change = false;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    change = true;
                    E temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                }
            }
        }
    }
}
