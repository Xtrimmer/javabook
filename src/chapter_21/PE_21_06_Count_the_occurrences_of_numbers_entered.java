package chapter_21;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * (Count the occurrences of numbers entered) Write a program that reads an
 * unspecified number of integers and finds the one that has the most occurrences.
 * The input ends when the input is 0. For example, if you entered 2 3 40 3 5 4 –3
 * 3 3 2 0, the number 3 occurred most often. If not one but several numbers have
 * the most occurrences, all of them should be reported. For example, since 9 and 3
 * appear twice in the list 9 30 3 9 3 2 4, both occurrences should be reported.
 */
public class PE_21_06_Count_the_occurrences_of_numbers_entered {

    private static final Map<Integer, Integer> numberMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        promptUserForInput();
        addInputToMap(scanner);
        displayTopOccurrences();
    }

    private static void addInputToMap(Scanner scanner) {
        int number;
        while (scanner.hasNextInt() && (number = scanner.nextInt()) != 0) {
            if (numberMap.containsKey(number)) {
                numberMap.put(number, numberMap.get(number) + 1);
            } else {
                numberMap.put(number, 1);
            }
        }
    }

    private static void displayTopOccurrences() {
        int maxValue = Collections.max(numberMap.values());
        System.out.println("The number(s) with the most occurrences:");
        for (Map.Entry<Integer, Integer> entry : numberMap.entrySet()) {
            if (entry.getValue() == maxValue) {
                System.out.print(entry.getKey() + " ");
            }
        }
    }

    private static void promptUserForInput() {
        System.out.println("Enter some integers.");
        System.out.println("The input ends when the input is 0.");
    }
}
