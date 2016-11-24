package chapter_13;

/**
 * (The ComparableCircle class) Define a class named ComparableCircle
 * that extends Circle and implements Comparable. Draw the UML diagram and
 * implement the compareTo method to compare the circles on the basis of area.
 * Write a test class to find the larger of two instances of ComparableCircle objects.
 */
public class PE_13_06_The_ComparableCircle_class {
    public static void main(String[] args) {
        PE_13_06_ComparableCircle circle1 = new PE_13_06_ComparableCircle((int)(Math.random() * 5) + 5);
        PE_13_06_ComparableCircle circle2 = new PE_13_06_ComparableCircle((int)(Math.random() * 5) + 5);

        System.out.println("Area of circle1 = " + circle1.getArea());
        System.out.println("Area of circle2 = " + circle2.getArea());
        System.out.println();
        String s;
        if (circle1.compareTo(circle2) == 0) {
            s = "The circles are the same size";
        } else {
            s = "The bigger circle is " + (circle1.compareTo(circle2) > 0 ? "circle1" : "circle2");
        }
        System.out.println(s);
    }
}

class PE_13_06_Circle {
    private double radius;

    public PE_13_06_Circle(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }
}

class PE_13_06_ComparableCircle extends PE_13_06_Circle implements Comparable<PE_13_06_ComparableCircle>{
    public PE_13_06_ComparableCircle(double radius) {
        super(radius);
    }

    @Override
    public int compareTo(PE_13_06_ComparableCircle o) {
        if (this.getArea() > o.getArea()) {
            return 1;
        } else if (this.getArea() < o.getArea()) {
            return -1;
        } else {
            return 0;
        }
    }
}