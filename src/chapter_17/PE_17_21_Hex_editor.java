package chapter_17;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;

/**
 * (Binary editor) Write a GUI application that lets the user enter a file name in the
 * text field and press the Enter key to display its binary representation in a text area.
 * The user can also modify the binary code and save it back to the file, as shown in
 * Figure 17.23a.
 */
public class PE_17_21_Hex_editor extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        EditorPane pane = new EditorPane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise17_21");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class EditorPane extends BorderPane {
        final TextField textField = new TextField();
        final TextArea textArea = new TextArea();
        final Button button = new Button("Save the change");
        final HexEditor hexEditor = new HexEditor();

        public EditorPane() {
            button.setDisable(true);
            setTop(createFileInputPane());
            setCenter(createFileEditPane());
            setBottom(createButtonPane());
            textField.setOnAction(this::loadHexFromFile);
            button.setOnAction(this::saveHex);
        }

        private StackPane createButtonPane() {
            StackPane stackPane = new StackPane(button);
            stackPane.setPadding(new Insets(5));
            return stackPane;
        }

        private ScrollPane createFileEditPane() {
            ScrollPane scrollPane = new ScrollPane(textArea);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            textArea.setWrapText(true);
            return scrollPane;
        }

        private HBox createFileInputPane() {
            HBox hBox = new HBox(new Label("Enter a file:"), textField);
            HBox.setHgrow(textField, Priority.ALWAYS);
            hBox.setPadding(new Insets(5));
            hBox.setSpacing(5);
            return hBox;
        }

        private void loadHexFromFile(ActionEvent event) {
            hexEditor.setFile(textField.getText());
            textArea.setText(hexEditor.getHex());
            button.setDisable(false);
        }

        private void saveHex(ActionEvent actionEvent) {
            hexEditor.setHex(textArea.getText());
        }
    }

    private class HexEditor {
        File file;
        String hexString = "";

        public String getHex() {
            return getHexFromFile();
        }

        public void setHex(String hexString) {
            try (HexOutputStream outputStream = new HexOutputStream(file)) {
                outputStream.writeHex(hexString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void setFile(String fileName) {
            file = new File(fileName);
        }

        private String getHex(int value) {
            return String.format("%02X", value);
        }

        private String getHexFromFile() {
            StringBuilder hex = new StringBuilder();
            try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                int value;
                while ((value = inputStream.read()) != -1) {
                    hex.append(getHex(value));
                }
            } catch (FileNotFoundException e) {
                System.out.println("The file " + file.getName() + " cannot be found");
                System.exit(1);
            } catch (IOException e) {
                System.out.println("There was a problem reading the file " + file.getName());
                System.exit(2);
            }
            return hex.toString();
        }
    }

    private class HexOutputStream implements AutoCloseable {
        private int hexCount = 0;
        private int byteValue = 0;
        private FileOutputStream outputStream;

        public HexOutputStream(File file) throws FileNotFoundException {
            outputStream = new FileOutputStream(file);
        }

        @Override
        public void close() throws Exception {
            if (hexCount > 0) {
                byteValue = byteValue << 4;
                outputStream.write(byteValue);
            }
            outputStream.close();
        }

        public void writeHex(String bit) throws IOException {
            for (int i = 0; i < bit.length(); i++) {
                writeHex(bit.charAt(i));
            }
        }

        public void writeHex(char hex) throws IOException {
            if (!((hex >= '0' && hex <= '9') || (hex >= 'A' && hex <= 'F')))
                throw new IllegalArgumentException("only hex values allowed");
            addHexToByte(Integer.valueOf("" + hex, 16));
        }

        private void addHexToByte(int i) throws IOException {
            hexCount++;
            byteValue = (byteValue << 4);
            byteValue += i;
            if (hexCount == 2) {
                hexCount = 0;
                outputStream.write(byteValue);
                byteValue = 0;
            }
        }
    }
}
