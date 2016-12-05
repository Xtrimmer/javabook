package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Use ScrollBar and Slider) Write a program that uses scroll bars or sliders
 * to select the color for a text, as shown in Figure 16.43b. Four horizontal
 * scroll bars are used for selecting the colors: red, green, blue, and opacity
 * percentages.
 */
public class PE_16_17_Use_ScrollBar_and_Slider extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        ColorSelectionPane pane = new ColorSelectionPane();
        Scene scene = new Scene(pane, 300, 200);

        primaryStage.setTitle("Exercise16_17");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class ColorSelectionPane extends BorderPane {
        private Text text;
        private ScrollBar scrollBarRed;
        private ScrollBar scrollBarGreen;
        private ScrollBar scrollBarBlue;
        private ScrollBar scrollBarOpacity;
        private double red = 0;
        private double blue = 0;
        private double green = 0;
        private double opacity = 1;

        public ColorSelectionPane() {
            initializeInstanceVariables();
            createLayout();
            addScrollEvents();
        }

        private void addScrollEvents() {
            scrollBarRed.valueProperty().addListener((observable, oldValue, newValue) -> {
                red = (double) newValue;
                text.setFill(Color.color(red, green, blue, opacity));
            });
            scrollBarBlue.valueProperty().addListener((observable, oldValue, newValue) -> {
                blue = (double) newValue;
                text.setFill(Color.color(red, green, blue, opacity));
            });
            scrollBarGreen.valueProperty().addListener((observable, oldValue, newValue) -> {
                green = (double) newValue;
                text.setFill(Color.color(red, green, blue, opacity));
            });
            scrollBarOpacity.valueProperty().addListener((observable, oldValue, newValue) -> {
                opacity = (double) newValue;
                text.setFill(Color.color(red, green, blue, opacity));
            });
        }

        private void createLayout() {
            StackPane stackPane = new StackPane(text);
            stackPane.setPadding(new Insets(25));

            GridPane gridPane = new GridPane();
            gridPane.addColumn(0,
                    new Label("Red"), new Label("Green"),
                    new Label("Blue"), new Label("Opacity")
            );
            gridPane.addColumn(1, scrollBarRed, scrollBarGreen, scrollBarBlue, scrollBarOpacity);
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setVgap(5);
            gridPane.setHgap(10);

            setTop(stackPane);
            setCenter(gridPane);
        }

        private void initializeInstanceVariables() {
            text = new Text("Show Colors");
            text.setFont(Font.font(30));

            scrollBarRed = new ScrollBar();
            scrollBarRed.setMax(1);
            scrollBarRed.setValue(red);

            scrollBarGreen = new ScrollBar();
            scrollBarGreen.setMax(1);
            scrollBarGreen.setValue(blue);

            scrollBarBlue = new ScrollBar();
            scrollBarBlue.setMax(1);
            scrollBarBlue.setValue(green);

            scrollBarOpacity = new ScrollBar();
            scrollBarOpacity.setMax(1);
            scrollBarOpacity.setValue(opacity);
        }
    }
}
