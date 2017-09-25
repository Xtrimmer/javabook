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
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static javafx.beans.binding.Bindings.add;
import static javafx.geometry.Pos.CENTER;

/**
 * (Merge animation) Write a program that animates the merge of two sorted lists.
 * Create two arrays, list1 and list2, each of which consists of 8 random numbers
 * from 1 to 999. The array elements are displayed, as shown in Figure 23.22a.
 * Clicking the Step button causes the program to move an element from list1 or
 * list2 to temp. Clicking the Reset button creates two new random arrays for
 * a new start. When the algorithm is finished, clicking the Step button displays a
 * message to inform the user.
 */
public class PE_23_18_Merge_animation extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new MergeListPane();
        Scene scene = new Scene(pane, 1000, 400);

        primaryStage.setTitle("Exercise23_18: Merge two sorted lists");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    enum Direction {UP, DOWN}

    class MergeListPane extends BorderPane {
        private static final int SPACING = 5;
        private final Label label = new Label();
        private final Button buttonStep = new Button("Step");
        private final Button buttonReset = new Button("Reset");
        private final MergeDisplayPane mergeDisplayPane = new MergeDisplayPane();

        public MergeListPane() {
            setTop(createMessagePane());
            setCenter(mergeDisplayPane);
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
                boolean sorted = mergeDisplayPane.step();
                label.setText(sorted ? "The two lists are merged." : null);
            });
            buttonReset.setOnAction(event -> {
                mergeDisplayPane.reset();
                label.setText(null);
            });
        }
    }

    class MergeDisplayPane extends Pane {
        private final ArrayBlock list1;
        private final ArrayBlock list2;
        private final ArrayBlock temp;

        public MergeDisplayPane() {

            Integer[] array1 = generateRandomInts(8, 1000);
            Arrays.sort(array1);
            list1 = new ArrayBlock(array1, "List 1");
            list1.layoutXProperty().bind(widthProperty().divide(2.0).subtract(list1.widthProperty().add(10)));
            list1.setLayoutY(100);
            ArrayBlockPointer pointer1 = new ArrayBlockPointer("Current1", Direction.DOWN);
            list1.setPointer(pointer1);

            Integer[] array2 = generateRandomInts(8, 1000);
            Arrays.sort(array2);
            list2 = new ArrayBlock(array2, "List 2");
            list2.layoutXProperty().bind(widthProperty().divide(2.0).add(10));
            list2.setLayoutY(100);
            ArrayBlockPointer pointer2 = new ArrayBlockPointer("Current2", Direction.DOWN);
            list2.setPointer(pointer2);

            temp = new ArrayBlock(new Integer[16], "Temp");
            temp.layoutXProperty().bind(widthProperty().divide(2.0).subtract(temp.widthProperty().divide(2.0)));
            temp.setLayoutY(175);
            ArrayBlockPointer pointerTemp = new ArrayBlockPointer("Current3", Direction.UP);
            temp.setPointer(pointerTemp);

            setStyle("-fx-border-color: black; -fx-border-width: 1pt;");
            getChildren().addAll(list1, list2, temp);
        }

        public void reset() {
            Integer[] array1 = generateRandomInts(8, 1000);
            Arrays.sort(array1);
            list1.fillArray(array1);
            list1.setPosition(0);
            Integer[] array2 = generateRandomInts(8, 1000);
            Arrays.sort(array2);
            list2.fillArray(array2);
            list2.setPosition(0);
            temp.fillArray(new Integer[16]);
            temp.setPosition(0);
        }

        public boolean step() {
            if (temp.getPosition() < temp.size()) {
                if (list1.getPosition() < list1.size() && list2.position < list2.size()) {
                    int integer1 = list1.getElement(list1.getPosition()).getValue();
                    int integer2 = list2.getElement(list2.getPosition()).getValue();
                    if (integer1 < integer2) {
                        temp.getElement(temp.getPosition()).setValue(integer1);
                        list1.setPosition(list1.getPosition() + 1);
                    } else {
                        temp.getElement(temp.getPosition()).setValue(integer2);
                        list2.setPosition(list2.getPosition() + 1);
                    }
                } else if (list1.getPosition() < list1.size()) {
                    int integer = list1.getElement(list1.getPosition()).getValue();
                    temp.getElement(temp.getPosition()).setValue(integer);
                    list1.setPosition(list1.getPosition() + 1);
                } else {
                    int integer = list2.getElement(list2.getPosition()).getValue();
                    temp.getElement(temp.getPosition()).setValue(integer);
                    list2.setPosition(list2.getPosition() + 1);
                }
                temp.setPosition(temp.getPosition() + 1);
                return false;
            } else {
                return true;
            }
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
        private ArrayBlockPointer pointer;
        private int position = 0;

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

        public void fillArray(Integer[] numbers) {
            for (int i = 0; i < numbers.length; i++) {
                elements.get(i).setValue(numbers[i]);
            }
        }

        public ArrayBlockElement getElement(int i) {
            return elements.get(i);
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
            if (position >= 0 && position < size())
                bindPointer();
        }

        public void setPointer(ArrayBlockPointer pointer) {
            this.pointer = pointer;
            bindPointer();
            getChildren().add(pointer);
        }

        public int size() {
            return elements.size();
        }

        public DoubleBinding widthProperty() {
            return Bindings.createDoubleBinding(() -> layoutBoundsProperty().get().getWidth(), this.layoutBoundsProperty());
        }

        private void bindPointer() {
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
        private final Label label = new Label();
        private final Line shaft = new Line();
        private final Line pointLeft = new Line();
        private final Line pointRight = new Line();

        public ArrayBlockPointer(String text, Direction direction) {
            this.direction = direction;
            label.setText(text);
            shaft.startXProperty().bind(add(label.layoutXProperty(), label.widthProperty().divide(2.0)));
            shaft.endXProperty().bind(shaft.startXProperty());
            pointLeft.startXProperty().bind(shaft.endXProperty());
            pointLeft.startYProperty().bind(shaft.endYProperty());
            pointRight.startXProperty().bind(shaft.endXProperty());
            pointRight.startYProperty().bind(shaft.endYProperty());
            if (direction == Direction.UP) {
                shaft.startYProperty().bind(label.layoutYProperty());
                shaft.endYProperty().bind(shaft.startYProperty().subtract(50));
                pointLeft.endXProperty().bind(shaft.endXProperty().subtract(10));
                pointLeft.endYProperty().bind(shaft.endYProperty().add(10));
                pointRight.endXProperty().bind(shaft.endXProperty().add(10));
                pointRight.endYProperty().bind(shaft.endYProperty().add(10));
            } else {
                shaft.startYProperty().bind(label.layoutYProperty().add(label.heightProperty()));
                shaft.endYProperty().bind(shaft.startYProperty().add(50));
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

        public DoubleBinding heightProperty() {
            if (direction == Direction.DOWN)
                return Bindings.createDoubleBinding(() -> boundsInLocalProperty().get().getHeight(), boundsInLocalProperty());
            else
                return Bindings.createDoubleBinding(() -> boundsInLocalProperty().get().getHeight() - label.getHeight(), boundsInLocalProperty());

        }

        public DoubleBinding widthProperty() {
            return Bindings.createDoubleBinding(() -> boundsInLocalProperty().get().getWidth(), boundsInLocalProperty());
        }
    }
}
