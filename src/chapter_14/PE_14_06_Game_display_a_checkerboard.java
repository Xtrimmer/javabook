package chapter_14;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * (Game: display a checkerboard) Write a program that displays a checkerboard
 * in which each white and black cell is a Rectangle with a fill color black or
 * white, as shown in Figure 14.44c(p.579).
 */
public class PE_14_06_Game_display_a_checkerboard extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    pane.add(new Rectangle(0, 0, Color.WHITE), j, i);
                } else {
                    pane.add(new Rectangle(0, 0, Color.BLACK), j, i);
                }
            }
        }
        for (Node node : pane.getChildren()) {
            ((Rectangle)node).widthProperty().bind(pane.widthProperty().divide(8));
            ((Rectangle)node).heightProperty().bind(pane.heightProperty().divide(8));
        }
        Scene scene = new Scene(pane, 300, 300);
        primaryStage.setTitle("Exercise14_06");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

