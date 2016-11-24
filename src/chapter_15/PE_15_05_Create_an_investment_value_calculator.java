package chapter_15;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * (Create an investment-value calculator) Write a program that calculates the
 * future value of an investment at a given interest rate for a specified number of
 * years. The formula for the calculation is:
 *
 *      futureValue = investmentAmount * (1 + monthlyInterestRate)^years*12
 *
 * Use text fields for the investment amount, number of years, and annual interest
 * rate. Display the future amount in a text field when the user clicks the Calculate
 * button, as shown in Figure 15.25b.
 */
public class PE_15_05_Create_an_investment_value_calculator extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Label labelInvestmentAmount = new Label("Investment Amount:");
        Label labelNumberOfYears = new Label("Number of Years:");
        Label labelAnnualInterestRate = new Label("Annual Interest Rate:");
        Label labelFutureValue = new Label("Future Value:");

        TextField textFieldInvestmentAmount = new TextField("10000");
        textFieldInvestmentAmount.setAlignment(Pos.BASELINE_RIGHT);
        TextField textFieldNumberOfYears = new TextField("4");
        textFieldNumberOfYears.setAlignment(Pos.BASELINE_RIGHT);
        TextField textFieldAnnualInterestRate = new TextField("3.25");
        textFieldAnnualInterestRate.setAlignment(Pos.BASELINE_RIGHT);
        TextField textFieldFutureValue = new TextField();
        textFieldFutureValue.setAlignment(Pos.BASELINE_RIGHT);
        textFieldFutureValue.setEditable(false);

        Button buttonCalculate = new Button("Calculate");
        GridPane.setHalignment(buttonCalculate, HPos.RIGHT);
        buttonCalculate.setOnAction(event -> {
            double investmentAmount = getDoubleFromText(textFieldInvestmentAmount);
            double numberOfYears = getDoubleFromText(textFieldNumberOfYears);
            double annualInterestRate = getDoubleFromText(textFieldAnnualInterestRate);
            double futureValue = investmentAmount * Math.pow(1 + (annualInterestRate / 1200), numberOfYears * 12);
            futureValue = Math.round(futureValue * 100) / 100.0;
            textFieldFutureValue.setText("$" + futureValue);
        });

        GridPane pane = new GridPane();
        pane.add(labelInvestmentAmount, 0, 0);
        pane.add(labelNumberOfYears, 0, 1);
        pane.add(labelAnnualInterestRate, 0, 2);
        pane.add(labelFutureValue, 0, 3);
        pane.add(textFieldInvestmentAmount, 1, 0);
        pane.add(textFieldNumberOfYears, 1, 1);
        pane.add(textFieldAnnualInterestRate, 1, 2);
        pane.add(textFieldFutureValue, 1, 3);
        pane.add(buttonCalculate, 1, 4);
        pane.setHgap(15);
        pane.setVgap(5);
        pane.setPadding(new Insets(15));
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise15_05");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double getDoubleFromText(TextField textField) {
        double value;
        try {
            value = Double.parseDouble(textField.getText());
        } catch (NumberFormatException e) {
            textField.setText("0");
            return 0;
        }
        return value;
    }
}
