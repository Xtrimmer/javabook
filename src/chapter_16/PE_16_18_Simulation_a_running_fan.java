package chapter_16;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
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
 * (Simulation: a running fan) Rewrite Programming Exercise 15.28 to add a slider
 * to control the speed of the fan, as shown in Figure 16.43c.
 */
public class PE_16_18_Simulation_a_running_fan extends Application {
    private FanPane fanPane;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        fanPane = new FanPane(400, 400);
        HBox buttonPane = createButtonPane();
        Slider slider = createSliderControl();

        BorderPane pane = new BorderPane();
        pane.setTop(buttonPane);
        pane.setCenter(fanPane);
        pane.setBottom(slider);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Exercise16_18");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Slider createSliderControl() {
        Slider slider = new Slider();
        slider.setMin(1);
        slider.setMax(10);
        slider.setValue(1);
        slider.valueProperty().addListener(observable -> fanPane.setSpeed((int) slider.getValue()));
        return slider;
    }

    private HBox createButtonPane() {
        Button buttonPause = new Button("Pause");
        Button buttonResume = new Button("Resume");
        Button buttonReverse = new Button("Reverse");
        HBox buttonPane = new HBox(5, buttonPause, buttonResume, buttonReverse);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(new Insets(5));

        buttonResume.setOnAction(event -> fanPane.resume());
        buttonPause.setOnAction(event -> fanPane.pause());
        buttonReverse.setOnAction(event -> fanPane.reverse());
        return buttonPane;
    }

    class FanPane extends Pane {
        private final Arc[] arcs = new Arc[4];
        private final Timeline timeline;
        private final KeyFrame animation;
        private int increment = 1;
        private int direction = -1;
        private double startAngle = 90;

        public FanPane(double width, double height) {
            setWidth(width);
            setHeight(height);
            animation = new KeyFrame(Duration.millis(10), event -> {
                startAngle += increment * direction;
                for (int i = 0; i < 4; i++) {
                    arcs[i].setStartAngle(startAngle + i * 90);
                }
            });
            timeline = new Timeline(animation);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
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
                arcs[i] = new Arc(centerX, centerY, radius2, radius2, startAngle + i * 90, 30);
                arcs[i].setType(ArcType.ROUND);
                arcs[i].setFill(Color.DARKRED);
            }
            getChildren().addAll(arcs);
        }

        public void pause() {
            timeline.pause();
        }

        public void resume() {
            timeline.play();
        }

        public void reverse() {
            direction = -direction;
        }

        public void setSpeed(int speed) {
            increment = speed;
        }
    }
}
