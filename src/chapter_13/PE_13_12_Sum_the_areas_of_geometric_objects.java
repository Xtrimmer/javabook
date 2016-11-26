package chapter_13;

import textbook_listings.Circle;
import textbook_listings.GeometricObject;
import textbook_listings.Rectangle;

/**
 * (Sum the areas of geometric objects) Write a method that sums the areas of all the
 * geometric objects in an array. The method signature is:
 *
 *      public static double sumArea(GeometricObject[] a)
 *
 * Write a test program that creates an array of four objects (two circles and two
 * rectangles) and computes their total area using the sumArea method.
 */
public class PE_13_12_Sum_the_areas_of_geometric_objects {
    public static void main(String[] args) {
        GeometricObject[] shapes = generateGeometricObjectArray(4);
        double totalArea = sumArea(shapes);
        System.out.println("The sum of the areas is: " + totalArea);
    }

    private static GeometricObject[] generateGeometricObjectArray(int size) {
        GeometricObject[] shapes = new GeometricObject[size];
        for (int i = 0; i < shapes.length; i++) {
            if (i % 2 == 0) {
                shapes[i] = new Circle(i + 1);
            } else {
                shapes[i] = new Rectangle(i + 1, i + 2);
            }
        }
        return shapes;
    }

    public static double sumArea(GeometricObject[] a){
        double sum = 0;
        for (GeometricObject shape : a) {
            sum += shape.getArea();
        }
        return sum;
    }
}
