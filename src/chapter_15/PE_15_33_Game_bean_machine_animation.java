package chapter_15;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * (Game: bean-machine animation) Write a program that animates the bean
 * machine introduced in Programming Exercise 7.21. The animation terminates
 * after ten balls are dropped, as shown in Figure 15.36b and c.
 */
public class PE_15_33_Game_bean_machine_animation extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BeanMachinePane pane = new BeanMachinePane();
        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setTitle("Exercise15_33");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.play();
    }

    private class BeanMachinePane extends Pane {

        private final BeanAnimation beanAnimation;

        BeanMachinePane() {
            beanAnimation = new BeanAnimation(this);
        }

        public void play() {
            beanAnimation.play();
        }

        @Override
        public void setWidth(double width) {
            super.setWidth(width);
            paint();
        }

        private void paint() {
            getChildren().clear();
            double width = getWidth();
            double height = getHeight();

            Polyline polyline = new Polyline(
                    width * (9 / 20.0), height * (1 / 12.0),
                    width * (9 / 20.0), height * (1 / 6.0),
                    width * (1 / 10.0), height * (3 / 4.0),
                    width * (1 / 10.0), height * (11 / 12.0),
                    width * (9 / 10.0), height * (11 / 12.0),
                    width * (9 / 10.0), height * (3 / 4.0),
                    width * (11 / 20.0), height * (1 / 6.0),
                    width * (11 / 20.0), height * (1 / 12.0)
            );
            getChildren().add(polyline);

            double startY = height * (3 / 4.0);
            double endY = height * (11 / 12.0);
            for (int i = 0; i < 7; i++) {
                double lineX = width * ((i + 2) / 10.0);
                getChildren().add(new Line(lineX, startY, lineX, endY));
            }

            double ellipseRadiusX = width / 40;
            double ellipseRadiusY = height / 40;
            for (int i = 0; i < 7; i++) {
                double ellipseCenterY = height * ((i + 3) / 12.0);
                for (int j = 0; j < i + 1; j++) {
                    double ellipseCenterX = width * ((10 - i + 2 * j) / 20.0);
                    getChildren().add(new Ellipse(ellipseCenterX, ellipseCenterY, ellipseRadiusX, ellipseRadiusY));
                }
            }
        }

        @Override
        public void setHeight(double height) {
            super.setHeight(height);
            paint();
        }
    }

    private class BeanAnimation {
        private final Pane beanMachinePane;
        private final int[] beanSlots;
        private  PathTransition animation = new PathTransition();
        private final double radius;
        private int cycles = 0;
        private int totalCycles = 10;

        BeanAnimation(Pane beanMachinePane) {
            this.beanMachinePane = beanMachinePane;
            animation = new PathTransition();
            beanSlots = new int[8];
            radius = 7.5;
        }

        public void play() {
            runAnimation();
            animation.setOnFinished(event -> runAnimation());

        }

        private void runAnimation() {
            int slot = 0;
            int posX = 10;
            Polyline path = new Polyline();
            path.setStroke(Color.TRANSPARENT);
            Circle circle = new Circle(radius, Color.ORANGE);
            ObservableList<Double> points = path.getPoints();
            points.add(beanMachinePane.getWidth() * (6 / 12.0));
            points.add(beanMachinePane.getHeight() * (1 / 12.0));
            points.add(beanMachinePane.getWidth() * (6 / 12.0));
            points.add(beanMachinePane.getHeight() * (2 / 12.0));
            for (int i = 3; i < 10; i++) {
                int rand = (int) (Math.random() * 2);
                if (rand == 0) {
                    posX += 1;
                    slot += 1;
                } else {
                    posX -= 1;
                }
                points.add(beanMachinePane.getWidth() * (posX / 20.0));
                points.add(beanMachinePane.getHeight() * (i / 12.0));
            }
            points.add(beanMachinePane.getWidth() * (posX / 20.0));
            points.add(beanMachinePane.getHeight() * (11 / 12.0) - radius - (beanSlots[slot] * 2 * radius));
            beanSlots[slot]++;
            beanMachinePane.getChildren().addAll(circle, path);
            animation.setPath(path);
            animation.setNode(circle);
            animation.setDuration(Duration.millis(5000));
            animation.setCycleCount(1);
            animation.play();
            if (++cycles >= totalCycles) {
                animation.setOnFinished(null);
            }
        }
    }
}

