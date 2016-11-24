package chapter_14;

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
 * (Display a STOP sign) Write a program that displays a STOP sign, as shown
 * in Figure 14.47b. The octagon is in red and the sign is in white. (Hint: Place an
 * octagon and a text in a stack pane.)
 */
public class PE_14_15_Display_a_STOP_sign extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a polygon and place polygon to pane
        Polygon polygon = new Polygon();
        polygon.setFill(Color.RED);
        polygon.setRotate(22.5);
        ObservableList<Double> list = polygon.getPoints();

        double centerX = 200;
        double centerY = 200;
        double radius = 160;

        // Add points to the polygon list
        for (int i = 0; i < 8; i++) {
            list.add(centerX + radius * Math.cos(2 * i * Math.PI / 8));
            list.add(centerY - radius * Math.sin(2 * i * Math.PI / 8));
        }

        Text text = new Text("STOP");
        text.setFill(Color.WHITE);
        text.setFont(new Font("Times New Roman", 100));

        StackPane pane = new StackPane();
        pane.getChildren().clear();
        pane.getChildren().addAll(polygon, text);

        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setTitle("Exercise14_15");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}