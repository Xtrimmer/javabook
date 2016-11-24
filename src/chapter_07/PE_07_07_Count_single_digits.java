package chapter_07;

/**
 * (Count single digits) Write a program that generates 100 random integers between
 * 0 and 9 and displays the count for each number. (Hint: Use an array of ten integers,
 * say counts, to store the counts for the number of 0s, 1s, . . . , 9s.)
 */
public class PE_07_07_Count_single_digits {
    public static void main(String[] args) {
        printNumbers(generateSingleDigitIntegers(100));
    }

    public static int[] generateSingleDigitIntegers(int amount) {
        int[] counts = new int[10];
        for (int i = 0; i < amount; i++) {
            counts[(int)(Math.random() * 10)]++;
        }
        return counts;
    }

    public static void printNumbers(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(i + "'s - " + array[i]);
        }
    }
}
