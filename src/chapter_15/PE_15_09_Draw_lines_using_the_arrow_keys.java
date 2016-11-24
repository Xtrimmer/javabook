package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * (Draw lines using the arrow keys) Write a program that draws line segments
 * using the arrow keys. The line starts from the center of the pane and draws
 * toward east, north, west, or south when the right-arrow key, up-arrow key, leftarrow
 * key, or down-arrow key is pressed, as shown in Figure 15.26b.
 */
public class PE_15_09_Draw_lines_using_the_arrow_keys extends Application {
    private final Pane pane = new Pane();
    private double startX, startY, endX, endY;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        pane.setOnKeyPressed(event -> drawLine(event.getCode()));
        Scene scene = new Scene(pane, 400, 400);

        startX = pane.getWidth() / 2.0;
        endX = startX;
        startY = pane.getHeight() / 2.0;
        endY = startY;

        primaryStage.setTitle("Exercise15_09");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.requestFocus();
    }

    private void drawLine(KeyCode keyCode) {
        switch (keyCode) {
            case DOWN:
                endY = startY + 25;
                break;
            case UP:
                endY = startY - 25;
                break;
            case LEFT:
                endX = startX - 25;
                break;
            case RIGHT:
                endX = startX + 25;
                break;
        }
        Line line = new Line(startX, startY, endX, endY);
        pane.getChildren().add(line);
        startX = endX;
        startY = endY;
    }
}
