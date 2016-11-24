package chapter_15;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * (Simulation: self-avoiding random walk) A self-avoiding walk in a lattice is a
 * path from one point to another that does not visit the same point twice. Self-avoiding
 * walks have applications in physics, chemistry, and mathematics. They
 * can be used to model chain-like entities such as solvents and polymers. Write
 * a program that displays a random path that starts from the center and ends at a
 * point on the boundary, as shown in Figure 15.37a or ends at a dead-end point
 * (i.e., surrounded by four points that have already been visited), as shown in
 * Figure 15.37b. Assume the size of the lattice is 16 by 16.
 */
public class PE_15_34_Simulation_self_avoiding_random_walk extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        LatticePane latticePane = new LatticePane(16, 16);
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
            action = new SelfAvoidingRandomWalk(this);
            drawLattice();
        }

        void drawLattice() {
            for (int i = 0; i <= latticeWidth; i++) {
                double x = getWidth() * (i / (double) latticeWidth);
                Line line = new Line(x, 0, x, getHeight());
                line.setStroke(Color.LIGHTGRAY);
                getChildren().add(line);
            }
            for (int i = 0; i <= latticeHeight; i++) {
                double y = getHeight() * (i / (double) latticeHeight);
                Line line = new Line(0, y, getWidth(), y);
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

    private class SelfAvoidingRandomWalk implements LatticeAction {

        private final LatticePane pane;
        private boolean[][] grid;

        SelfAvoidingRandomWalk(LatticePane pane) {
            this.pane = pane;
            grid = new boolean[pane.getLatticeWidth() + 1][pane.getLatticeHeight() + 1];
        }

        @Override
        public void execute() {
            pane.getChildren().clear();
            pane.drawLattice();
            grid = new boolean[pane.getLatticeWidth() + 1][pane.getLatticeHeight() + 1];
            Point point = new Point(grid.length / 2, grid[0].length / 2);
            grid[point.getX()][point.getY()] = true;
            Polyline polyline = new Polyline(
                    pane.getWidth() * (point.getX() / (double) pane.getLatticeWidth()),
                    pane.getHeight() * (point.getY() / (double) pane.getLatticeHeight())
            );
            polyline.setStroke(Color.RED);
            ObservableList<Double> points = polyline.getPoints();
            while (!isDeadEnd(point)) {
                List<Point> availablePoints = getAvailablePoints(point);
                Collections.shuffle(availablePoints);
                point = availablePoints.get(0);
                grid[point.getX()][point.getY()] = true;
                points.addAll(
                        pane.getWidth() * (point.getX() / (double) pane.getLatticeWidth()),
                        pane.getHeight() * (point.getY() / (double) pane.getLatticeHeight())
                );
            }
            pane.getChildren().add(polyline);
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