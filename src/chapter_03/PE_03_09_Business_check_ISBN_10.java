package chapter_03;

import java.util.Scanner;

/**
 * (Business: check ISBN-10) An ISBN-10 (International Standard Book Number)
 * consists of 10 digits: d1d2d3d4d5d6d7d8d9d10. The last digit, d10, is a checksum,
 * which is calculated from the other nine digits using the following formula:
 *
 *      (d1 * 1 + d2 * 2 + d3 * 3 + d4 * 4 + d5 * 5 + d6 * 6 + d7 * 7 + d8 * 8 + d9 * 9) % 11
 *
 * If the checksum is 10, the last digit is denoted as X according to the ISBN-10
 * convention. Write a program that prompts the user to enter the first 9 digits and
 * displays the 10-digit ISBN (including leading zeros). Your program should read
 * the input as an integer.
 */
public class PE_03_09_Business_check_ISBN_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first 9 digits of an ISBN as integer: ");
        int input = scanner.nextInt();
        String ISBN = input + "";

        int d9 = input % 10;
        input /= 10;
        int d8 = input % 10;
        input /= 10;
        int d7 = input % 10;
        input /= 10;
        int d6 = input % 10;
        input /= 10;
        int d5 = input % 10;
        input /= 10;
        int d4 = input % 10;
        input /= 10;
        int d3 = input % 10;
        input /= 10;
        int d2 = input % 10;
        input /= 10;
        int d1 = input % 10;
        int checksum = (d1 + d2 * 2 + d3 * 3 + d4 * 4 + d5 * 5 + d6 * 6 + d7 * 7 + d8 * 8 + d9 * 9) % 11;

        if (checksum == 10) {
            ISBN += "X";
        } else {
            ISBN += checksum;
        }

        System.out.println("The ISBN-10 number is " + ISBN);
    }
}
