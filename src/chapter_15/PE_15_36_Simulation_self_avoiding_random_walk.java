package chapter_15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * (Simulation: self-avoiding random walk) Write a simulation program to show
 * that the chance of getting dead-end paths increases as the grid size increases.
 * Your program simulates lattices with size from 10 to 80. For each lattice size,
 * simulate a self-avoiding random walk 10,000 times and display the probability
 * of the dead-end paths, as shown in the following sample output:
 *
 *      For a lattice of size 10, the probability of dead-end paths is 10.6%
 *      For a lattice of size 11, the probability of dead-end paths is 14.0%
 *      ...
 *      For a lattice of size 80, the probability of dead-end paths is 99.5%
 */
public class PE_15_36_Simulation_self_avoiding_random_walk {

    private static boolean[][] grid;

    public static void main(String[] args) {

        for (int i = 10; i <= 80; i++) {
            int totalCount;
            int deadEndCount = 0;
            for (totalCount = 0; totalCount < 10000; totalCount++) {
                grid = new boolean[i][i];
                Point point = new Point(grid.length / 2, grid[0].length / 2);
                grid[point.getX()][point.getY()] = true;
                while (!isDeadEnd(point)) {
                    List<Point> availablePoints = getAvailablePoints(point);
                    Collections.shuffle(availablePoints);
                    point = availablePoints.get(0);
                    grid[point.getX()][point.getY()] = true;
                }
                if (!isEdge(point)) {
                    deadEndCount++;
                }
            }
            System.out.printf("For a lattice of size %d, the probability of dead-end paths is %.1f%%%n",
                    i, 100 * deadEndCount / (double) totalCount);
        }
    }

    private static boolean isDeadEnd(Point p) {
        return isEdge(p) || grid[p.getX() - 1][p.getY()] && grid[p.getX() + 1][p.getY()]
                && grid[p.getX()][p.getY() - 1] && grid[p.getX()][p.getY() + 1];
    }

    private static List<Point> getAvailablePoints(Point point) {
        List<Point> availablePoints = new ArrayList<>();
        if (!grid[point.getX() - 1][point.getY()])
            availablePoints.add(new Point(point.getX() - 1, point.getY()));
        if (!grid[point.getX() + 1][point.getY()])
            availablePoints.add(new Point(point.getX() + 1, point.getY()));
        if (!grid[point.getX()][point.getY() - 1])
            availablePoints.add(new Point(point.getX(), point.getY() - 1));
        if (!grid[point.getX()][point.getY() + 1])
            availablePoints.add(new Point(point.getX(), point.getY() + 1));
        return availablePoints;
    }

    private static boolean isEdge(Point p) {
        return p.getX() == 0 || p.getX() == grid.length - 1 || p.getY() == 0 || p.getY() == grid[0].length - 1;
    }

    private static class Point {
        private final int x;
        private final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
