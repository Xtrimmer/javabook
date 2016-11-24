package chapter_08;

/**
 * (Points nearest to each other) Listing 8.3 gives a program that finds two points in a
 * two-dimensional space nearest to each other. Revise the program so that it finds two
 * points in a three-dimensional space nearest to each other. Use a two-dimensional
 * array to represent the points. Test the program using the following points:
 *
 *      double[][] points = {{-1, 0, 3}, {-1, -1, -1}, {4, 1, 1},
 *      {2, 0.5, 9}, {3.5, 2, -1}, {3, 1.5, 3}, {-1.5, 4, 2},
 *      {5.5, 4, -0.5}};
 *
 * The formula for computing the distance between two points (x1, y1, z1) and
 * (x2, y2, z2) is
 *
 *      √((x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2).
 */
public class PE_08_07_Points_nearest_to_each_other {
    public static void main(String[] args) {
        double[][] points = {{-1, 0, 3}, {-1, -1, -1}, {4, 1, 1},
                {2, 0.5, 9}, {3.5, 2, -1}, {3, 1.5, 3}, {-1.5, 4, 2},
                {5.5, 4, -0.5}};
        int point1 = 0;
        int point2 = 1;
        double shortestDistance = computeDistance(points[0], points[1]);
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double distance = computeDistance(points[i], points[j]);
                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    point1 = i;
                    point2 = j;
                }
            }
        }
        System.out.println("The closest two points are (" + points[point1][0] + ", " + points[point1][1] + ", " +
                points[point1][2] + ") and (" + points[point2][0] + ", " + points[point2][1] + ", " +
                points[point2][2] + ")");
    }

    public static double computeDistance(double[] p1, double[] p2) {
        return Math.sqrt(Math.pow(p2[0] - p1[0], 2) + Math.pow(p2[1] - p1[1], 2) + Math.pow(p2[2] - p1[2], 2));
    }
}
