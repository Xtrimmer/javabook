package Chapter_15;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by jtrimmer on 10/3/2016.
 */
public class PE_15_28_Display_a_running_fan extends Application {
    public static void main(String[] args) {
    Application.launch(args);
}

    @Override
    public void start(Stage primaryStage) {

        Button buttonPause = new Button("Pause");
        Button buttonResume = new Button("Resume");
        Button buttonReverse = new Button("Reverse");
        HBox buttonPane = new HBox(5, buttonPause, buttonResume, buttonReverse);
        buttonPane.setAlignment(Pos.CENTER);
        FanPane fanPane = new FanPane(400, 400);

        buttonResume.setOnAction(event -> fanPane.resume());

        BorderPane pane = new BorderPane(fanPane, null, null, buttonPane, null);
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise15_");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class FanPane extends Pane {
        private Arc[] arcs = new Arc[4];
        private Timeline timeline;
        private KeyFrame spinForward;
        private KeyFrame spinReverse;

        public FanPane(double width, double height) {
            setWidth(width);
            setHeight(height);
            spinForward = new KeyFrame(Duration.millis(3), event -> {
                for (Arc arc : arcs) {
                    arc.setStartAngle(arc.getStartAngle() + 1);
                }
            });
            spinReverse = new KeyFrame(Duration.millis(3), event -> {
                for (Arc arc : arcs) {
                    arc.setStartAngle(arc.getStartAngle() - 1);
                }
            });
            timeline = new Timeline(spinForward);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }

        public void paint() {
            double centerX = getWidth() / 2;
            double centerY = getHeight() / 2;
            double radius1 = Math.min(getWidth(), getHeight()) * 0.4;
            double radius2 = Math.min(getWidth(), getHeight()) * 0.33;
            Circle circle = new Circle(centerX, centerY, radius1, Color.WHITE);
            circle.setStroke(Color.BLACK);
            getChildren().clear();
            getChildren().add(circle);
            for (int i = 0; i < 4; i++) {
                arcs[i] = new Arc(centerX, centerY, radius2, radius2, 90 * i + 30, 30);
                arcs[i].setType(ArcType.ROUND);
                arcs[i].setFill(Color.GRAY);
            }
            getChildren().addAll(arcs);
        }

        @Override
        public void setWidth(double width) {
            super.setWidth(width);
            paint();
        }

        @Override
        public void setHeight(double height) {
            super.setHeight(height);
            paint();
        }

        public void pause() {

        }

        public void resume() {
            timeline.setOnFinished(event -> {
                for (Arc arc : arcs) {
                    arc.setStartAngle(arc.getStartAngle() + 1);
                }
            });
        }

        public void reverse() {

        }
    }
}
