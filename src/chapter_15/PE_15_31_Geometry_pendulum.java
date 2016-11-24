package chapter_15;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * (Geometry: pendulum) Write a program that animates a pendulum swinging,
 * as shown in Figure 15.35. Press the UP arrow key to increase the speed and the
 * DOWN key to decrease it. Press the S key to stop animation and the R key to
 * resume it.
 */
public class PE_15_31_Geometry_pendulum extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        double size = 300;
        PendulumPane pane = new PendulumPane(size, size);
        Scene scene = new Scene(pane, size, size);

        primaryStage.setTitle("Exercise15_31");
        primaryStage.setScene(scene);
        primaryStage.show();
        pane.startPendulum();
    }

    class PendulumPane extends Pane {

        final Circle pivot = new Circle(5);
        final Circle bob = new Circle(10);
        final Line rod = new Line();
        final Arc arc = new Arc();
        PathTransition pathTransition;

        public PendulumPane(double width, double height) {
            getChildren().addAll(pivot, bob, rod, arc);
            pivot.setCenterX(width * (1 / 2.0));
            pivot.setCenterY(height * (1 / 10.0));
            rod.setStartX(pivot.getCenterX());
            rod.setStartY(pivot.getCenterY());
            rod.endXProperty().bind(bob.centerXProperty().add(bob.translateXProperty()));
            rod.endYProperty().bind(bob.centerYProperty().add(bob.translateYProperty()));
            arc.setCenterX(pivot.getCenterX());
            arc.setCenterY(pivot.getCenterY());
            arc.setRadiusX(width * (8 / 10.0));
            arc.setRadiusY(arc.getRadiusX());
            arc.setStartAngle(240);
            arc.setLength(60);
            arc.setType(ArcType.OPEN);
            arc.setStroke(Color.TRANSPARENT);
            arc.setFill(Color.TRANSPARENT);
        }

        public void startPendulum(){
            pathTransition = new PathTransition(
                    Duration.millis(getHeight() * 3), arc, bob);
            pathTransition.setCycleCount(Timeline.INDEFINITE);
            pathTransition.setAutoReverse(true);
            pathTransition.play();
        }
    }
}