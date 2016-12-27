package chapter_16;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * (Game: connect four) Programming Exercise 8.20 enables two players to play
 * the connect-four game on the console. Rewrite a GUI version for the program,
 * as shown in Figure 16.49c. The program enables two players to place red and
 * yellow discs in turn. To place a disk, the player needs to click an available cell.
 * An available cell is unoccupied and its downward neighbor is occupied. The
 * program flashes the four winning cells if a player wins and reports no winners if
 * all cells are occupied with no winners.
 */
public class PE_16_31_Game_connect_four extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        ConnectFourPane pane = new ConnectFourPane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise16_31");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class ConnectFourPane extends BorderPane {
        private final int ROW_COUNT = 6;
        private final int COLUMN_COUNT = 7;
        private final int SEQUENCE_LENGTH = 4;
        private final Label label = new Label("It's Red's turn");
        private final char[][] board;
        private final Circle[][] circles = new Circle[ROW_COUNT][COLUMN_COUNT];
        private final Circle[] pattern = new Circle[SEQUENCE_LENGTH];
        private char player = 'R';

        public ConnectFourPane() {
            board = initializeBoard(ROW_COUNT, COLUMN_COUNT);
            setCenter(createBoard());
            setBottom(createMessagePane());
        }

        public boolean hasWinner() {
            return hasHorizontalWin()
                    || hasVerticalWin()
                    || hasPositiveDiagonalWin()
                    || hasNegativeDiagonalWin();
        }

        private void changePlayer() {
            if (player == 'Y') player = 'R';
            else player = 'Y';
            label.setText("It's " + (player == 'R' ? "Red" : "Yellow") + "'s turn");
        }

        private GridPane createBoard() {
            GridPane gridPane = new GridPane();
            gridPane.setStyle("-fx-background-color: blue");
            gridPane.setVgap(5);
            gridPane.setHgap(5);
            gridPane.setPadding(new Insets(5));
            gridPane.setAlignment(Pos.CENTER);
            for (int row = 0; row < ROW_COUNT; row++) {
                for (int col = 0; col < COLUMN_COUNT; col++) {
                    Circle circle = new Circle(25);
                    circle.setFill(Color.LIGHTBLUE);
                    circle.setStroke(Color.BLACK);
                    circle.setStrokeWidth(2);
                    circle.setOnMouseClicked(event -> play(event));
                    gridPane.add(circle, col, row);
                    circles[row][col] = circle;
                }
            }
            return gridPane;
        }

        private StackPane createMessagePane() {
            StackPane stackPane = new StackPane(label);
            label.setTextFill(Color.WHITE);
            label.setFont(Font.font(20));
            stackPane.setPadding(new Insets(5));
            stackPane.setStyle("-fx-background-color: blue");
            return stackPane;
        }

        private void endGame(String message) {
            for (Circle[] row : circles) {
                for (Circle circle : row) {
                    circle.setOnMouseClicked(null);
                }
            }
            label.setText(message);
        }

        private void flashWinningCells() {
            KeyFrame keyFrame = new KeyFrame(Duration.millis(500), event -> {
                for (Circle circle : pattern) {
                    if (circle.getStroke().equals(Color.BLACK)) {
                        circle.setStroke(Color.WHITE);
                    } else {
                        circle.setStroke(Color.BLACK);
                    }
                }
            });
            Timeline timeline = new Timeline(keyFrame);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }

        private boolean hasHorizontalWin() {
            boolean isConsecutiveFour;
            int offset = SEQUENCE_LENGTH - 1;
            for (int row = 0; row < ROW_COUNT; row++) {
                for (int col = 0; col < COLUMN_COUNT - offset; col++) {
                    if (board[row][col] == ' ') continue;
                    isConsecutiveFour = true;
                    pattern[0] = circles[row][col];
                    for (int i = 1; i < SEQUENCE_LENGTH; i++) {
                        isConsecutiveFour &= board[row][col] == board[row][col + i];
                        pattern[i] = circles[row][col + i];
                    }
                    if (isConsecutiveFour) return true;
                }
            }
            return false;
        }

        private boolean hasNegativeDiagonalWin() {
            boolean isConsecutiveFour;
            int offset = SEQUENCE_LENGTH - 1;
            for (int row = SEQUENCE_LENGTH; row < board.length; row++) {
                for (int col = 0; col < board[row].length - offset; col++) {
                    if (board[row][col] == ' ') continue;
                    isConsecutiveFour = true;
                    pattern[0] = circles[row][col];
                    for (int i = 1; i < SEQUENCE_LENGTH; i++) {
                        isConsecutiveFour &= board[row][col] == board[row - i][col + i];
                        pattern[i] = circles[row - i][col + i];
                    }
                    if (isConsecutiveFour) return true;
                }
            }
            return false;
        }

        private boolean hasPositiveDiagonalWin() {
            boolean isConsecutiveFour;
            int offset = SEQUENCE_LENGTH - 1;
            for (int row = 0; row < board.length - offset; row++) {
                for (int col = 0; col < board[row].length - offset; col++) {
                    if (board[row][col] == ' ') continue;
                    isConsecutiveFour = true;
                    pattern[0] = circles[row][col];
                    for (int i = 1; i < SEQUENCE_LENGTH; i++) {
                        isConsecutiveFour &= board[row][col] == board[row + i][col + i];
                        pattern[i] = circles[row + i][col + i];
                    }
                    if (isConsecutiveFour) return true;
                }
            }
            return false;
        }

        private boolean hasVerticalWin() {
            boolean isConsecutiveFour;
            int offset = SEQUENCE_LENGTH - 1;
            for (int row = 0; row < board.length - offset; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    if (board[row][col] == ' ') continue;
                    isConsecutiveFour = true;
                    pattern[0] = circles[row][col];
                    for (int i = 1; i < SEQUENCE_LENGTH; i++) {
                        isConsecutiveFour &= board[row][col] == board[row + i][col];
                        pattern[i] = circles[row + i][col];
                    }
                    if (isConsecutiveFour) return true;
                }
            }
            return false;
        }

        private char[][] initializeBoard(int rows, int columns) {
            char[][] board = new char[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    board[row][col] = ' ';
                }
            }
            return board;
        }

        private boolean isBoardFull() {
            for (int col = 0; col < COLUMN_COUNT; col++) {
                if (board[0][col] == ' ') return false;
            }
            return true;
        }

        private boolean isValidPlay(int rowIndex, int columnIndex) {
            return (rowIndex == ROW_COUNT - 1 && board[rowIndex][columnIndex] == ' ')
                    || (board[rowIndex][columnIndex] == ' ' && board[rowIndex + 1][columnIndex] != ' ');
        }

        private void play(MouseEvent event) {
            Circle circle = (Circle) event.getSource();
            int rowIndex = GridPane.getRowIndex(circle);
            int columnIndex = GridPane.getColumnIndex(circle);
            if (isValidPlay(rowIndex, columnIndex)) {
                board[rowIndex][columnIndex] = player;
                circle.setFill(player == 'R' ? Color.RED : Color.YELLOW);
                if (hasWinner()) {
                    endGame((player == 'R' ? "Red" : "Yellow") + " wins!");
                    flashWinningCells();
                    return;
                }
                if (isBoardFull()) {
                    endGame("No winner!");
                    return;
                }
                changePlayer();
            }
        }
    }
}
