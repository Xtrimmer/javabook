package chapter_05;

/**
 * (Conversion from miles to kilometers) Write a program that displays the following
 * two tables side by side:
 *
 *      Miles    Kilometers   |   Kilometers   Miles
 *      1        1.609        |   20           12.430
 *      2        3.218        |   25           15.538
 *      ...
 *      9        14.481       |   60           37.290
 *      10       16.090       |   65           40.398
 */
public class PE_05_06_Conversion_from_miles_to_kilometers {
    public static void main(String[] args) {
        System.out.println("Miles     Kilometers  |  Kilometers     Miles");
        for (int i = 1, j = 20; i <= 10; i++, j += 5) {
            System.out.printf("%-10d%-10.3f  |  %-15d%.3f%n", i, i * 1.609, j, j / 1.609);
        }
    }
}
