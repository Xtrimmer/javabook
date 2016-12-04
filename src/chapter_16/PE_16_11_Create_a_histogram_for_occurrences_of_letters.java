package chapter_16;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * (Create a histogram for occurrences of letters) Write a program that reads a
 * file and displays a histogram to show the occurrences of each letter in the file,
 * as shown in Figure 16.40b. The file name is entered from a text field. Pressing
 * the Enter key on the text field causes the program to start to read and process
 * the file and displays the histogram. The histogram is displayed in the center of the
 * window. Define a class named Histogram that extends Pane. The class contains
 * the property counts that is an array of 26 elements. counts[0] stores the
 * number of A, counts[1] the number of B, and so on. The class also contains a
 * setter method for setting a new counts and displaying the histogram for the new
 * counts.
 */
public class PE_16_11_Create_a_histogram_for_occurrences_of_letters extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        HistogramPane pane = new HistogramPane();
        Scene scene = new Scene(pane, 400, 300);

        primaryStage.setTitle("Exercise16_11");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class HistogramPane extends BorderPane {
        private final int LETTER_COUNT = 26;
        private final Histogram histogram = new Histogram();
        private final FileSelectionPane fileSelectionPane = new FileSelectionPane();

        public HistogramPane() {
            setCenter(histogram);
            setBottom(fileSelectionPane);
            fileSelectionPane.setButtonAction(event -> drawHistogramFromFile());
            fileSelectionPane.setTextFieldAction(event -> drawHistogramFromFile());
        }

        private void drawHistogramFromFile() {
            File file = fileSelectionPane.getFile();
            int[] counts = countLetterOccurrences(file);
            histogram.setCounts(counts);
        }

        private int[] countLetterOccurrences(File file) {
            int[] counts = new int[LETTER_COUNT];
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    char[] chars = line.toCharArray();
                    for (char aChar : chars) {
                        if (Character.isAlphabetic(aChar)) {
                            counts[Character.toUpperCase(aChar) - 'A']++;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                fileSelectionPane.setTextFieldText("ERROR: File Not Found");
            }
            return counts;
        }

        private class Histogram extends Pane {
            private int[] counts;

            public Histogram() {
                counts = new int[LETTER_COUNT];
            }

            public void setCounts(int[] counts) {
                this.counts = counts;
                displayHistogram();
            }

            private void displayHistogram() {
                getChildren().clear();
                addColumnLabels();
                addColumns();
            }

            private void addColumns() {
                int maxCountValue = 0;
                for (int count : counts) {
                    maxCountValue = Math.max(count, maxCountValue);
                }
                double maxColumnHeight = getHeight() * 16 / 20.0;
                double rectangleX = getWidth() * 10 / 280.0;
                double minColumnY = getHeight() * 1 / 20.0;
                double rectangleWidth = getWidth() * 8 / 280.0;
                double strokeWidth = getWidth() / 200;
                for (int i = 0; i < counts.length; i++) {
                    double heightPercentage = counts[i] / (double) maxCountValue;
                    double rectangleHeight = maxColumnHeight * heightPercentage;
                    double rectangleYOffset = maxColumnHeight - rectangleHeight;
                    Rectangle rectangle = new Rectangle(
                            rectangleX * (i + 1),
                            minColumnY + rectangleYOffset,
                            rectangleWidth,
                            rectangleHeight
                    );
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setFill(Color.BLUE);
                    rectangle.setStrokeWidth(strokeWidth);
                    getChildren().add(rectangle);
                }
            }

            private void addColumnLabels() {
                Font font = new Font("Lucida Console", getWidth() / 33.5);
                Text text = new Text("A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
                text.setFont(font);
                text.setX(getWidth() * (10 / 280.0));
                text.setY(getHeight() * 18 / 20.0);
                getChildren().add(text);
            }
        }

        private class FileSelectionPane extends HBox {
            private final Label label;
            private final TextField textField;
            private final Button button;

            public FileSelectionPane() {
                label = new Label("Filename");
                textField = new TextField();
                textField.setPromptText("File Name");
                button = new Button("View");
                setHgrow(textField, Priority.ALWAYS);
                getChildren().addAll(label, textField, button);
            }

            public void setButtonAction(EventHandler<ActionEvent> actionEventEventHandler) {
                button.setOnAction(actionEventEventHandler);
            }

            public void setTextFieldAction(EventHandler<ActionEvent> actionEventEventHandler) {
                textField.setOnAction(actionEventEventHandler);
            }

            public void setTextFieldText(String text) {
                textField.setText(text);
            }

            public File getFile() {
                return new File(textField.getText());
            }
        }
    }
}
