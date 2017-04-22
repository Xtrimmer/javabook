package chapter_20;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * (Guessing the capitals) Rewrite Programming Exercise 8.37 to store the pairs
 * of states and capitals so that the questions are displayed randomly.
 */
public class PE_20_03_Guessing_the_capitals {
    public static void main(String[] args) {
        String[][] statesAndCapitalsArray = {
                {"Alabama", "Montgomery"},
                {"Alaska", "Juneau"},
                {"Arizona", "Phoenix"},
                {"Arkansas", "Little Rock"},
                {"California", "Sacramento"},
                {"Colorado", "Denver"},
                {"Connecticut", "Hartford"},
                {"Delaware", "Dover"},
                {"Florida", "Tallahassee"},
                {"Georgia", "Atlanta"},
                {"Hawaii", "Honolulu"},
                {"Idaho", "Boise"},
                {"Illinois", "Springfield"},
                {"Indiana", "Indianapolis"},
                {"Iowa", "Des Moines"},
                {"Kansas", "Topeka"},
                {"Kentucky", "Frankfort"},
                {"Louisiana", "Baton Rouge"},
                {"Maine", "Augusta"},
                {"Maryland", "Annapolis"},
                {"Massachusetts", "Boston"},
                {"Michigan", "Lansing"},
                {"Minnesota", "St. Paul"},
                {"Mississippi", "Jackson"},
                {"Missouri", "Jefferson City"},
                {"Montana", "Helena"},
                {"Nebraska", "Lincoln"},
                {"Nevada", "Carson City"},
                {"New Hampshire", "Concord"},
                {"New Jersey", "Trenton"},
                {"New Mexico", "Santa Fe"},
                {"New York", "Albany"},
                {"North Carolina", "Raleigh"},
                {"North Dakota", "Bismarck"},
                {"Ohio", "Columbus"},
                {"Oklahoma", "Oklahoma City"},
                {"Oregon", "Salem"},
                {"Pennsylvania", "Harrisburg"},
                {"Rhode Island", "Providence"},
                {"South Carolina", "Columbia"},
                {"South Dakota", "Pierre"},
                {"Tennessee", "Nashville"},
                {"Texas", "Austin"},
                {"Utah", "Salt Lake City"},
                {"Vermont", "Montpelier"},
                {"Virginia", "Richmond"},
                {"Washington", "Olympia"},
                {"West Virginia", "Charleston"},
                {"Wisconsin", "Madison"},
                {"Wyoming", "Cheyenne"}
        };
        List<String[]> statesAndCapitalsList = Arrays.asList(statesAndCapitalsArray);
        Collections.shuffle(statesAndCapitalsList);
        guessStateCapitals(statesAndCapitalsList);
    }

    private static void guessStateCapitals(List<String[]> statesAndCapitals) {
        final int STATE = 0, CAPITAL = 1;
        int correctAnswerCount = 0;
        String submittedAnswer, actualAnswer;
        Scanner scanner = new Scanner(System.in);
        for (String[] statesAndCapital : statesAndCapitals) {
            System.out.print("What is the capital of " + statesAndCapital[STATE] + "? ");
            submittedAnswer = scanner.nextLine().toUpperCase();
            actualAnswer = statesAndCapital[CAPITAL].toUpperCase();
            if (submittedAnswer.equals(actualAnswer)) {
                System.out.println("Your answer is correct");
                correctAnswerCount++;
            } else {
                System.out.println("The correct answer should be " + statesAndCapital[CAPITAL]);
            }
        }
        System.out.println("The correct count is " + correctAnswerCount);
    }
}
