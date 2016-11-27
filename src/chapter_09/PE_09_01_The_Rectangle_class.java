package chapter_09;

/**
 * (The Rectangle class) Following the example of the Circle class in Section 9.2,
 * design a class named Rectangle to represent a rectangle. The class contains:
 * - Two double data fields named width and height that specify the width and
 *   height of the rectangle. The default values are 1 for both width and height.
 * - A no-arg constructor that creates a default rectangle.
 * - A constructor that creates a rectangle with the specified width and height.
 * - A method named getArea() that returns the area of this rectangle.
 * - A method named getPerimeter() that returns the perimeter.
 Draw the UML diagram for the class and then implement the class. Write a test
 program that creates two Rectangle objects—one with width 4 and height 40
 and the other with width 3.5 and height 35.9. Display the width, height, area,
 and perimeter of each rectangle in this order.
 */
public class PE_09_01_The_Rectangle_class {
    public static void main(String[] args) {
        Rectangle rectangle1 = new Rectangle(40, 4);
        Rectangle rectangle2 = new Rectangle(35.9, 3.5);

        printHeader("rectangle1");
        printRectangleData(rectangle1);

        printHeader("rectangle2");
        printRectangleData(rectangle2);
    }

    private static void printHeader(String title) {
        System.out.println(title);
        System.out.println("------------");
    }

    private static void printRectangleData(Rectangle rectangle) {
        System.out.println("Width:    " + rectangle.getWidth());
        System.out.println("Height:   " + rectangle.getHeight());
        System.out.println("Area:     " + rectangle.getArea());
        System.out.println("Perimeter " + rectangle.getPerimeter());
        System.out.println();
    }

    private static class Rectangle {
        private double width;
        private double height;

        public Rectangle() {
            width = 1;
            height = 1;
        }

        public Rectangle(double height, double width) {
            this.height = height;
            this.width = width;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public double getArea() {
            return width * height;
        }

        public double getPerimeter() {
            return width + width + height + height;
        }
    }
}

