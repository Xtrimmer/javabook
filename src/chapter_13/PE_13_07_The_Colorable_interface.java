package chapter_13;

/**
 * (The Colorable interface) Design an interface named Colorable with a void
 * method named howToColor(). Every class of a colorable object must implement
 * the Colorable interface. Design a class named Square that extends
 * GeometricObject and implements Colorable. Implement howToColor to
 * display the message Color all four sides.
 *
 * Draw a UML diagram that involves Colorable, Square, and GeometricObject.
 * Write a test program that creates an array of five GeometricObjects. For each
 * object in the array, display its area and invoke its howToColor method if it is
 * colorable.
 */
public class PE_13_07_The_Colorable_interface {
    public static void main(String[] args) {
        PE_13_07_GeometricObject[] shapes = generateShapesArray(5);
        displayShapeInfo(shapes);
    }

    private static void displayShapeInfo(PE_13_07_GeometricObject[] shapes) {
        for (PE_13_07_GeometricObject shape : shapes) {
            System.out.println(shape.getClass() + "\n   Area: " + shape.getArea());
            if (shape instanceof PE_13_07_Colorable) {
                System.out.print("   ");
                ((PE_13_07_Colorable) shape).howToColor();
            }
        }
    }

    private static PE_13_07_GeometricObject[] generateShapesArray(int size) {
        PE_13_07_GeometricObject[] shapes = new PE_13_07_GeometricObject[size];
        for (int i = 0; i <shapes.length ; i++) {
            if ((int) (Math.random() * 2) == 0) {
                shapes[i] = new PE_13_07_Square(i + 1);
            } else {
                shapes[i] = new PE_13_07_Circle(i + 1);
            }
        }
        return shapes;
    }
}

interface PE_13_07_Colorable{
    void howToColor();
}

abstract class PE_13_07_GeometricObject{
    public abstract double getArea();
}

class PE_13_07_Square extends PE_13_07_GeometricObject implements PE_13_07_Colorable{
    private double side;

    public PE_13_07_Square(double side) {
        this.side = side;
    }

    @Override
    public void howToColor() {
        System.out.println("Color all four sides.");
    }

    @Override
    public double getArea() {
        return side * side;
    }
}

class PE_13_07_Circle extends PE_13_07_GeometricObject{
    private double radius;

    public PE_13_07_Circle(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }
}