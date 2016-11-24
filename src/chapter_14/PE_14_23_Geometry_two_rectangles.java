package chapter_14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utility.MyRectangle2D;

import java.util.List;

/**
 * (Geometry: two rectangles) Write a program that prompts the user to enter the
 * center coordinates, width, and height of two rectangles from the command line.
 * The program displays the rectangles and a text indicating whether the two are
 * overlapping, whether one is contained in the other, or whether they don’t overlap,
 * as shown in Figure 14.50. See Programming Exercise 10.13 for checking the
 * relationship between two rectangles.
 */
public class PE_14_23_Geometry_two_rectangles extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final Parameters parameters = getParameters();
        final List<String> args = parameters.getRaw();

        validateParameterCount(args);

        double rectangle1CenterX = parseDoubleParameter(args.get(0));
        double rectangle1CenterY = parseDoubleParameter(args.get(1));
        double rectangle1Width = parseDoubleParameter(args.get(2));
        double rectangle1Height = parseDoubleParameter(args.get(3));
        double rectangle2CenterX = parseDoubleParameter(args.get(4));
        double rectangle2CenterY = parseDoubleParameter(args.get(5));
        double rectangle2Width = parseDoubleParameter(args.get(6));
        double rectangle2Height = parseDoubleParameter(args.get(7));

        MyRectangle2D rectangle2D1 = new MyRectangle2D(rectangle1CenterX, rectangle1CenterY,
                rectangle1Width, rectangle1Height);
        MyRectangle2D rectangle2D2 = new MyRectangle2D(rectangle2CenterX, rectangle2CenterY,
                rectangle2Width, rectangle2Height);

        String rectangleRelativePositionMessage =
                getRectangleRelativePositionMessage(rectangle2D1, rectangle2D2);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10));
        Scene scene = new Scene(pane, 400, 400);

        PE_14_23_RectangleViewPane rectangleViewPane = new PE_14_23_RectangleViewPane(rectangle2D1, rectangle2D2);
        PE_14_24_MessagePane messagePane = new PE_14_24_MessagePane(rectangleRelativePositionMessage);

        pane.setTop(rectangleViewPane);
        pane.setBottom(messagePane);

        primaryStage.setTitle("Exercise14_23");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getRectangleRelativePositionMessage(
            MyRectangle2D rectangle2D1, MyRectangle2D rectangle2D2) {
        if (rectangle2D1.contains(rectangle2D2) || rectangle2D2.contains(rectangle2D1)) {
            return "One rectangle is contained in another";
        }
        if (rectangle2D1.overlaps(rectangle2D2) || rectangle2D2.overlaps(rectangle2D1)) {
            return "The rectangles overlap";
        }
        return "The rectangles do not overlap";
    }

    private void validateParameterCount(List<String> list) {
        if (list.size() != 8) {
            System.out.println("Invalid number of arguments");
            System.exit(0);
        }
    }

    private double parseDoubleParameter(String stringValue) {
        double doubleValue = 0;
        try {
            doubleValue = Double.parseDouble(stringValue);
        } catch (NumberFormatException e) {
            System.out.println("One or more of the arguments are of an invalid type.");
            System.exit(0);
        }
        return doubleValue;
    }
}

class PE_14_23_RectangleViewPane extends Pane {
    private MyRectangle2D r1 = null;
    private MyRectangle2D r2 = null;

    public PE_14_23_RectangleViewPane(MyRectangle2D r1, MyRectangle2D r2) {
        this.r1 = r1;
        this.r2 = r2;
        paint();
    }

    private void paint() {
        Rectangle rectangle1 = new Rectangle(r1.getLeft(), r1.getBottom(), r1.getWidth(), r1.getHeight());
        Rectangle rectangle2 = new Rectangle(r2.getLeft(), r2.getBottom(), r2.getWidth(), r2.getHeight());
        String style = "-fx-fill: transparent; -fx-stroke: black";
        rectangle1.setStyle(style);
        rectangle2.setStyle(style);
        getChildren().addAll(rectangle1, rectangle2);
    }
}

class PE_14_23_MessagePane extends StackPane {
    public PE_14_23_MessagePane(String message) {
        Text text = new Text(message);
        setPadding(new Insets(10, 0, 0, 0));
        getChildren().add(text);
    }
}