package chapter_16;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * (Slide show) Programming Exercise 15.30 developed a slide show using images.
 * Rewrite that program to develop a slide show using text files. Suppose ten text
 * files named slide0.txt, slide1.txt, . . . , and slide9.txt are stored in the text directory.
 * Each slide displays the text from one file. Each slide is shown for one second,
 * and the slides are displayed in order. When the last slide finishes, the first
 * slide is redisplayed, and so on. Use a text area to display the slide.
 */
public class PE_16_28_Slide_show extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        SlideShowPane pane = new SlideShowPane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Chapter16_28");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class SlideShowPane extends Pane {

        private final TextArea textArea = new TextArea();
        private int slideNumber = 0;
        private final String[] slideTexts = new String[10];

        public SlideShowPane() {
            getChildren().add(textArea);
            loadSlideText();
            textArea.setText(slideTexts[0]);
            textArea.setWrapText(true);
            KeyFrame keyFrame = new KeyFrame(
                    Duration.millis(1000),
                    event -> nextSlide()
            );
            Timeline timeline = new Timeline(keyFrame);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }

        private void loadSlideText() {
            for (int i = 0; i < slideTexts.length; i++) {
                File file = new File("resources/text/slide" + i + ".txt");
                try {
                    Scanner scanner = new Scanner(file);
                    slideTexts[i] = "";
                    while (scanner.hasNext()) {
                        slideTexts[i] += scanner.nextLine() + "\n";
                    }
                } catch (FileNotFoundException e) {
                    slideTexts[i] = "Slide text not found!";

                }
            }
        }

        private void nextSlide() {
            slideNumber = (slideNumber + 1) % 10;
            textArea.setText(slideTexts[slideNumber]);
        }
    }
}
