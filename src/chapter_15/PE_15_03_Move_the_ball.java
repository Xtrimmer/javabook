package chapter_15;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * (Move the ball) Write a program that moves the ball in a pane. You should
 * define a pane class for displaying the ball and provide the methods for moving
 * the ball left, right, up, and down, as shown in Figure 15.24c. Check the boundary
 * to prevent the ball from moving out of sight completely.
 */
public class PE_15_03_Move_the_ball extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        CirclePane circlePane = new CirclePane();
        Button buttonLeft = new Button("Left");
        Button buttonRight = new Button("Right");
        Button buttonUp = new Button("Up");
        Button buttonDown = new Button("Down");

        buttonLeft.setOnAction(event -> circlePane.moveLeft(circlePane.getWidth() * 0.05));
        buttonRight.setOnAction(event -> circlePane.moveRight(circlePane.getWidth() * 0.05));
        buttonUp.setOnAction(event -> circlePane.moveUp(circlePane.getHeight() * 0.05));
        buttonDown.setOnAction(event -> circlePane.moveDown(circlePane.getHeight() * 0.05));

        HBox hBox = new HBox(5, buttonLeft, buttonRight, buttonUp, buttonDown);
        BorderPane pane = new BorderPane(circlePane, null, null, hBox, null);
        hBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise15_03");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class CirclePane extends Pane {
        private final Circle circle = new Circle(200, 150, 25, Color.TRANSPARENT);

        public CirclePane() {
            setPrefHeight(300);
            setPrefWidth(400);
            circle.setStroke(Color.BLACK);
            getChildren().add(circle);
        }

        public void moveDown(double distance) {
            if (!(circle.getCenterY() + distance > getHeight())) {
                circle.setCenterY(circle.getCenterY() + distance);
            }
        }

        public void moveLeft(double distance) {
            if (!(circle.getCenterX() - distance < 0)) {
                circle.setCenterX(circle.getCenterX() - distance);
            }
        }

        public void moveRight(double distance) {
            if (!(circle.getCenterX() + distance > getWidth())) {
                circle.setCenterX(circle.getCenterX() + distance);
            }
        }

        public void moveUp(double distance) {
            if (!(circle.getCenterY() - distance < 0)) {
                circle.setCenterY(circle.getCenterY() - distance);
            }
        }
    }
}
