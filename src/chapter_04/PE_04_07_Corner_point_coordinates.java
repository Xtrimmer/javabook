package chapter_04;

import java.util.Scanner;
/**
 * (Corner point coordinates) Suppose a pentagon is centered at (0, 0) with one point
 * at the 0 o’clock position, as shown in Figure 4.7c. Write a program that prompts
 * the user to enter the radius of the bounding circle of a pentagon and displays the
 * coordinates of the five corner points on the pentagon.
 */
public class PE_04_07_Corner_point_coordinates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the radius of the bounding circle: ");
        double radius = scanner.nextDouble();
        double angle = Math.PI / 2.0;
        double increment = ((2 * Math.PI) / 5.0);
        System.out.printf("(%-1.2f, %-1.2f)\n", radius * Math.cos(angle), radius * Math.sin(angle));
        angle += increment;
        System.out.printf("(%-1.2f, %-1.2f)\n", radius * Math.cos(angle), radius * Math.sin(angle));
        angle += increment;
        System.out.printf("(%-1.2f, %-1.2f)\n", radius * Math.cos(angle), radius * Math.sin(angle));
        angle += increment;
        System.out.printf("(%-1.2f, %-1.2f)\n", radius * Math.cos(angle), radius * Math.sin(angle));
        angle += increment;
        System.out.printf("(%-1.2f, %-1.2f)\n", radius * Math.cos(angle), radius * Math.sin(angle));
    }
}
