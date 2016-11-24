package chapter_07;

import java.util.Scanner;

/**
 * (Culture: Chinese Zodiac) Simplify Listing 3.9 using an array of strings to store
 * the animal names.
 */
public class PE_07_33_Culture_Chinese_Zodiac {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a year: ");
        int year = input.nextInt();
        String[] names = {"monkey", "rooster", "dog", "pig", "rat", "ox",
                "tiger", "rabbit", "dragon", "snake", "horse", "sheep"};
        System.out.println(names[year % 12]);
    }
}
