package chapter_09;

import java.util.Random;

/**
 * (Stopwatch) Design a class named StopWatch. The class contains:
 *
 * - Private data fields startTime and endTime with getter methods.
 * - A no-arg constructor that initializes startTime with the current time.
 * - A method named start() that resets the startTime to the current time.
 * - A method named stop() that sets the endTime to the current time.
 * - A method named getElapsedTime() that returns the elapsed time for the
 *   stopwatch in milliseconds.
 *
 * Draw the UML diagram for the class and then implement the class. Write a test
 * program that measures the execution time of sorting 100,000 numbers using
 * selection sort.
 */
public class PE_09_06_Stopwatch {
    public static void main(String[] args) {
        int[] numbers = generateIntArray(100000);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        selectionSort(numbers);
        stopWatch.stop();
        System.out.println("The time taken to sort 100,000 numbers is: ");
        System.out.println(stopWatch.getElapsedTime() + " milliseconds.");
    }

    private static int[] generateIntArray(int size) {
        int[] numbers = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            numbers[i] = random.nextInt();
        }
        return numbers;
    }

    private static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // Find the minimum in the array[i..array.length-1]
            int currentMax = array[i];
            int currentMaxIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (currentMax < array[j]) {
                    currentMax = array[j];
                    currentMaxIndex = j;
                }
            }
            // Swap array[i] with array[currentMaxIndex] if necessary
            if (currentMaxIndex != i) {
                array[currentMaxIndex] = array[i];
                array[i] = currentMax;
            }
        }
    }

    private static class StopWatch {
        private long startTime;
        private long endTime;

        StopWatch() {
            startTime = System.currentTimeMillis();
        }

        long getStartTime() {
            return startTime;
        }

        long getEndTime() {
            return endTime;
        }

        void start() {
            startTime = System.currentTimeMillis();
        }

        void stop() {
            endTime = System.currentTimeMillis();
        }

        long getElapsedTime() {
            return endTime - startTime;
        }
    }
}

