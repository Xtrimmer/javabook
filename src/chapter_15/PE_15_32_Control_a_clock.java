package chapter_15;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * (Control a clock) Modify Listing 14.21, ClockPane.java, to add the animation
 * into this class and add two methods start() and stop() to start and stop the
 * clock. Write a program that lets the user control the clock with the Start and
 * Stop buttons, as shown in Figure 15.36a.
 */

public class PE_15_32_Control_a_clock extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
        HBox hBox = new HBox(10, startButton, stopButton);
        hBox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.CENTER);
        ClockPane clockPane = new ClockPane();

        startButton.setOnAction(event -> clockPane.start());
        stopButton.setOnAction(event -> clockPane.stop());
        BorderPane pane = new BorderPane(clockPane, null, null, hBox, null);
        Scene scene = new Scene(pane, 250, 300);

        primaryStage.setTitle("Exercise15_32");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class ClockPane extends Pane {
        private int hour;
        private int minute;
        private int second;
        // Clock pane's width and height
        private double w = 250, h = 250;
        final Timeline animation = new Timeline(
                new KeyFrame(
                        Duration.millis(1000),
                        event -> {
                            incrementClock();
                            paintClock();
                        }
                )
        );

        /**
         * Construct a default clock with the current time
         */
        public ClockPane() {
            animation.setCycleCount(Timeline.INDEFINITE);
            setCurrentTime();
        }

        /**
         * Construct a clock with specified hour, minute, and second
         */
        public ClockPane(int hour, int minute, int second) {
            animation.setCycleCount(Timeline.INDEFINITE);
            this.hour = hour;
            this.minute = minute;
            this.second = second;
            paintClock();
        }

        /**
         * Return hour
         */
        public int getHour() {
            return hour;
        }

        /**
         * Set a new hour
         */
        public void setHour(int hour) {
            this.hour = hour;
            paintClock();
        }

        /**
         * Return minute
         */
        public int getMinute() {
            return minute;
        }

        /**
         * Set a new minute
         */
        public void setMinute(int minute) {
            this.minute = minute;
            paintClock();
        }

        /**
         * Return second
         */
        public int getSecond() {
            return second;
        }

        /**
         * Set a new second
         */
        public void setSecond(int second) {
            this.second = second;
            paintClock();
        }

        /**
         * Return clock pane's width
         */
        public double getW() {
            return w;
        }

        /**
         * Set clock pane's width
         */
        public void setW(double w) {
            this.w = w;
            paintClock();
        }

        /**
         * Return clock pane's height
         */
        public double getH() {
            return h;
        }

        /**
         * Set clock pane's height
         */
        public void setH(double h) {
            this.h = h;
            paintClock();
        }

        /* Set the current time for the clock */
        public void setCurrentTime() {
            // Construct a calendar for the current date and time
            Calendar calendar = new GregorianCalendar();

            // Set current hour, minute and second
            this.hour = calendar.get(Calendar.HOUR_OF_DAY);
            this.minute = calendar.get(Calendar.MINUTE);
            this.second = calendar.get(Calendar.SECOND);

            paintClock(); // Repaint the clock
        }

        /**
         * Paint the clock
         */
        protected void paintClock() {
            // Initialize clock parameters
            double clockRadius = Math.min(w, h) * 0.8 * 0.5;
            double centerX = w / 2;
            double centerY = h / 2;

            // Draw circle
            Circle circle = new Circle(centerX, centerY, clockRadius);
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            Text t1 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
            Text t2 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
            Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
            Text t4 = new Text(centerX - 3, centerY + clockRadius - 3, "6");

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

            getChildren().clear();
            getChildren().addAll(circle, t1, t2, t3, t4, sLine, mLine, hLine);
        }

        public void start() {
            animation.play();
        }

        public void stop() {
            animation.stop();
        }

        private void incrementClock() {
            second++;
            int minuteAdder = second / 60;
            second %= 60;
            minute += minuteAdder;
            int hourAdder = minute / 60;
            minute %= 60;
            hour = (hour + hourAdder) % 12;
        }
    }

}

