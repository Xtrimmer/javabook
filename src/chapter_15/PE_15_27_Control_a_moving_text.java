package chapter_15;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * (Control a moving text) Write a program that displays a moving text, as shown
 * in Figure 15.33a and b. The text moves from left to right circularly. When it
 * disappears in the right, it reappears from the left. The text freezes when the
 * mouse is pressed and moves again when the button is released.
 */
public class PE_15_27_Control_a_moving_text extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 300, 100);

        Text text = new Text("Programming is fun");
        Line line = new Line(-65, 50, 365, 50);
        line.setStroke(Color.TRANSPARENT);

        PathTransition pathTransition = new PathTransition(Duration.millis(3000), line, text);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.play();

        pane.getChildren().addAll(text, line);
        pane.setOnMousePressed(event -> pathTransition.pause());
        pane.setOnMouseReleased(event -> pathTransition.play());

        primaryStage.setTitle("Exercise15_27");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.requestFocus();
    }
}
