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
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javafx.beans.binding.Bindings.add;
import static javafx.geometry.Pos.CENTER;

/**
 * (Quick sort partition animation) Write a program that animates the partition for
 * a quick sort. The program creates a list that consists of 20 random numbers from
 * 1 to 999. The list is displayed, as shown in Figure 23.22b. Clicking the Step button
 * causes the program to move low to the right or high to the left, or swap the
 * elements at low and high. Clicking the Reset button creates a new list of random
 * numbers for a new start. When the algorithm is finished, clicking the Step button
 * displays a message to inform the user.
 */
public class PE_23_19_Quick_sort_partition_animation extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new QuickSortPartitionPane();
        Scene scene = new Scene(pane, 1000, 400);

        primaryStage.setTitle("Exercise23_19: Partition of List for Quick Sort");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    enum Direction {UP, DOWN}

    class QuickSortPartitionPane extends BorderPane {
        private static final int SPACING = 5;
        private final Label label = new Label();
        private final Button buttonStep = new Button("Step");
        private final Button buttonReset = new Button("Reset");
        private final PartitionDisplayPane displayPane = new PartitionDisplayPane();

        public QuickSortPartitionPane() {
            setTop(createMessagePane());
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
                boolean isFinished = displayPane.step();
                if (isFinished) {
                    buttonStep.setDisable(true);
                    label.setText("Partition algorithm is complete.");
                }
            });
            buttonReset.setOnAction(event -> {
                displayPane.reset();
                buttonStep.setDisable(false);
            });
        }
    }

    class PartitionDisplayPane extends Pane {
        private static final int PIVOT = 0;
        private static final int LOW = 1;
        private static final int HIGH = 2;
        private final ArrayBlock list;

        public PartitionDisplayPane() {
            Integer[] array = generateRandomInts(20, 1000);
            list = new ArrayBlock(array, "Temp");
            list.layoutXProperty().bind(widthProperty().divide(2.0).subtract(list.widthProperty().divide(2.0)));
            list.layoutYProperty().bind(heightProperty().divide(2.0));
            ArrayBlockPointer pointerPivot = new ArrayBlockPointer("Pivot", Direction.UP, 100);
            ArrayBlockPointer pointerLow = new ArrayBlockPointer("Low", Direction.DOWN, 50);
            pointerLow.setPosition(1);
            ArrayBlockPointer pointerHigh = new ArrayBlockPointer("High", Direction.DOWN, 100);
            pointerHigh.setPosition(list.size() - 1);
            list.addPointer(pointerPivot);
            list.addPointer(pointerLow);
            list.addPointer(pointerHigh);
            setStyle("-fx-border-color: black; -fx-border-width: 1pt;");
            getChildren().add(list);
        }

        public void reset() {
            list.fillArray(generateRandomInts(20, 1000));
            list.getPointer(0).setPosition(0);
            list.getPointer(1).setPosition(1);
            list.getPointer(2).setPosition(list.size() - 1);
            list.movePointer(0, 0);
            list.movePointer(1, 0);
            list.movePointer(2, 0);
        }

        public boolean step() {
            int highIndex = list.pointers.get(HIGH).getPosition();
            int lowIndex = list.pointers.get(LOW).getPosition();
            int highValue = list.getElement(list.pointers.get(HIGH).getPosition()).getValue();
            int lowValue = list.getElement(list.pointers.get(LOW).getPosition()).getValue();
            int pivotValue = list.getElement(list.pointers.get(PIVOT).getPosition()).getValue();

            if (highIndex > lowIndex) {
                if (lowIndex <= highIndex && lowValue <= pivotValue) {
                    list.movePointer(LOW, 1);
                } else if (lowIndex <= highIndex && highValue > pivotValue) {
                    list.movePointer(HIGH, -1);
                } else if (highIndex > lowIndex) {
                    list.getElement(highIndex).setValue(list.getElement(lowIndex).getValue());
                    list.getElement(lowIndex).setValue(highValue);
                }
            } else if (highIndex > 0 && highValue >= pivotValue) {
                list.movePointer(HIGH, -1);
            } else if (pivotValue > highValue) {
                list.getElement(0).setValue(highValue);
                list.getElement(highIndex).setValue(pivotValue);
                return true;
            }
            return false;
        }

        private Integer[] generateRandomInts(int size, int bound) {
            Integer[] ints = new Integer[size];
            Random random = new Random();
            for (int i = 0; i < size; i++) {
                ints[i] = random.nextInt(bound);
            }
            return ints;
        }
    }

    class ArrayBlock extends Group {
        private final List<ArrayBlockElement> elements = new ArrayList<>();
        private final Label label = new Label();
        private final List<ArrayBlockPointer> pointers = new ArrayList<>();

        public ArrayBlock(Integer[] numbers, String name) {

            if (numbers.length > 0) {
                label.setText(name);
                label.setPadding(new Insets(ArrayBlockElement.CELL_PADDING));
                ArrayBlockElement element = new ArrayBlockElement(numbers[0]);
                element.layoutXProperty().bind(add(label.layoutXProperty(), label.widthProperty()));
                elements.add(element);
                for (int i = 1; i < numbers.length; i++) {
                    element = new ArrayBlockElement(numbers[i]);
                    element.layoutXProperty().bind(
                            elements.get(elements.size() - 1).layoutXProperty().add(elements.get(elements.size() - 1).widthProperty())
                    );
                    elements.add(element);
                }
            }
            getChildren().add(label);
            getChildren().addAll(elements);
        }

        public void addPointer(ArrayBlockPointer pointer) {
            pointers.add(pointer);
            bindPointer(pointers.size() - 1);
            getChildren().add(pointer);
        }

        public void fillArray(Integer[] numbers) {
            for (int i = 0; i < numbers.length; i++) {
                elements.get(i).setValue(numbers[i]);
            }
        }

        public ArrayBlockElement getElement(int i) {
            return elements.get(i);
        }

        public ArrayBlockPointer getPointer(int pointerIndex) {
            return pointers.get(pointerIndex);
        }

        public void movePointer(int pointerIndex, int spaces) {
            ArrayBlockPointer pointer = pointers.get(pointerIndex);
            pointer.setPosition(pointer.getPosition() + spaces);
            bindPointer(pointerIndex);
        }

        public int size() {
            return elements.size();
        }

        public DoubleBinding widthProperty() {
            return Bindings.createDoubleBinding(() -> layoutBoundsProperty().get().getWidth(), this.layoutBoundsProperty());
        }

        private void bindPointer(int pointerIndex) {
            ArrayBlockPointer pointer = pointers.get(pointerIndex);
            int position = pointer.getPosition();
            if (position >= 0 && position < size()) {
                pointer.layoutXProperty().bind(
                        elements.get(position).layoutXProperty()
                                .add(elements.get(position).widthProperty().divide(2.0))
                                .subtract(pointer.widthProperty().divide(2.0))
                );
                if (pointer.getDirection() == Direction.DOWN) {
                    pointer.layoutYProperty().bind(
                            elements.get(position).layoutYProperty()
                                    .subtract(pointer.heightProperty())
                    );
                } else {
                    pointer.layoutYProperty().bind(
                            elements.get(position).layoutYProperty()
                                    .add(pointer.heightProperty().add(elements.get(position).heightProperty()))
                    );
                }
            }
        }
    }

    class ArrayBlockElement extends Label {
        public static final int CELL_PADDING = 3;
        private Integer value;

        public ArrayBlockElement(Integer value) {
            setStyle("-fx-border-color: black");
            setAlignment(Pos.CENTER);
            setPrefWidth(45);
            setPadding(new Insets(CELL_PADDING));
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

    class ArrayBlockPointer extends Group {
        private final Direction direction;
        private int position = 0;

        public ArrayBlockPointer(String text, Direction direction, double length) {
            this.direction = direction;
            Label label = new Label();
            label.setText(text);
            Line shaft = new Line();
            shaft.startXProperty().bind(add(label.layoutXProperty(), label.widthProperty().divide(2.0)));
            shaft.endXProperty().bind(shaft.startXProperty());
            Line pointLeft = new Line();
            pointLeft.startXProperty().bind(shaft.endXProperty());
            pointLeft.startYProperty().bind(shaft.endYProperty());
            Line pointRight = new Line();
            pointRight.startXProperty().bind(shaft.endXProperty());
            pointRight.startYProperty().bind(shaft.endYProperty());
            if (direction == Direction.UP) {
                shaft.startYProperty().bind(label.layoutYProperty());
                shaft.endYProperty().bind(shaft.startYProperty().subtract(length));
                pointLeft.endXProperty().bind(shaft.endXProperty().subtract(10));
                pointLeft.endYProperty().bind(shaft.endYProperty().add(10));
                pointRight.endXProperty().bind(shaft.endXProperty().add(10));
                pointRight.endYProperty().bind(shaft.endYProperty().add(10));
            } else {
                shaft.startYProperty().bind(label.layoutYProperty().add(label.heightProperty()));
                shaft.endYProperty().bind(shaft.startYProperty().add(length));
                pointLeft.endXProperty().bind(shaft.endXProperty().subtract(10));
                pointLeft.endYProperty().bind(shaft.endYProperty().subtract(10));
                pointRight.endXProperty().bind(shaft.endXProperty().add(10));
                pointRight.endYProperty().bind(shaft.endYProperty().subtract(10));
            }
            getChildren().addAll(label, shaft, pointLeft, pointRight);
        }

        public Direction getDirection() {
            return direction;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public DoubleBinding heightProperty() {
            if (direction == Direction.DOWN)
                return Bindings.createDoubleBinding(() -> boundsInLocalProperty().get().getHeight(), boundsInLocalProperty());
            else
                return Bindings.createDoubleBinding(() -> boundsInLocalProperty().get().getHeight()/* - label.getHeight()*/, boundsInLocalProperty());

        }

        public DoubleBinding widthProperty() {
            return Bindings.createDoubleBinding(() -> boundsInLocalProperty().get().getWidth(), boundsInLocalProperty());
        }
    }
}
