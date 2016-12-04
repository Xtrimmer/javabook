package chapter_16;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Select a font) Write a program that can dynamically change the font of a text
 * in a label displayed on a stack pane. The text can be displayed in bold and
 * italic at the same time. You can select the font name or font size from combo
 * boxes, as shown in Figure 16.42a. The available font names can be obtained
 * using Font.getFamilies(). The combo box for the font size is initialized
 * with numbers from 1 to 100.
 */
public class PE_16_14_Select_a_font extends Application {
    private FontBoldItalicPane fontBoldItalicPane;
    private FontNameSizePane fontNameSizePane;
    private TextPane textPane;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        textPane = new TextPane();
        fontBoldItalicPane = new FontBoldItalicPane();
        fontNameSizePane = new FontNameSizePane();
        fontNameSizePane.setFontName(textPane.getFont().getName());
        fontNameSizePane.setFontSize((int) textPane.getFont().getSize());

        BorderPane pane = new BorderPane();
        pane.setTop(fontNameSizePane);
        pane.setCenter(textPane);
        pane.setBottom(fontBoldItalicPane);
        Scene scene = new Scene(pane, 550, 200);

        fontNameSizePane.setNameChangeAction(
                event -> textPane.setFontName(fontNameSizePane.getFontName()));
        fontNameSizePane.setSizeChangeAction(
                event -> textPane.setFontSize(fontNameSizePane.getFontSize()));
        fontBoldItalicPane.setBoldChangeAction(
                event -> textPane.setBold(fontBoldItalicPane.isBoldChecked()));
        fontBoldItalicPane.setItalicChangeAction(
                event -> textPane.setItalic(fontBoldItalicPane.isItalicChecked()));

        primaryStage.setTitle("Exercise16_14");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class FontBoldItalicPane extends HBox {
        private final CheckBox checkBoxBold;
        private final CheckBox checkBoxItalic;

        public FontBoldItalicPane() {
            checkBoxBold = new CheckBox("Bold");
            checkBoxItalic = new CheckBox("Italic");
            getChildren().addAll(checkBoxBold, checkBoxItalic);
            setAlignment(Pos.CENTER);
            setSpacing(5);
            setPadding(new Insets(5));
        }

        public void setBoldChangeAction(EventHandler<ActionEvent> action) {
            checkBoxBold.setOnAction(action);
        }

        public void setItalicChangeAction(EventHandler<ActionEvent> action) {
            checkBoxItalic.setOnAction(action);
        }

        public boolean isBoldChecked() {
            return checkBoxBold.isSelected();
        }

        public boolean isItalicChecked() {
            return checkBoxItalic.isSelected();
        }
    }

    private class FontNameSizePane extends HBox {
        private final ComboBox<String> comboBoxName;
        private final ComboBox<Integer> comboBoxSize;

        public FontNameSizePane() {
            comboBoxName = new ComboBox<>();
            comboBoxName.getItems().addAll(Font.getFamilies());

            comboBoxSize = new ComboBox<>();
            ObservableList<Integer> sizeList = comboBoxSize.getItems();
            for (int i = 1; i <= 100; i++) {
                sizeList.add(i);
            }

            getChildren().addAll(
                    new Label("Font Name"), comboBoxName,
                    new Label("Font Size"), comboBoxSize
            );
            setAlignment(Pos.CENTER);
            setSpacing(5);
            setPadding(new Insets(5));
        }

        public int getFontSize() {
            return comboBoxSize.getValue();
        }

        public void setFontSize(int size) {
            comboBoxSize.setValue(size);
        }

        public String getFontName() {
            return comboBoxName.getValue();
        }

        public void setFontName(String name) {
            comboBoxName.setValue(name);
        }

        public void setNameChangeAction(EventHandler<ActionEvent> action) {
            comboBoxName.setOnAction(action);
        }

        public void setSizeChangeAction(EventHandler<ActionEvent> action) {
            comboBoxSize.setOnAction(action);
        }
    }

    private class TextPane extends StackPane {
        private final Text text;
        private boolean isBold;
        private boolean isItalic;
        private String fontName;
        private int fontSize;

        public TextPane() {
            text = new Text("Programming is fun");
            fontName = text.getFont().getFamily();
            fontSize = (int) text.getFont().getSize();
            getChildren().add(text);
        }

        public void setFontName(String fontName) {
            this.fontName = fontName;
            updateFont();
        }

        public void setFontSize(int fontSize) {
            this.fontSize = fontSize;
            updateFont();
        }

        public void setBold(boolean value) {
            isBold = value;
            updateFont();
        }

        public void setItalic(boolean value) {
            isItalic = value;
            updateFont();
        }

        public Font getFont() {
            return text.getFont();
        }

        private void updateFont() {
            text.setFont(Font.font(
                    fontName,
                    isBold ? FontWeight.BOLD : FontWeight.NORMAL,
                    isItalic ? FontPosture.ITALIC : FontPosture.REGULAR,
                    fontSize
            ));
        }
    }
}
