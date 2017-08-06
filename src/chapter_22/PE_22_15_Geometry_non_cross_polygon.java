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
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * (Geometry: non-cross polygon) Write a program that enables the user to add/
 * remove points by clicking the left/right mouse button, and displays a noncrossed
 * polygon that links all the points, as shown in Figure 22.11a. A polygon
 * is crossed if two or more sides intersect, as shown in Figure 22.11b. Use the
 * following algorithm to construct a polygon from a set of points.
 *
 * FIGURE 22.11 (a) Programming Exercise22.15 displays a non-crossed polygon for a set of
 * points. (b) Two or more sides intersect in a crossed polygon.
 *
 * Step 1: Given a set of points S, select the rightmost lowest
 *         point p0 in the set S.
 * Step 2: Sort the points in S angularly along the x-axis
 *         with p0 as the center. If there is a tie and two points have
 *         the same angle, the one that is closer to p0 is considered
 *         greater. The points in S are now sorted as p0, p1, p2, ...,
 *         pn-1.
 * Step 3: The sorted points form a non-cross polygon.
 */
public class PE_22_15_Geometry_non_cross_polygon extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new NonCrossPolygonPane();
        Scene scene = new Scene(pane, 500, 300);

        primaryStage.setTitle("Exercise22_15");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class NonCrossPolygonPane extends Pane {
        private static final int POINT_RADIUS = 7;
        private static final String INSTRUCTION = "INSTRUCTION\nAdd: Left Click\nRemove: Right Click";
        private final Rectangle rectangle;
        private final Polygon polygon;
        private final NonCrossPolygonGenerator polygonGenerator = new NonCrossPolygonGenerator();

        public NonCrossPolygonPane() {
            Text text = new Text(20, 35, INSTRUCTION);
            rectangle = new Rectangle(10, 10, 150, 80);
            polygon = new Polygon();
            polygon.setFill(Color.TRANSPARENT);
            polygon.setStroke(Color.DARKRED);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.BEIGE);
            getChildren().addAll(rectangle, text, polygon);
            setClickEvents();
        }

        private void addPoint(Point2D clickLocation) {
            Circle circle = new Circle(clickLocation.getX(), clickLocation.getY(), POINT_RADIUS);
            getChildren().add(circle);
        }

        private void executeEvent(MouseEvent mouseEvent) {
            Point2D clickLocation = new Point2D(mouseEvent.getX(), mouseEvent.getY());
            if (!rectangle.contains(clickLocation)) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    addPoint(clickLocation);
                } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    removePoints(clickLocation);
                }
            }
            redrawPolygon();
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

        private void redrawPolygon() {
            polygon.getPoints().clear();
            double[][] points = getPoints();
            if (points.length > 1) {
                List<MyPoint> polygonPoints = polygonGenerator.getPolygon(points);
                for (MyPoint polygonPoint : polygonPoints) {
                    polygon.getPoints().addAll(polygonPoint.x, polygonPoint.y);
                }
            }
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

    class NonCrossPolygonGenerator {
        private static final int X = 0;
        private static final int Y = 1;

        public List<MyPoint> getPolygon(double[][] s) {
            MyPoint p0 = getRightmostLowestPoint(s);
            List<MyPoint> points = parsePoints(s, p0);
            Collections.sort(points);
            return points;
        }

        private MyPoint getRightmostLowestPoint(double[][] points) {
            double[] rightmostLowestPoint = points[0];
            for (double[] point : points) {
                if (point[Y] == rightmostLowestPoint[Y]) {
                    if (point[X] > rightmostLowestPoint[X]) {
                        rightmostLowestPoint = point;
                    }
                } else if (point[Y] < rightmostLowestPoint[Y]) {
                    rightmostLowestPoint = point;
                }
            }
            MyPoint newPoint = new MyPoint(rightmostLowestPoint[X], rightmostLowestPoint[Y]);
            newPoint.setRightMostLowestPoint(newPoint);
            return newPoint;
        }

        private ArrayList<MyPoint> parsePoints(double[][] pointArray, MyPoint rightmostLowestPoint) {
            ArrayList<MyPoint> pointList = new ArrayList<>();
            for (double[] point : pointArray) {
                MyPoint newPoint = new MyPoint(point[X], point[Y]);
                newPoint.setRightMostLowestPoint(rightmostLowestPoint);
                pointList.add(newPoint);
            }
            return pointList;
        }
    }

    class MyPoint implements Comparable<MyPoint> {
        final double x;
        final double y;
        MyPoint rightMostLowestPoint;

        MyPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(MyPoint o) {
            double thisAngle = Math.atan2(this.y - rightMostLowestPoint.y, this.x - rightMostLowestPoint.x);
            double thisDistance = Math.sqrt(Math.pow(this.y - rightMostLowestPoint.y, 2) + Math.pow(this.x - rightMostLowestPoint.x, 2));
            double thatAngle = Math.atan2(o.y - rightMostLowestPoint.y, o.x - rightMostLowestPoint.x);
            double thatDistance = Math.sqrt(Math.pow(o.y - rightMostLowestPoint.y, 2) + Math.pow(o.x - rightMostLowestPoint.x, 2));

            if (Double.compare(thisAngle, thatAngle) == 0) {
                return Double.compare(thisDistance, thatDistance);
            } else
                return Double.compare(thisAngle, thatAngle);
        }

        public void setRightMostLowestPoint(MyPoint p) {
            rightMostLowestPoint = p;
        }
    }
}