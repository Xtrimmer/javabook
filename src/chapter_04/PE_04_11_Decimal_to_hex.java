package chapter_04;

import java.util.Scanner;
/**
 * (Decimal to hex) Write a program that prompts the user to enter an integer between
 * 0 and 15 and displays its corresponding hex number.
 */
public class PE_04_11_Decimal_to_hex {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a decimal value (0 to 15): ");
        int value = scanner.nextInt();
        if (value <= 9 && value >= 0) {
            System.out.println("The hex value is " + value);
        } else if (value >= 10 && value <= 15) {
            char ch = (char)(value - 10 + 'A');
            System.out.println("The hex value is " + ch);
        }else {
            System.out.println(value + " is an invalid input");
        }
    }
}
