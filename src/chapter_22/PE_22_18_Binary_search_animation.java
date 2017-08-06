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

/**
 * (Binary search animation) Write a program that animates the binary search algorithm.
 * Create an array with numbers from 1 to 20 in this order. The array elements
 * are displayed in a histogram, as shown in Figure 22.13. You need to enter
 * a search key in the text field. Clicking the Step button causes the program to
 * perform one comparison in the algorithm. Use a light-gray color to paint the bars
 * for the numbers in the current search range and use a black color to paint the a bar
 * indicating the middle number in the search range. The Step button also freezes the
 * text field to prevent its value from being changed. When the algorithm is finished,
 * display the status in a label at the top of a border pane. Clicking the Reset button
 * enables a new search to start. This button also makes the text field editable.
 */
public class PE_22_18_Binary_search_animation extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane pane = new BinarySearchPane();
        Scene scene = new Scene(pane, 600, 400);

        primaryStage.setTitle("Exercise22_18");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class BinarySearchPane extends BorderPane {
        private static final int ARRAY_SIZE = 20;
        private final Label label = new Label();
        private final TextField textField = new TextField();
        private final Button buttonStep = new Button("Step");
        private final Button buttonReset = new Button("Reset");
        private final int[] numbers = initializeNumbers();
        private HistogramPane histogramPane;
        private double key;
        private int low = 0;
        private int high = numbers.length - 1;
        private int mid = high / 2;


        public BinarySearchPane() {
            setTop(createLabelPane());
            setCenter(resetHistogramPane());
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
            int[] numbers = new int[ARRAY_SIZE];
            for (int i = 0; i < ARRAY_SIZE; i++) {
                numbers[i] = i + 1;
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

        private void performNextSearchIteration() {
            if (key < numbers[mid]) {
                histogramPane.setBarFill(mid, high, Color.WHITE);
                high = mid - 1;
            } else {
                histogramPane.setBarFill(low, mid, Color.WHITE);
                low = mid + 1;
            }
            mid = (high + low) / 2;
            histogramPane.setBarFill(mid, Color.BLACK);
        }

        private void resetAction() {
            textField.setEditable(true);
            buttonStep.setDisable(false);
            setCenter(resetHistogramPane());
        }

        private Node resetHistogramPane() {
            low = 0;
            high = numbers.length - 1;
            mid = high / 2;
            histogramPane = new HistogramPane(numbers);
            histogramPane.setBarFill(low, high, Color.LIGHTGRAY);
            histogramPane.setBarFill(mid, Color.BLACK);
            return histogramPane;
        }

        private void setButtonActions() {
            buttonStep.setOnAction(event -> stepAction());
            buttonReset.setOnAction(event -> resetAction());
        }

        private void stepAction() {
            key = parseDouble(textField, 0);
            textField.setEditable(false);
            if (numbers[mid] == key) {
                label.setText("The key is found in the array at index " + mid);
                buttonStep.setDisable(true);
            } else if (low == high) {
                label.setText("The key is not in the array");
                buttonStep.setDisable(true);
            } else {
                performNextSearchIteration();
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

        public void setBarFill(int startIndex, int endIndex, Paint paint) {
            for (int i = startIndex; i <= endIndex; i++) {
                setBarFill(i, paint);
            }
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
