package chapter_11;

import java.util.ArrayList;
import java.util.Collections;

/**
 * (Shuffle ArrayList) Write the following method that shuffles the elements
 * an ArrayList of integers.
 *
 *      public static void shuffle(ArrayList<Integer> list)
 */
public class PE_11_07_Shuffle_ArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
        System.out.println(numbers);
        shuffle(numbers);
        System.out.println(numbers);
    }

    public static void shuffle(ArrayList<Integer> list) {
        Collections.shuffle(list);
    }
}
