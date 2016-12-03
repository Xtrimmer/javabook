package chapter_16;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * (Text viewer) Write a program that displays a text file in a text area, as shown
 * in Figure 16.40a. The user enters a file name in a text field and clicks the View
 * button; the file is then displayed in a text area.
 */
public class PE_16_10_Text_viewer extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        TextViewerPane pane = new TextViewerPane();
        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setTitle("Exercise16_10");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class TextViewerPane extends BorderPane {
        final TextPane textPane = new TextPane();
        final FileSelectionPane fileSelectionPane = new FileSelectionPane();

        public TextViewerPane() {
            setCenter(textPane);
            setBottom(fileSelectionPane);
            fileSelectionPane.setAction(event -> displayTextFromFile());
        }

        private void displayTextFromFile() {
            textPane.setText("");
            File file = fileSelectionPane.getFile();
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNext()) {
                    textPane.appendText(scanner.nextLine() + "\n");
                }
            } catch (FileNotFoundException e) {
                textPane.appendText("Exception - File not Found");
            }
        }

        private class TextPane extends ScrollPane {
            private final TextArea textArea;

            public TextPane() {
                textArea = new TextArea();
                setFitToHeight(true);
                setFitToWidth(true);
                setContent(textArea);
            }

            public void setText(String text) {
                textArea.setText(text);
            }

            public void appendText(String text) {
                textArea.appendText(text);
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

            public void setAction(EventHandler<ActionEvent> actionEvent) {
                button.setOnAction(actionEvent);
            }

            public File getFile() {
                return new File(textField.getText());
            }
        }
    }
}
