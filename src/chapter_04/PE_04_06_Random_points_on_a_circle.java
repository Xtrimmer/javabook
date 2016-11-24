package chapter_04;

/**
 * (Random points on a circle) Write a program that generates three random points
 * on a circle centered at (0, 0) with radius 40 and display three angles in a triangle
 * formed by these three points. (Hint: Generate a random angle a in radians between
 * 0 and 2 * pi and the point determined by this angle is(r * cos(a), r * sin(a)).)
 */
public class PE_04_06_Random_points_on_a_circle {
    public static void main(String[] args) {
        double angle1 = Math.random() * 2 * Math.PI;
        double angle2 = Math.random() * 2 * Math.PI;
        double angle3 = Math.random() * 2 * Math.PI;
        double radius = 40;
        System.out.printf("Point 1 = (%5.2f, %6.2f)\n", radius * Math.cos(angle1), radius * Math.sin(angle1));
        System.out.printf("Point 2 = (%5.2f, %6.2f)\n", radius * Math.cos(angle2), radius * Math.sin(angle2));
        System.out.printf("Point 3 = (%5.2f, %6.2f)\n", radius * Math.cos(angle3), radius * Math.sin(angle3));

    }
}
