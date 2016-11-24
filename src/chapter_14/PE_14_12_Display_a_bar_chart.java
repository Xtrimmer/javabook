package chapter_14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * (Display a bar chart) Write a program that uses a bar chart to display the percentages
 * of the overall grade represented by projects, quizzes, midterm exams, and the
 * final exam, as shown in Figure 14.46b. Suppose that projects take 20 percent and
 * are displayed in red, quizzes take 10 percent and are displayed in blue, midterm
 * exams take 30 percent and are displayed in green, and the final exam takes 40
 * percent and is displayed in orange. Use the Rectangle class to display the bars.
 * Interested readers may explore the JavaFX BarChart class for further study.
 */
public class PE_14_12_Display_a_bar_chart extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ChartBar bar1 = new ChartBar("Project", 20, Color.RED);
        ChartBar bar2 = new ChartBar("Quiz", 10, Color.BLUE);
        ChartBar bar3 = new ChartBar("Midterm", 30, Color.GREEN);
        ChartBar bar4 = new ChartBar("Final", 40, Color.ORANGE);

        HBox pane = new HBox(10);
        pane.setPadding(new Insets(10));
        pane.getChildren().addAll(bar1, bar2, bar3, bar4);
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Exercise14_12");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

class ChartBar extends VBox {
    public ChartBar(String title, double percent, Color color) {
        super(10);
        Rectangle rectangle = new Rectangle(150, percent * 4, color);
        setAlignment(Pos.BOTTOM_LEFT);
        Label label = new Label(title + " -- " + formatPercent(percent) + "%");
        getChildren().addAll(label, rectangle);
    }

    private String formatPercent(double percent) {
        if ((int) percent == percent) {
            return "" + (int) percent;
        } else {
            return "" + percent;
        }
    }
}