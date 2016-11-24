package chapter_02;

import java.util.Scanner;

/**
 * (Compute the volume of a cylinder) Write a program that reads in the radius and
 * length of a cylinder and computes the area and volume using the following formulas:
 * area = radius * radius * pi
 * volume = area * length
 */
public class PE_02_02_Compute_the_volume_of_a_cylinder {
    public static void main(String[] args) {

        final Scanner SCANNER = new Scanner(System.in);
        double radius;
        double length;
        double area;
        double volume;

        System.out.print("Enter the radius and length of a cylinder: ");
        radius = SCANNER.nextDouble();
        length = SCANNER.nextDouble();

        area = radius * radius * Math.PI;
        volume = area * length;

        System.out.println("The area is: " + area);
        System.out.println("The volume is: " + volume);


    }

}
