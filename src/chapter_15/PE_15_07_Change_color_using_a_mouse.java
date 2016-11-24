package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * (Change color using a mouse) Write a program that displays the color of a
 * circle as black when the mouse button is pressed and as white when the mouse
 * button is released.
 */
public class PE_15_07_Change_color_using_a_mouse extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Circle circle = new Circle(0, 0, 180);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        circle.setOnMousePressed(event -> circle.setFill(Color.BLACK));
        circle.setOnMouseReleased(event -> circle.setFill(Color.WHITE));

        StackPane pane = new StackPane(circle);
        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setTitle("Exercise15_06");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
