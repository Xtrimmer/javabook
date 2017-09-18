package chapter_23;

import javafx.application.Application;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javafx.scene.paint.Color.DARKRED;

/**
 * (Bubble sort animation) Write a program that animates the bubble sort algorithm.
 * Create an array that consists of 20 distinct numbers from 1 to 20 in a
 * random order. The array elements are displayed in a histogram, as shown in
 * Figure 23.20b. Clicking the Step button causes the program to perform one comparison
 * in the algorithm and repaints the histogram for the new array. Color the
 * bar that represents the number being considered in the swap. When the algorithm
 * is finished, display a message to inform the user. Clicking the Reset button creates
 * a new random array for a new start.
 */
public class PE_23_16_Bubble_sort_animation extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new LinearSearchPane();
        Scene scene = new Scene(pane, 600, 400);

        primaryStage.setTitle("Exercise23_16: Bubble Sort");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class LinearSearchPane extends BorderPane {
        private static final int ARRAY_SIZE = 20;
        private final Label label = new Label();
        private final Button buttonStep = new Button("Step");
        private final Button buttonReset = new Button("Reset");
        private int[] numbers = initializeNumbers();
        private HistogramPane histogramPane = new HistogramPane(numbers);
        private int step = 0;
        private int pass = 1;
        private boolean needNextPass = false;

        public LinearSearchPane() {
            setTop(createLabelPane());
            setCenter(histogramPane);
            histogramPane.setBarFill(0, DARKRED);
            setBottom(createControlPane());
            setButtonActions();
        }

        private Node createControlPane() {
            HBox hBox = new HBox(5, buttonStep, buttonReset);
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

        private void resetAction() {
            label.setText(null);
            step = 0;
            pass = 1;
            numbers = initializeNumbers();
            histogramPane = new HistogramPane(numbers);
            histogramPane.setBarFill(step, DARKRED);
            setCenter(histogramPane);
        }

        private void setButtonActions() {
            buttonStep.setOnAction(event -> stepAction());
            buttonReset.setOnAction(event -> resetAction());
        }

        private void stepAction() {
            if (step < numbers.length - pass) {
                if (numbers[step] > numbers[step + 1]) {
                    int temp = numbers[step];
                    numbers[step] = numbers[step + 1];
                    numbers[step + 1] = temp;
                    needNextPass = true;
                }
                if (++step == numbers.length - pass) {
                    step = 0;
                    if (needNextPass) {
                        pass++;
                        needNextPass = false;
                    } else {
                        pass = numbers.length;
                    }
                }
                histogramPane = new HistogramPane(numbers);
                setCenter(histogramPane);
                histogramPane.setBarFill(step, DARKRED);
            } else {
                label.setText("The array is sorted");
            }
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
