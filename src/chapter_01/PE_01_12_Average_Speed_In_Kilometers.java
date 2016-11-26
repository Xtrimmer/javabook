package chapter_01;

/**
 * (Average speed in kilometers) Assume a runner runs 24 miles in 1 hour, 40 minutes,
 * and 35 seconds. Write a program that displays the average speed in kilometers per
 * hour. (Note that 1 mile is 1.6 kilometers.)
 */
public class PE_01_12_Average_Speed_In_Kilometers {
    public static void main(String[] args) {
        double mph = 24.0 * (60 * 60) / ((60 * 60) + (40 * 60) + 35);
        double kph = mph * 1.6;

        System.out.println("Mph = " + mph);
        System.out.println("Kph = " + kph);
    }
}
