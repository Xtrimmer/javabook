package chapter_03;

/**
 * (Random point) Write a program that displays a random coordinate in a rectangle.
 * The rectangle is centered at (0, 0) with width 100 and height 200.
 */
public class PE_03_16_Random_point {
    public static void main(String[] args) {
        int sign = (int) (Math.random() * 2);
        if (sign == 0) sign = -1;
        int width = (int) (Math.random() * 51) * sign;
        sign = (int) (Math.random() * 2);
        if (sign == 0) sign = -1;
        int height = (int) (Math.random() * 101) * sign;
        System.out.println("Random point: (" + width + ", " + height + ")");
    }
}
