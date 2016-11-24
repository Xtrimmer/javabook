package chapter_15;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * (Racing car) Write a program that simulates car racing, as shown in
 * Figure 15.34a. The car moves from left to right. When it hits the right end, it
 * restarts from the left and continues the same process. You can use a timer to
 * control animation. Redraw the car with a new base coordinates (x, y), as shown
 * in Figure 15.34b. Also let the user pause/resume the animation with a button
 * press/release and increase/decrease the car speed by pressing the UP and
 * DOWN arrow keys.
 */
public class PE_15_29_Racing_car extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CarPane pane = new CarPane();
        Scene scene = new Scene(pane, 400, 200);

        primaryStage.setTitle("Exercise15_29");
        primaryStage.setScene(scene);
        primaryStage.show();
        pane.requestFocus();
    }

    class CarPane extends Pane {

        private final Car car = new Car();
        private double increment = 1;

        public CarPane() {
            getChildren().addAll(car);
            car.setPosition(0, getHeight());
            car.setSize(2);

            KeyFrame keyFrame = new KeyFrame(Duration.millis(25), event -> moveCar(increment));
            Timeline timeline = new Timeline(keyFrame);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();

            setOnMousePressed(event -> timeline.pause());
            setOnMouseReleased(event -> timeline.play());
            setOnKeyReleased(event -> {
                if (event.getCode() == KeyCode.UP) {
                    increment++;
                } else if (event.getCode() == KeyCode.DOWN) {
                    increment--;
                }
            });
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
    }

    class Car extends ArrayList<Shape> {

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

        public double getWidth() {
            return 50 * size;
        }
    }
}
