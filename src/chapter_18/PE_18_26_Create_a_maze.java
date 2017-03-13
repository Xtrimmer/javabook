package chapter_18;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Create a maze) Write a program that will find a path in a maze, as shown in
 * Figure 18.13a. The maze is represented by an 8 * 8 board. The path must meet
 * the following conditions:
 *
 * - The path is between the upper-left corner cell and the lower-right corner cell
 *   in the maze.
 * - The program enables the user to place or remove a mark on a cell. A path
 *   consists of adjacent unmarked cells. Two cells are said to be adjacent if they
 *   are horizontal or vertical neighbors, but not if they are diagonal neighbors.
 * - The path does not contain cells that form a square. The path in Figure 18.13b,
 *   for example, does not meet this condition. (The condition makes a path easy
 *   to identify on the board.)
 */
public class PE_18_26_Create_a_maze extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MazePane pane = new MazePane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise18_26");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class MazePane extends BorderPane {
        private static final int GRID_SIZE = 8;
        private static final double CELL_SIZE = 35;
        private Cell[][] cells;
        private Text text;
        private Button buttonFind;
        private Button buttonClear;

        MazePane() {
            this.setTop(createMessagePane());
            this.setBottom(createButtonPane());
            this.setCenter(createGridPane());
        }

        private void clearPath() {
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int column = 0; column < GRID_SIZE; column++) {
                    cells[row][column].setPath(false);
                    cells[row][column].setMarked(false);
                }
            }
        }

        private HBox createButtonPane() {
            buttonFind = new Button("Find Path");
            buttonClear = new Button("Clear Path");
            HBox hBox = new HBox(buttonFind, buttonClear);
            hBox.setPadding(new Insets(10));
            hBox.setSpacing(5);
            hBox.setAlignment(Pos.CENTER);
            buttonFind.setOnAction(event -> findPath());
            buttonClear.setOnAction(event -> clearPath());
            return hBox;
        }

        private GridPane createGridPane() {
            cells = new Cell[GRID_SIZE][GRID_SIZE];
            GridPane gridPane = new GridPane();
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int column = 0; column < GRID_SIZE; column++) {
                    Cell cell = new Cell(CELL_SIZE, CELL_SIZE);
                    cells[row][column] = cell;
                    gridPane.add(cell, column, row);
                }
            }
            cells[0][0].disableClickEvent();
            cells[7][7].disableClickEvent();
            gridPane.setVgap(1);
            gridPane.setHgap(1);
            gridPane.setGridLinesVisible(true);
            gridPane.setPadding(new Insets(0, 10, 0, 10));
            return gridPane;
        }

        private StackPane createMessagePane() {
            text = new Text();
            StackPane stackPane = new StackPane(text);
            stackPane.setPadding(new Insets(10));
            return stackPane;
        }

        private boolean doesFormSquare(int row, int column) {
            int count = 0;
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (isOutOfBounds(row + x, column + y)) continue;
                    if (cells[row + x][column + y].isPath) count++;
                    if (count > 2) return true;
                }
            }
            return false;
        }

        private void findPath() {
            boolean pathFound = findPath(0, 0);
            if (pathFound) text.setText("Path Found");
            else text.setText("Path Not Found");
        }

        private boolean findPath(int row, int column) {
            cells[row][column].setPath(true);
            if (isEnd(row, column)) {
                return true;
            }
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (!isAdjacent(x, y)) continue;
                    if (isValidPath(row, column, x, y)) {
                        continue;
                    }
                    System.out.println("Moving to " + (row + x) + " " + (column + y));
                    if (findPath(row + x, column + y)) {
                        return true;
                    }
                }
            }
            cells[row][column].setPath(false);
            return false;
        }

        private boolean isAdjacent(int x, int y) {
            return !((x * x) == (y * y));
        }

        private boolean isEnd(int row, int column) {
            return row == GRID_SIZE - 1 && column == GRID_SIZE - 1;
        }

        private boolean isOutOfBounds(int row, int column) {
            return row < 0 || row > 7 || column < 0 || column > 7;
        }

        private boolean isValidPath(int row, int column, int x, int y) {
            return (isOutOfBounds(row + x, column + y)
                    || cells[row + x][column + y].isMarked
                    || cells[row + x][column + y].isPath)
                    || doesFormSquare(row + x, column + y);
        }
    }

    private class Cell extends Pane {
        private boolean isMarked;
        private boolean isPath;

        Cell(double width, double height) {
            isMarked = false;
            this.setPrefWidth(width);
            this.setPrefHeight(height);
            this.setOnMouseClicked(event -> setMarked(!isMarked));
        }

        private void clearX() {
            this.getChildren().clear();
        }

        private void drawX() {
            Line line1 = new Line();
            Line line2 = new Line();

            line1.startXProperty().bind(this.widthProperty().multiply(0.1));
            line1.startYProperty().bind(this.heightProperty().multiply(0.1));
            line1.endXProperty().bind(this.widthProperty().multiply(0.9));
            line1.endYProperty().bind(this.heightProperty().multiply(0.9));

            line2.startXProperty().bind(this.widthProperty().multiply(0.9));
            line2.startYProperty().bind(this.heightProperty().multiply(0.1));
            line2.endXProperty().bind(this.widthProperty().multiply(0.1));
            line2.endYProperty().bind(this.heightProperty().multiply(0.9));

            this.getChildren().addAll(line1, line2);
        }

        void disableClickEvent() {
            this.setOnMouseClicked(null);
        }

        void setMarked(boolean marked) {
            isMarked = marked;
            if (marked) drawX();
            else clearX();
        }

        void setPath(boolean path) {
            BackgroundFill fill = new BackgroundFill(Color.RED, null, null);
            Background background = new Background(fill);
            isPath = path;
            if (path) this.setBackground(background);
            else this.setBackground(Background.EMPTY);
        }
    }
}
