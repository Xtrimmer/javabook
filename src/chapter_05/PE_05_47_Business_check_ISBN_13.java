package chapter_05;

import java.util.Scanner;

/**
 * (Business: check ISBN-13) ISBN-13 is a new standard for indentifying books. It
 * uses 13 digits d1d2d3d4d5d6d7d8d9d10d11d12d13. The last digit d13 is a checksum,
 * which is calculated from the other digits using the following formula:
 *
 *      10 - (d1 + 3d2 + d3 + 3d4 + d5 + 3d6 + d7 + 3d8 + d9 + 3d10 + d11 + 3d12)%10
 *
 * If the checksum is 10, replace it with 0. Your program should read the input as a
 * string. Here are sample runs:
 *
 *      Enter the first 12 digits of an ISBN-13 as a string: 978013213080 (enter)
 *      The ISBN-13 number is 9780132130806
 *
 *      Enter the first 12 digits of an ISBN-13 as a string: 978013213079 (enter)
 *      The ISBN-13 number is 9780132130790
 *
 *      Enter the first 12 digits of an ISBN-13 as a string: 97801320 (enter)
 *      97801320 is an invalid input
 */
public class PE_05_47_Business_check_ISBN_13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        int lastDigit;
        int sum = 0;
        System.out.print("Enter the first 12 digits of an ISBN-13 as a string: ");
        input = scanner.next();
        if (input.length() != 12){
            System.out.println( input + " is an invalid input");
            return;
        }
        for (int i = 0; i < input.length(); i++) {
            sum += i % 2 == 0 ? Integer.parseInt("" + input.charAt(i)) : Integer.parseInt("" + input.charAt(i)) * 3;
        }
        lastDigit = 10 - sum % 10 == 10 ? 0 : 10 - sum % 10;
        System.out.println("The ISBN-13 number is " + input + lastDigit);
    }
}
