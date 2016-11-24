package chapter_05;

/**
 * (Conversion from miles to kilometers) Write a program that displays the following
 * table (note that 1 mile is 1.609 kilometers):
 *
 *      Miles   Kilometers
 *      1       1.609
 *      2       3.218
 *      ...
 *      9       14.481
 *      10      16.090
 */
public class PE_05_04_Conversion_from_miles_to_kilometers {
    public static void main(String[] args) {
        System.out.println("Miles     Kilometers");
        for (int i = 1; i <=10 ; i++) {
            System.out.printf("%-10d%.3f%n", i, i * 1.609);
        }
    }
}
