package chapter_15;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * (Animation: self-avoiding random walk) Revise the preceding exercise to display
 * the walk step by step in an animation, as shown in Figure 15.37c and d.
 */
public class PE_15_35_Animation_self_avoiding_random_walk extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        LatticePane latticePane = new LatticePane(10, 10);
        Button button = new Button("Start");
        button.setOnAction(event -> latticePane.execute());
        HBox hBox = new HBox(button);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10, 0, 0, 0));
        Pane pane = new BorderPane(latticePane, null, null, hBox, null);
        Scene scene = new Scene(pane, 400, 441);

        primaryStage.setTitle("Exercise15_34");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    interface LatticeAction {
        void execute();
    }

    private class LatticePane extends Pane {
        private final int latticeWidth;
        private final int latticeHeight;
        private final LatticeAction action;

        LatticePane(int latticeWidth, int latticeHeight) {
            setWidth(400);
            setHeight(400);
            this.latticeWidth = latticeWidth;
            this.latticeHeight = latticeHeight;
            action = new SelfAvoidingRandomWalkAnimation(this);
            drawLattice();
        }

        void drawLattice() {
            for (int i = 0; i <= latticeWidth; i++) {
                Line line = new Line();
                line.setStroke(Color.LIGHTGRAY);
                line.setStartY(0);
                line.startXProperty().bind(widthProperty().multiply(i / (double) latticeWidth));
                line.endXProperty().bind(widthProperty().multiply(i / (double) latticeWidth));
                line.endYProperty().bind(heightProperty());
                getChildren().add(line);
            }
            for (int i = 0; i <= latticeHeight; i++) {
                Line line = new Line();
                line.setStartX(0);
                line.startYProperty().bind(heightProperty().multiply(i / (double) latticeHeight));
                line.endYProperty().bind(heightProperty().multiply(i / (double) latticeHeight));
                line.endXProperty().bind(widthProperty());
                line.setStroke(Color.LIGHTGRAY);
                getChildren().add(line);
            }
        }

        void execute() {
            action.execute();
        }

        int getLatticeWidth() {
            return latticeWidth;
        }

        int getLatticeHeight() {
            return latticeHeight;
        }
    }

    private class SelfAvoidingRandomWalkAnimation implements LatticeAction {

        final KeyFrame keyFrame;
        final Timeline timeline;
        private final LatticePane pane;
        private boolean[][] grid;
        private Polyline polyline;
        private Point point;

        SelfAvoidingRandomWalkAnimation(LatticePane pane) {
            this.pane = pane;
            grid = new boolean[pane.getLatticeWidth() + 1][pane.getLatticeHeight() + 1];
            keyFrame = new KeyFrame(Duration.millis(100), event -> addLineSegment());
            timeline = new Timeline(keyFrame);
            timeline.setCycleCount(Timeline.INDEFINITE);
        }

        private void addLineSegment() {
            List<Point> availablePoints = getAvailablePoints(point);
            Collections.shuffle(availablePoints);
            point = availablePoints.get(0);
            grid[point.getX()][point.getY()] = true;
            ObservableList<Double> points = polyline.getPoints();
            points.addAll(
                    pane.getWidth() * (point.getX() / (double) pane.getLatticeWidth()),
                    pane.getHeight() * (point.getY() / (double) pane.getLatticeHeight())
            );
            if (isDeadEnd(point)) {
                timeline.stop();
            }
        }

        private boolean isDeadEnd(Point p) {
            return isEdge(p) || grid[p.getX() - 1][p.getY()] && grid[p.getX() + 1][p.getY()]
                    && grid[p.getX()][p.getY() - 1] && grid[p.getX()][p.getY() + 1];
        }

        private List<Point> getAvailablePoints(Point point) {
            List<Point> availablePoints = new ArrayList<>();
            if (!grid[point.getX() - 1][point.getY()])
                availablePoints.add(new Point(point.getX() - 1, point.getY()));
            if (!grid[point.getX() + 1][point.getY()])
                availablePoints.add(new Point(point.getX() + 1, point.getY()));
            if (!grid[point.getX()][point.getY() - 1])
                availablePoints.add(new Point(point.getX(), point.getY() - 1));
            if (!grid[point.getX()][point.getY() + 1])
                availablePoints.add(new Point(point.getX(), point.getY() + 1));
            return availablePoints;
        }

        private boolean isEdge(Point p) {
            return p.getX() == 0 || p.getX() == grid.length - 1 || p.getY() == 0 || p.getY() == grid[0].length - 1;
        }

        @Override
        public void execute() {
            pane.getChildren().remove(polyline);
            grid = new boolean[pane.getLatticeWidth() + 1][pane.getLatticeHeight() + 1];
            point = new Point(grid.length / 2, grid[0].length / 2);
            grid[point.getX()][point.getY()] = true;
            polyline = new Polyline(
                    pane.getWidth() * (point.getX() / (double) pane.getLatticeWidth()),
                    pane.getHeight() * (point.getY() / (double) pane.getLatticeHeight())
            );
            polyline.setStroke(Color.RED);
            pane.getChildren().add(polyline);
            timeline.play();
        }
    }

    private class Point {
        private final int x;
        private final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}