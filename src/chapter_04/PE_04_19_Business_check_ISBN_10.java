package chapter_04;

import java.util.Scanner;

/**
 * (Business: check ISBN-10) Rewrite the Programming Exercise 3.9 by entering the
 * ISBN number as a string.
 */
public class PE_04_19_Business_check_ISBN_10 {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        String input = null;
        String output = null;
        int calculation = 0;
        int isbn = 0;
        boolean valid = false;
        do {
            System.out.print("Enter the first 9 digits of an ISBN as integer: ");
            try {
                input = SCANNER.nextLine();
                isbn = Integer.parseInt(input);
                valid = true;
            } catch (Exception e){
                System.out.println("Unable to process input. Try again.");
            }
            if (input.length() != 9){
                System.out.println("The input does not contain 9 numbers");
                valid = false;
            }
        } while (!valid);
        for(int i = 9; i > 0; i--){
            calculation += (isbn % 10) * i;
            isbn /= 10;
        }
        calculation %= 11;
        if (calculation == 10) {
            output = input + "X";
        } else {
            output = input + calculation;
        }
        System.out.println("The ISBN-10 number is " + output);
    }
}
