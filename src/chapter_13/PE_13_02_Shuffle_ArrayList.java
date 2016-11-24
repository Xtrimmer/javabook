package chapter_13;

import java.util.ArrayList;
import java.util.Collections;

/**
 * (Shuffle ArrayList) Write the following method that shuffles an ArrayList of
 * numbers:
 *
 *      public static void shuffle(ArrayList<Number> list)
 */
public class PE_13_02_Shuffle_ArrayList {
    public static void main(String[] args) {
        ArrayList<Number> list = generateSortedArray(10);
        System.out.println("Original list");
        System.out.println(list);
        shuffle(list);
        System.out.println("After Shuffling");
        System.out.println(list);

    }

    private static ArrayList<Number> generateSortedArray(int size) {
        ArrayList<Number> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    public static void shuffle(ArrayList<Number> list) {
        Collections.shuffle(list);
    }
}
