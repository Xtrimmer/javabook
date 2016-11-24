package chapter_15;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * (Animation: palindrome) Write a program that animates a palindrome swing as
 * shown in Figure 15.31. Press/release the mouse to pause/resume the animation.
 */
public class PE_15_24_Animation_palindrome extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();

        Arc arc = new Arc(200, 200, 150, 50, 200, 140);
        arc.setFill(Color.TRANSPARENT);
        arc.setStroke(Color.BLACK);

        Circle circle = new Circle(20, Color.ORANGE);

        PathTransition pathTransition = new PathTransition(Duration.millis(1000), arc, circle);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();

        pane.getChildren().addAll(arc, circle);
        Scene scene = new Scene(pane, 400, 300);

        pane.setOnMousePressed(event -> pathTransition.pause());
        pane.setOnMouseReleased(event -> pathTransition.play());

        primaryStage.setTitle("Exercise15_24");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
