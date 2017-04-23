package chapter_20;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * (Combine colliding bouncing balls) The example in Section 20.7 displays multiple
 * bouncing balls. Extend the example to detect collisions. Once two balls
 * collide, remove the later ball that was added to the pane and add its radius to
 * the other ball, as shown in Figure 20.17b. Use the Suspend button to suspend
 * the animation and the Resume button to resume the animation. Add a mouse
 * pressed handler that removes a ball when the mouse is pressed on the ball.
 */
public class PE_20_05_Combine_colliding_bouncing_balls extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        MultipleBallPane ballPane = new MultipleBallPane();
        ballPane.setStyle("-fx-border-color: yellow");

        Button btSuspend = new Button("Suspend");
        Button btResume = new Button("Resume");
        Button btAdd = new Button("+");
        Button btSubtract = new Button("-");
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(btSuspend, btResume, btAdd, btSubtract);
        hBox.setAlignment(Pos.CENTER);

        // Add or remove a ball
        btAdd.setOnAction(e -> ballPane.add());
        btSubtract.setOnAction(e -> ballPane.subtract());
        ballPane.setOnMouseReleased(e -> removeBall(e, ballPane.getChildren()));

        // Pause and resume animation
        btSuspend.setOnAction(event -> ballPane.pause());
        btResume.setOnAction(event -> ballPane.play());

        // Use a scroll bar to control animation speed
        ScrollBar sbSpeed = new ScrollBar();
        sbSpeed.setMax(20);
        sbSpeed.setValue(10);
        ballPane.rateProperty().bind(sbSpeed.valueProperty());

        BorderPane pane = new BorderPane();
        pane.setCenter(ballPane);
        pane.setTop(sbSpeed);
        pane.setBottom(hBox);

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 250, 150);
        primaryStage.setTitle("MultipleBounceBall"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    private void removeBall(MouseEvent e, ObservableList<Node> children) {
        Point2D click = new Point2D(e.getX(), e.getY());
        List<Node> childrenToRemove = new ArrayList<>();
        for (Node child : children) {
            if (child.contains(click)) {
                childrenToRemove.add(child);
            }
        }
        children.removeAll(childrenToRemove);
    }

    private class MultipleBallPane extends Pane {
        private Timeline animation;

        public MultipleBallPane() {
            // Create an animation for moving the ball
            animation = new Timeline(
                    new KeyFrame(Duration.millis(50), e -> moveBall()));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play(); // Start animation
        }

        public void add() {
            Color color = new Color(Math.random(),
                    Math.random(), Math.random(), 0.5);
            getChildren().add(new Ball(30, 30, 10, color));
        }

        public void pause() {
            animation.pause();
        }

        public void play() {
            animation.play();
        }

        public DoubleProperty rateProperty() {
            return animation.rateProperty();
        }

        public void subtract() {
            if (getChildren().size() > 0) {
                getChildren().remove(getChildren().size() - 1);
            }
        }

        protected void moveBall() {
            ObservableList<Node> children = this.getChildren();
            List<Ball> ballsToRemove = new ArrayList<>();
            for (Node node : children) {
                Ball thisBall = (Ball) node;
                // Check boundaries
                if (thisBall.getCenterX() < thisBall.getRadius() ||
                        thisBall.getCenterX() > getWidth() - thisBall.getRadius()) {
                    thisBall.dx *= -1; // Change thisBall move direction
                }
                if (thisBall.getCenterY() < thisBall.getRadius() ||
                        thisBall.getCenterY() > getHeight() - thisBall.getRadius()) {
                    thisBall.dy *= -1; // Change thisBall move direction
                }

                // Merge ball collisions
                List<Node> slice = children.subList(children.indexOf(thisBall) + 1, children.size());
                for (Node element : slice) {
                    Ball otherBall = (Ball) element;
                    if (thisBall.intersects(otherBall.getBoundsInLocal())) {
                        double otherBallRadius = otherBall.getRadius();
                        thisBall.setRadius(thisBall.getRadius() + otherBallRadius);
                        ballsToRemove.add(otherBall);
                    }
                }

                // Adjust thisBall position
                thisBall.setCenterX(thisBall.dx + thisBall.getCenterX());
                thisBall.setCenterY(thisBall.dy + thisBall.getCenterY());
            }
            children.removeAll(ballsToRemove);
        }
    }

    class Ball extends Circle {
        private double dx = 1, dy = 1;

        Ball(double x, double y, double radius, Color color) {
            super(x, y, radius);
            setFill(color); // Set ball color
        }
    }
}
