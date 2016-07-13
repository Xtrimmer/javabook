package utility;

import java.awt.geom.Line2D;

/**
 * Added for Chapter 10 Exercise 12.
 */
public class Triangle2D {
    private MyPoint p1;
    private MyPoint p2;
    private MyPoint p3;

    public Triangle2D() {
        this(new MyPoint(0, 0),
                new MyPoint(1, 1),
                new MyPoint(2, 5));
    }

    public Triangle2D(MyPoint p1, MyPoint p2, MyPoint p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public MyPoint getP1() {
        return p1;
    }

    public void setP1(MyPoint p1) {
        this.p1 = p1;
    }

    public MyPoint getP2() {
        return p2;
    }

    public void setP2(MyPoint p2) {
        this.p2 = p2;
    }

    public MyPoint getP3() {
        return p3;
    }

    public void setP3(MyPoint p3) {
        this.p3 = p3;
    }

    public double getArea() {
        return Math.abs(
                (p1.getX() * (p2.getY() - p3.getY())
                + p2.getX() * (p3.getY() - p1.getY())
                + p3.getX() * (p1.getY() - p2.getY())) / 2d);
    }

    public double getPerimeter() {
        return p1.distance(p2) + p2.distance(p3) + p3.distance(p1);
    }

    public boolean contains(MyPoint p) {
        double area1 = new Triangle2D(p, p1, p2).getArea();
        double area2 = new Triangle2D(p, p2, p3).getArea();
        double area3 = new Triangle2D(p, p3, p1).getArea();
        return Math.abs(area1 + area2 + area3 - getArea()) < 0.00000000001;
    }

    public boolean contains(Triangle2D t) {
        return contains(t.p1) && contains(t.p2) && contains(t.p3);
    }

    public boolean overlaps(Triangle2D t) {
        Line2D[] linesT1 = {
                new Line2D.Double(p1.getX(), p1.getY(), p2.getX(), p2.getY()),
                new Line2D.Double(p2.getX(), p2.getY(), p3.getX(), p3.getY()),
                new Line2D.Double(p3.getX(), p3.getY(), p1.getX(), p1.getY())
        };
        Line2D[] linesT2 = {
                new Line2D.Double(t.p1.getX(), t.p1.getY(), t.p2.getX(), t.p2.getY()),
                new Line2D.Double(t.p2.getX(), t.p2.getY(), t.p3.getX(), t.p3.getY()),
                new Line2D.Double(t.p3.getX(), t.p3.getY(), t.p1.getX(), t.p1.getY())
        };
        for (int i = 0; i < linesT1.length; i++) {
            for (int j = 0; j < linesT2.length; j++) {
                if (linesT1[i].intersectsLine(linesT2[j])) return true;
            }
        }
        return contains(t) || t.contains(this);
    }
}
