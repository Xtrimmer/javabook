package chapter_22;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * (Closest-pair animation) Write a program that enables the user to add/remove
 * points by clicking the left/right mouse button, and displays a line that connects
 * the pair of nearest points, as shown in Figure 22.4.
 */
public class PE_22_17_Closest_pair_animation extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new ClosestPointPane();
        Scene scene = new Scene(pane, 500, 300);

        primaryStage.setTitle("Exercise22_17");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class ClosestPointPane extends Pane {
        private static final int POINT_RADIUS = 7;
        private static final String INSTRUCTION = "INSTRUCTION\nAdd: Left Click\nRemove: Right Click";
        private final Rectangle rectangle;
        private final ClosestPointsFinder closestPointsFinder = new ClosestPointsFinder();
        private Line line;

        public ClosestPointPane() {
            Text text = new Text(20, 35, INSTRUCTION);
            rectangle = new Rectangle(10, 10, 150, 80);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.BEIGE);
            getChildren().addAll(rectangle, text);
            setClickEvents();
        }

        private void addPoint(Point2D clickLocation) {
            Circle circle = new Circle(clickLocation.getX(), clickLocation.getY(), POINT_RADIUS);
            getChildren().add(circle);
        }

        private void executeEvent(MouseEvent mouseEvent) {
            getChildren().remove(line);
            Point2D clickLocation = new Point2D(mouseEvent.getX(), mouseEvent.getY());
            if (!rectangle.contains(clickLocation)) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    addPoint(clickLocation);
                } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    removePoints(clickLocation);
                }
            }
            double[][] points = getPoints();
            if (points.length >= 2) {
                line = closestPointsFinder.getClosestPair(points);
                getChildren().add(line);
            }
        }

        private double[][] getPoints() {
            int pointCount = 0;
            for (Node node : getChildren()) {
                if (node instanceof Circle) pointCount++;
            }
            double[][] pointCoordinates = new double[pointCount][2];
            pointCount = 0;
            for (Node node : getChildren()) {
                if (node instanceof Circle) {
                    pointCoordinates[pointCount][0] = ((Circle) node).getCenterX();
                    pointCoordinates[pointCount][1] = ((Circle) node).getCenterY();
                    pointCount++;
                }
            }
            return pointCoordinates;
        }

        private void removePoints(Point2D clickLocation) {
            List<Node> nodesToRemove = new ArrayList<>();
            getChildren().forEach(node -> {
                if (node instanceof Circle && node.contains(clickLocation)) {
                    nodesToRemove.add(node);
                }
            });
            getChildren().removeAll(nodesToRemove);
        }

        private void setClickEvents() {
            setOnMouseClicked(this::executeEvent);
        }
    }

    public class ClosestPointsFinder {

        /** Return the distance of the closest pair of points
         * in pointsOrderedOnX[low..high]. This is a recursive
         * method. pointsOrderedOnX and pointsOrderedOnY are
         * not changed in the subsequent recursive calls.
         */
        public Pair distance(Point[] pointsOrderedOnX,
                             int low, int high, Point[] pointsOrderedOnY) {
            if (low >= high) {
                return null;
            } else if (low + 1 == high) {
                return new Pair(pointsOrderedOnX[low], pointsOrderedOnX[high]);
            }

            int midpoint = (low + high) / 2;
            Pair pair1 = distance(pointsOrderedOnX, low, midpoint, pointsOrderedOnY);
            Pair pair2 = distance(pointsOrderedOnX, midpoint + 1, high, pointsOrderedOnY);

            double distance;
            Pair closestPair = null;

            if (pair1 == null && pair2 == null) {
                distance = Double.MAX_VALUE;
            } else if (pair1 == null) {
                distance = pair2.getDistance();
                closestPair = pair2;
            } else if (pair2 == null) {
                distance = pair1.getDistance();
                closestPair = pair1;
            } else {
                distance = Math.min(pair1.getDistance(), pair2.getDistance());
                closestPair = ((pair1.getDistance() <= pair2.getDistance()) ? pair1 : pair2);
            }

            ArrayList<Point> stripL = new ArrayList<>();
            ArrayList<Point> stripR = new ArrayList<>();
            for (Point point : pointsOrderedOnY) {
                if ((point.x <= pointsOrderedOnX[midpoint].x) &&
                        (point.x >= pointsOrderedOnX[midpoint].x - distance)) {
                    stripL.add(point);
                } else {
                    stripR.add(point);
                }
            }

            double d3 = distance;
            int r = 0;
            for (Point point : stripL) {
                while (r < stripR.size() && point.y > stripR.get(r).y + distance) {
                    r++;
                }

                int r1 = r;
                while (r1 < stripR.size() && stripR.get(r1).y <= point.y + distance) {
                    if (d3 > distance(point, stripR.get(r1))) {
                        d3 = distance(point, stripR.get(r1));
                        closestPair.p1 = point;
                        closestPair.p2 = stripR.get(r1);
                    }
                    r1++;
                }
            }

            return closestPair;
        }

        /** Compute the distance between two points p1 and p2 */
        public double distance(Point p1, Point p2) {
            return distance(p1.x, p1.y, p2.x, p2.y);
        }

        /** Compute the distance between points (x1, y1) and (x2, y2) */
        public double distance(double x1, double y1,
                               double x2, double y2) {
            return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        }

        /** Return the distance of the closest pair of points */
        public Pair getClosestPair(Point[] points) {
            Point[] pointsOrderedOnX = new Point[points.length];
            Point[] pointsOrderedOnY = new Point[points.length];
            System.arraycopy(points, 0, pointsOrderedOnX, 0, points.length);
            System.arraycopy(points, 0, pointsOrderedOnY, 0, points.length);
            Arrays.sort(pointsOrderedOnX);
            Arrays.sort(pointsOrderedOnY, new CompareY());
            return distance(pointsOrderedOnX, 0, points.length - 1, pointsOrderedOnY);
        }

        /** Return the distance of the closest pair of points */
        public Line getClosestPair(double[][] points) {
            Point[] pointArray = new Point[points.length];
            for (int i = 0; i < points.length; i++) {
                pointArray[i] = new Point(points[i][0], points[i][1]);
            }
            Pair pair = getClosestPair(pointArray);
            return new Line(pair.p1.x, pair.p1.y, pair.p2.x, pair.p2.y);
        }

        private class Point implements Comparable<Point> {

            private final Double x;
            private final Double y;

            public Point(double x, double y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public int compareTo(Point that) {
                int firstComparison = this.x.compareTo(that.x);
                return firstComparison == 0 ? this.y.compareTo(that.y) : firstComparison;
            }

            @Override
            public String toString() {
                return "[X:" + x + ", Y:" + y + "]";
            }
        }

        private class CompareY implements Comparator<Point> {

            @Override
            public int compare(Point point1, Point point2) {
                int firstComparison = point1.y.compareTo(point2.y);
                return firstComparison == 0 ? point1.x.compareTo(point2.x) : firstComparison;
            }
        }

        private class Pair {
            private Point p1;
            private Point p2;

            public Pair(Point p1, Point p2) {
                this.p1 = p1;
                this.p2 = p2;
            }

            public double getDistance() {
                return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
            }
        }
    }
}
