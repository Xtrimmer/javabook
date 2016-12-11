package chapter_16;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * (Create an image animator with audio) Create animation in Figure 16.46b to
 * meet the following requirements:
 *
 * - Allow the user to specify the animation speed in a text field.
 * - Get the number of images and image’s file-name prefix from the user. For
 *   example, if the user enters n for the number of images and L for the image
 *   prefix, then the files are L1.gif, L2.gif, and so on, to Ln.gif. Assume that the
 *   images are stored in the image directory, a subdirectory of the program’s
 *   class directory. The animation displays the images one after the other.
 * - Allow the user to specify an audio file URL. The audio is played while the
 *   animation runs.
 */
public class PE_16_23_Create_an_image_animator_with_audio extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        AnimatorPane pane = new AnimatorPane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise16_23");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class AnimatorPane extends BorderPane {

        private TextField textFieldSpeed;
        private TextField textFieldPrefix;
        private TextField textFieldNumber;
        private TextField textFieldAudioURL;
        private ImageView imageView;
        private AudioClip audioClip;
        private int currentImage = 1;

        public AnimatorPane() {
            setTop(createButtonPane());
            setCenter(createImagePane());
            setBottom(createInputPane());
        }

        private HBox createButtonPane() {
            Button buttonStart = new Button("Start Animation");
            buttonStart.setOnAction(event -> startAnimation());
            HBox hBox = new HBox(buttonStart);
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5));
            return hBox;
        }

        private StackPane createImagePane() {
            imageView = new ImageView(new Image("image/L1.gif"));
            StackPane pane = new StackPane(imageView);
            pane.setPrefWidth(750);
            pane.setPrefHeight(650);
            return pane;
        }

        private GridPane createInputPane() {
            textFieldSpeed = new TextField("200");
            textFieldPrefix = new TextField("L");
            textFieldNumber = new TextField("52");
            textFieldAudioURL = new TextField("http://cs.armstrong.edu/liang/common/audio/anthem/anthem2.mp3");
            GridPane gridPane = new GridPane();
            gridPane.addRow(0, new Label("Enter information for animation"));
            gridPane.addRow(1, new Label("Animation speed in milliseconds"), textFieldSpeed);
            gridPane.addRow(2, new Label("Image file prefix"), textFieldPrefix);
            gridPane.addRow(3, new Label("Number of Images"), textFieldNumber);
            gridPane.addRow(4, new Label("Audio file URL"), textFieldAudioURL);
            gridPane.setHgap(5);
            gridPane.setVgap(5);
            gridPane.setPadding(new Insets(5));

            ColumnConstraints c0 = new ColumnConstraints(
                    USE_COMPUTED_SIZE, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, false);
            ColumnConstraints c1 = new ColumnConstraints(
                    USE_COMPUTED_SIZE, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true);
            gridPane.getColumnConstraints().addAll(c0, c1);
            return gridPane;
        }

        private int parseInt(TextField textField, int defaultValue) {
            try {
                int speed = (int) Double.parseDouble(textField.getText());
                textField.setText(speed + "");
                return speed;
            } catch (NumberFormatException e) {
                textField.setText(defaultValue + "");
                return defaultValue;
            }
        }

        private void reset() {
            imageView.setImage(new Image("image/L1.gif"));
            currentImage = 1;
            audioClip.stop();
        }

        private void startAnimation() {
            audioClip = new AudioClip(textFieldAudioURL.getText());
            int speed = parseInt(textFieldSpeed, 200);
            int imageCount = parseInt(textFieldNumber, 52);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(speed), event -> updateImage());
            Timeline timeLine = new Timeline(keyFrame);
            timeLine.setCycleCount(imageCount - 1);
            timeLine.play();
            timeLine.setOnFinished(event -> reset());
            audioClip.play();
        }

        private void updateImage() {
            String url = "image/" + textFieldPrefix.getText() + ++currentImage + ".gif";
            Image nextImage = new Image(url);
            imageView.setImage(nextImage);
        }
    }
}
