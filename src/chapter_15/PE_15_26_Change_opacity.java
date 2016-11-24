package chapter_15;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
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
 * (Change opacity) Rewrite Programming Exercise 15.24 so that the ball’s
 * opacity is changed as it swings.
 */
public class PE_15_26_Change_opacity extends Application {

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

        Duration duration = Duration.millis(1000);
        Animation[] animations = new Animation[2];
        animations[0] = new PathTransition(duration, arc, circle);
        animations[1] = new FadeTransition(duration, circle);
        ((FadeTransition)animations[1]).setFromValue(0.1);
        ((FadeTransition)animations[1]).setToValue(1.0);
        for (Animation animation : animations) {
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.setAutoReverse(true);
            animation.play();
        }

        pane.getChildren().addAll(arc, circle);
        Scene scene = new Scene(pane, 400, 300);

        pane.setOnMousePressed(event -> pauseAnimations(animations));
        pane.setOnMouseReleased(event -> playAnimations(animations));

        primaryStage.setTitle("Exercise15_26");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void pauseAnimations(Animation[] animations) {
        for (Animation animation : animations) {
            animation.pause();
        }
    }

    private void playAnimations(Animation[] animations) {
        for (Animation animation : animations) {
            animation.play();
        }
    }
}
