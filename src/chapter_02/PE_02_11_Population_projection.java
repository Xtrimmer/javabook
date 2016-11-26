package chapter_02;

import java.util.Scanner;

/**
 * (Population projection) Rewrite Exercise 1.11 to prompt the user to enter the
 * number of years and displays the population after the number of years.
 */
public class PE_02_11_Population_projection {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        final int STARTING_POPULATION = 312032486;
        final int SECONDS_PER_YEAR = 365 * 24 * 60 * 60;

        int birthsPerYear = SECONDS_PER_YEAR / 7;
        int deathsPerYear = SECONDS_PER_YEAR / 13;
        int immigrantsPerYear = SECONDS_PER_YEAR / 45;

        System.out.print("Enter the number of years: ");
        int years = SCANNER.nextInt();

        int population = STARTING_POPULATION + years * (birthsPerYear + immigrantsPerYear - deathsPerYear);

        System.out.print("The population in " + years + " years is " + population);
    }
}
