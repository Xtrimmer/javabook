package chapter_22;

import javafx.application.Application;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * (Linear search animation) Write a program that animates the linear search
 * algorithm. Create an array that consists of 20 distinct numbers from 1 to 20
 * in a random order. The array elements are displayed in a histogram, as shown
 * in Figure 22.12. You need to enter a search key in the text field. Clicking the
 * Step button causes the program to perform one comparison in the algorithm and
 * repaints the histogram with a bar indicating the search position. This button
 * also freezes the text field to prevent its value from being changed. When the
 * algorithm is finished, display the status in the label at the top of the border pane
 * to inform the user. Clicking the Reset button creates a new random array for a
 * new start. This button also makes the text field editable.
 */
public class PE_22_16_Linear_search_animation extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new LinearSearchPane();
        Scene scene = new Scene(pane, 600, 400);

        primaryStage.setTitle("Exercise22_16");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class LinearSearchPane extends BorderPane {
        private static final int ARRAY_SIZE = 20;
        private final Label label = new Label();
        private final TextField textField = new TextField();
        private final Button buttonStep = new Button("Step");
        private final Button buttonReset = new Button("Reset");
        private int[] numbers = initializeNumbers();
        private HistogramPane histogramPane = new HistogramPane(numbers);
        private int stepCount = 0;
        private double key;


        public LinearSearchPane() {
            setTop(createLabelPane());
            setCenter(histogramPane);
            setBottom(createControlPane());
            setButtonActions();
        }

        private Node createControlPane() {
            Label label = new Label("Key (in double)");
            HBox hBox = new HBox(5, label, textField, buttonStep, buttonReset);
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(5));
            return hBox;
        }

        private Node createLabelPane() {
            StackPane stackPane = new StackPane(label);
            stackPane.setPadding(new Insets(5));
            return stackPane;
        }

        private int[] initializeNumbers() {
            List<Integer> numberList = new ArrayList<>();
            for (int i = 1; i <= ARRAY_SIZE; i++) {
                numberList.add(i);
            }
            Collections.shuffle(numberList);
            int[] numbers = new int[ARRAY_SIZE];
            for (int i = 0; i < ARRAY_SIZE; i++) {
                numbers[i] = numberList.get(i);
            }
            return numbers;
        }

        private double parseDouble(TextField textField, double defaultValue) {
            try {
                double number = Double.parseDouble(textField.getText());
                textField.setText(number + "");
                return number;
            } catch (NumberFormatException e) {
                textField.setText(defaultValue + "");
                return defaultValue;
            }
        }

        private void resetAction() {
            textField.setEditable(true);
            label.setText("");
            buttonStep.setDisable(false);
            numbers = initializeNumbers();
            histogramPane = new HistogramPane(numbers);
            setCenter(histogramPane);
            stepCount = 0;
        }

        private void setButtonActions() {
            buttonStep.setOnAction(event -> stepAction());
            buttonReset.setOnAction(event -> resetAction());
        }

        private void stepAction() {
            histogramPane.setBarFill(stepCount, Color.DARKRED);
            if (stepCount == 0) {
                textField.setEditable(false);
                key = parseDouble(textField, 0);
            } else if (stepCount == ARRAY_SIZE - 1) {
                histogramPane.setBarFill(stepCount - 1, Color.WHITE);
                label.setText("The key is not in the array");
                buttonStep.setDisable(true);
            } else {
                histogramPane.setBarFill(stepCount - 1, Color.WHITE);
                if (Double.compare(numbers[stepCount], key) == 0) {
                    label.setText("The key is found in the array at index " + stepCount);
                    buttonStep.setDisable(true);
                }
            }
            stepCount++;
        }
    }

    class HistogramPane extends Pane {
        private static final int TOP_SPACE = 35;
        private Rectangle[] bars;
        private Label[] labels;

        public HistogramPane(int[] numbers) {
            setBorder(new Border(new BorderStroke(
                    Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            initializeBars(numbers);
            initializeLabels(numbers);
        }

        public void setBarFill(int index, Paint paint) {
            bars[index].setFill(paint);
        }

        private void initializeBars(int[] numbers) {
            bars = new Rectangle[numbers.length];
            NumberBinding barWidth = widthProperty().divide(numbers.length + 1);
            NumberBinding maxBarHeight = heightProperty().subtract(TOP_SPACE);
            if (numbers.length > 0) {
                double maxNumber = numbers[0];
                for (int i = 1; i < numbers.length; i++) {
                    maxNumber = Math.max(maxNumber, numbers[i]);
                }
                for (int i = 0; i < numbers.length; i++) {
                    Rectangle rectangle = new Rectangle();
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setFill(Color.WHITE);
                    rectangle.widthProperty().bind(barWidth);
                    NumberBinding barHeight = maxBarHeight.multiply(numbers[i] / maxNumber);
                    rectangle.heightProperty().bind(barHeight);
                    rectangle.xProperty().bind(barWidth.multiply(i).add(barWidth.divide(2.0)));
                    rectangle.yProperty().bind(heightProperty().subtract(barHeight).subtract(rectangle.getStrokeWidth()));
                    bars[i] = rectangle;
                }
            }
            getChildren().addAll(bars);
        }

        private void initializeLabels(int[] numbers) {
            labels = new Label[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                Label label = new Label("" + numbers[i]);
                label.layoutXProperty().bind(bars[i].xProperty()
                        .add(bars[i].widthProperty().divide(2.0))
                        .subtract(label.widthProperty().divide(2.0)));
                label.layoutYProperty().bind(bars[i].yProperty().subtract(label.heightProperty()));
                labels[i] = label;
            }
            getChildren().addAll(labels);
        }
    }
}
