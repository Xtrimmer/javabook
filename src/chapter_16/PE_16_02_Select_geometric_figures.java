package chapter_16;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * (Select geometric figures) Write a program that draws various figures, as shown
 * in Figure 16.36b. The user selects a figure from a radio button and uses a check
 * box to specify whether it is filled.
 */
public class PE_16_02_Select_geometric_figures extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        ShapePane shapePane = new ShapePane();
        SelectionPane selectionPane = new SelectionPane(shapePane);
        BorderPane pane = new BorderPane(shapePane, null, null, selectionPane, null);
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise16_02");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class ShapePane extends StackPane {
        private Shape form;
        boolean isFilled;

        /**
         * Creates a StackPane layout with default CENTER alignment.
         */
        ShapePane() {
            setPrefHeight(150);
            setPrefWidth(350);
            setStyle("-fx-border-color: black");
            setForm(new Rectangle(150, 100));
        }

        void setForm(Shape form) {
            this.form = form;
            form.setStroke(Color.BLACK);
            form.setFill(isFilled ? Color.BLACK : Color.TRANSPARENT);
            getChildren().clear();
            getChildren().add(form);
        }

        void setFilled(boolean isFilled) {
            this.isFilled = isFilled;
            form.setFill(isFilled ? Color.BLACK : Color.TRANSPARENT);
        }
    }

    private class SelectionPane extends HBox {
        private final RadioButton radioButtonCircle;
        private final RadioButton radioButtonRectangle;
        private final RadioButton radioButtonEllipse;
        private final CheckBox checkBoxFill;

        SelectionPane(ShapePane pane) {
            setSpacing(10);
            setAlignment(Pos.CENTER);

            ToggleGroup group = new ToggleGroup();

            radioButtonCircle = new RadioButton("Circle");
            radioButtonCircle.setToggleGroup(group);
            radioButtonCircle.setOnAction(event -> pane.setForm(new Circle(50)));

            radioButtonRectangle = new RadioButton("Rectangle");
            radioButtonRectangle.setToggleGroup(group);
            radioButtonRectangle.setOnAction(event -> pane.setForm(new Rectangle(150, 100)));
            radioButtonRectangle.setSelected(true);

            radioButtonEllipse = new RadioButton("Ellipse");
            radioButtonEllipse.setToggleGroup(group);
            radioButtonEllipse.setOnAction(event -> pane.setForm(new Ellipse(75, 50)));

            checkBoxFill = new CheckBox("Fill");
            checkBoxFill.setOnAction(event -> pane.setFilled(checkBoxFill.isSelected()));

            getChildren().addAll(
                    radioButtonCircle, radioButtonRectangle, radioButtonEllipse, checkBoxFill
            );
        }
    }
}
