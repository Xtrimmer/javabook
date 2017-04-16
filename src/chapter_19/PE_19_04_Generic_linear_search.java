package chapter_19;

/**
 * (Generic linear search) Implement the following generic method for linear search.
 *
 *      public static <E extends Comparable<E>>
 *          int linearSearch(E[] list, E key)
 */
public class PE_19_04_Generic_linear_search {
    public static void main(String[] args) {
        String[] strings = {"The", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"};
        System.out.println(linearSearch(strings, "quick")); //1
        System.out.println(linearSearch(strings, "fox")); //3
        System.out.println(linearSearch(strings, "dog")); //8
        System.out.println(linearSearch(strings, "slow")); //-1

        Integer[] ints = {0, 1, 2, 3, 4, 5};
        System.out.println(linearSearch(ints, 1)); //1
        System.out.println(linearSearch(ints, 3)); //3
        System.out.println(linearSearch(ints, 6)); //-1
    }

    public static <E extends Comparable<E>> int linearSearch(E[] list, E key) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(key)) return i;
        }
        return -1;
    }
}
