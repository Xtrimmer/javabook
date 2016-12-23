package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * (Display a calendar) Write a program that displays the calendar for the current
 * month. You can use the Prior and Next buttons to show the calendar of the
 * previous or next month. Display the dates in the current month in black and
 * display the dates in the previous month and next month in gray, as shown in
 * Figure 16.48.
 */
public class PE_16_29_Display_a_calendar extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        CalendarPane pane = new CalendarPane(primaryStage);
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise16_29");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class CalendarPane extends BorderPane {
        private final String[] MONTH_NAMES = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        private final String[] WEEKDAY_NAMES = {"Sunday", "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday"};
        private final Stage stage;
        private final Calendar calendar = new GregorianCalendar();
        private final Button buttonPrior = new Button("Prior");
        private final Button buttonNext = new Button("Next");
        private final Label calendarHeader = new Label();

        public CalendarPane(Stage stage) {
            this.stage = stage;
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            setCenter(createCalendar());
            setBottom(createButtonPane());
            setButtonAction();
        }

        private void addCurrentMonthDays(GridPane gridPane) {
            int numberOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            int row;
            int col;
            for (int day = 1; day <= numberOfDaysInMonth; day++) {
                col = (day + firstDayOfMonth - 1) % 7;
                row = (day + firstDayOfMonth - 1) / 7 + 1;
                gridPane.add(new Label(day + ""), col, row);
            }
        }

        private void addMonth(int monthsToAdd) {
            calendar.add(Calendar.MONTH, monthsToAdd);
            setCenter(createCalendar());
            stage.sizeToScene();
        }

        private void addNextMonthDays(GridPane gridPane) {
            int numberOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            int col = (firstDayOfMonth + numberOfDaysInMonth) % 7;
            if (col == 0) return;
            int row = ((firstDayOfMonth + numberOfDaysInMonth) / 7) + 1;
            int day = 1;
            for (int i = col; i < 7; i++) {
                Label label = new Label(day++ + "");
                label.setTextFill(Color.LIGHTGREY);
                gridPane.add(label, col++, row);
            }
        }

        private void addPreviousMonthDays(GridPane gridPane) {
            Calendar lastMonth = new GregorianCalendar(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            lastMonth.add(Calendar.MONTH, -1);
            int numberOfDaysInMonth = lastMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
            int firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            int col = 0;
            int day = numberOfDaysInMonth - firstDayOfMonth + 1;
            for (int i = 0; i < firstDayOfMonth; i++) {
                Label label = new Label(day++ + "");
                label.setTextFill(Color.LIGHTGREY);
                gridPane.add(label, col++, 1);
            }
        }

        private void addWeekdayHeaders(GridPane calendarBody) {
            for (int i = 0; i < 7; i++) {
                String weekday = WEEKDAY_NAMES[i];
                calendarBody.add(new Label(weekday), i, 0);
            }
        }

        private GridPane createBody() {
            GridPane gridPane = new GridPane();
            gridPane.setVgap(5);
            gridPane.setHgap(5);
            gridPane.setPadding(new Insets(5));
            addWeekdayHeaders(gridPane);
            addCurrentMonthDays(gridPane);
            addPreviousMonthDays(gridPane);
            addNextMonthDays(gridPane);
            return gridPane;
        }

        private HBox createButtonPane() {
            HBox hBox = new HBox(buttonPrior, buttonNext);
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(5);
            hBox.setPadding(new Insets(5));
            return hBox;
        }

        private BorderPane createCalendar() {
            BorderPane borderPane = new BorderPane();
            borderPane.setPadding(new Insets(5));
            borderPane.setTop(createHeader());
            borderPane.setCenter(createBody());
            return borderPane;
        }

        private StackPane createHeader() {
            calendarHeader.setText(
                    MONTH_NAMES[calendar.get(Calendar.MONTH)] + ", " + calendar.get(Calendar.YEAR));
            StackPane stackPane = new StackPane(calendarHeader);
            stackPane.setPadding(new Insets(5));
            return stackPane;
        }

        private void setButtonAction() {
            buttonPrior.setOnAction(event -> addMonth(-1));
            buttonNext.setOnAction(event -> addMonth(1));
        }
    }
}
