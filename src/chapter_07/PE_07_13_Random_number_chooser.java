package chapter_07;

/**
 * (Random number chooser) Write a method that returns a random number between
 * 1 and 54, excluding the numbers passed in the argument. The method header is
 * specified as follows:
 *
 *      public static int getRandom(int... numbers)
 */
public class PE_07_13_Random_number_chooser {
    public static void main(String[] args) {
        int num;
        for (int i = 1; i <= 100; i++) {
            num = getRandom(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
            if (i % 10 == 0) System.out.printf("%2d%n", num);
            else System.out.printf("%2d ", num);
        }
    }

    public static int getRandom(int... numbers) {
        int rand;
        do {
            rand = (int)(Math.random() * 55);
        } while (hasNumber(numbers, rand));
        return rand;
    }

    public static boolean hasNumber(int[] array, int number){
        for (int i : array) {
            if (i == number) return true;
        }
        return false;
    }
}
