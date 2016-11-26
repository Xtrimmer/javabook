package chapter_03;

import java.util.Scanner;

/**
 * (Financial: compare costs) Suppose you shop for rice in two different packages.
 * You would like to write a program to compare the cost. The program prompts the
 * user to enter the weight and price of the each package and displays the one with
 * the better price.
 */
public class PE_03_33_Financial_compare_costs {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter weight and price for package 1: ");
        double weight1 = SCANNER.nextDouble();
        double price1 = SCANNER.nextDouble();

        System.out.print("Enter weight and price for package 2: ");
        double weight2 = SCANNER.nextDouble();
        double price2 = SCANNER.nextDouble();

        double ppw1 = price1 / weight1;
        double ppw2 = price2 / weight2;

        if (ppw1 < ppw2) System.out.println("Package 1 has a better price.");
        else if (ppw1 == ppw2) System.out.println("Two packages have the same price.");
        else System.out.println("Package 2 has a better price.");
    }
}
