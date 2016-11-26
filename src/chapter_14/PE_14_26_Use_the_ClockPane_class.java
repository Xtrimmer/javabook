package chapter_14;

import textbook_listings.ClockPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * (Use the ClockPane class) Write a program that displays two clocks. The hour,
 * minute, and second values are 4, 20, 45 for the first clock and 22, 46, 15 for the
 * second clock, as shown in Figure 14.51c.
 */
public class PE_14_26_Use_the_ClockPane_class extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ClockPane clock1 = new ClockPane(4, 20, 45);
        clock1.setHeight(200);
        clock1.setWidth(200);
        ClockPane clock2 = new ClockPane(22, 46, 15);
        clock2.setHeight(200);
        clock2.setWidth(200);
        HBox pane = new HBox(clock1, clock2);
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise14_26");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
