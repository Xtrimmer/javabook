package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Game: eye-hand coordination) Write a program that displays a circle of radius
 * 10 pixels filled with a random color at a random location on a pane, as shown
 * in Figure 15.29b. When you click the circle, it disappears and a new randomcolor
 * circle is displayed at another random location. After twenty circles are
 * clicked, display the time spent in the pane, as shown in Figure 15.29c.
 */
public class PE_15_19_Game_eye_hand_coordination extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        EyeHandCoordinationPane pane = new EyeHandCoordinationPane(20);
        Scene scene = new Scene(pane, 400, 200);
        pane.start();

        primaryStage.setTitle("Exercise15_19");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class EyeHandCoordinationPane extends Pane {
        long startTime;
        long endTime;
        private final int rounds;
        private int currentRound = 1;
        private final Circle circle = new Circle(10);

        public EyeHandCoordinationPane(int rounds) {
            this.rounds = rounds;
        }

        public void start() {
            circle.setOnMouseClicked(event -> {
                if (currentRound < rounds) {
                    advanceRound();
                } else {
                    displayTime();
                }
            });
            resetCircle();
            getChildren().add(circle);
            startTime = System.currentTimeMillis();
        }

        private void displayTime() {
            endTime = System.currentTimeMillis();
            getChildren().clear();
            Text text = new Text();
            long timeSpent = endTime - startTime;
            text.setText("Time spent is " + timeSpent + " milliseconds");
            text.setX((getWidth() / 2.0) - (text.getBoundsInLocal().getWidth() / 2.0));
            text.setY((getHeight() / 2.0) + (text.getBoundsInLocal().getHeight() / 2.0));
            getChildren().clear();
            getChildren().add(text);
        }

        private void advanceRound() {
            resetCircle();
            currentRound++;
        }

        private void resetCircle() {
            circle.setCenterX(Math.random() * (getWidth() - 2 * circle.getRadius()) + circle.getRadius());
            circle.setCenterY(Math.random() * (getHeight() - 2 * circle.getRadius()) + circle.getRadius());
            circle.setStroke(Color.BLACK);
            circle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        }
    }
}
