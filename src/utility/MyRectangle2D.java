package utility;

/**
 * Added for Chapter 10 Exercise 13.
 * Used for  Chapter 10 Exercise 15.
 */
public class MyRectangle2D {
    private double x;
    private double y;
    private double width;
    private double height;

    public MyRectangle2D() {
        this(0, 0, 1, 1);
    }

    public MyRectangle2D(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getTop() {
        return this.y + height / 2.0;
    }

    public double getLeft() {
        return this.x - width / 2.0;
    }

    public double getBottom() {
        return this.y - height / 2.0;
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
        return 2 * (width + height);
    }

    public boolean contains(double x, double y) {
        double right = this.x + width / 2.0;
        double left = getLeft();
        double top = getTop();
        double bottom = getBottom();
        return x <= right && x >= left && y <= top && y >= bottom;
    }

    public boolean contains(MyRectangle2D r) {
        double right = r.x + r.width / 2.0;
        double left = r.x - r.width / 2.0;
        double top = r.y + r.height / 2.0;
        double bottom = r.y - r.height / 2.0;
        return contains(left, top) && contains(left, bottom)
                && contains(right, top) && contains (right, bottom);
    }

    public boolean overlaps(MyRectangle2D r) {
        double right = r.x + r.width / 2.0;
        double left = r.x - r.width / 2.0;
        double top = r.y + r.height / 2.0;
        double bottom = r.y - r.height / 2.0;
        return contains(r) || r.contains(this)
                || contains(left, top) || contains(left, bottom)
                || contains(right, top) || contains (right, bottom);
    }
}
