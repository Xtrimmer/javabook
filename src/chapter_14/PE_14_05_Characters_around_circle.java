package chapter_14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Characters around circle) Write a program that displays a string Welcome to
 * Java around the circle, as shown in Figure 14.44b. Hint: You need to display each
 * character in the right location with appropriate rotation using a loop.
 */
public class PE_14_05_Characters_around_circle extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new CircleTextPane("WELCOME TO JAVA");
        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise14_05");
        primaryStage.show();
    }
}

class CircleTextPane extends Pane {
    private char[] text = {'T','E','X','T'};

    public CircleTextPane() {
    }

    public CircleTextPane(String text) {
        this.text = (text + " ").toCharArray();
    }

    private void paint() {
        getChildren().clear();
        double centerX = (getWidth() / 2) - (getWidth() * 0.05);
        double centerY = (getHeight() / 2) + (getHeight() * 0.05);
        double radius = Math.min(getWidth(), getHeight()) * 0.3;
        int charCount = text.length;
        for (int i = 0; i < charCount; i++) {
            Text ch = new Text("" + text[i]);
            ch.setRotate(90 + (360 / charCount) * i);
            ch.setX(centerX + radius * Math.cos(2 * i * Math.PI / charCount));
            ch.setY(centerY - -radius * Math.sin(2 * i * Math.PI / charCount));
            ch.setFont(Font.font("Times New Roman",
                    FontWeight.EXTRA_BOLD,
                    Math.min(getWidth(),getHeight()) * 0.1 ));
            getChildren().add(ch);
        }
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paint();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paint();
    }
}