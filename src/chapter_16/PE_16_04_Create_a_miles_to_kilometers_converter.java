package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * (Create a miles/kilometers converter) Write a program that converts miles and
 * kilometers, as shown in Figure 16.37b. If you enter a value in the Mile text field
 * and press the Enter key, the corresponding kilometer measurement is displayed
 * in the Kilometer text field. Likewise, if you enter a value in the Kilometer text
 * field and press the Enter key, the corresponding miles is displayed in the Mile
 * text field.
 */
public class PE_16_04_Create_a_miles_to_kilometers_converter extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        DistanceConversionPane pane = new DistanceConversionPane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise16_04");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class DistanceConversionPane extends GridPane {

        static final double KILOMETERS_PER_MILE = 1.609344;

        final TextField textFieldMile;
        final TextField textFieldKilometer;

        DistanceConversionPane() {
            textFieldMile = new TextField("1");
            textFieldMile.setAlignment(Pos.BASELINE_RIGHT);
            textFieldKilometer = new TextField(KILOMETERS_PER_MILE + "");
            textFieldKilometer.setAlignment(Pos.BASELINE_RIGHT);

            add(new Label("Mile"), 0, 0);
            add(new Label("Kilometer"), 0, 1);
            add(textFieldMile, 1, 0);
            add(textFieldKilometer, 1, 1);

            setPadding(new Insets(10));
            setHgap(5);
            setVgap(5);

            textFieldMile.setOnAction(event -> MilesToKilometers());
            textFieldKilometer.setOnAction(event -> KilometersToMiles());
        }

        private void MilesToKilometers() {
            if (isNumeric(textFieldMile.getText())) {
                double kilometers = Double.valueOf(textFieldMile.getText())
                        * KILOMETERS_PER_MILE;
                textFieldKilometer.setText("" + kilometers);
            }
        }

        private void KilometersToMiles() {
            if (isNumeric(textFieldKilometer.getText())) {
                double miles = Double.valueOf(textFieldKilometer.getText())
                        / KILOMETERS_PER_MILE;
                textFieldMile.setText("" + miles);
            }
        }

        private boolean isNumeric(String s) {
            return s.matches("[-+]?\\d*\\.?\\d+");
        }
    }
}
