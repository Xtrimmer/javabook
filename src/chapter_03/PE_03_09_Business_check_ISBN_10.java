package chapter_03;

import java.util.Scanner;

/**
 * (Business: check ISBN-10) An ISBN-10 (International Standard Book Number)
 * consists of 10 digits: d1d2d3d4d5d6d7d8d9d10. The last digit, d10, is a checksum,
 * which is calculated from the other nine digits using the following formula:
 *
 * (d1 * 1 + d2 * 2 + d3 * 3 + d4 * 4 + d5 * 5 + d6 * 6 + d7 * 7 + d8 * 8 + d9 * 9) % 11
 *
 * If the checksum is 10, the last digit is denoted as X according to the ISBN-10
 * convention. Write a program that prompts the user to enter the first 9 digits and
 * displays the 10-digit ISBN (including leading zeros). Your program should read
 * the input as an integer.
 */
public class PE_03_09_Business_check_ISBN_10 {
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
