package chapter_14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * (Create four fans) Write a program that places four fans in a GridPane with two
 * rows and two columns, as shown in Figure 14.45b.
 */
public class PE_14_09_Create_four_fans extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane pane = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        pane.getColumnConstraints().addAll(col1, col2);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(50);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(50);
        pane.getRowConstraints().addAll(row1, row2);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Pane fanPane = new FanPane();
                pane.add(fanPane, j, i);
            }
        }
        Scene scene = new Scene(pane, 250, 250);
        primaryStage.setTitle("Exercise14_09");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

class FanPane extends Pane {

    public void paint() {
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;
        double radius1 = Math.min(getWidth(), getHeight()) * 0.4;
        double radius2 = Math.min(getWidth(), getHeight()) * 0.33;
        Circle circle = new Circle(centerX, centerY, radius1, Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().clear();
        getChildren().add(circle);
        for (int i = 0; i < 4; i++) {
            Arc arc = new Arc(centerX, centerY, radius2, radius2, 90 * i + 30, 30);
            arc.setType(ArcType.ROUND);
            arc.setFill(Color.GRAY);
            getChildren().add(arc);
        }
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paint();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paint();
    }
}
