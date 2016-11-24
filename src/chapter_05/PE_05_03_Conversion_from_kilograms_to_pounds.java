package chapter_05;

/**
 * (Conversion from kilograms to pounds) Write a program that displays the following
 * table (note that 1 kilogram is 2.2 pounds):
 *
 *      Kilograms Pounds
 *      1            2.2
 *      3            6.6
 *      ...
 *      197        433.4
 *      199        437.8
 */
public class PE_05_03_Conversion_from_kilograms_to_pounds {
    public static void main(String[] args) {
        System.out.println("Kilograms   Pounds");
        for (int i = 1; i < 200; i++) {
            if (i % 2 == 0) continue;
            System.out.printf("%-9d%9.1f%n", i, i * 2.2);
        }
    }
}
