package chapter_07;

import java.util.Scanner;

/**
 * (Sort students) Write a program that prompts the user to enter the number of students,
 * the students’ names, and their scores, and prints student names in decreasing
 * order of their scores.
 */
public class PE_07_17_Sort_students {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of students: ");
        int number = scanner.nextInt();
        int[] scores = new int[number];
        String[] names = new String[number];
        System.out.print("Enter the student's names and scores: ");
        for (int i = 0; i < number; i++) {
            names[i] = scanner.next();
            scores[i] = scanner.nextInt();
        }
        selectionSort(scores, names);
        print(names, scores);
    }

    public static void selectionSort(int[] list, String[] names) {
        for (int i = 0; i < list.length - 1; i++) {
            // Find the minimum in the list[i..list.length-1]
            int currentMax = list[i];
            int currentMaxIndex = i;

            for (int j = i + 1; j < list.length; j++) {
                if (currentMax < list[j]) {
                    currentMax = list[j];
                    currentMaxIndex = j;
                }
            }

            // Swap list[i] with list[currentMaxIndex] if necessary
            if (currentMaxIndex != i) {
                list[currentMaxIndex] = list[i];
                list[i] = currentMax;
                String temp = names[i];
                names[i] = names[currentMaxIndex];
                names[currentMaxIndex] = temp;
            }
        }
    }

    public static void print(String[] names, int[] scores) {
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + " " + scores[i]);
        }
    }
}
