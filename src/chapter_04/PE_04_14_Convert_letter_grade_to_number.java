package chapter_04;

import java.util.Scanner;
/**
 * (Convert letter grade to number) Write a program that prompts the user to enter a
 * letter grade A, B, C, D, or F and displays its corresponding numeric value 4, 3, 2,
 * 1, or 0.
 */
public class PE_04_14_Convert_letter_grade_to_number {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        String m1 = "The numeric value for grade ";
        String m2 = " is ";
        System.out.print("Enter a Letter Grade: ");
        char ch = SCANNER.nextLine().toUpperCase().charAt(0);
        switch (ch){
            case 'A':
                System.out.println(m1 + ch + m2 + 4);
                break;
            case 'B':
                System.out.println(m1 + ch + m2 + 3);
                break;
            case 'C':
                System.out.println(m1 + ch + m2 + 2);
                break;
            case 'D':
                System.out.println(m1 + ch + m2 + 1);
                break;
            case 'F':
                System.out.println(m1 + ch + m2 + 0);
                break;
            default:
                System.out.println(ch + " is an invalid grade");
        }
    }
}
