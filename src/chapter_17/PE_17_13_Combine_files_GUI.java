package chapter_17;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;

/**
 * (Combine files GUI) Rewrite Exercise 17.12 with a GUI, as shown in
 * Figure 17.21b.
 */
public class PE_17_13_Combine_files_GUI extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new FileCombinerPane();
        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise17_13");
        primaryStage.show();
    }

    private class FileCombinerPane extends BorderPane {
        private static final String MESSAGE = "If the base file is named temp.txt with 3 pieces,"
                + "\ntemp.txt.1, temp.txt.2, and temp.txt.3 are combined into temp.txt.";
        private final Button button = new Button("Start");
        private final TextField textFieldFilePath = new TextField();
        private final TextField textFieldPieces = new TextField();
        private final FileCombiner fileCombiner = new FileCombiner();

        public FileCombinerPane() {
            setPadding(new Insets(5));
            setTop(new Label(MESSAGE));
            setCenter(createInputPane());
            setBottom(createButtonPane());
            button.setOnAction(event -> start());
        }

        private StackPane createButtonPane() {
            StackPane stackPane = new StackPane();
            stackPane.setPadding(new Insets(5));
            stackPane.getChildren().add(button);
            return stackPane;
        }

        private GridPane createInputPane() {
            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(5, 0, 0, 0));
            gridPane.setHgap(5);
            gridPane.setVgap(5);
            gridPane.add(new Label("Enter a file:"), 0, 0);
            gridPane.add(new Label("Specify the number of smaller files:"), 0, 1);
            gridPane.add(textFieldFilePath, 1, 0);
            gridPane.add(textFieldPieces, 1, 1);
            return gridPane;
        }

        private int parseInt(TextField textField, int defaultValue) {
            try {
                return (int) Double.parseDouble(textField.getText());
            } catch (NumberFormatException e) {
                textField.setText(defaultValue + "");
                return defaultValue;
            }
        }

        private void start() {
            String sourceFile = textFieldFilePath.getText();
            int pieces = parseInt(textFieldPieces, 2);
            fileCombiner.setFileInfo(sourceFile, pieces);
            fileCombiner.combineFiles();
        }
    }

    class FileCombiner {
        private File[] sourceFiles;
        private File destinationFile;

        public void combineFiles() {
            try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destinationFile))) {
                for (File sourceFile : sourceFiles) {
                    BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(sourceFile));
                    int data;
                    while ((data = inputStream.read()) != -1) {
                        outputStream.write(data);
                    }
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void setFileInfo(String fileName, int fileCount) {
            sourceFiles = new File[fileCount];
            destinationFile = new File(fileName);
            for (int fileIndex = 0; fileIndex < fileCount; fileIndex++) {
                sourceFiles[fileIndex] = new File(fileName + "." + (fileIndex + 1));
                if (!sourceFiles[fileIndex].exists()) {
                    System.out.println("Not all source files can be found.");
                    System.exit(1);
                }
            }
        }
    }
}
