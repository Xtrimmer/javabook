package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * (Demonstrate TextField properties) Write a program that sets the horizontal
 * alignment and column-size properties of a text field dynamically, as shown in
 * Figure 16.38a.
 */
public class PE_16_06_Demonstrate_TextField_properties extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Label labelTextField = new Label("TextField");
        TextField textField = new TextField("JavaFX");

        HBox hBoxTop = new HBox(labelTextField, textField);
        hBoxTop.setAlignment(Pos.CENTER);
        hBoxTop.setSpacing(5);
        hBoxTop.setPadding(new Insets(0, 0, 10, 0));

        RadioButton radioButtonLeft = new RadioButton("Left");
        RadioButton radioButtonCenter = new RadioButton("Center");
        RadioButton radioButtonRight = new RadioButton("Right");

        radioButtonLeft.setOnAction(event -> textField.setAlignment(Pos.BASELINE_LEFT));
        radioButtonCenter.setOnAction(event -> textField.setAlignment(Pos.BASELINE_CENTER));
        radioButtonRight.setOnAction(event -> textField.setAlignment(Pos.BASELINE_RIGHT));

        ToggleGroup group = new ToggleGroup();
        radioButtonLeft.setToggleGroup(group);
        radioButtonCenter.setToggleGroup(group);
        radioButtonRight.setToggleGroup(group);

        radioButtonLeft.setSelected(true);

        Label labelColumnSize = new Label("Column Size");

        TextField textFieldColumnSize = new TextField(TextField.DEFAULT_PREF_COLUMN_COUNT + "");
        textFieldColumnSize.setPrefColumnCount(4);
        textFieldColumnSize.setAlignment(Pos.BASELINE_RIGHT);
        textFieldColumnSize.setOnAction(event -> textField.setPrefColumnCount(getInt(textFieldColumnSize.getText())));

        HBox hBoxBottom = new HBox(
                radioButtonLeft, radioButtonCenter, radioButtonRight, labelColumnSize, textFieldColumnSize
        );
        hBoxBottom.setAlignment(Pos.CENTER);
        hBoxBottom.setSpacing(10);

        BorderPane pane = new BorderPane(hBoxTop, null, null, hBoxBottom, null);
        pane.setPadding(new Insets(10));
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise16_06");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private int getInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return TextField.DEFAULT_PREF_COLUMN_COUNT;
        }
    }
}
