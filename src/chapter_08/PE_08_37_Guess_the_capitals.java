package chapter_08;

import java.util.Scanner;

/**
 * (Guess the capitals) Write a program that repeatedly prompts the user to enter
 * a capital for a state. Upon receiving the user input, the program reports whether
 * the answer is correct. Assume that 50 states and their capitals are stored in a two-dimensional
 * array, as shown in Figure 8.10. The program prompts the user to
 * answer all states’ capitals and displays the total correct count. The user’s answer
 * is not case-sensitive.
 *
 *      Alabama     Montgomery
 *      Alaska      Juneau
 *      Arizona     Phoenix
 *      ...         ...
 *      ...         ...
 *
 *      A two-dimensional array stores states and their capitals.
 *
 * Here is a sample run:
 *
 *      What is the capital of Alabama? Montogomery (enter)
 *      The correct answer should be Montgomery
 *      What is the capital of Alaska? Juneau (enter)
 *      Your answer is correct
 *      What is the capital of Arizona? ...
 *      ...
 *      The correct count is 35
 */
public class PE_08_37_Guess_the_capitals {
    public static void main(String[] args) {
        String[][] statesAndCapitals = {
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
        guessStateCapitals(statesAndCapitals);
    }

    private static void guessStateCapitals(String[][] statesAndCapitals) {
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
