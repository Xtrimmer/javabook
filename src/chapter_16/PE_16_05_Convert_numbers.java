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
 * (Convert numbers) Write a program that converts between decimal, hex, and binary
 * numbers, as shown in Figure 16.37c. When you enter a decimal value in the decimalvalue
 * text field and press the Enter key, its corresponding hex and binary numbers are
 * displayed in the other two text fields. Likewise, you can enter values in the other fields
 * and convert them accordingly. (Hint: Use the Integer.parseInt(s, radix)
 * method to parse a string to a decimal and use Integer.toHexString(decimal)
 * and Integer.toBinaryString(decimal) to obtain a hex number or a binary
 * number from a decimal.)
 */
public class PE_16_05_Convert_numbers extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        NumberConversionPane pane = new NumberConversionPane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise16_05");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class NumberConversionPane extends GridPane {
        static final int DECIMAL = 0;
        static final int HEX = 1;
        static final int BINARY = 2;
        private final TextField[] textFields;

        NumberConversionPane() {
            textFields = new TextField[3];
            for (int i = 0; i < textFields.length; i++) {
                textFields[i] = new TextField("0");
                textFields[i].setAlignment(Pos.BASELINE_RIGHT);
                int finalI = i;
                textFields[i].setOnAction(event -> convert(finalI));
            }

            setPadding(new Insets(10));
            setHgap(5);
            setVgap(5);

            addColumn(0, new Label("Decimal"), new Label("Hex"), new Label("Binary"));
            addColumn(1, textFields[DECIMAL], textFields[HEX], textFields[BINARY]);
        }

        private void convert(int numberType) {
            int[] radix = {10, 16, 2};
            String value = textFields[numberType].getText();
            if (isNumeric(value, numberType)) {
                int number = 0;
                try {
                    number = Integer.parseInt(value, radix[numberType]);
                } catch (NumberFormatException ignored) {
                }
                textFields[DECIMAL].setText(number + "");
                textFields[HEX].setText(Integer.toHexString(number).toUpperCase());
                textFields[BINARY].setText(Integer.toBinaryString(number));
            }
        }

        private boolean isNumeric(String s, int numberType) {
            if (numberType == DECIMAL) return s.matches("[-+]?\\d*\\.?\\d+");
            if (numberType == HEX) return s.toUpperCase().matches("[-+]?[0-9A-F]+");
            return numberType == BINARY && s.matches("[01]+");
        }
    }
}
