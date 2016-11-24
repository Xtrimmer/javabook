package chapter_04;

import java.util.Scanner;
/**
 * (Student major and status) Write a program that prompts the user to enter two
 * characters and displays the major and status represented in the characters. The first
 * character indicates the major and the second is number character 1, 2, 3, 4, which
 * indicates whether a student is a freshman, sophomore, junior, or senior. Suppose
 * the following chracters are used to denote the majors:
 *      M: Mathematics
 *      C: Computer Science
 *      I: Information Technology
 */
public class PE_04_18_Student_major_and_status {
    public static void main(String[] args) {
        Scanner SCANNER = new Scanner(System.in);
        System.out.print("Enter two characters: ");
        String input = SCANNER.nextLine().toUpperCase();
        char letter = input.charAt(0);
        char num = input.charAt(1);
        if (!(letter == 'M' || letter == 'C' || letter == 'I' || num >= '1' || num <= '4' )) {
            System.out.println("Invalid input");
            System.exit(1);
        }
        String major = null;
        String status = null;
        if (letter == 'M') {
            major = "Mathematics ";
        } else if (letter == 'C') {
            major = "Computer Science ";
        } else if (letter == 'I') {
            major = "Information Technology ";
        }
        if (num == '1') {
            status = "Freshman";
        } else if (num == '2') {
            status = "Sophmore";
        } else if (num == '3') {
            status = "Junior";
        } else if (num == '4') {
            status = "Senior";
        }
        System.out.println(major + status);
    }
}
