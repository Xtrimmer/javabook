package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * (Move a circle using keys) Write a program that moves a circle up, down, left,
 * or right using the arrow keys.
 */
public class PE_15_11_Move_a_circle_using_keys extends Application {
    private CirclePane pane = new CirclePane();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        pane.setOnKeyPressed(event -> moveCircle(event.getCode()));
        Scene scene = new Scene(pane, 400, 300);

        primaryStage.setTitle("Exercise15_11");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.requestFocus();
    }

    private void moveCircle(KeyCode keyCode) {
        double percentage = 0.05;
        double verticalDistance = pane.getHeight() * percentage;
        double horizontalDistance = pane.getWidth() * percentage;
        switch (keyCode) {
            case DOWN:
                pane.moveDown(verticalDistance);
                break;
            case UP:
                pane.moveUp(verticalDistance);
                break;
            case LEFT:
                pane.moveLeft(horizontalDistance);
                break;
            case RIGHT:
                pane.moveRight(horizontalDistance);
                break;
        }
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
