package chapter_14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.stage.Stage;

/**
 * (Paint a smiley face) Write a program that paints a smiley face, as shown in
 * Figure 14.46a.(p.580)
 */
public class PE_14_11_Paint_a_smiley_face extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        Pane pane = new FacePane();
        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setTitle("Exercise14_11");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

class FacePane extends Pane {

    public void paint() {
        String style = "-fx-stroke: black; -fx-fill: white";
        double paneSize = Math.min(getWidth(), getHeight());
        //Create Head
        Circle head = new Circle(
                paneSize * (1 / 2.0),
                paneSize * (1 / 2.0),
                paneSize * (1 / 3.0)
        );
        head.setStyle(style);

        //Create eyes
        Ellipse eyeL = new Ellipse(
                paneSize * (13 / 36.0),
                paneSize * (7 / 18.0),
                paneSize * (1 / 12.0),
                paneSize * (1 / 18.0)
        );
        eyeL.setStyle(style);

        Ellipse eyeR = new Ellipse(
                paneSize * (23 / 36.0),
                paneSize * (7 / 18.0),
                paneSize * (1 / 12.0),
                paneSize * (1 / 18.0)
        );
        eyeR.setStyle(style);

        Circle pupilL = new Circle(
                paneSize * (13 / 36.0),
                paneSize * (7 / 18.0),
                paneSize * (1 / 25.0)
        );

        Circle pupilR = new Circle(
                paneSize * (23 / 36.0),
                paneSize * (7 / 18.0),
                paneSize * (1 / 25.0)
        );

        //Create nose
        Line noseL = new Line(
                paneSize * (1 / 2.0),
                paneSize * (7 / 18.0),
                paneSize * (5 / 12.0),
                paneSize * (11 / 18.0)
        );
        Line noseR = new Line(
                paneSize * (1 / 2.0),
                paneSize * (7 / 18.0),
                paneSize * (7 / 12.0),
                paneSize * (11 / 18.0)
        );
        Line noseB = new Line(
                paneSize * (5 / 12.0),
                paneSize * (11 / 18.0),
                paneSize * (7 / 12.0),
                paneSize * (11 / 18.0)
        );

        //Create mouth
        Arc mouth = new Arc(
                paneSize * (1 / 2.0),
                paneSize * (23 / 36.0),
                paneSize * (1 / 6.0),
                paneSize * (1 / 12.0),
                180,
                180
        );
        mouth.setType(ArcType.OPEN);
        mouth.setStyle(style);
        getChildren().clear();
        getChildren().addAll(head, eyeL, eyeR, pupilL, pupilR, noseL, noseR, noseB, mouth);
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paint();
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paint();
    }
}