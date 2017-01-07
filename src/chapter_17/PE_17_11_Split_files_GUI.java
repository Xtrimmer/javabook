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
 * (Split files GUI) Rewrite Exercise 17.10 with a GUI, as shown in Figure 17.21a.
 */
public class PE_17_11_Split_files_GUI extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane pane = new FileSplitterPane();
        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise17_11");
        primaryStage.show();
    }

    private class FileSplitterPane extends BorderPane {
        private static final String MESSAGE = "If you split a file named temp.txt into 3 smaller files,"
                + "\nthe three smaller files are temp.txt.1, temp.txt.2, and temp.txt.3.";
        private final Button button = new Button("Start");
        private final TextField textFieldFilePath = new TextField();
        private final TextField textFieldPieces = new TextField();
        private final FileSplitter fileSplitter = new FileSplitter();

        public FileSplitterPane() {
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
            File sourceFile = new File(textFieldFilePath.getText());
            int pieces = parseInt(textFieldPieces, 2);
            fileSplitter.setSourceFile(sourceFile);
            fileSplitter.setPieces(pieces);
            fileSplitter.splitFile();
            fileSplitter.showFileSizes();
        }
    }

    private class FileSplitter {
        private File sourceFile;
        private int pieces;

        public void setPieces(int pieces) {
            this.pieces = pieces;
        }

        public void setSourceFile(File sourceFile) {
            this.sourceFile = sourceFile;
        }

        public void showFileSizes() {
            System.out.println("InputFile size: " + sourceFile.length() + " bytes");
            for (int i = 1; i <= pieces; i++) {
                File file = new File(sourceFile.getAbsolutePath() + "." + i);
                System.out.println("File " + i + " size: " + file.length() + " bytes");
            }
        }

        public void splitFile() {
            long pieceSize = (long) Math.ceil(1.0 * sourceFile.length() / pieces);
            String fileName = sourceFile.getAbsolutePath();
            try (
                    BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(sourceFile))
            ) {
                int value;
                for (long i = 1; i <= pieces; i++) {
                    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileName + "." + i));
                    long bytesWritten = 0;
                    while (bytesWritten++ < pieceSize && (value = inputStream.read()) != -1) {
                        outputStream.write(value);
                    }
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
