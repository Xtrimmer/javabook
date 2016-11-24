package chapter_14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

/**
 * (Game: bean machine) Write a program that displays a bean machine introduced
 * in Programming Exercise 7.21, as shown in Figure 14.52c.
 */
public class PE_14_29_Game_bean_machine extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BeanMachinePane pane = new BeanMachinePane();
        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setTitle("Exercise14_29");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

class BeanMachinePane extends Pane {

    public void paint() {
        getChildren().clear();
        double width = getWidth();
        double height = getHeight();

        Polyline polyline = new Polyline(
                width * (9 / 20.0), height * (1 / 12.0),
                width * (9 / 20.0), height * (1 / 6.0),
                width * (1 / 10.0), height * (3 / 4.0),
                width * (1 / 10.0), height * (11 / 12.0),
                width * (9 / 10.0), height * (11 / 12.0),
                width * (9 / 10.0), height * (3 / 4.0),
                width * (11 / 20.0), height * (1 / 6.0),
                width * (11 / 20.0), height * (1 / 12.0)
        );
        getChildren().add(polyline);

        double startY = height * (3 / 4.0);
        double endY = height * (11 / 12.0);
        for (int i = 0; i < 7; i++) {
            double lineX = width * ((i + 2) / 10.0);
            getChildren().add(new Line(lineX, startY, lineX, endY));
        }

        double ellipseRadiusX = width / 40;
        double ellipseRadiusY = height / 40;
        for (int i = 0; i < 7; i++) {
            double ellipseCenterY = height * ((i + 3) / 12.0);
            for (int j = 0; j < i + 1; j++) {
                double ellipseCenterX = width * ((10 - i + 2 * j) / 20.0);
                getChildren().add(new Ellipse(ellipseCenterX, ellipseCenterY, ellipseRadiusX, ellipseRadiusY));
            }
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
