package chapter_13;

/**
 * (Enable GeometricObject comparable) Modify the GeometricObject class
 * to implement the Comparable interface, and define a static max method in the
 * GeometricObject class for finding the larger of two GeometricObject objects.
 * Draw the UML diagram and implement the new GeometricObject class. Write
 * a test program that uses the max method to find the larger of two circles and the
 * larger of two rectangles.
 */
public class PE_13_05_Enable_GeometricObject_comparable {

    private static PE_13_05_Circle circle1 = new PE_13_05_Circle(Math.random() * 10);
    private static PE_13_05_Circle circle2 = new PE_13_05_Circle(Math.random() * 10);

    private static PE_13_05_Rectangle rectangle1 = new PE_13_05_Rectangle(Math.random() * 10, Math.random() * 10);
    private static PE_13_05_Rectangle rectangle2 = new PE_13_05_Rectangle(Math.random() * 10, Math.random() * 10);

    public static void main(String[] args) {
        System.out.printf("Area of circle1 = %.3f%n", circle1.getArea());
        System.out.printf("Area of circle2 = %.3f%n", circle2.getArea());
        System.out.printf("Area of rectangle1 = %.3f%n", rectangle1.getArea());
        System.out.printf("Area of rectangle2 = %.3f%n", rectangle2.getArea());
        System.out.println();
        System.out.println((circle1 == PE_13_05_GeometricObject.Max(circle1, circle2) ?
                "circle1" : "circle2") + " is the larger circle");
        System.out.println((rectangle1 == PE_13_05_GeometricObject.Max(rectangle1, rectangle2) ?
                "rectangle1" : "rectangle2") + " is is the larger rectangle");
    }
}

abstract class PE_13_05_GeometricObject implements Comparable<PE_13_05_GeometricObject>{

    public abstract double getArea();

    static PE_13_05_GeometricObject Max(PE_13_05_GeometricObject o1,
                                        PE_13_05_GeometricObject o2)
            throws NullPointerException{
        if (o1 == null || o2 == null) {
            throw new NullPointerException();
        }
        return o1.compareTo(o2) >= 0 ? o1 : o2;
    }

    @Override
    public int compareTo(PE_13_05_GeometricObject o) {
        if (getArea() > o.getArea())
            return 1;
        else if (getArea() < o.getArea())
            return -1;
        else
            return 0;
    }
}

class PE_13_05_Circle extends PE_13_05_GeometricObject{
    private double radius;

    PE_13_05_Circle(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }
}

class PE_13_05_Rectangle extends PE_13_05_GeometricObject{
    private double width;
    private double height;

    PE_13_05_Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }
}
