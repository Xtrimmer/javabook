package chapter_06;

import java.util.Scanner;

/**
 * (Check password) Some websites impose certain rules for passwords. Write a
 * method that checks whether a string is a valid password. Suppose the password
 * rules are as follows:
 * - A password must have at least eight characters.
 * - A password consists of only letters and digits.
 * - A password must contain at least two digits.
 * Write a program that prompts the user to enter a password and displays Valid
 * Password if the rules are followed or Invalid Password otherwise.
 */
public class PE_06_18_Check_password {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.println(isPasswordValid(password) ? "Valid Password" : "Invalid Password");
    }

    public static boolean isPasswordValid(String password) {
        int length = password.length();
        int digitCount = 0;
        if (length < 8) return false;
        for (int i = 0; i < length; i++) {
            char ch = password.charAt(i);
            if (!((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))) return false;
            if (ch >= '0' && ch <= '9') digitCount++;
        }
        return digitCount >= 2;
    }
}
