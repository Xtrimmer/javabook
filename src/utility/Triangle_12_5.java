package utility;

/**
 * Created for Chapter 12 Exercise 05.
 */
public class Triangle_12_5 extends GeometricObject{
    private double side1;
    private double side2;
    private double side3;

    public Triangle_12_5() throws IllegalTriangleException {
        this(1.0, 1.0, 1.0);
    }

    public Triangle_12_5(double side1, double side2, double side3)
            throws IllegalTriangleException{
        if (side1 + side2 > side3
                && side2 + side3 > side1
                && side3 + side1 > side2) {
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        } else {
            throw new IllegalTriangleException(side1 + ", " + side2 + ", " + side3);
        }
    }

    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }

    public double getArea() {
        double s = getPerimeter() / 2.0;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public String toString() {
        return "Triangle: side1 = " + side1 + " side2 = " + side2 +
                " side3 = " + side3;
    }
}
