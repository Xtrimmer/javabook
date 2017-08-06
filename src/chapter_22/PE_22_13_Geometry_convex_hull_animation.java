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

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * (Geometry: convex hull animation) Programming Exercise 22.11 finds a convex
 * hull for a set of points entered from the console. Write a program that enables
 * the user to add/remove points by clicking the left/right mouse button, and
 * displays a convex hull, as shown in Figure 22.8c.
 */
public class PE_22_13_Geometry_convex_hull_animation extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new ConvexHullPane();
        Scene scene = new Scene(pane, 500, 300);

        primaryStage.setTitle("Exercise22_13");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class ConvexHullPane extends Pane {
        private static final int POINT_RADIUS = 7;
        private static final String INSTRUCTION = "INSTRUCTION\nAdd: Left Click\nRemove: Right Click";
        private final Rectangle rectangle;
        private final ArrayList<Line> hull;
        private final GrahamsConvexHull convexHull = new GrahamsConvexHull();

        public ConvexHullPane() {
            hull = new ArrayList<>();
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
            Point2D clickLocation = new Point2D(mouseEvent.getX(), mouseEvent.getY());
            if (!rectangle.contains(clickLocation)) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    addPoint(clickLocation);
                } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    removePoints(clickLocation);
                }
            }
            redrawConvexHull();
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

        private void redrawConvexHull() {
            getChildren().removeAll(hull);
            hull.clear();
            double[][] points = getPoints();
            if (points.length > 2) {
                List<MyPoint> hullPoints = convexHull.getConvexHull(points);
                MyPoint p1;
                MyPoint p2 = new MyPoint(0, 0);
                for (int i = 1; i < hullPoints.size(); i++) {
                    p1 = hullPoints.get(i - 1);
                    p2 = hullPoints.get(i);
                    hull.add(new Line(p1.x, p1.y, p2.x, p2.y));
                }
                p1 = hullPoints.get(0);
                hull.add(new Line(p2.x, p2.y, p1.x, p1.y));
                getChildren().addAll(hull);
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

    class GrahamsConvexHull {
        private static final int X = 0;
        private static final int Y = 1;
        private static final int LEFT_SIDE_OF_LINE = -1;

        public List<MyPoint> getConvexHull(double[][] s) {
            MyPoint p0 = getRightmostLowestPoint(s);
            List<MyPoint> points = parsePoints(s, p0);
            Collections.sort(points);
            DiscardCloserPointWithSameAngle(points);
            LinkedList<MyPoint> convexHull = new LinkedList<>();
            convexHull.push(points.get(0));
            convexHull.push(points.get(1));
            convexHull.push(points.get(2));
            int i = 3;
            while (i < points.size()) {
                MyPoint t1 = convexHull.getFirst();
                MyPoint t2 = convexHull.get(1);
                MyPoint p = points.get(i);
                Line2D line = new Line2D.Double(t2.x, t2.y, t1.x, t1.y);
                if (line.relativeCCW(p.x, p.y) == LEFT_SIDE_OF_LINE) {
                    convexHull.push(p);
                    i++;
                } else {
                    convexHull.pop();
                }
            }
            return convexHull;
        }

        private void DiscardCloserPointWithSameAngle(List<MyPoint> points) {
            List<MyPoint> pointsCopy = new ArrayList<>(points);
            for (int i = 1; i < pointsCopy.size(); i++) {
                MyPoint p1 = pointsCopy.get(i - 1);
                MyPoint p2 = pointsCopy.get(i);
                MyPoint min = p1.rightMostLowestPoint;
                double p1Angle = Math.atan2(p1.y - min.y, p1.x - min.x);
                double p2Angle = Math.atan2(p2.y - min.y, p2.x - min.x);
                if (Double.compare(p1Angle, p2Angle) == 0) points.remove(p1);
            }
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