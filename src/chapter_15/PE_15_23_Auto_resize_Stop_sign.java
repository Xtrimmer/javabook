package chapter_15;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Auto resize stop sign) Rewrite Programming Exercise 14.15 so that the stop
 * sign’s width and height are automatically resized when the window is resized.
 */
public class PE_15_23_Auto_resize_Stop_sign extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        StopSignPane pane = new StopSignPane();
        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setTitle("Exercise15_23");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class StopSignPane extends StackPane {
        private void paint() {
            // Create a polygon and place polygon to pane
            Polygon polygon = new Polygon();
            polygon.setFill(Color.RED);
            polygon.setRotate(22.5);
            ObservableList<Double> list = polygon.getPoints();

            double centerX = getWidth() / 2, centerY = getHeight() / 2;
            double radius = Math.min(getWidth(), getHeight()) * 0.4;

            // Add points to the polygon list
            for (int i = 0; i < 8; i++) {
                list.add(centerX + radius * Math.cos(2 * i * Math.PI / 8));
                list.add(centerY - radius * Math.sin(2 * i * Math.PI / 8));
            }

            Text text = new Text("STOP");
            text.setFill(Color.WHITE);
            text.setFont(new Font("Times New Roman", Math.min(getWidth(), getHeight()) / 4));
            getChildren().clear();
            getChildren().addAll(polygon, text);
        }

        @Override
        public void setWidth(double width) {
            super.setWidth(width);
            paint();
        }

        @Override
        public void setHeight(double height) {
            super.setHeight(height);
            paint();
        }
    }
}

