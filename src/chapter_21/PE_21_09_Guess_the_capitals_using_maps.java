package chapter_21;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * (Guess the capitals using maps) Rewrite Programming Exercise 8.37 to store
 * pairs of each state and its capital in a map. Your program should prompt the user
 * to enter a state and should display the capital for the state.
 */
public class PE_21_09_Guess_the_capitals_using_maps {
    public static void main(String[] args) {
        Map<String, String> statesAndCapitalsMap = new TreeMap<>();
        statesAndCapitalsMap.put("Alabama", "Montgomery");
        statesAndCapitalsMap.put("Alaska", "Juneau");
        statesAndCapitalsMap.put("Arizona", "Phoenix");
        statesAndCapitalsMap.put("Arkansas", "Little Rock");
        statesAndCapitalsMap.put("California", "Sacramento");
        statesAndCapitalsMap.put("Colorado", "Denver");
        statesAndCapitalsMap.put("Connecticut", "Hartford");
        statesAndCapitalsMap.put("Delaware", "Dover");
        statesAndCapitalsMap.put("Florida", "Tallahassee");
        statesAndCapitalsMap.put("Georgia", "Atlanta");
        statesAndCapitalsMap.put("Hawaii", "Honolulu");
        statesAndCapitalsMap.put("Idaho", "Boise");
        statesAndCapitalsMap.put("Illinois", "Springfield");
        statesAndCapitalsMap.put("Indiana", "Indianapolis");
        statesAndCapitalsMap.put("Iowa", "Des Moines");
        statesAndCapitalsMap.put("Kansas", "Topeka");
        statesAndCapitalsMap.put("Kentucky", "Frankfort");
        statesAndCapitalsMap.put("Louisiana", "Baton Rouge");
        statesAndCapitalsMap.put("Maine", "Augusta");
        statesAndCapitalsMap.put("Maryland", "Annapolis");
        statesAndCapitalsMap.put("Massachusetts", "Boston");
        statesAndCapitalsMap.put("Michigan", "Lansing");
        statesAndCapitalsMap.put("Minnesota", "St. Paul");
        statesAndCapitalsMap.put("Mississippi", "Jackson");
        statesAndCapitalsMap.put("Missouri", "Jefferson City");
        statesAndCapitalsMap.put("Montana", "Helena");
        statesAndCapitalsMap.put("Nebraska", "Lincoln");
        statesAndCapitalsMap.put("Nevada", "Carson City");
        statesAndCapitalsMap.put("New Hampshire", "Concord");
        statesAndCapitalsMap.put("New Jersey", "Trenton");
        statesAndCapitalsMap.put("New Mexico", "Santa Fe");
        statesAndCapitalsMap.put("New York", "Albany");
        statesAndCapitalsMap.put("North Carolina", "Raleigh");
        statesAndCapitalsMap.put("North Dakota", "Bismarck");
        statesAndCapitalsMap.put("Ohio", "Columbus");
        statesAndCapitalsMap.put("Oklahoma", "Oklahoma City");
        statesAndCapitalsMap.put("Oregon", "Salem");
        statesAndCapitalsMap.put("Pennsylvania", "Harrisburg");
        statesAndCapitalsMap.put("Rhode Island", "Providence");
        statesAndCapitalsMap.put("South Carolina", "Columbia");
        statesAndCapitalsMap.put("South Dakota", "Pierre");
        statesAndCapitalsMap.put("Tennessee", "Nashville");
        statesAndCapitalsMap.put("Texas", "Austin");
        statesAndCapitalsMap.put("Utah", "Salt Lake City");
        statesAndCapitalsMap.put("Vermont", "Montpelier");
        statesAndCapitalsMap.put("Virginia", "Richmond");
        statesAndCapitalsMap.put("Washington", "Olympia");
        statesAndCapitalsMap.put("West Virginia", "Charleston");
        statesAndCapitalsMap.put("Wisconsin", "Madison");
        statesAndCapitalsMap.put("Wyoming", "Cheyenne");
        guessStateCapitals(statesAndCapitalsMap);
    }

    private static void guessStateCapitals(Map statesAndCapitals) {
        int correctAnswerCount = 0;
        String submittedAnswer, actualAnswer;
        Scanner scanner = new Scanner(System.in);
        Set<Map.Entry<String, String>> entries = statesAndCapitals.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.print("What is the capital of " + entry.getKey() + "? ");
            submittedAnswer = scanner.nextLine().toUpperCase();
            actualAnswer = entry.getValue().toUpperCase();
            if (submittedAnswer.equals(actualAnswer)) {
                System.out.println("Your answer is correct");
                correctAnswerCount++;
            } else {
                System.out.println("The correct answer should be " + entry.getValue());
            }
        }
        System.out.println("The correct count is " + correctAnswerCount);
    }
}
