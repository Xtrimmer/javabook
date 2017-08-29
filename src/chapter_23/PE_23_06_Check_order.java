package chapter_23;

import java.util.Comparator;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * (Check order) Write the following overloaded methods that check whether an
 * array is ordered in ascending order or descending order. By default, the method
 * checks ascending order. To check descending order, pass false to the ascending
 * argument in the method.
 *
 *      public static boolean ordered(int[] list)
 *      public static boolean ordered(int[] list, boolean ascending)
 *      public static boolean ordered(double[] list)
 *      public static boolean ordered(double[] list, boolean ascending)
 *      public static <E extends Comparable<E>> boolean ordered(E[] list)
 *      public static <E extends Comparable<E>> boolean ordered (E[] list, boolean ascending)
 *      public static <E> boolean ordered(E[] list, Comparator<? super E> comparator)
 *      public static <E> boolean ordered(E[] list, Comparator<? super E> comparator, boolean ascending)
 */
public class PE_23_06_Check_order {
    public static void main(String[] args) {
        int[] ascendingInts = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 89, 89};
        System.out.println(ordered(ascendingInts));

        int[] descendingInts = {89, 89, 89, 55, 34, 21, 13, 8, 5, 3, 2, 1, 1};
        System.out.println(ordered(descendingInts, false));

        double[] ascendingDbls = {0.1, 0.1, 0.2, 0.3, 0.5, 0.8, 1.3, 2.1, 3.4, 5.5, 8.9, 8.9, 8.9};
        System.out.println(ordered(ascendingDbls));

        double[] descendingDbls = {8.9, 8.9, 8.9, 5.5, 3.4, 2.1, 1.3, 0.8, 0.5, 0.3, 0.2, 0.1, 0.1};
        System.out.println(ordered(descendingDbls, false));

        Integer[] ascendingIntegers = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 89, 89};
        System.out.println(ordered(ascendingIntegers));

        Integer[] descendingIntegers = {89, 89, 89, 55, 34, 21, 13, 8, 5, 3, 2, 1, 1};
        System.out.println(ordered(descendingIntegers, false));

        Double[] ascendingDoubles = {0.1, 0.1, 0.2, 0.3, 0.5, 0.8, 1.3, 2.1, 3.4, 5.5, 8.9, 8.9, 8.9};
        System.out.println(ordered(ascendingDoubles, Comparator.naturalOrder()));

        Double[] descendingDoubles = {8.9, 8.9, 8.9, 5.5, 3.4, 2.1, 1.3, 0.8, 0.5, 0.3, 0.2, 0.1, 0.1};
        System.out.println(ordered(descendingDoubles, Comparator.naturalOrder(), false));
    }

    public static boolean ordered(int[] list) {
        return ordered(list, true);
    }

    public static boolean ordered(int[] list, boolean ascending) {
        Integer[] integers = IntStream.of(list).boxed().toArray(Integer[]::new);
        return ordered(integers, ascending);
    }

    public static boolean ordered(double[] list) {
        return ordered(list, true);
    }

    public static boolean ordered(double[] list, boolean ascending) {
        Double[] doubles = DoubleStream.of(list).boxed().toArray(Double[]::new);
        return ordered(doubles, ascending);
    }

    public static <E extends Comparable<E>> boolean ordered(E[] list) {
        return ordered(list, true);
    }

    public static <E extends Comparable<E>> boolean ordered(E[] list, boolean ascending) {
        return ordered(list, Comparator.naturalOrder(), ascending);
    }

    public static <E> boolean ordered(E[] list, Comparator<? super E> comparator) {
        return ordered(list, comparator, true);
    }

    public static <E> boolean ordered(E[] list, Comparator<? super E> comparator, boolean ascending) {
        comparator = ascending ? comparator : comparator.reversed();
        for (int i = 0; i < list.length - 1; i++) {
            if (comparator.compare(list[i], list[i + 1]) > 0) return false;
        }
        return true;
    }
}
