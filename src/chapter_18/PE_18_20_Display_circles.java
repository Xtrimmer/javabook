package chapter_18;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * (Display circles) Write a Java program that displays ovals, as shown in Figure
 * 18.12b. The circles are centered in the pane. The gap between two adjacent
 * circles is 10 pixels, and the gap between the border of the pane and the largest
 * circle is also 10.
 */
public class PE_18_20_Display_circles extends Application {
    private static final int SIZE = 300;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CirclePane pane = new CirclePane();
        Scene scene = new Scene(pane, SIZE, SIZE);

        primaryStage.setTitle("Exercise18_20");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.drawCircles();
    }

    private class CirclePane extends StackPane {
        public void drawCircles() {
            drawCircles((SIZE - 20) / 2.0);
        }

        @Override
        public void setWidth(double width) {
            super.setWidth(width);
            getChildren().clear();
            drawCircles((Math.min(width, getHeight()) - 20) / 2.0);
        }

        @Override
        public void setHeight(double height) {
            super.setHeight(height);
            getChildren().clear();
            drawCircles((Math.min(height, getWidth()) - 20) / 2.0);
        }

        private void drawCircles(double size) {
            if (size < 0) return;
            Circle circle = new Circle(size, Color.TRANSPARENT);
            circle.setStroke(Color.BLACK);
            getChildren().add(circle);
            drawCircles(size - 10);
        }
    }
}
