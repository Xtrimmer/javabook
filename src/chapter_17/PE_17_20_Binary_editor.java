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
public class PE_17_20_Binary_editor extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        EditorPane pane = new EditorPane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise17_20");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class EditorPane extends BorderPane {
        final TextField textField = new TextField();
        final TextArea textArea = new TextArea();
        final Button button = new Button("Save the change");
        final BitEditor bitEditor = new BitEditor();

        public EditorPane() {
            button.setDisable(true);
            setTop(createFileInputPane());
            setCenter(createFileEditPane());
            setBottom(createButtonPane());
            textField.setOnAction(this::LoadBitsFromFile);
            button.setOnAction(this::saveBits);
        }

        private void LoadBitsFromFile(ActionEvent event) {
            bitEditor.setFile(textField.getText());
            textArea.setText(bitEditor.getBits());
            button.setDisable(false);
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

        private void saveBits(ActionEvent actionEvent) {
            bitEditor.setBits(textArea.getText());
        }
    }

    private class BitEditor {
        private File file;
        private String bits = "";

        public String getBits() {
            return getBitStringFromFile();
        }

        public void setBits(String bitString) {
            try (BitOutputStream outputStream = new BitOutputStream(file)) {
                outputStream.writeBit(bitString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getBits(int value) {
            int mask = 0b1;
            String binaryString = "";
            for (int i = 0; i < 8; i++) {
                int bit = value & mask;
                binaryString = bit + binaryString;
                value = value >> 1;
            }
            return binaryString;
        }

        public void setFile(String fileName) {
            file = new File(fileName);
        }

        private String getBitStringFromFile() {
            StringBuilder bits = new StringBuilder();
            try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                int value;
                while ((value = inputStream.read()) != -1) {
                    bits.append(getBits(value));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bits.toString();
        }
    }

    private class BitOutputStream implements AutoCloseable {
        private int bitCount = 0;
        private int byteValue = 0;
        private FileOutputStream outputStream;

        public BitOutputStream(File file) throws FileNotFoundException {
            outputStream = new FileOutputStream(file);
        }

        @Override
        public void close() throws IOException {
            if (bitCount > 0) {
                byteValue = byteValue << (8 - bitCount);
                outputStream.write(byteValue);
            }
            outputStream.close();
        }

        public void writeBit(String bit) throws IOException {
            for (int i = 0; i < bit.length(); i++) {
                writeBit(bit.charAt(i));
            }
        }

        public void writeBit(char bit) throws IOException {
            if (!(bit == '0' || bit == '1')) throw new IllegalArgumentException("only 0 or 1 allowed");
            addBitToByte(bit - '0');
        }

        private void addBitToByte(int i) throws IOException {
            bitCount++;
            byteValue = (byteValue << 1);
            byteValue += i;
            if (bitCount == 8) {
                bitCount = 0;
                outputStream.write(byteValue);
                byteValue = 0;
            }
        }
    }
}
