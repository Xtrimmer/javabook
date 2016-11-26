package chapter_03;

import java.util.Scanner;

/**
 * (Sort three integers) Write a program that sorts three integers. The integers are
 * entered from the input dialogs and stored in variables num1, num2, and num3,
 * respectively. The program sorts the numbers so that num1 <= num2 <= num3.
 */
public class PE_03_08_Sort_three_integers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter three integers: ");
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        int num3 = scanner.nextInt();

        int temp;
        if (num1 > num2) {
            temp = num2;
            num2 = num1;
            num1 = temp;
        }
        if (num2 > num3) {
            temp = num3;
            num3 = num2;
            num2 = temp;
        }
        if (num1 > num2) {
            temp = num2;
            num2 = num1;
            num1 = temp;
        }

        System.out.println("[" + num1 + ", " + num2 + ", " + num3 + "]");
    }
}
