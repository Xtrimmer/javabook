package chapter_04;

import java.util.Scanner;
/**
 * Created by jtrimmer on 5/18/2015.
 */
public class PE_04_09_Find_the_Unicode_of_a_character {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a character: ");
        String s = scanner.nextLine();
        char ch = s.charAt(0);
        int unicode = ch;
        System.out.println("The Unicode for the character " + ch + " is " + unicode);
    }
}
