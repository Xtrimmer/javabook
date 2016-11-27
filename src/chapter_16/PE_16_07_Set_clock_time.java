package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import textbook_listings.ClockPane;

/**
 * (Set clock time) Write a program that displays a clock and sets the time with the
 * input from three text fields, as shown in Figure 16.38b. Use the ClockPane in
 * Listing 14.21. Resize the clock to the center of the pane.
 */
public class PE_16_07_Set_clock_time extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        ClockPane clockPane = new ClockPane();
        TimeControlPane timeControlPane = new TimeControlPane(clockPane);
        BorderPane pane = new BorderPane(clockPane);
        pane.setBottom(timeControlPane);
        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setTitle("Exercise16_07");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class TimeControlPane extends HBox {

        TimeControlPane(ClockPane clock) {

            setAlignment(Pos.CENTER);
            setSpacing(5);
            setPadding(new Insets(10));

            TextField textFieldHour = new TextField(clock.getHour() + "");
            TextField textFieldMinute = new TextField(clock.getMinute() + "");
            TextField textFieldSecond = new TextField(clock.getSecond() + "");

            textFieldHour.setPrefColumnCount(4);
            textFieldMinute.setPrefColumnCount(4);
            textFieldSecond.setPrefColumnCount(4);

            textFieldHour.setOnAction(event -> clock.setHour(getValue(textFieldHour, 1, 12)));
            textFieldMinute.setOnAction(event -> clock.setMinute(getValue(textFieldMinute, 0, 59)));
            textFieldSecond.setOnAction(event -> clock.setSecond(getValue(textFieldSecond, 0, 59)));

            getChildren().addAll(
                    new Label("Hour"), textFieldHour,
                    new Label("Minute"), textFieldMinute,
                    new Label("Second"), textFieldSecond
            );
        }

        private int getValue(TextField textField, int min, int max) {
            int value;
            try {
                value = Integer.parseInt(textField.getText());
                if (value >= min && value <= max) {
                    return value;
                } else {
                    textField.setText(min + "");
                    return min;
                }
            } catch (Exception e) {
                textField.setText(min + "");
                return min;
            }
        }
    }
}
