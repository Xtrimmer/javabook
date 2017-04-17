package chapter_19;

import java.util.Arrays;

/**
 * (Maximum element in a two-dimensional array) Write a generic method that
 * returns the maximum element in a two-dimensional array.
 *
 *      public static <E extends Comparable<E>> E max(E[][] list)
 */
public class PE_19_06_Maximum_element_in_a_two_dimensional_array {
    public static void main(String[] args) {
        String[][] strings = {
                {"Take", "this", "kiss", "upon", "the", "brow"},
                {"And", "in", "parting", "from", "you", "now"},
                {"Thus", "much", "let", "me", "avow"},
                {"You", "are", "not", "wrong,", "who", "deem"},
                {"That", "my", "days", "have", "been", "a", "dream"}
        };
        Integer[][] integers = {
                {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {0, 10, 7}, {6, 5, 4}
        };

        print(strings);
        System.out.println("Max string : " + max(strings));
        System.out.println();
        print(integers);
        System.out.println("Max integer: " + max(integers));
    }

    public static <E extends Comparable<E>> E max(E[][] list) {
        if (list == null || list.length == 0) return null;
        E max = list[0][0];
        for (int i = 1; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                if (list[i][j] != null && list[i][j].compareTo(max) > 0) max = list[i][j];
            }
        }
        return max;
    }

    public static <E> void print(E[][] list) {
        if (list == null || list.length == 0) return;
        if (list[0][0] != null) {
            String name = list[0][0].getClass().getName();
            name = name.substring(name.lastIndexOf(".") + 1, name.length());
            System.out.println(name + ": ");
        }
        for (E[] row : list) {
            System.out.println(Arrays.toString(row));
        }
    }
}
