package chapter_18;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * (Game: Knight’s Tour animation) Write a program for the Knight’s Tour problem.
 * Your program should let the user move a knight to any starting square and
 * click the Solve button to animate a knight moving along the path, as shown in
 * Figure 18.16.
 */
public class PE_18_33_Game_Knights_Tour_animation extends Application {

    private static final String KNIGHT = "image/knight.jpg";
    private static final Background CHESS_KNIGHT = new Background(
            new BackgroundImage(
                    new Image(KNIGHT),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(1, 1,
                            true, true,
                            false, false
                    )
            )
    );

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Board pane = new Board(8, 8);
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise18_33");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class Board extends BorderPane {
        private static final double SIZE = 50;
        final ImageView imageView = new ImageView(KNIGHT);
        private final List<Cell> path = new ArrayList<>();
        private Button button;
        private Cell[][] cells;
        private Cell knightCell;
        private boolean isSolved;

        public Board(int width, int height) {
            initializeCells(width, height);
            setCenter(generateBoard());
            setBottom(createButtonPane());
        }

        private void buttonAction() {
            solveKnightsTour(knightCell);
            showPath();
            button.setDisable(true);
        }

        private int calculateCellPriority(int row, int column) {
            int value = 0;
            if (row > 0 && row < 7) value++;
            if (column > 0 && column < 7) value++;
            if (row > 1 && row < 6) value++;
            if (column > 1 && column < 6) value++;
            return value;
        }

        private Node createButtonPane() {
            button = new Button("Solve");
            button.setOnAction(event -> buttonAction());
            HBox hBox = new HBox(button);
            hBox.setPadding(new Insets(10));
            hBox.setAlignment(Pos.CENTER);
            return hBox;
        }

        private void doAction(PathTransition pathTransition) {
            Line pathLine = (Line) pathTransition.getPath();
            pathLine.setStroke(Color.ORANGE);
            pathLine.setStrokeWidth(SIZE / 15);
            Circle circle = new Circle(pathLine.getEndX(), pathLine.getEndY(), SIZE / 10, Color.ORANGE);
            getChildren().add(circle);
            pathTransition.play();
        }

        private Node generateBoard() {
            GridPane gridPane = new GridPane();
            for (int row = 0; row < cells.length; row++) {
                for (int column = 0; column < cells[row].length; column++) {
                    gridPane.add(cells[row][column], column, row);
                }
            }
            gridPane.setVgap(1);
            gridPane.setHgap(1);
            gridPane.setGridLinesVisible(true);
            gridPane.setPadding(new Insets(10, 10, 0, 10));
            return gridPane;
        }

        private List<Cell> getValidMoves(Cell cell) {
            int cellRow = GridPane.getRowIndex(cell);
            int cellColumn = GridPane.getColumnIndex(cell);
            List<Cell> validMoves = new ArrayList<>();
            for (int row = -2; row <= 2; row++) {
                for (int column = -2; column <= 2; column++) {
                    if (isValidMove(cell, row, column)) {
                        validMoves.add(cells[cellRow + row][cellColumn + column]);
                    }
                }
            }
            return validMoves;
        }

        private void initializeCells(int width, int height) {
            cells = new Cell[height][width];
            for (int row = 0; row < cells.length; row++) {
                for (int column = 0; column < cells[row].length; column++) {
                    cells[row][column] = new Cell(SIZE, SIZE);
                    cells[row][column].setOnMouseClicked(this::setStart);
                    cells[row][column].setPriority(calculateCellPriority(row, column));
                }
            }
            knightCell = cells[0][0];
        }

        private boolean isOutOfBounds(int row, int column) {
            return row < 0 || row > 7 || column < 0 || column > 7;
        }

        private boolean isSolved() {
            for (Cell[] row : cells) {
                for (Cell cell : row) {
                    if (!cell.isVisited()) return false;
                }
            }
            return true;
        }

        private boolean isValidMove(Cell cell, int rowOffset, int columnOffset) {
            int cellRow = GridPane.getRowIndex(cell);
            int cellColumn = GridPane.getColumnIndex(cell);
            return Math.abs(rowOffset) + Math.abs(columnOffset) == 3
                    && !isOutOfBounds(cellRow + rowOffset, cellColumn + columnOffset)
                    && !cells[cellRow + rowOffset][cellColumn + columnOffset].isVisited();
        }

        private void setStart(MouseEvent mouseEvent) {
            knightCell.setBackground(null);
            knightCell = (Cell) mouseEvent.getSource();
            knightCell.setBackground(CHESS_KNIGHT);
        }

        private PathTransition setupPathTransitions(int index) {
            Cell firstCell = path.get(index - 1);
            Cell secondCell = path.get(index);
            double startX = firstCell.getLayoutX() + firstCell.getWidth() / 2;
            double startY = firstCell.getLayoutY() + firstCell.getHeight() / 2;
            double endX = secondCell.getLayoutX() + secondCell.getWidth() / 2;
            double endY = secondCell.getLayoutY() + secondCell.getHeight() / 2;
            Line line = new Line(startX, startY, endX, endY);
            line.setStroke(Color.TRANSPARENT);
            line.setStrokeWidth(SIZE / 15);
            getChildren().add(line);
            PathTransition pathTransition = new PathTransition(Duration.millis(600), line, imageView);
            if (index < path.size() - 1) {
                PathTransition subPathTransition = setupPathTransitions(index + 1);
                pathTransition.setOnFinished(event -> doAction(subPathTransition));
            } else return pathTransition;
            return pathTransition;
        }

        private void showPath() {
            imageView.setFitHeight(SIZE - 2);
            imageView.setFitWidth(SIZE - 2);
            getChildren().add(imageView);
            PathTransition pathTransition = setupPathTransitions(1);
            pathTransition.play();
            Line line = (Line) pathTransition.getPath();
            line.setStroke(Color.ORANGE);
            Circle circle = new Circle(line.getEndX(), line.getEndY(), SIZE / 10, Color.ORANGE);
            getChildren().add(circle);
        }

        private void solveKnightsTour(Cell location) {
            location.setVisited(true);
            path.add(location);
            isSolved = isSolved();
            if (isSolved) return;
            List<Cell> validMoves = getValidMoves(location);
            if (!validMoves.isEmpty()) {
                Collections.shuffle(validMoves);
                Collections.sort(validMoves);
                for (Cell validMove : validMoves) {
                    solveKnightsTour(validMove);
                    if (isSolved) return;
                }
            }
            location.setVisited(false);
            path.remove(location);
        }
    }

    class Cell extends Pane implements Comparable<Cell> {

        private boolean isVisited;
        private int priority;

        public Cell(double width, double height) {
            this.setPrefWidth(width);
            this.setPrefHeight(height);
        }

        @Override
        public int compareTo(Cell that) {
            Integer thisCellPriority = this.getPriority();
            Integer thatCellPriority = that.getPriority();
            return thisCellPriority.compareTo(thatCellPriority);
        }

        public boolean isVisited() {
            return isVisited;
        }

        public void setVisited(boolean visited) {
            isVisited = visited;
        }

        @Override
        public String toString() {
            return "Cell[" + GridPane.getRowIndex(this) + ", " + GridPane.getColumnIndex(this) + "]";
        }

        private int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
    }
}

