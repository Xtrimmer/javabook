package Chapter_10;

import utility.MyPoint;
import utility.Triangle2D;

/**
 * (Geometry: the Triangle2D class) Define the Triangle2D class that contains:
 *
 * - Three points named p1, p2, and p3 of the type MyPoint with getter and
 *   setter methods. MyPoint is defined in Programming Exercise 10.4.
 * - A no-arg constructor that creates a default triangle with the points (0, 0), (1,
 *   1), and (2, 5).
 * - A constructor that creates a triangle with the specified points.
 * - A method getArea() that returns the area of the triangle.
 * - A method getPerimeter() that returns the perimeter of the triangle.
 * - A method contains(MyPoint p) that returns true if the specified point
 *   p is inside this triangle (see Figure 10.22a).
 * - A method contains(Triangle2D t) that returns true if the specified
 *   triangle is inside this triangle (see Figure 10.22b).
 * - A method overlaps(Triangle2D t) that returns true if the specified
 *   triangle overlaps with this triangle (see Figure 10.22c).
 *
 *      SEE FIGURE 10.22 (p. 404)
 *      (a) A point is inside the triangle.
 *      (b) A triangle is inside another triangle.
 *      (c) A triangle overlaps another triangle.
 *
 * Draw the UML diagram for the class and then implement the class. Write
 * a test program that creates a Triangle2D objects t1 using the constructor
 * new Triangle2D(new MyPoint(2.5, 2), new MyPoint(4.2, 3),
 * new MyPoint(5, 3.5)), displays its area and perimeter, and displays the
 * result of t1.contains(3, 3), r1.contains(new Triangle2D(new
 * MyPoint(2.9, 2), new MyPoint(4, 1), MyPoint(1, 3.4))), and t1.
 * overlaps(new Triangle2D(new MyPoint(2, 5.5), new MyPoint(4,
 * -3), MyPoint(2, 6.5))).
 *
 * (Hint: For the formula to compute the area of a triangle, see Programming Exercise
 * 2.19. To detect whether a point is inside a triangle, draw three dashed lines,
 * as shown in Figure 10.23. If the point is inside a triangle, each dashed line
 * should intersect a side only once. If a dashed line intersects a side twice, then
 * the point must be outside the triangle. For the algorithm of finding the intersecting
 * point of two lines, see Programming Exercise 3.25.)
 *
 *      SEE FIGURE 10.23 (p. 404)
 *      (a) A point is inside the triangle.
 *      (b) A point is outside the triangle.
 */
public class PE_10_12_Geometry_the_Triangle2D_class {
    public static void main(String[] args) {
        Triangle2D triangle2D = new Triangle2D(
                new MyPoint(2.5, 2),
                new MyPoint(4.2, 3),
                new MyPoint(5, 3.5)
        );
        double area = triangle2D.getArea();
        System.out.println("area = " + area);
        double perimeter = triangle2D.getPerimeter();
        System.out.println("perimeter = " + perimeter);
        boolean isContained = triangle2D.contains(new MyPoint(1.0951357, 1.074123));
        System.out.println("isContained = " + isContained);
        isContained = triangle2D.contains(
                new Triangle2D(
                        new MyPoint(1,1),
                        new MyPoint(2,2),
                        new MyPoint(1,2)
                )
        );
        System.out.println("isContained = " + isContained);
        boolean isOverlap = triangle2D.overlaps(
                new Triangle2D(
                        new MyPoint(2,4),
                        new MyPoint(2,1),
                        new MyPoint(6,1)
                )
        );
        System.out.println("isOverlap = " + isOverlap);
    }
}
