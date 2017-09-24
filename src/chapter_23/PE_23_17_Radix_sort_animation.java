package chapter_23;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

import static javafx.beans.binding.Bindings.*;
import static javafx.geometry.Pos.CENTER;

/**
 * (Radix sort animation) Write a program that animates the radix sort algorithm.
 * Create an array that consists of 20 random numbers from 0 to 1,000. The array
 * elements are displayed, as shown in Figure 23.21. Clicking the Step button
 * causes the program to place a number in a bucket. The number that has just
 * been placed is displayed in red. Once all the numbers are placed in the buckets,
 * clicking the Step button collects all the numbers from the buckets and moves
 * them back to the array. When the algorithm is finished, clicking the Step button
 * displays a message to inform the user. Clicking the Reset button creates a new
 * random array for a new start.
 */
public class PE_23_17_Radix_sort_animation extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new RadixSortPane(20, 3);
        Scene scene = new Scene(pane, 1000, 400);

        primaryStage.setTitle("Exercise23_17: Radix Sort");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class RadixSortPane extends BorderPane {
        private static final int SPACING = 5;
        private final Label label = new Label();
        private final Button buttonStep = new Button("Step");
        private final Button buttonReset = new Button("Reset");
        private final RadixDisplayPane displayPane;

        public RadixSortPane(int arraySize, int digits) {
            setTop(createMessagePane());
            displayPane = new RadixDisplayPane(arraySize, digits);
            setCenter(displayPane);
            setBottom(createControlsPane());
            setButtonActions();
        }

        private Node createControlsPane() {
            HBox hBox = new HBox(SPACING, buttonStep, buttonReset);
            hBox.setPadding(new Insets(SPACING));
            hBox.setAlignment(CENTER);
            return hBox;
        }

        private Node createMessagePane() {
            Pane pane = new StackPane(label);
            pane.setPadding(new Insets(SPACING));
            return pane;
        }

        private void setButtonActions() {
            buttonStep.setOnAction(event -> {
                boolean unSorted = displayPane.step();
                label.setText(unSorted ? null : "The Array is Sorted.");
            });
            buttonReset.setOnAction(event -> {
                displayPane.initialize();
                label.setText(null);
            });
        }
    }

    class RadixDisplayPane extends Pane {
        private final int maxRadix;
        private final int arraySize;
        private RadixArray radixArray;
        private BucketArray bucketArray;
        private int radixPosition;
        private int arrayIndex;
        private boolean isFillingBuckets;

        public RadixDisplayPane(int arraySize, int digits) {
            this.arraySize = arraySize;
            this.maxRadix = digits;
            setStyle("-fx-border-color: black; -fx-border-width: 1pt;");
            initialize();
        }

        public void initialize() {
            getChildren().clear();
            radixPosition = 1;
            arrayIndex = 0;
            isFillingBuckets = true;

            radixArray = new RadixArray(generateRandomInts(arraySize, (int) Math.pow(10, maxRadix)));
            radixArray.layoutXProperty().bind(divide(subtract(widthProperty(), radixArray.widthProperty()), 2));
            radixArray.setLayoutY(25);

            bucketArray = new BucketArray();
            bucketArray.layoutXProperty().bind(divide(subtract(widthProperty(), bucketArray.widthProperty()), 2));
            bucketArray.setLayoutY(85);

            getChildren().addAll(radixArray, bucketArray);
        }

        public boolean step() {
            if (radixPosition <= maxRadix) {
                if (isFillingBuckets) {
                    radixArray.resetNumberColor();
                    RadixElement element = radixArray.getElement(arrayIndex);
                    element.setTextFill(Color.RED);
                    int bucket = getRadix(element.getValue(), radixPosition);
                    bucketArray.getBucket(bucket).push(element.getValue());
                    arrayIndex++;
                    if (arrayIndex == radixArray.size()) {
                        isFillingBuckets = false;
                    }
                } else {
                    int[] numbers = new int[arraySize];
                    int index = 0;
                    for (int i = 0; i < 10; i++) {
                        Bucket bucket = bucketArray.getBucket(i);
                        int size = bucket.size();
                        for (int j = 0; j < size; j++) {
                            numbers[index++] = bucket.poll();
                        }
                    }
                    radixArray.resetNumberColor();
                    radixArray.clear();
                    radixArray.fillArray(numbers);
                    isFillingBuckets = true;
                    arrayIndex = 0;
                    radixPosition++;
                }
                return true;
            } else {
                return false;
            }
        }

        private int[] generateRandomInts(int size, int bound) {
            int[] ints = new int[size];
            Random random = new Random();
            for (int i = 0; i < size; i++) {
                ints[i] = random.nextInt(bound);
            }
            return ints;
        }

        private int getRadix(int number, int radix) {
            int divisorModulo = (int) Math.pow(10, radix);
            int divisorDivision = (int) Math.pow(10, radix - 1);
            return number % divisorModulo / divisorDivision;
        }
    }

    class RadixArray extends Group {
        private final List<RadixElement> elements = new ArrayList<>();

        public RadixArray(int[] numbers) {
            if (numbers.length > 0) {
                RadixElement element = new RadixElement(numbers[0]);
                elements.add(element);
                for (int i = 1; i < numbers.length; i++) {
                    element = new RadixElement(numbers[i]);
                    element.layoutXProperty().bind(
                            elements.get(elements.size() - 1).layoutXProperty().add(elements.get(elements.size() - 1).widthProperty())
                    );
                    elements.add(element);
                }
            }
            getChildren().addAll(elements);
        }

        public void clear() {
            for (RadixElement element : elements) {
                element.setValue(null);
            }
        }

        public void fillArray(int[] numbers) {
            for (int i = 0; i < numbers.length; i++) {
                elements.get(i).setValue(numbers[i]);
            }
        }

        public RadixElement getElement(int i) {
            return elements.get(i);
        }

        public void resetNumberColor() {
            for (RadixElement element : elements) {
                element.setTextFill(Color.BLACK);
            }
        }

        public int size() {
            return elements.size();
        }

        public DoubleBinding widthProperty() {
            return Bindings.createDoubleBinding(() -> layoutBoundsProperty().get().getWidth(), this.layoutBoundsProperty());
        }
    }

    class RadixElement extends Label {
        private Integer value;

        public RadixElement(Integer value) {
            setStyle("-fx-border-color: black");
            setAlignment(Pos.CENTER);
            setPrefWidth(45);
            setPadding(new Insets(3));
            setValue(value);
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
            setText(value == null ? "" : value.toString());
        }
    }

    class BucketArray extends Group {
        private final Bucket[] buckets = new Bucket[10];

        public BucketArray() {
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new Bucket();
                buckets[i].setLayoutX(i * (Bucket.WIDTH + 25));
                Label label = new Label("Bucket[" + i + "]");
                label.layoutXProperty().bind(
                        add(divide(subtract(buckets[i].widthProperty(), label.widthProperty()), 2.0), buckets[i].layoutXProperty()));
                label.layoutYProperty().bind(buckets[i].layoutYProperty().add(Bucket.HEIGHT + 5));
                getChildren().addAll(buckets[i], label);
            }
        }

        public Bucket getBucket(int index) {
            return buckets[index];
        }

        public DoubleBinding widthProperty() {
            return Bindings.createDoubleBinding(() -> layoutBoundsProperty().get().getWidth(), this.layoutBoundsProperty());
        }
    }

    class Bucket extends Pane {
        public static final int WIDTH = 65;
        public static final int HEIGHT = 200;
        private final Deque<Label> labels = new LinkedList<>();
        private final Deque<Integer> numbers = new LinkedList<>();

        public Bucket() {
            setStyle("-fx-border-color: black");
            setPrefHeight(HEIGHT);
            setPrefWidth(WIDTH);
        }

        public int poll() {
            Label firstLabel = labels.removeFirst();
            getChildren().remove(firstLabel);
            return numbers.removeFirst();
        }

        public void push(int number) {
            numbers.addLast(number);
            Label label = new Label(number + "");
            label.setPadding(new Insets(5, 0, 0, 0));
            label.layoutXProperty().bind(divide(subtract(widthProperty(), label.widthProperty()), 2));
            if (labels.isEmpty()) {
                label.setLayoutY(0);
            } else {
                Label previous = labels.getLast();
                label.layoutYProperty().bind(add(previous.layoutYProperty(), previous.heightProperty()));
            }
            labels.addLast(label);
            getChildren().add(label);
        }

        public int size() {
            return numbers.size();
        }
    }
}
