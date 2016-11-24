package chapter_14;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.Arrays;

/**
 * (Random points on a circle) Modify Programming Exercise 4.6 to create five
 * random points on a circle, form a polygon by connecting the points clockwise,
 * and display the circle and the polygon, as shown in Figure 14.51b.
 */
public class PE_14_25_Random_points_on_a_circle extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 400);

        Circle circle = new Circle(200, 200, 180, Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        double[] radians = generateRandomSortedRadians(5);
        Polygon polygon = generatePolygonFromDegrees(radians, circle);

        pane.getChildren().addAll(circle, polygon);

        primaryStage.setTitle("Exercise14_25");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Polygon generatePolygonFromDegrees(double[] radians, Circle circle) {
        Polygon polygon = new Polygon();
        polygon.setStyle("-fx-fill: transparent; -fx-stroke: black");
        ObservableList<Double> points = polygon.getPoints();
        for (double radian : radians) {
            points.add(circle.getCenterX() + circle.getRadius() * Math.cos(radian));
            points.add(circle.getCenterY() - circle.getRadius() * Math.sin(radian));
        }
        return polygon;
    }

    private double[] generateRandomSortedRadians(int size) {
        double[] radians = new double[size];
        for (int i = 0; i < size; i++) {
            radians[i] = Math.random() * 2 * Math.PI;
        }
        Arrays.sort(radians);
        return radians;
    }
}
