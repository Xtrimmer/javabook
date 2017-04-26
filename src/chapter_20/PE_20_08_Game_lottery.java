package chapter_20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * (Game: lottery) Revise Programming Exercise 3.15 to add an additional $2,000
 * award if two digits from the user input are in the lottery number. (Hint: Sort
 * the three digits in the lottery number and three digits in the user input into two
 * lists, and use the Collection’s containsAll method to check whether the
 * two digits in the user input are in the lottery number.)
 */
public class PE_20_08_Game_lottery {
    public static void main(String[] args) {
        List<Integer> lottery = generateLottery(3);
        List<Integer> guess = getGuessFromUser();
        showResults(lottery, guess);
    }

    private static List<Integer> generateLottery(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add((int) (Math.random() * 10));
        }
        return list;
    }

    private static List<Integer> getGuessFromUser() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your lottery pick (three digits): ");
        int guess = input.nextInt();
        List<Integer> list = new ArrayList<>();
        list.add(guess / 100);
        list.add(guess / 10 % 10);
        list.add(guess % 10);
        return list;
    }

    private static void showResults(List<Integer> lottery, List<Integer> guess) {
        System.out.println("The lottery number is " + lottery);
        Collections.sort(lottery);
        Collections.sort(guess);
        if (guess.equals(lottery)) {
            System.out.println("Exact match: you win $10,000");
        } else if (lottery.containsAll(guess)) {
            System.out.println("Match all digits: you win $3,000");
        } else if (lottery.containsAll(guess.subList(0, 2))
                || lottery.containsAll(guess.subList(1, 3))
                || (lottery.contains(guess.get(0)) && lottery.contains(guess.get(2)))) {
            System.out.println("Match two digits: you win $2,000");
        } else if (lottery.contains(guess.get(0))
                || lottery.contains(guess.get(1))
                || lottery.contains(guess.get(2))) {
            System.out.println("Match one digit: you win $1,000");
        } else {
            System.out.println("Sorry, no match");
        }
    }
}
