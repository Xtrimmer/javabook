package chapter_16;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * (Control a group of fans) Write a program that displays three fans in a group,
 * with control buttons to start and stop all of them, as shown in Figure 16.44.
 */
public class PE_16_19_Control_a_group_of_fans extends Application {
    private FanPane[] panes;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        pane.setBottom(createButtonPane());
        pane.setCenter(createFanPane());
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise16_19");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createButtonPane() {
        Button buttonStartAll = new Button("Start All");
        Button buttonStopAll = new Button("Stop All");
        buttonStartAll.setOnAction(event -> {
            for (FanPane pane : panes) {
                pane.startFan();
            }
        });
        buttonStopAll.setOnAction(event -> {
            for (FanPane pane : panes) {
                pane.stopFan();
            }
        });
        HBox buttonPane = new HBox(5, buttonStartAll, buttonStopAll);
        buttonPane.setPadding(new Insets(5));
        buttonPane.setAlignment(Pos.CENTER);
        return buttonPane;
    }

    private HBox createFanPane() {
        panes = new FanPane[3];
        for (int i = 0; i < panes.length; i++) {
            panes[i] = new FanPane(300, 300);
            HBox.setHgrow(panes[i], Priority.ALWAYS);
        }
        HBox fanPane = new HBox(5, panes);
        fanPane.setPadding(new Insets(5));
        fanPane.setAlignment(Pos.CENTER);
        return fanPane;
    }

    public class FanPane extends BorderPane {

        private final Arc[] arcs = new Arc[4];
        private Timeline timeline;
        private double increment = 1;
        private int direction = -1;
        private double startAngle = 90;

        public FanPane(double width, double height) {
            setPrefWidth(width);
            setPrefHeight(height);
            setCenter(createFanPane());
            setTop(createButtonPane());
            setBottom(createSliderControl());
            setStyle("-fx-border-color: black");
            initializeTimeline();
        }

        private void initializeTimeline() {
            KeyFrame animation = new KeyFrame(Duration.millis(10), event -> {
                startAngle += increment * direction;
                for (int i = 0; i < 4; i++) {
                    arcs[i].setStartAngle(startAngle + i * 90);
                }
            });
            timeline = new Timeline(animation);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }

        private Slider createSliderControl() {
            Slider slider = new Slider();
            slider.setMin(1);
            slider.setMax(10);
            slider.setValue(1);
            slider.setPadding(new Insets(5));
            slider.valueProperty().addListener((observable, oldValue, newValue) ->
                    increment = (double) newValue);
            return slider;
        }

        private Pane createFanPane() {
            Pane pane = new Pane();
            Circle circle = new Circle();
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            circle.centerXProperty().bind(pane.widthProperty().divide(2));
            circle.centerYProperty().bind(pane.heightProperty().divide(2));
            circle.radiusProperty().bind(
                    Bindings.min(pane.widthProperty(), pane.heightProperty()).multiply(0.4)
            );
            for (int i = 0; i < 4; i++) {
                arcs[i] = new Arc();
                arcs[i].setStartAngle(startAngle + i * 90);
                arcs[i].setLength(30);
                arcs[i].centerXProperty().bind(pane.widthProperty().divide(2));
                arcs[i].centerYProperty().bind(pane.heightProperty().divide(2));
                arcs[i].radiusXProperty().bind(
                        Bindings.min(pane.widthProperty(), pane.heightProperty()).multiply(0.33)
                );
                arcs[i].radiusYProperty().bind(
                        Bindings.min(pane.widthProperty(), pane.heightProperty()).multiply(0.33)
                );
                arcs[i].setType(ArcType.ROUND);
                arcs[i].setFill(Color.DARKRED);
            }
            pane.getChildren().add(circle);
            pane.getChildren().addAll(arcs);
            return pane;
        }

        private HBox createButtonPane() {
            Button buttonPause = new Button("Pause");
            Button buttonResume = new Button("Resume");
            Button buttonReverse = new Button("Reverse");

            buttonPause.setOnAction(event -> stopFan());
            buttonResume.setOnAction(event -> startFan());
            buttonReverse.setOnAction(event -> direction = -direction);

            HBox buttonPane = new HBox(5, buttonPause, buttonResume, buttonReverse);
            buttonPane.setAlignment(Pos.CENTER);
            buttonPane.setPadding(new Insets(5));
            return buttonPane;
        }

        public void startFan() {
            timeline.play();
        }

        public void stopFan() {
            timeline.pause();
        }
    }
}
