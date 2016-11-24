package chapter_15;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * (Create a simple calculator) Write a program to perform addition, subtraction,
 * multiplication, and division, as shown in Figure 15.25a.
 */
public class PE_15_04_Create_a_simple_calculator extends Application {
    private final TextField tfNumber1 = new TextField();
    private final TextField tfNumber2 = new TextField();
    private final TextField tfResult = new TextField();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Label lblNumber1 = new Label("Number 1:");
        Label lblNumber2 = new Label("Number 2:");
        Label lblResult = new Label("Result:");

        tfNumber1.setPrefColumnCount(4);
        tfNumber2.setPrefColumnCount(4);
        tfResult.setPrefColumnCount(4);
        tfResult.setEditable(false);

        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(event -> calculate('+'));
        Button btnSubtract = new Button("Subtract");
        btnSubtract.setOnAction(event -> calculate('-'));
        Button btnMultiply = new Button("Multiply");
        btnMultiply.setOnAction(event -> calculate('*'));
        Button btnDivide = new Button("Divide");
        btnDivide.setOnAction(event -> calculate('/'));

        HBox top = new HBox(10, lblNumber1, tfNumber1, lblNumber2, tfNumber2, lblResult, tfResult);
        HBox bottom = new HBox(10, btnAdd, btnSubtract, btnMultiply, btnDivide);
        bottom.setAlignment(Pos.CENTER);
        VBox pane = new VBox(10, top, bottom);
        pane.setPadding(new Insets(5));
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise15_04");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculate(char operator) {
        double result = 0;
        double value1 = getValue(tfNumber1);
        double value2 = getValue(tfNumber2);
        switch (operator) {
            case '+':
                result = value1 + value2;
                break;
            case '-':
                result = value1 - value2;
                break;
            case '*':
                result = value1 * value2;
                break;
            case '/':
                result = value1 / value2;
                break;
        }
        tfResult.setText("" + result);
    }

    private double getValue(TextField textfield) {
        try {
            return Double.parseDouble(textfield.getText());
        } catch (NumberFormatException ex) {
            textfield.setText("0");
            return 0;
        }
    }
}
