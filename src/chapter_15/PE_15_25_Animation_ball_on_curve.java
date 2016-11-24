package chapter_15;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * (Animation: ball on curve) Write a program that animates a ball moving along
 * a sine curve, as shown in Figure 15.32. When the ball gets to the right border,
 * it starts over from the left. Enable the user to resume/pause the animation with
 * a click on the left/right mouse button.
 */
public class PE_15_25_Animation_ball_on_curve extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 200);

        Polyline sinWave = new Polyline();
        sinWave.setStroke(Color.BLACK);
        ObservableList<Double> sinList = sinWave.getPoints();
        for (int x = -170; x <= 170; x++) {
            sinList.add(x + 200.0);
            sinList.add(100 - 50 * Math.sin((x / 100.0) * 2 * Math.PI));
        }

        Circle circle = new Circle(10, Color.ORANGE);

        Line yLine1 = new Line(200,10,190,20);
        Line yLine2 = new Line(200,10,210,20);
        Line yLine3 = new Line(200,10,200,200);

        Line xLine1 = new Line(10,100,390,100);
        Line xLine2 = new Line(390,100,380,110);
        Line xLine3 = new Line(390,100,380,90);

        Text textX = new Text(380, 70, "X");
        Text textY = new Text(220, 20, "Y");
        Text text3Pin = new Text(50, 115, "-3\u03c0");
        Text text2Pin = new Text(100, 115, "-2\u03c0");
        Text text1Pin = new Text(150, 115, "-\u03c0");
        Text text3Pi = new Text(250, 115, "\u03c0");
        Text text2Pi = new Text(300, 115, "2\u03c0");
        Text text1Pi = new Text(350, 115, "3\u03c0");
        Text zero = new Text(205, 115, "0");

        pane.getChildren().addAll(sinWave, circle,
                yLine1, yLine2, yLine3, xLine1, xLine2, xLine3,
                textX, textY, text3Pin, text2Pin, text1Pin,
                text1Pi, text2Pi, text3Pi, zero);

        PathTransition pathTransition = new PathTransition(Duration.millis(3000), sinWave, circle);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.play();

        pane.setOnMouseClicked(event -> {
            if (pathTransition.getStatus().equals(PathTransition.Status.RUNNING)) {
                pathTransition.pause();
            } else {
                pathTransition.play();
            }
        });

        primaryStage.setTitle("Exercise15_25");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.requestFocus();
    }
}