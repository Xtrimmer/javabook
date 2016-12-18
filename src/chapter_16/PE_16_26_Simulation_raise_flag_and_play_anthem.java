package chapter_16;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * (Simulation: raise flag and play anthem) Write a program that displays a flag
 * rising up, as shown in Figure 15.14. As the national flag rises, play the national
 * anthem. (You may use a flag image and anthem audio file from Listing 16.15.)
 */
public class PE_16_26_Simulation_raise_flag_and_play_anthem extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        FlagRisingPane pane = new FlagRisingPane();
        Scene scene = new Scene(pane, 250, 200);

        primaryStage.setTitle("Exercise16_26");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class FlagRisingPane extends Pane {
        public FlagRisingPane() {

            // Add an image view and add it to pane
            ImageView imageView = new ImageView("image/us.gif");
            getChildren().add(imageView);

            // Create an audio clip
            AudioClip ac = new AudioClip(
                    "http://cs.armstrong.edu/liang/common/audio/anthem/anthem6.mp3");

            // Create a path transition
            PathTransition pt = new PathTransition(Duration.millis(10000),
                    new Line(100, 200, 100, 0), imageView);
            pt.setCycleCount(5);
            pt.setOnFinished(event -> ac.stop());

            pt.play(); // Start animation
            ac.play(); // Start audio
        }
    }
}
