package chapter_15;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * (Geometry: find the bounding Rectangle) Write a program that enables the user
 * to add and remove points in a two-dimensional plane dynamically, as shown
 * in Figure 15.29a. A minimum bounding Rectangle is updated as the points are
 * added and removed. Assume that the radius of each point is 10 pixels.
 */
public class PE_15_17_Geometry_find_the_bounding_rectangle extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        PointPane pane = new PointPane();
        Scene scene = new Scene(pane, 600, 300);

        primaryStage.setTitle("Exercise15_17");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class PointPane extends Pane {
        private final String style = "-fx-fill: transparent; -fx-stroke: black";
        private final Rectangle boundingRectangle = new Rectangle();
        private final double radius = 10;


        public PointPane() {
            Text instructionText = new Text(30, 30,
                    "INSTRUCTION\nAdd: Left Click\nRemove: Right Click");
            Rectangle instructionBorder = new Rectangle(20, 10, 150, 70);
            boundingRectangle.setStyle(style);
            getChildren().addAll(instructionText, instructionBorder, boundingRectangle);
            instructionBorder.setStyle(style);
            setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    addPoint(event.getX(), event.getY());
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    removePoint(event.getX(), event.getY());
                }
                updateBoundingRectangle();
            });
        }

        private void updateBoundingRectangle() {
            double xMin = Double.MAX_VALUE;
            double yMin = Double.MAX_VALUE;
            double xMax = Double.MIN_VALUE;
            double yMax = Double.MIN_VALUE;
            for (Node node : getChildren()) {
                if (node instanceof Circle) {
                    Circle circle = ((Circle) node);
                    if (circle.getCenterX() < xMin) xMin = circle.getCenterX();
                    if (circle.getCenterY() < yMin) yMin = circle.getCenterY();
                    if (circle.getCenterX() > xMax) xMax = circle.getCenterX();
                    if (circle.getCenterY() > yMax) yMax = circle.getCenterY();
                }
            }
            double height = yMax - yMin;
            double width = xMax - xMin;
            boundingRectangle.setX(xMin - radius);
            boundingRectangle.setY(yMin - radius);
            boundingRectangle.setWidth(width + 2 * radius);
            boundingRectangle.setHeight(height + 2 * radius);
        }

        private void removePoint(double x, double y) {
            ObservableList<Node> nodes = getChildren();
            List<Node> nodesToRemove = new ArrayList<>();
            for (Node node : nodes) {
                if (node instanceof Circle) {
                    if (node.contains(x, y)) nodesToRemove.add(node);
                }
            }
            nodes.removeAll(nodesToRemove);
        }

        private void addPoint(double x, double y) {
            Circle circle = new Circle(x, y, radius);
            circle.setStyle(style);
            getChildren().add(circle);
        }
    }
}
