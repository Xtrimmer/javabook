package chapter_07;

import java.util.Scanner;

/**
 * (Eliminate duplicates) Write a method that returns a new array by eliminating the
 * duplicate values in the array using the following method header:
 *
 *      public static int[] eliminateDuplicates(int[] list)
 *
 * Write a test program that reads in ten integers, invokes the method, and displays
 * the result. Here is the sample run of the program:
 *
 *      Enter ten numbers: 1 2 3 2 1 6 3 4 5 2 (enter)
 *      The distinct numbers are: 1 2 3 6 4 5
 */
public class PE_07_15_Eliminate_duplicates {
    public static void main(String[] args) {
        int[] numbers = getInput(10);
        int[] distinctNumbers = eliminateDuplicates(numbers);
        printNumbers(distinctNumbers);
    }

    public static int[] getInput(int size){
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[size];
        System.out.print("Enter " + size + " numbers: ");
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }
        return numbers;
    }

    public static int[] eliminateDuplicates(int[] list) {
        int[] distinctList = new int[getDistinctCount(list)];
        int index = 0;
        for (int i : list) {
            if (!hasNumber(distinctList, index, i)) {
                distinctList[index] = i;
                index++;
            }
        }
        return distinctList;
    }

    public static int getDistinctCount(int[] list) {
        int distinctCount = 1;
        boolean exists;
        for (int i = 1; i < list.length; i++) {
            exists = false;
            for (int j = 0; j < i; j++) {
                if (list[j] == list[i]) {
                    exists= true;
                    break;
                }
            }
            if (!exists) distinctCount++;
        }
        return distinctCount;
    }

    public static boolean hasNumber(int[] array, int index, int number){
        for (int i = 0; i < index; i++) {
            if (array[i] == number) return true;
        }
        return false;
    }

    public static void printNumbers(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
    }
}
