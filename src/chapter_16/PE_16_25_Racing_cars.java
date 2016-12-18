package chapter_16;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * (Racing cars) Write a program that simulates four cars racing, as shown in
 * Figure 16.47b. You can set the speed for each car, with maximum 100.
 */
public class PE_16_25_Racing_cars extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        RaceCarPane pane = new RaceCarPane(4);
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise16_25");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class RaceCarPane extends VBox {
        final CarPane[] carPanes;
        final TextField[] textFields;

        public RaceCarPane(int carCount) {
            setSpacing(5);
            setPadding(new Insets(5));
            carPanes = new CarPane[carCount];
            textFields = new TextField[carCount];
            getChildren().add(generateControlBar(carCount));
            for (int i = 0; i < carCount; i++) {
                carPanes[i] = new CarPane();
                carPanes[i].setPrefHeight(75);
                getChildren().add(carPanes[i]);
            }
        }

        private HBox generateControlBar(int carCount) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(5);
            hBox.setPadding(new Insets(5));
            ObservableList<Node> children = hBox.getChildren();
            for (int i = 0; i < carCount; i++) {
                children.add(new Label("Car " + (i + 1) + ":"));
                textFields[i] = new TextField("1");
                textFields[i].setPrefColumnCount(2);
                int finalI = i;
                textFields[i].setOnAction(event -> setCarSpeed(finalI));
                children.add(textFields[i]);
            }
            return hBox;
        }

        private double getDoubleFromTextField(TextField textField, int min, int max) {
            try {
                double i = Double.parseDouble(textField.getText());
                if (i > max) {
                    textField.setText(max + "");
                    return max;
                }
                if (i < min) {
                    textField.setText(min + "");
                    return min;
                }
                return i;
            } catch (NumberFormatException e) {
                textField.setText(min + "");
                return min;
            }
        }

        private void setCarSpeed(int i) {
            double speed = getDoubleFromTextField(textFields[i], 1, 100);
            carPanes[i].setIncrement(speed);
        }
    }

    private class CarPane extends Pane {

        private final Car car = new Car();
        private double increment = 1;

        public CarPane() {
            getChildren().add(GenerateRectangle());
            getChildren().addAll(car);
            car.setPosition(0, getHeight());
            car.setSize(1.5);

            KeyFrame keyFrame = new KeyFrame(Duration.millis(25), event -> moveCar(increment));
            Timeline timeline = new Timeline(keyFrame);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }

        public void moveCar(double increment) {
            double x = car.getX();
            if (x + increment > getWidth()) {
                x = -car.getWidth();
            } else if (x + increment < -car.getWidth()) {
                x = getWidth();
            } else {
                x += increment;
            }
            car.setPosition(x + increment, getHeight());
        }

        public void setIncrement(double increment) {
            this.increment = increment;
        }

        private Rectangle GenerateRectangle() {
            Rectangle rectangle = new Rectangle(0, 0, Color.TRANSPARENT);
            rectangle.setStroke(Color.BLACK);
            rectangle.widthProperty().bind(widthProperty());
            rectangle.heightProperty().bind(heightProperty().subtract(5));
            return rectangle;
        }
    }

    private class Car extends ArrayList<Shape> {

        private final Circle wheelLeft = new Circle(5, Color.BLACK);
        private final Circle wheelRight = new Circle(5, Color.BLACK);
        private final Rectangle bodyLower = new Rectangle(50, 10, Color.LIGHTBLUE);
        private final Polygon bodyUpper = new Polygon();
        private double x = 0;
        private double y = 0;
        private double size = 1;

        public Car() {
            this(0, 0, 1);
        }

        public Car(double x, double y, double size) {
            bodyUpper.setFill(Color.BLUE);
            add(bodyUpper);
            add(bodyLower);
            add(wheelLeft);
            add(wheelRight);
            this.x = x;
            this.y = y;
            this.size = size;
            update();
        }

        public double getWidth() {
            return 50 * size;
        }

        public double getX() {
            return x;
        }

        public void setPosition(double x, double y) {
            this.x = x;
            this.y = y;
            update();
        }

        public void setSize(double size) {
            this.size = size;
            update();
        }

        private void update() {
            ObservableList<Double> points = bodyUpper.getPoints();
            points.clear();
            points.addAll(
                    x + 10 * size, y - 20 * size,
                    x + 20 * size, y - 30 * size,
                    x + 30 * size, y - 30 * size,
                    x + 40 * size, y - 20 * size
            );
            bodyLower.setX(x);
            bodyLower.setY(y - 20 * size);
            bodyLower.setWidth(50 * size);
            bodyLower.setHeight(10 * size);
            wheelLeft.setRadius(5 * size);
            wheelLeft.setCenterX(x + 15 * size);
            wheelLeft.setCenterY(y - 5 * size);
            wheelRight.setRadius(5 * size);
            wheelRight.setCenterX(x + 35 * size);
            wheelRight.setCenterY(y - 5 * size);
        }
    }
}
