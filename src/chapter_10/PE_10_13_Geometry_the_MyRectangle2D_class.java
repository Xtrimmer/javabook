package chapter_10;

/**
 * (Geometry: the MyRectangle2D class) Define the MyRectangle2D class that
 * contains:
 *
 * - Two double data fields named x and y that specify the center of the rectangle
 *   with getter and setter methods. (Assume that the rectangle sides are
 *   parallel to x- or y- axes.)
 * - The data fields width and height with getter and setter methods.
 * - A no-arg constructor that creates a default rectangle with (0, 0) for (x, y) and
 *   1 for both width and height.
 * - A constructor that creates a rectangle with the specified x, y, width, and
 *   height.
 * - A method getArea() that returns the area of the rectangle.
 * - A method getPerimeter() that returns the perimeter of the rectangle.
 * - A method contains(double x, double y) that returns true if the
 *   specified point (x, y) is inside this rectangle (see Figure 10.24a).
 * - A method contains(MyRectangle2D r) that returns true if the specified
 *   rectangle is inside this rectangle (see Figure 10.24b).
 * - A method overlaps(MyRectangle2D r) that returns true if the specified
 *   rectangle overlaps with this rectangle (see Figure 10.24c).
 *
 *      SEE FIGURE 10.24 (p. 405)
 *      (a) A point is inside the rectangle.
 *      (b) A rectangle is inside another rectangle.
 *      (c) A rectangle overlaps another rectangle.
 *      (d) Points are enclosed inside a rectangle.
 *
 * Draw the UML diagram for the class and then implement the class. Write a test
 * program that creates a MyRectangle2D object r1 (new MyRectangle2D(2,
 * 2, 5.5, 4.9)), displays its area and perimeter, and displays the result of
 * r1.contains(3, 3), r1.contains(new MyRectangle2D(4, 5, 10.5,
 * 3.2)), and r1.overlaps(new MyRectangle2D(3, 5, 2.3, 5.4)).
 */
public class PE_10_13_Geometry_the_MyRectangle2D_class {
    public static void main(String[] args) {
        MyRectangle2D r1 = new MyRectangle2D(2, 2, 5.5, 4.9);
        double area = r1.getArea();
        System.out.println("area = " + area);
        double perimeter = r1.getPerimeter();
        System.out.println("perimeter = " + perimeter);
        boolean containsPoint = r1.contains(3, 3);
        System.out.println("containsPoint (3, 3) = " + containsPoint);
        boolean containsRectangle = r1.contains(
                new MyRectangle2D(4, 5, 10.5, 3.2)
        );
        System.out.println("containsRectangle (4, 5, 10.5, 3.2) = " + containsRectangle);
        boolean overlapsRectangle = r1.overlaps(
                new MyRectangle2D(3, 5, 2.3, 5.4)
        );
        System.out.println("overlapsRectangle (3, 5, 2.3, 5.4) = " + overlapsRectangle);
    }

    private static class MyRectangle2D {
        private double x;
        private double y;
        private double width;
        private double height;

        MyRectangle2D() {
            this(0, 0, 1, 1);
        }

        MyRectangle2D(double x, double y, double width, double height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        double getX() {
            return x;
        }

        void setX(double x) {
            this.x = x;
        }

        double getY() {
            return y;
        }

        void setY(double y) {
            this.y = y;
        }

        double getTop() {
            return this.y + height / 2.0;
        }

        double getLeft() {
            return this.x - width / 2.0;
        }

        double getBottom() {
            return this.y - height / 2.0;
        }

        double getWidth() {
            return width;
        }

        void setWidth(double width) {
            this.width = width;
        }

        double getHeight() {
            return height;
        }

        void setHeight(double height) {
            this.height = height;
        }

        double getArea() {
            return width * height;
        }

        double getPerimeter() {
            return 2 * (width + height);
        }

        boolean contains(double x, double y) {
            double right = this.x + width / 2.0;
            double left = getLeft();
            double top = getTop();
            double bottom = getBottom();
            return x <= right && x >= left && y <= top && y >= bottom;
        }

        boolean contains(MyRectangle2D r) {
            double right = r.x + r.width / 2.0;
            double left = r.x - r.width / 2.0;
            double top = r.y + r.height / 2.0;
            double bottom = r.y - r.height / 2.0;
            return contains(left, top) && contains(left, bottom)
                    && contains(right, top) && contains (right, bottom);
        }

        boolean overlaps(MyRectangle2D r) {
            double right = r.x + r.width / 2.0;
            double left = r.x - r.width / 2.0;
            double top = r.y + r.height / 2.0;
            double bottom = r.y - r.height / 2.0;
            return contains(r) || r.contains(this)
                    || contains(left, top) || contains(left, bottom)
                    || contains(right, top) || contains (right, bottom);
        }
    }
}
