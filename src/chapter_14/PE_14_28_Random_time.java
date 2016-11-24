package chapter_14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * (Random time) Modify the ClockPane class with three new Boolean properties—
 * hourHandVisible, minuteHandVisible, and secondHandVisible—and
 * their associated accessor and mutator methods. You can use the set methods to
 * make a hand visible or invisible. Write a test program that displays only the hour
 * and minute hands. The hour and minute values are randomly generated. The hour
 * is between 0 and 11, and the minute is either 0 or 30, as shown in Figure 14.52b.
 */
public class PE_14_28_Random_time extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        int hour = (int)(Math.random() * 12);
        int minute = ((int)(Math.random() * 2)) == 0 ? 0 : 30;
        PE_14_28_ClockPane clockPane = new PE_14_28_ClockPane(hour, minute, 0);
        clockPane.setPrefWidth(400);
        clockPane.setPrefHeight(400);
        clockPane.setSecondHandVisible(false);
        StackPane stackPane = new StackPane(
                new Text(clockPane.getHour() + ":" + clockPane.getMinute() + ":" + clockPane.getSecond())
        );
        BorderPane pane = new BorderPane(stackPane, clockPane, null, null, null);
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise14_28");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

class PE_14_28_ClockPane extends Pane {
    private int hour;
    private int minute;
    private int second;
    private boolean hourHandVisible = true;
    private boolean minuteHandVisible = true;
    private boolean secondHandVisible = true;

    /** Construct a default clock with the current time*/
    public PE_14_28_ClockPane() {
        setCurrentTime();
    }

    /** Construct a clock with specified hour, minute, and second */
    public PE_14_28_ClockPane(int hour, int minute, int second) {
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

    public boolean isHourHandVisible() {
        return hourHandVisible;
    }

    public void setHourHandVisible(boolean hourHandVisible) {
        this.hourHandVisible = hourHandVisible;
    }

    public boolean isMinuteHandVisible() {
        return minuteHandVisible;
    }

    public void setMinuteHandVisible(boolean minuteHandVisible) {
        this.minuteHandVisible = minuteHandVisible;
    }

    public boolean isSecondHandVisible() {
        return secondHandVisible;
    }

    public void setSecondHandVisible(boolean secondHandVisible) {
        this.secondHandVisible = secondHandVisible;
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
        double clockRadius =
                Math.min(getWidth(), getHeight()) * 0.8 * 0.5;
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;

        // Draw circle
        Circle circle = new Circle(centerX, centerY, clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text t1 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
        Text t2 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
        Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
        Text t4 = new Text(centerX - 3, centerY + clockRadius - 3, "6");

        getChildren().addAll(circle, t1, t2, t3, t4);

        // Draw second hand
        if (secondHandVisible) {
            double sLength = clockRadius * 0.8;
            double secondX = centerX + sLength *
                    Math.sin(second * (2 * Math.PI / 60));
            double secondY = centerY - sLength *
                    Math.cos(second * (2 * Math.PI / 60));
            Line sLine = new Line(centerX, centerY, secondX, secondY);
            sLine.setStroke(Color.RED);
            getChildren().add(sLine);
        }

        // Draw minute hand
        if (minuteHandVisible) {
            double mLength = clockRadius * 0.65;
            double xMinute = centerX + mLength *
                    Math.sin(minute * (2 * Math.PI / 60));
            double minuteY = centerY - mLength *
                    Math.cos(minute * (2 * Math.PI / 60));
            Line mLine = new Line(centerX, centerY, xMinute, minuteY);
            mLine.setStroke(Color.BLUE);
            getChildren().add(mLine);
        }

        // Draw hour hand
        if (hourHandVisible) {
            double hLength = clockRadius * 0.5;
            double hourX = centerX + hLength *
                    Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
            double hourY = centerY - hLength *
                    Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
            Line hLine = new Line(centerX, centerY, hourX, hourY);
            hLine.setStroke(Color.GREEN);
            getChildren().add(hLine);
        }
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
