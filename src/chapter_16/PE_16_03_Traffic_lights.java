package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * (Traffic lights) Write a program that simulates a traffic light. The program lets
 * the user select one of three lights: red, yellow, or green. When a radio button
 * is selected, the light is turned on. Only one light can be on at a time (see
 * Figure 16.37a). No light is on when the program starts.
 */
public class PE_16_03_Traffic_lights extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        TrafficLightPane trafficLightPane = new TrafficLightPane();
        ColorSelectionPane colorSelectionPane = new ColorSelectionPane(trafficLightPane);
        BorderPane pane = new BorderPane(
                trafficLightPane, null, null, colorSelectionPane, null
        );
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise16_03");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class TrafficLightPane extends Pane {

        static final int RED = 0;
        static final int YELLOW = 1;
        static final int GREEN = 2;

        final Circle[] lights;
        final Color[] colors;

        TrafficLightPane() {
            setPrefHeight(400);
            setPrefWidth(400);

            Rectangle rectangle = new Rectangle();
            rectangle.setFill(Color.GRAY);
            rectangle.setStroke(Color.BLACK);
            rectangle.strokeWidthProperty().bind(heightProperty().multiply(1 / 180.0));
            rectangle.heightProperty().bind(heightProperty().multiply(10 / 12.0));
            rectangle.widthProperty().bind(heightProperty().multiply(4 / 12.0));
            rectangle.yProperty().bind(heightProperty().multiply(1 / 12.0));
            rectangle.xProperty().bind(widthProperty().multiply(6 / 12.0)
                    .subtract(heightProperty().multiply(2 / 12.0)));

            lights = new Circle[3];
            for (int i = 0; i < 3; i++) {
                lights[i] = new Circle();
                lights[i].setFill(Color.BLACK);
                lights[i].setStroke(Color.BLACK);
                lights[i].strokeWidthProperty().bind(heightProperty().multiply(1 / 180.0));
                lights[i].radiusProperty().bind(heightProperty().multiply(1 / 12.0));
                lights[i].centerYProperty().bind(heightProperty().multiply((3 + 3 * i) / 12.0));
                lights[i].centerXProperty().bind(widthProperty().multiply(6 / 12.0));
            }

            colors = new Color[3];
            colors[RED] = Color.RED;
            colors[YELLOW] = Color.YELLOW;
            colors[GREEN] = Color.LIMEGREEN;

            getChildren().add(rectangle);
            getChildren().addAll(lights);
        }

        void setLight(int color) {
            for (Circle light : lights) {
                light.setFill(Color.BLACK);
            }
            lights[color].setFill(colors[color]);
        }
    }

    private class ColorSelectionPane extends HBox {

        private ColorSelectionPane(TrafficLightPane pane) {
            setAlignment(Pos.CENTER);
            setSpacing(10);
            setPadding(new Insets(10));

            ToggleGroup group = new ToggleGroup();

            RadioButton radioButtonRed = new RadioButton("Red");
            radioButtonRed.setToggleGroup(group);
            radioButtonRed.setOnAction(event -> pane.setLight(TrafficLightPane.RED));

            RadioButton radioButtonYellow = new RadioButton("Yellow");
            radioButtonYellow.setToggleGroup(group);
            radioButtonYellow.setOnAction(event -> pane.setLight(TrafficLightPane.YELLOW));

            RadioButton radioButtonGreen = new RadioButton("Green");
            radioButtonGreen.setToggleGroup(group);
            radioButtonGreen.setOnAction(event -> pane.setLight(TrafficLightPane.GREEN));

            getChildren().addAll(radioButtonRed, radioButtonYellow, radioButtonGreen);
        }
    }
}
