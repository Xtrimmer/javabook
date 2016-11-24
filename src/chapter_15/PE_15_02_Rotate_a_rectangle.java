package chapter_15;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * (Rotate a rectangle) Write a program that rotates a rectangle 15 degrees right
 * when the Rotate button is clicked, as shown in Figure 15.24b.
 */
public class PE_15_02_Rotate_a_rectangle extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Rectangle rectangle = new Rectangle(0, 0, 50, 100);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);

        StackPane stackPane = new StackPane(rectangle);
        stackPane.setPrefWidth(300);
        stackPane.setPrefHeight(200);

        Button button = new Button("Rotate");
        button.setOnAction(e -> rectangle.setRotate(rectangle.getRotate() + 15));

        BorderPane pane = new BorderPane(stackPane, null, null, button, null);
        BorderPane.setAlignment(button, Pos.CENTER);

        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise15_02");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
