package chapter_16;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * (Compare loans with various interest rates) Rewrite Programming Exercise
 * 5.21 to create a GUI, as shown in Figure 16.41b. Your program should let the
 * user enter the loan amount and loan period in the number of years from text
 * fields, and it should display the monthly and total payments for each interest
 * rate starting from 5 percent to 8 percent, with increments of one-eighth, in a
 * text area.
 */
public class PE_16_13_Compare_loans_with_various_interest_rates extends Application {

    private TextPane textPane;
    private ControlBar controlBar;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        textPane = new TextPane();
        textPane.setEditable(false);
        controlBar = new ControlBar();
        controlBar.setButtonEvent(event -> displayLoanInfo());

        BorderPane pane = new BorderPane();
        pane.setCenter(textPane);
        pane.setTop(controlBar);
        Scene scene = new Scene(pane, 550, 400);

        primaryStage.setTitle("Exercise16_13");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayLoanInfo() {
        double InterestRateStart = 5;
        double InterestRateEnd = 8;
        double loanAmount = controlBar.getAmount();
        double numberOfYears = controlBar.getYears();
        textPane.setText("Interest Rate     Monthly Payment     Total Payment\n");
        double interestRate = InterestRateStart;
        while (interestRate <= InterestRateEnd) {
            double monthlyInterestRate = interestRate / 1200.0;
            double monthlyPayment = loanAmount * monthlyInterestRate /
                    (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
            double totalPayment = monthlyPayment * numberOfYears * 12;
            textPane.appendText(String.format("%-18.3f", interestRate));
            textPane.appendText(String.format("%-20.2f", monthlyPayment));
            textPane.appendText(String.format("%.2f", totalPayment));
            textPane.appendText("\n");
            interestRate += (1 / 8.0);
        }
    }

    private class TextPane extends ScrollPane {
        private final TextArea textArea;

        public TextPane() {
            textArea = new TextArea();
            Font font = new Font("Lucida Console", 16);
            textArea.setFont(font);
            setFitToHeight(true);
            setFitToWidth(true);
            setContent(textArea);
        }

        public void setText(String text) {
            textArea.setText(text);
        }

        public void appendText(String text) {
            textArea.appendText(text);
        }

        public void setEditable(boolean value) {
            textArea.setEditable(value);
        }
    }

    private class ControlBar extends HBox {
        private final TextField textFieldAmount;
        private final TextField textFieldYears;
        private final Button buttonShowTable;

        public ControlBar() {
            textFieldAmount = new TextField();
            textFieldYears = new TextField();
            textFieldAmount.setPrefColumnCount(5);
            textFieldYears.setPrefColumnCount(2);
            buttonShowTable = new Button("Show Table");
            setSpacing(5);
            setAlignment(Pos.CENTER_LEFT);
            setPadding(new Insets(5));
            getChildren().addAll(
                    new Label("Loan Amount"), textFieldAmount,
                    new Label("Number of Years"), textFieldYears,
                    buttonShowTable
            );
        }

        public void setButtonEvent(EventHandler<ActionEvent> actionEventEventHandler) {
            buttonShowTable.setOnAction(actionEventEventHandler);
        }

        public double getAmount() {
            try {
                return Double.parseDouble(textFieldAmount.getText());
            } catch (Exception e) {
                textFieldAmount.setText("0");
                return 0;
            }
        }

        public int getYears() {
            try {
                int years = (int)Double.parseDouble(textFieldYears.getText());
                textFieldYears.setText(years + "");
                return years;
            } catch (Exception e) {
                textFieldYears.setText("0");
                return 0;
            }
        }
    }
}
