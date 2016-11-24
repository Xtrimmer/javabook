package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * (Move a rectangle using mouse) Write a program that displays a rectangle.
 * You can point the mouse inside the rectangle and drag (i.e., move with mouse
 * pressed) the rectangle wherever the mouse goes. The mouse point becomes the
 * center of the rectangle.
 */
public class PE_15_18_Move_a_rectangle_using_mouse extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 400);
        Rectangle draggableRectangle = new Rectangle(100, 100, 100, 100);
        pane.getChildren().add(draggableRectangle);
        draggableRectangle.setOnMouseDragged(event -> {
            draggableRectangle.setX(event.getX() - draggableRectangle.getWidth() / 2.0);
            draggableRectangle.setY(event.getY() - draggableRectangle.getHeight() / 2.0);
        });
        primaryStage.setTitle("Exercise15_18");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
