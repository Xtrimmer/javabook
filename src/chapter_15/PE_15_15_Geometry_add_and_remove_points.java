package chapter_15;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * (Geometry: add and remove points) Write a program that lets the user click on
 * a pane to dynamically create and remove points (see Figure 15.28a). When the
 * user left-clicks the mouse (primary button), a point is created and displayed
 * at the mouse point. The user can remove a point by pointing to it and rightclicking
 * the mouse (secondary button).
 */
public class PE_15_15_Geometry_add_and_remove_points extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        PointPane pane = new PointPane();
        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setTitle("Exercise15_15");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.requestFocus();
    }

    private class PointPane extends Pane {
        public PointPane() {
            setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    addPoint(event.getX(), event.getY());
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    removePoint(event.getX(), event.getY());
                }
            });
        }

        private void removePoint(double x, double y) {
            ObservableList<Node> nodes = getChildren();
            List<Node> nodesToRemove = new ArrayList<>();
            for (Node node : nodes) {
                if (node.contains(x, y)) {
                    nodesToRemove.add(node);
                }
            }
            nodes.removeAll(nodesToRemove);
        }

        private void addPoint(double x, double y) {
            Circle circle = new Circle(x, y, 10, Color.WHITE);
            circle.setStroke(Color.BLACK);
            getChildren().add(circle);
        }
    }
}
