package chapter_04;

import java.util.Scanner;
/**
 * Created by jtrimmer on 5/19/2015.
 */
public class PE_04_12_Hex_to_binary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a hex digit : ");
        String s = "";
        char ch = scanner.nextLine().charAt(0);
        switch (ch) {
            case '0': s = "0000"; break;
            case '1': s = "0001"; break;
            case '2': s = "0010"; break;
            case '3': s = "0011"; break;
            case '4': s = "0100"; break;
            case '5': s = "0101"; break;
            case '6': s = "0110"; break;
            case '7': s = "0111"; break;
            case '8': s = "1000"; break;
            case '9': s = "1001"; break;
            case 'A': s = "1010"; break;
            case 'B': s = "1011"; break;
            case 'C': s = "1100"; break;
            case 'D': s = "1101"; break;
            case 'E': s = "1110"; break;
            case 'F': s = "1111"; break;
            default: System.out.println(ch + " is an invalid input"); System.exit(1);
        }
        System.out.println("The binary value is " + s);
    }
}
