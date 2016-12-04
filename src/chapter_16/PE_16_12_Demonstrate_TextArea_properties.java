package chapter_16;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * (Demonstrate TextArea properties) Write a program that demonstrates the
 * properties of a text area. The program uses a check box to indicate whether the
 * text is wrapped onto next line, as shown in Figure 16.41a.
 */
public class PE_16_12_Demonstrate_TextArea_properties extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TextPane textPane = new TextPane();
        setDefaultText(textPane);

        HBox textControlBar = getTextControlBar(textPane);

        BorderPane pane = new BorderPane();
        pane.setCenter(textPane);
        pane.setBottom(textControlBar);
        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setTitle("Exercise16_12");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setDefaultText(TextPane textPane) {
        textPane.setText("Four score and seven years ago our fathers brought forth on this continent, a new nation, \n" +
                "conceived in Liberty, and dedicated to the proposition that all men are created equal.\n" +
                "\n" +
                "Now we are engaged in a great civil war, testing whether that nation, or any nation so \n" +
                "conceived and dedicated, can long endure. We are met on a great battle-field of that war. \n" +
                "We have come to dedicate a portion of that field, as a final resting place for those who here gave their \n" +
                "lives that that nation might live. It is altogether fitting and proper that we should do this.\n" +
                "\n" +
                "But, in a larger sense, we can not dedicate -- we can not consecrate -- we can not hallow -- this ground. \n" +
                "The brave men, living and dead, who struggled here, have consecrated it, far above our poor power to add or detract. \n" +
                "The world will little note, nor long remember what we say here, but it can never forget what they did here. \n" +
                "It is for us the living, rather, to be dedicated here to the unfinished work which they who fought here have thus \n" +
                "far so nobly advanced. It is rather for us to be here dedicated to the great task remaining before us -- that from these \n" +
                "honored dead we take increased devotion to that cause for which they gave the last full measure of devotion -- that \n" +
                "we here highly resolve that these dead shall not have died in vain -- that this nation, under God, shall have a new \n" +
                "birth of freedom -- and that government of the people, by the people, for the people, shall not perish from the earth. ");
    }

    private HBox getTextControlBar(TextPane textPane) {
        CheckBox checkBoxEditable = new CheckBox("Editable");
        CheckBox checkBoxWrap = new CheckBox("Wrap");

        checkBoxEditable.setSelected(true);
        checkBoxEditable.setOnAction(event -> textPane.setEditable(checkBoxEditable.isSelected()));
        checkBoxWrap.setOnAction(event -> textPane.setWrap(checkBoxWrap.isSelected()));

        HBox hBox = new HBox(checkBoxEditable, checkBoxWrap);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
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

        public void setEditable(boolean value) {
            textArea.setEditable(value);
        }

        public void setWrap(boolean value) {
            textArea.setWrapText(value);
        }
    }
}
