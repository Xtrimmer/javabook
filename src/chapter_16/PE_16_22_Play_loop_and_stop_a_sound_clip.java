package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

/**
 * (Play, loop, and stop a sound clip) Write a program that meets the following
 * requirements:
 *
 * - Get an audio file from the class directory using AudioClip.
 * - Place three buttons labeled Play, Loop, and Stop, as shown in Figure 16.46a.
 * - If you click the Play button, the audio file is played once. If you click the
 *   Loop button, the audio file keeps playing repeatedly. If you click the Stop
 *   button, the playing stops.
 */
public class PE_16_22_Play_loop_and_stop_a_sound_clip extends Application {

    private AudioClip audioClip;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        audioClip = new AudioClip("file:///C:/Windows/Media/Alarm01.wav");
        Scene scene = new Scene(createButtonPane());
        primaryStage.setTitle("Exercise16_22");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createButtonPane() {
        Button buttonPlay = new Button("Play");
        Button buttonLoop = new Button("Loop");
        Button buttonStop = new Button("Stop");

        buttonPlay.setPrefWidth(75);
        buttonLoop.setPrefWidth(75);
        buttonStop.setPrefWidth(75);

        buttonPlay.setOnAction(event -> play());
        buttonLoop.setOnAction(event -> loop());
        buttonStop.setOnAction(event -> audioClip.stop());

        HBox hBox = new HBox(5, buttonPlay, buttonLoop, buttonStop);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(25));
        return hBox;
    }

    private void loop() {
        audioClip.stop();
        audioClip.setCycleCount(AudioClip.INDEFINITE);
        audioClip.play();
    }

    private void play() {
        audioClip.stop();
        audioClip.setCycleCount(1);
        audioClip.play();
    }
}
