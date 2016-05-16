package Chapter_02;
import java.util.Scanner;
/**
 * (Find the character of an ASCII code) Write a program that receives an ASCII
 * code (an integer between 0 and 127) and displays its character. For example, if the
 * user enters 97, the program displays character a.
 */
public class PE_02_23 {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter an ASCII code: ");
        int character = SCANNER.nextInt();
        char ch = (char)(character % 127);

        System.out.println("The character is '" + ch + "'");
    }
}
