package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Two movable vertices and their distances) Write a program that displays two
 * circles with radius 10 at location (40, 40) and (120, 150) with a line connecting
 * the two circles, as shown in Figure 15.28b. The distance between the circles
 * is displayed along the line. The user can drag a circle. When that happens, the
 * circle and its line are moved and the distance between the circles is updated.
 */
public class PE_15_16_Two_movable_vertices_and_their_distances extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        PointDistancePane pane = new PointDistancePane();
        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setTitle("Exercise15_16");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private class PointDistancePane extends Pane {
        final double radius = 10;
        private final Circle circle1 = new Circle();
        private final Circle circle2 = new Circle();
        private final Line line = new Line();
        final Text text = new Text();

        public PointDistancePane() {
            String style = "-fx-fill: transparent; -fx-stroke: black";
            
            circle1.setCenterX(40);
            circle1.setCenterY(40);
            circle1.setRadius(radius);
            circle1.setStyle(style);
            circle1.setOnMouseDragged(event -> moveCircle(event));
            
            circle2.setCenterX(120);
            circle2.setCenterY(150);
            circle2.setRadius(radius);
            circle2.setStyle(style);
            circle2.setOnMouseDragged(event -> moveCircle(event));

            drawLineBetweenCircles();
            getChildren().addAll(circle1, circle2, line, text);
        }

        private void moveCircle(MouseEvent event) {
            Circle circle = (Circle)event.getSource();
            circle.setCenterX(event.getX());
            circle.setCenterY(event.getY());
            drawLineBetweenCircles();
        }

        private void drawLineBetweenCircles() {
            double distance = Math.sqrt(
                    Math.pow(circle2.getCenterX() - circle1.getCenterX(), 2)
                    + Math.pow(circle2.getCenterY() - circle1.getCenterY(), 2)
            );
            double lineStartX = circle1.getCenterX() + ((radius / distance)
                    * (circle2.getCenterX() - circle1.getCenterX()));
            double lineStartY = circle1.getCenterY() + ((radius / distance)
                    * (circle2.getCenterY() - circle1.getCenterY()));
            double lineEndX = circle2.getCenterX() - ((radius / distance)
                    * (circle2.getCenterX() - circle1.getCenterX()));
            double lineEndY = circle2.getCenterY() - ((radius / distance)
                    * (circle2.getCenterY() - circle1.getCenterY()));
            line.setStartX(lineStartX);
            line.setStartY(lineStartY);
            line.setEndX(lineEndX);
            line.setEndY(lineEndY);

            double textX = (circle1.getCenterX() + circle2.getCenterX()) / 2.0;
            double textY = (circle1.getCenterY() + circle2.getCenterY()) / 2.0;
            text.setX(textX);
            text.setY(textY);
            text.setText(Math.round(distance) + "");
        }
    }
}
