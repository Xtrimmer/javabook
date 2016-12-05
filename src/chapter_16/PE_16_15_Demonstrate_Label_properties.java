package chapter_16;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * (Demonstrate Label properties) Write a program to let the user dynamically
 * set the properties contentDisplay and graphicTextGap, as shown in
 * Figure 16.42b.
 */
public class PE_16_15_Demonstrate_Label_properties extends Application {
    private ControlPane controlPane;
    private LabelPane labelPane;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        controlPane = new ControlPane();
        labelPane = new LabelPane();

        controlPane.setContentDisplay(labelPane.getContentDisplay());
        controlPane.setGraphicTextGap(labelPane.getGraphicTextGap());
        controlPane.setComboBoxChangedAction(
                event -> labelPane.setContentDisplay(controlPane.getContentDisplay()));
        controlPane.setTextFieldEvent(
                event -> labelPane.setGraphicTextGap(controlPane.getGraphicTextGap()));

        BorderPane pane = new BorderPane();
        pane.setCenter(labelPane);
        pane.setTop(controlPane);
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise16_15");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class LabelPane extends StackPane {
        private final Label label;

        public LabelPane() {
            label = new Label("Grapes");
            label.setGraphic(new ImageView("image/grapes.gif"));
            getChildren().add(label);
            setPadding(new Insets(30));
        }

        public ContentDisplay getContentDisplay() {
            return label.getContentDisplay();
        }

        public void setContentDisplay(ContentDisplay value) {
            label.setContentDisplay(value);
        }

        public double getGraphicTextGap() {
            return label.getGraphicTextGap();
        }

        public void setGraphicTextGap(double value) {
            label.setGraphicTextGap(value);
        }
    }

    private class ControlPane extends HBox {
        final ComboBox<ContentDisplay> comboBoxContentDisplay;
        final TextField textFieldGraphicTextGap;

        public ControlPane() {
            ObservableList<ContentDisplay> comboBoxItems = FXCollections.observableArrayList(ContentDisplay.values());
            comboBoxContentDisplay = new ComboBox<>(comboBoxItems);
            textFieldGraphicTextGap = new TextField();
            textFieldGraphicTextGap.setPrefColumnCount(2);
            getChildren().addAll(
                    new Label("contentDisplay"), comboBoxContentDisplay,
                    new Label("graphicTextGap"), textFieldGraphicTextGap
            );
            setAlignment(Pos.CENTER);
            setSpacing(5);
            setPadding(new Insets(5));
        }

        public ContentDisplay getContentDisplay() {
            return comboBoxContentDisplay.getValue();
        }

        public void setContentDisplay(ContentDisplay contentDisplay) {
            comboBoxContentDisplay.setValue(contentDisplay);
        }

        public double getGraphicTextGap() {
            try {
                return Double.parseDouble(textFieldGraphicTextGap.getText());
            } catch (Exception e) {
                textFieldGraphicTextGap.setText("5");
                return 5;
            }
        }

        public void setGraphicTextGap(double value) {
            textFieldGraphicTextGap.setText(value + "");
        }

        public void setComboBoxChangedAction(EventHandler<ActionEvent> event) {
            comboBoxContentDisplay.setOnAction(event);
        }

        public void setTextFieldEvent(EventHandler<ActionEvent> event) {
            textFieldGraphicTextGap.setOnAction(event);
        }
    }
}
