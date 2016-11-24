package chapter_15;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * (Slide show) Twenty-five slides are stored as image files (slide0.jpg, slide1
 * .jpg, . . . , slide24.jpg) in the image directory downloadable along with the
 * source code in the book. The size of each image is 800 * 600. Write a program
 * that automatically displays the slides repeatedly. Each slide is shown for
 * two seconds. The slides are displayed in order. When the last slide finishes, the
 * first slide is redisplayed, and so on. Click to pause if the animation is currently
 * playing. Click to resume if the animation is currently paused.
 */
public class PE_15_30_Slide_show extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        SlideShowPane pane = new SlideShowPane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Chapter15_30");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class SlideShowPane extends Pane{

        private final ImageView imageView = new ImageView();
        private int slideNumber = 0;

        public SlideShowPane() {
            getChildren().add(imageView);
            imageView.setImage(new Image("image/slide0.jpg"));
            KeyFrame keyFrame = new KeyFrame(
                    Duration.millis(5000),
                    event -> nextSlide()
            );
            Timeline timeline = new Timeline(keyFrame);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }

        private void nextSlide() {
            slideNumber = (slideNumber + 1) % 24;
            imageView.setImage(new Image("image/slide" + slideNumber + ".jpg"));
        }
    }
}
