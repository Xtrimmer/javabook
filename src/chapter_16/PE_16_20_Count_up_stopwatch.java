package chapter_16;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * (Count-up stopwatch) Write a program that simulates a stopwatch, as shown
 * in Figure 16.45a. When the user clicks the Start button, the button’s label is
 * changed to Pause, as shown in Figure 16.45b. When the user clicks the Pause
 * button, the button’s label is changed to Resume, as shown in Figure 16.45c. The
 * Clear button resets the centiseconds to 0 and resets the button’s label to Start.
 */
public class PE_16_20_Count_up_stopwatch extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        TimerPane pane = new TimerPane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise16_20");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class TimerPane extends BorderPane {
        Label labelTime;
        Button buttonStart;
        Button buttonClear;
        Timeline timeline;
        int centiseconds;

        public TimerPane() {
            initializeInstanceVariables();
            setCenter(createTimePane());
            setBottom(createButtonPane());
        }

        private HBox createButtonPane() {
            HBox hBox = new HBox(5, buttonStart, buttonClear);
            hBox.setPadding(new Insets(5));
            hBox.setAlignment(Pos.CENTER);
            return hBox;
        }

        private StackPane createTimePane() {
            StackPane stackPane = new StackPane(labelTime);
            stackPane.setPadding(new Insets(25, 50, 25, 50));
            return stackPane;
        }

        private void initializeInstanceVariables() {
            centiseconds = 0;
            labelTime = new Label();
            labelTime.setFont(Font.font(50));
            updateTimeDisplay();
            buttonStart = new Button("Start");
            buttonStart.setPrefWidth(75);
            buttonStart.setOnAction(event -> startTime());
            buttonClear = new Button("Clear");
            buttonClear.setPrefWidth(75);
            buttonClear.setOnAction(event -> clearTime());
            KeyFrame keyFrame = new KeyFrame(Duration.millis(10), event -> incrementTime());
            timeline = new Timeline(keyFrame);
            timeline.setCycleCount(Timeline.INDEFINITE);
        }

        private void incrementTime() {
            centiseconds++;
            updateTimeDisplay();
        }

        private void updateTimeDisplay() {
            String time = String.format("%02d:", centiseconds / 6000)
                    + String.format("%02d:", centiseconds / 100 % 60)
                    + String.format("%02d", centiseconds % 100);
                    labelTime.setText(time);
        }

        private void clearTime() {
            timeline.stop();
            buttonStart.setText("Start");
            buttonStart.setOnAction(event -> startTime());
            centiseconds = 0;
            updateTimeDisplay();
        }

        private void startTime() {
            timeline.play();
            buttonStart.setText("Pause");
            buttonStart.setOnAction(event -> pauseTime());
        }

        private void pauseTime() {
            timeline.pause();
            buttonStart.setText("Resume");
            buttonStart.setOnAction(event -> resumeTime());
        }

        private void resumeTime() {
            timeline.play();
            buttonStart.setText("Pause");
            buttonStart.setOnAction(event -> pauseTime());
        }
    }
}
