package chapter_19;

import java.util.ArrayList;

/**
 * (Shuffle ArrayList) Write the following method that shuffles an ArrayList:
 *
 *      public static <E> void shuffle(ArrayList<E> list)
 */
public class PE_19_08_Shuffle_ArrayList {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>() {{
            add("Alfa");
            add("Bravo");
            add("Charlie");
            add("Delta");
            add("Echo");
            add("Foxtrot");
            add("Golf");
            add("Hotel");
            add("India");
            add("Juliett");
        }};

        ArrayList<Integer> integers = new ArrayList<Integer>() {{
            add(0);
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
            add(8);
            add(9);
        }};

        System.out.println("strings before shuffle:\n" + strings);
        shuffle(strings);
        System.out.println("strings after shuffle:\n" + strings);
        System.out.println();
        System.out.println("integers before shuffle:\n" + integers);
        shuffle(integers);
        System.out.println("integers after shuffle:\n" + integers);
    }

    public static <E> void shuffle(ArrayList<E> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int random = (int) (Math.random() * (size - i) + i);
            E temp = list.get(random);
            list.set(random, list.get(i));
            list.set(i, temp);
        }
    }
}
