package chapter_14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * (Display images) Write a program that displays four images in a grid pane, as
 * shown in Figure 14.43a(p.578).
 */
public class PE_14_01_Display_images extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image flagUSA = new Image("image/flag1.gif");
        Image flagEngland = new Image("image/flag2.gif");
        Image flagCanada = new Image("image/flag0.gif");
        Image flagChina = new Image("image/flag7.gif");

        ImageView imageViewUSA = new ImageView(flagUSA);
        ImageView imageViewEngland = new ImageView(flagEngland);
        ImageView imageViewCanada = new ImageView(flagCanada);
        ImageView imageViewChina = new ImageView(flagChina);

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.add(imageViewEngland, 0, 0);
        pane.add(imageViewCanada, 1, 0);
        pane.add(imageViewChina, 0, 1);
        pane.add(imageViewUSA, 1, 1);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise14_01");
        primaryStage.show();
    }
}
