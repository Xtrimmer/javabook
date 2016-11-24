package chapter_14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Display a pie chart) Write a program that uses a pie chart to display the percentages
 * of the overall grade represented by projects, quizzes, midterm exams, and
 * the final exam, as shown in Figure 14.46c. Suppose that projects take 20 percent
 * and are displayed in red, quizzes take 10 percent and are displayed in blue,
 * midterm exams take 30 percent and are displayed in green, and the final exam
 * takes 40 percent and is displayed in orange. Use the Arc class to display the pies.
 * Interested readers may explore the JavaFX PieChart class for further study.
 */
public class PE_14_13_Display_a_pie_chart extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        double paneWidth = 300;
        double paneHeight = 300;
        double minDimension = Math.min(paneWidth, paneHeight);
        double centerX = paneWidth / 2.0;
        double centerY = paneHeight / 2.0;
        double radius = minDimension * 0.4;

        double startAngle = 0;
        double length = 360 * 0.2;
        Arc arcProject = new Arc(centerX, centerY, radius, radius, startAngle, length);
        arcProject.setType(ArcType.ROUND);
        arcProject.setFill(Color.RED);

        startAngle += length;length = 360 * 0.1;
        Arc arcQuiz = new Arc(centerX, centerY, radius, radius, startAngle, length);
        arcQuiz.setType(ArcType.ROUND);
        arcQuiz.setFill(Color.BLUE);

        startAngle += length;
        length = 360 * 0.3;
        Arc arcMidterm = new Arc(centerX, centerY, radius, radius, startAngle, length);
        arcMidterm.setType(ArcType.ROUND);
        arcMidterm.setFill(Color.GREEN);

        startAngle += length;
        length = 360 * 0.4;
        Arc arcFinal = new Arc(centerX, centerY, radius, radius, startAngle, length);
        arcFinal.setType(ArcType.ROUND);
        arcFinal.setFill(Color.ORANGE);

        Text textQuiz = new Text(paneWidth * (4 / 9.0), paneHeight * (1 / 18.0), "Quiz -- 10%");
        Text textMidterm = new Text(paneWidth * (1 / 18.0), paneHeight * (1 / 2.0), "Midterm -- 30%");
        Text textFinal = new Text(paneWidth * (4 / 9.0), paneHeight * (17 / 18.0), "Final -- 40%");
        Text textProject = new Text(paneWidth * (5 / 9.0), paneHeight * (7 / 18.0), "Project -- 20%");

        Pane pane = new Pane(arcProject, arcQuiz, arcMidterm, arcFinal,
                textQuiz, textMidterm, textProject, textFinal);
        Scene scene = new Scene(pane, paneWidth, paneHeight);

        primaryStage.setTitle("Exercise14_13");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}