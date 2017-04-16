package chapter_19;

import java.util.ArrayList;

/**
 * (Distinct elements in ArrayList) Write the following method that returns a new
 * ArrayList. The new list contains the non-duplicate elements from the original list.
 *
 *      public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list)
 */
public class PE_19_03_Distinct_elements_in_ArrayList {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("How");
        list.add("much");
        list.add("wood");
        list.add("could");
        list.add("a");
        list.add("woodchuck");
        list.add("chuck");
        list.add("if");
        list.add("a");
        list.add("woodchuck");
        list.add("could");
        list.add("chuck");
        list.add("wood");

        System.out.println(list.toString());
        ArrayList<String> set = removeDuplicates(list);
        System.out.println(set.toString());
    }

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> set = new ArrayList<>();
        for (E e : list) {
            if (!set.contains(e)) set.add(e);
        }
        return set;
    }
}
