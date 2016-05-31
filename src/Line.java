
public class Line {
    private Point p1;
    private Point p2;

    public Line(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * Determines whether the two line segments cross.
     * @param l1 the first line
     * @param l2 the second line
     * @return true if the lines cross otherwise false
     */
    public static boolean doescross(Line l1, Line l2){
        /**
         * Check if the cross product of (b-a) and (c-a) is 0
         * this tells you if the points a, b and c are aligned.
         * But, as you want to know if c is between a and b,
         * you also have to check that the dot product of (b-a) and (c-a)
         * is positive and is less than the square of the distance between a and b.
         */
        Point intersection = getIntersection(l1, l2);
        if (intersection == null) return false;
        final double EPSILON = 0.000001;
        double crossproduct = (intersection.y - l1.p1.y) * (l1.p2.x - l1.p1.x) -
                (intersection.x - l1.p1.x) * (l1.p2.y - l1.p1.y);
        if (Math.abs(crossproduct) > EPSILON) return false;

        double dotproduct = (intersection.x - l1.p1.x) *
                (l1.p2.x - l1.p1.x) + (intersection.y - l1.p1.y)*(l1.p2.y - l1.p1.y);
        if (dotproduct < 0) return false;

        double squaredlengthba = (l1.p2.x - l1.p1.x)*(l1.p2.x - l1.p1.x) +
                (l1.p2.y - l1.p1.y)*(l1.p2.y - l1.p1.y);
        return dotproduct <= squaredlengthba;

    }

    /**
     * Finds the intersecting point of two lines.
     * If the intersection does not exist, that is, if the two lines are parallel,
     * the the method returns null.
     * @param l1 the first line
     * @param l2 the second line
     * @return the point of intersection if it exists, else null
     */
    public static Point getIntersection(Line l1, Line l2){
        /*
        * The intersecting point of the two lines can be found by solving the following linear equation:
        *      (y1 - y2)x - (x1 - x2)y = (y1 - y2)x1 - (x1 - x2)y1
        *      (y3 - y4)x - (x3 - x4)y = (y3 - y4)x3 - (x3 - x4)y3
        * This linear equation can be solved using Cramer’s rule.
        *      ax + by = e
        *      cx + dy = f
        *      x = (ed - bf) / (ad - bc)
        *      y = (af - ec) / (ad - bc)
        * If the equation has no solutions, the two lines are parallel.
        */
        double a = l1.p1.y - l1.p2.y;
        double b = l1.p1.x - l1.p2.x;
        double c = l2.p1.y - l2.p2.y;
        double d = l2.p1.x - l2.p2.x;
        double e = (a * l1.p1.x) - (b * l1.p1.y);
        double f = (c * l2.p1.x) - (d * l2.p1.y);
        double determinant = (a * d) - (b * c);
        if (determinant == 0) {
            return null;
        } else {
            double x = (e * d - b * f) / determinant;
            double y = -((a * f) - (e * c)) / determinant;
            return new Point(x, y);
        }
    }

    public static void main(String[] args) {
        /**
         * Test output should be:
         *      True
         *      True
         *      False
         *      False
         */

        // non-parallel lines cross
        System.out.println(
                Line.doescross(new Line(new Point(1, 1), new Point(-1, -1)),
                               new Line(new Point(1, -1), new Point(-1, 1))));
        // non-parallel lines cross
        System.out.println(
                Line.doescross(new Line(new Point(0, 0), new Point(5, 5)),
                        new Line(new Point(3, 2), new Point(3, 4))));
        // non-parallel lines do not cross
        System.out.println(
                Line.doescross(new Line(new Point(-2, 2), new Point(-1, 1)),
                        new Line(new Point(0, 1), new Point(0, 3))));
        // parallel overlapping lines (Technically do not cross!)
        System.out.println(
                Line.doescross(new Line(new Point(-2, 2), new Point(0, 0)),
                        new Line(new Point(-2, 2), new Point(0, 0))));
    }
}

class Point{
    double x;
    double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
}
