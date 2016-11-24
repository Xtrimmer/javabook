package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Display the mouse position) Write two programs, such that one displays the
 * mouse position when the mouse button is clicked (see Figure 15.26a) and the
 * other displays the mouse position when the mouse button is pressed and ceases
 * to display it when the mouse button is released.
 */
public class PE_15_08_B_Display_the_mouse_position extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Text text = new Text();

        Pane pane = new Pane();
        pane.setOnMousePressed(event -> {
            double xCoordinate = event.getX();
            double yCoordinate = event.getY();
            text.setText("(" + xCoordinate + ", " + yCoordinate + ")");
            text.setX(xCoordinate);
            text.setY(yCoordinate);
            pane.getChildren().add(text);
        });
        pane.setOnMouseReleased(event -> pane.getChildren().remove(text));
        Scene scene = new Scene(pane, 400, 200);

        primaryStage.setTitle("Exercise15_08B");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.requestFocus();
    }
}
