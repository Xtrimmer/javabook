package chapter_14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * (Game: hangman) Write a program that displays a drawing for the popular hangman
 * game, as shown in Figure 14.48a.
 */
public class PE_14_17_Game_hangman extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        HangmanPane pane = new HangmanPane(10);
        Scene scene = new Scene(pane, 330, 420);

        primaryStage.setTitle("Exercise14_17");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

class HangmanPane extends Pane {

    private int level = 0;
    private ArrayList<Shape> shapes = new ArrayList<>();

    public HangmanPane() {
        init();
    }

    public HangmanPane(int level) {
        this();
        setLevel(level);
        addShapes();
    }

    public void incrementLevel(){
        setLevel(level + 1);
        addShapes();
    }

    private void addShapes() {
        getChildren().clear();
        for (int i = 0; i < level; i++) {
            getChildren().add(shapes.get(i));
        }
    }

    private void setLevel(int level) {
        if (level >= 0 && level <= shapes.size()) {
            this.level = level;
        } else {
            this.level = 0;
        }
    }

    private void init() {
        String style = "-fx-fill: transparent; -fx-stroke: black";
        Arc arc0 = new Arc();
        arc0.centerXProperty().bind(widthProperty().multiply(3 / 11.0));
        arc0.centerYProperty().bind(heightProperty().multiply(13 / 14.0));
        arc0.radiusXProperty().bind(widthProperty().multiply(2 / 11.0));
        arc0.radiusYProperty().bind(heightProperty().multiply(1 / 14.0));
        arc0.setStartAngle(0);
        arc0.setLength(180);
        arc0.setStyle(style);
        shapes.add(arc0);

        Line line1 = new Line();
        line1.startXProperty().bind(widthProperty().multiply(3 / 11.0));
        line1.startYProperty().bind(heightProperty().multiply(6 / 7.0));
        line1.endXProperty().bind(widthProperty().multiply(3 / 11.0));
        line1.endYProperty().bind(heightProperty().multiply(1 / 14.0));
        shapes.add(line1);

        Line line2 = new Line();
        line2.startXProperty().bind(widthProperty().multiply(3 / 11.0));
        line2.startYProperty().bind(heightProperty().multiply(1 / 14.0));
        line2.endXProperty().bind(widthProperty().multiply(8 / 11.0));
        line2.endYProperty().bind(heightProperty().multiply(1 / 14.0));
        shapes.add(line2);

        Line line3 = new Line();
        line3.startXProperty().bind(widthProperty().multiply(8 / 11.0));
        line3.startYProperty().bind(heightProperty().multiply(1 / 14.0));
        line3.endXProperty().bind(widthProperty().multiply(8 / 11.0));
        line3.endYProperty().bind(heightProperty().multiply(3 / 14.0));
        shapes.add(line3);

        Ellipse ellipse4 = new Ellipse();
        ellipse4.centerXProperty().bind(widthProperty().multiply(8 / 11.0));
        ellipse4.centerYProperty().bind(heightProperty().multiply(2 / 7.0));
        ellipse4.radiusXProperty().bind(widthProperty().multiply(1 / 11.0));
        ellipse4.radiusYProperty().bind(heightProperty().multiply(1 / 14.0));
        ellipse4.setStyle(style);
        shapes.add(ellipse4);

        Line line5 = new Line();
        line5.startXProperty().bind(widthProperty().multiply(8 / 11.0));
        line5.startYProperty().bind(heightProperty().multiply(5 / 14.0));
        line5.endXProperty().bind(widthProperty().multiply(8 / 11.0));
        line5.endYProperty().bind(heightProperty().multiply(9 / 14.0));
        shapes.add(line5);

        Line line6 = new Line();
        line6.startXProperty().bind(widthProperty().multiply(8 / 11.0));
        line6.startYProperty().bind(heightProperty().multiply(5 / 14.0));
        line6.endXProperty().bind(widthProperty().multiply(6 / 11.0));
        line6.endYProperty().bind(heightProperty().multiply(1 / 2.0));
        shapes.add(line6);

        Line line7 = new Line();
        line7.startXProperty().bind(widthProperty().multiply(8 / 11.0));
        line7.startYProperty().bind(heightProperty().multiply(5 / 14.0));
        line7.endXProperty().bind(widthProperty().multiply(10 / 11.0));
        line7.endYProperty().bind(heightProperty().multiply(1 / 2.0));
        shapes.add(line7);

        Line line8 = new Line();
        line8.startXProperty().bind(widthProperty().multiply(8 / 11.0));
        line8.startYProperty().bind(heightProperty().multiply(9 / 14.0));
        line8.endXProperty().bind(widthProperty().multiply(6 / 11.0));
        line8.endYProperty().bind(heightProperty().multiply(11 / 14.0));
        shapes.add(line8);

        Line line9 = new Line();
        line9.startXProperty().bind(widthProperty().multiply(8 / 11.0));
        line9.startYProperty().bind(heightProperty().multiply(9 / 14.0));
        line9.endXProperty().bind(widthProperty().multiply(10 / 11.0));
        line9.endYProperty().bind(heightProperty().multiply(11 / 14.0));
        shapes.add(line9);
    }
}