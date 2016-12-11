package chapter_16;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * (Count-down stopwatch) Write a program that allows the user to enter time in
 * seconds in the text field and press the Enter key to count down the seconds,
 * as shown in Figure 16.45d. The remaining seconds are redisplayed every
 * one second. When the seconds are expired, the program starts to play music
 * continuously.
 */
public class PE_16_21_Count_down_stopwatch extends Application {

    private MediaPlayer mediaPlayer;
    private TextField textField;
    private int count;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initializeMediaPlayer();
        Scene scene = new Scene(getTextPane());
        primaryStage.setTitle("Exercise16_21");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Pane getTextPane() {
        textField = new TextField();
        textField.setFont(Font.font(50));
        textField.setPrefWidth(300);
        textField.setAlignment(Pos.CENTER);
        textField.setOnAction(event -> startCountdown(textField.getText()));
        return new StackPane(textField);
    }

    private void initializeMediaPlayer() {
        Media music = new Media(
                "http://cs.armstrong.edu/liang/common/audio/anthem/anthem6.mp3");
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    private void startCountdown(String number) {
        try {
            mediaPlayer.stop();
            textField.setPromptText(null);
            count = (int) Double.parseDouble(number);
            KeyFrame keyFrame = new KeyFrame(
                    Duration.millis(1000), event -> updateTextField());
            Timeline timeline = new Timeline(keyFrame);
            timeline.setCycleCount(count);
            timeline.play();
        } catch (NumberFormatException e) {
            textField.setPromptText("Error");
        }
    }

    private void updateTextField() {
        count--;
        textField.setText(count + "");
        if (count == 0) {
            mediaPlayer.play();
        }
    }
}
