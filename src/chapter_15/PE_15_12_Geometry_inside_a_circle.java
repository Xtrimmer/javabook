package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Geometry: inside a circle?) Write a program that draws a fixed circle centered
 * at (100, 60) with radius 50. Whenever the mouse is moved, display a message
 * indicating whether the mouse point is inside the circle at the mouse point or
 * outside of it, as shown in Figure 15.27a.
 */
public class PE_15_12_Geometry_inside_a_circle extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ShapePane pane = new ShapePane(new Circle(100, 60, 50));
        Scene scene = new Scene(pane, 500, 200);

        primaryStage.setTitle("Exercise15_12");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.requestFocus();
    }

    private class ShapePane extends Pane {
        private final Text text = new Text();
        private final Shape shape;

        public ShapePane(Shape shape) {
            shape.setFill(Color.WHITE);
            shape.setStroke(Color.BLACK);
            this.shape = shape;
            getChildren().addAll(shape, text);
            setOnMouseMoved(event -> setTextShapeLocation(event));
        }

        private void setTextShapeLocation(MouseEvent event) {
            double coordinateX = event.getX();
            double coordinateY = event.getY();
            text.setX(coordinateX);
            text.setY(coordinateY);
            if (shape.contains(coordinateX, coordinateY)) {
                text.setText("Mouse point is inside the "
                        + shape.getClass().getSimpleName().toLowerCase());
            } else {
                text.setText("Mouse point is outside the "
                        + shape.getClass().getSimpleName().toLowerCase());
            }
        }
    }
}
