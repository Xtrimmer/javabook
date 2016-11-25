package chapter_16;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Use radio buttons) Write a GUI program as shown in Figure 16.36a. You can
 * use buttons to move the message to the left and right and use the radio buttons to
 * change the color for the message displayed.
 */
public class PE_16_01_Use_radio_buttons extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        TextPane textPane = new TextPane();
        ColorSelectionPane colorSelectionPane = new ColorSelectionPane(textPane);
        PositionSelectionPane positionSelectionPane = new PositionSelectionPane(textPane);
        BorderPane pane = new BorderPane(textPane, colorSelectionPane, null, positionSelectionPane, null);
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise16_01");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class TextPane extends Pane {
        private final Text text;

        TextPane() {
            setStyle("-fx-border-color: black");
            setPrefWidth(400);
            setPrefHeight(100);
            text = new Text(50, 50, "Programming is fun");
            text.setFont(new Font("Arial Black", 20));
            text.setStroke(Color.BLACK);
            text.setStrokeWidth(0.4);
            getChildren().add(text);
        }

        void moveTextHorizontal(double value) {
            text.setX(text.getX() + value);
        }

        void setTextColor(Color color) {
            text.setFill(color);
        }
    }

    private class ColorSelectionPane extends HBox {
        final RadioButton radioButtonRed;
        final RadioButton radioButtonYellow;
        final RadioButton radioButtonBlack;
        final RadioButton radioButtonOrange;
        final RadioButton radioButtonGreen;

        ColorSelectionPane(TextPane textPane) {
            ToggleGroup group = new ToggleGroup();

            radioButtonRed = new RadioButton("Red");
            radioButtonRed.setToggleGroup(group);
            radioButtonRed.setOnAction(event -> textPane.setTextColor(Color.RED));

            radioButtonYellow = new RadioButton("Yellow");
            radioButtonYellow.setToggleGroup(group);
            radioButtonYellow.setOnAction(event -> textPane.setTextColor(Color.YELLOW));

            radioButtonBlack = new RadioButton("Black");
            radioButtonBlack.setToggleGroup(group);
            radioButtonBlack.setOnAction(event -> textPane.setTextColor(Color.BLACK));
            radioButtonBlack.setSelected(true);

            radioButtonOrange = new RadioButton("Orange");
            radioButtonOrange.setToggleGroup(group);
            radioButtonOrange.setOnAction(event -> textPane.setTextColor(Color.ORANGE));

            radioButtonGreen = new RadioButton("Green");
            radioButtonGreen.setToggleGroup(group);
            radioButtonGreen.setOnAction(event -> textPane.setTextColor(Color.GREEN));

            setAlignment(Pos.CENTER);
            setSpacing(10);
            getChildren().addAll(
                    radioButtonRed, radioButtonYellow, radioButtonBlack, radioButtonOrange, radioButtonGreen
            );
        }
    }

    private class PositionSelectionPane extends HBox {
        final Button leftButton;
        final Button rightButton;

        PositionSelectionPane(TextPane textPane) {
            leftButton = new Button("<=");
            leftButton.setOnAction(event -> textPane.moveTextHorizontal(-10));
            rightButton = new Button("=>");
            rightButton.setOnAction(event -> textPane.moveTextHorizontal(10));
            setAlignment(Pos.CENTER);
            setSpacing(10);
            getChildren().addAll(leftButton, rightButton);
        }
    }
}
