package chapter_04;
import java.util.Scanner;

/**
 * (Order three cities) Write a program that prompts the user to enter three cities and
 * displays them in ascending order.
 */
public class PE_04_24_Order_three_cities {
    public static void main(String[] args) {

        // Get city names from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first City: ");
        String city1 = scanner.nextLine();
        System.out.print("Enter the second City: ");
        String city2 = scanner.nextLine();
        System.out.print("Enter the third City: ");
        String city3 = scanner.nextLine();

        // Sort the city names and print the results
        String out = "unknown";
        if (city1.compareToIgnoreCase(city2) <= 0 && city2.compareToIgnoreCase(city3) <= 0){
            out = " " + city1 + ", " + city2 + " & " + city3;
        } else if (city1.compareToIgnoreCase(city3) <= 0 && city3.compareToIgnoreCase(city2) <= 0){
            out = " " + city1 + ", " + city3 + " & " + city2;
        } else if (city2.compareToIgnoreCase(city1) <= 0 && city1.compareToIgnoreCase(city3) <= 0){
            out = " " + city2 + ", " + city1 + " & " + city3;
        } else if (city2.compareToIgnoreCase(city3) <= 0 && city3.compareToIgnoreCase(city1) <= 0){
            out = " " + city2 + ", " + city3 + " & " + city1;
        } else if (city3.compareToIgnoreCase(city1) <= 0 && city1.compareToIgnoreCase(city2) <= 0){
            out = " " + city3 + ", " + city1 + " & " + city2;
        } else if (city3.compareToIgnoreCase(city2) <= 0 && city2.compareToIgnoreCase(city1) <= 0){
            out = " " + city3 + ", " + city2 + " & " + city1;
        }
        System.out.println("The three cities in alphabetical order are" + out);
    }
}
