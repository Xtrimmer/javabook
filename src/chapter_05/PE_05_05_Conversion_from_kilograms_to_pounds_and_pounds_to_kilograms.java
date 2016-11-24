package chapter_05;

/**
 * (Conversion from kilograms to pounds and pounds to kilograms) Write a program
 * that displays the following two tables side by side:
 *
 *      Kilograms   Pounds  |   Pounds  Kilograms
 *      1              2.2  |   20           9.09
 *      3              6.6  |   25          11.36
 *      ...
 *      197          433.4  |   510        231.82
 *      199          437.8  |   515        234.09
 */

public class PE_05_05_Conversion_from_kilograms_to_pounds_and_pounds_to_kilograms {
    public static void main(String[] args) {
        System.out.println("Kilograms   Pounds   |   Pounds   Kilograms");
        for (int i = 1, j = 20; i < 200; i += 2, j += 5) {
            System.out.printf("%-9d%9.1f   |   %-9d%9.2f%n", i, i * 2.2, j, j / 2.2);
        }
    }
}
