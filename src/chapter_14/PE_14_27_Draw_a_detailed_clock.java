package chapter_14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * (Draw a detailed clock) Modify the ClockPane class in Section 14.12 to draw
 * the clock with more details on the hours and minutes, as shown in Figure 14.52a.
 */
public class PE_14_27_Draw_a_detailed_clock extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        PE_14_27_ClockPane clockPane = new PE_14_27_ClockPane(22, 44, 37);
        clockPane.setPrefWidth(400);
        clockPane.setPrefHeight(400);
        StackPane stackPane = new StackPane(
                new Text(clockPane.getHour() + ":" + clockPane.getMinute() + ":" + clockPane.getSecond())
        );
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10));
        pane.setTop(clockPane);
        pane.setBottom(stackPane);

        Scene scene = new Scene(pane);

        primaryStage.setTitle("Welcome to Java");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

class PE_14_27_ClockPane extends Pane {
    private int hour;
    private int minute;
    private int second;

    /** Construct a default clock with the current time*/
    public PE_14_27_ClockPane() {
        setCurrentTime();
    }

    /** Construct a clock with specified hour, minute, and second */
    public PE_14_27_ClockPane(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /** Return hour */
    public int getHour() {
        return hour;
    }

    /** Set a new hour */
    public void setHour(int hour) {
        this.hour = hour;
        paintClock();
    }

    /** Return minute */
    public int getMinute() {
        return minute;
    }

    /** Set a new minute */
    public void setMinute(int minute) {
        this.minute = minute;
        paintClock();
    }

    /** Return second */
    public int getSecond() {
        return second;
    }

    /** Set a new second */
    public void setSecond(int second) {
        this.second = second;
        paintClock();
    }

    /** Set the current time for the clock */
    public void setCurrentTime() {
        // Construct a calendar for the current date and time
        Calendar calendar = new GregorianCalendar();

        // Set current hour, minute and second
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);

        paintClock(); // Repaint the clock
    }

    /** Paint the clock */
    private void paintClock() {
        getChildren().clear();
        // Initialize clock parameters
        double minSize = Math.min(getWidth(), getHeight());
        double clockRadius = minSize * 0.8 * 0.5;
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;

        // Draw circle
        Circle circle = new Circle(centerX, centerY, clockRadius);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);

        // Draw tick marks
        for (int i = 6; i <= 360; i += 6) {
            double startX = centerX + clockRadius * Math.sin(Math.toRadians(i));
            double startY = centerY - clockRadius * Math.cos(Math.toRadians(i));
            double endX;
            double endY;
            if (i % 30 == 0) {
                endX = centerX + clockRadius * 0.90 * Math.sin(Math.toRadians(i));
                endY = centerY - clockRadius * 0.90 * Math.cos(Math.toRadians(i));
                double textX = centerX + clockRadius * 0.80 * Math.sin(Math.toRadians(i));
                double textY = centerY - clockRadius * 0.80 * Math.cos(Math.toRadians(i));
                Text text = new Text(textX - (minSize / 100), textY + (minSize / 100), i / 30 + "");
                text.setFont(new Font(minSize / 25));
                getChildren().add(text);
            } else {
                endX = centerX + clockRadius * 0.95 * Math.sin(Math.toRadians(i));
                endY = centerY - clockRadius * 0.95 * Math.cos(Math.toRadians(i));
            }
            Line line = new Line(startX, startY, endX, endY);
            getChildren().add(line);
        }

        // Draw second hand
        double sLength = clockRadius * 0.8;
        double secondX = centerX + sLength *
                Math.sin(second * (2 * Math.PI / 60));
        double secondY = centerY - sLength *
                Math.cos(second * (2 * Math.PI / 60));
        Line sLine = new Line(centerX, centerY, secondX, secondY);
        sLine.setStroke(Color.RED);

        // Draw minute hand
        double mLength = clockRadius * 0.65;
        double xMinute = centerX + mLength *
                Math.sin(minute * (2 * Math.PI / 60));
        double minuteY = centerY - mLength *
                Math.cos(minute * (2 * Math.PI / 60));
        Line mLine = new Line(centerX, centerY, xMinute, minuteY);
        mLine.setStroke(Color.BLUE);

        // Draw hour hand
        double hLength = clockRadius * 0.5;
        double hourX = centerX + hLength *
                Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        double hourY = centerY - hLength *
                Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        Line hLine = new Line(centerX, centerY, hourX, hourY);
        hLine.setStroke(Color.GREEN);


        getChildren().addAll(circle, sLine, mLine, hLine);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paintClock();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paintClock();
    }
}
