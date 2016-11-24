package chapter_02;

import java.util.Scanner;
/**
 * (Population projection) Rewrite Exercise 1.11 to prompt the user to enter the
 * number of years and displays the population after the number of years.
 */
public class PE_02_11_Population_projection {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        int startingPop = 312032486;
        int birthsPerYear;
        int deathsPerYear;
        int immigrantsPerYear;
        int secondsPerYear;

        secondsPerYear = 365 * 24 * 60 * 60;
        birthsPerYear = secondsPerYear / 7;
        deathsPerYear = secondsPerYear / 13;
        immigrantsPerYear = secondsPerYear / 45;

        System.out.print("Enter the number of years: ");
        int years = SCANNER.nextInt();

        int population = startingPop + years * (birthsPerYear + immigrantsPerYear - deathsPerYear);

        System.out.print("The population in " + years + " years is " + population);
    }
}
