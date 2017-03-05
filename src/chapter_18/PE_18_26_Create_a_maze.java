package chapter_18;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
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
public class PE_18_26_Create_a_maze extends Application{
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

    private class MazePane extends BorderPane{
        private static final int GRID_SIZE = 8;
        private static final double CELL_SIZE = 35;
        private Cell[][] cells;
        Label label;

        public MazePane(){
            cells = new Cell[GRID_SIZE][GRID_SIZE];
            this.setTop(createMessagePane());
        }

        private Label createMessagePane() {
            label = new Label("asfhas; dsajfh asdfafh asfuhas pfasihf puh");
            label.setPadding(new Insets(10));
            label.setTextAlignment(TextAlignment.CENTER);
            return label;
        }
    }

    private class Cell extends Pane{
        private boolean isMarked;
        private boolean isPath;

        public boolean isPath() {
            return isPath;
        }

        public void setPath(boolean path) {
            BackgroundFill fill = new BackgroundFill(Color.RED, null, null);
            Background background = new Background(fill);
            isPath = path;
            if (path) this.setBackground(background);
            else this.setBackground(Background.EMPTY);
        }

        public Cell(double width, double height){
            isMarked = false;
            this.setWidth(width);
            this.setHeight(height);
        }

        public boolean isMarked() {
            return isMarked;
        }

        public void setMarked(boolean marked) {
            isMarked = marked;
            if (marked) drawX();
            else clearX();
        }

        private void drawX(){
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

        private void clearX(){
            this.getChildren().clear();
        }
    }
}
