package chapter_16;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * (Use ComboBox and ListView) Write a program that demonstrates selecting
 * items in a list. The program uses a combo box to specify a selection mode, as
 * shown in Figure 16.43a. When you select items, they are displayed in a label
 * below the list.
 */
public class PE_16_16_Use_ComboBox_and_ListView extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        SelectionPane pane = new SelectionPane();
        Scene scene = new Scene(pane, 300, 400);

        primaryStage.setTitle("Exercise16_16");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class SelectionPane extends BorderPane {
        private final Label labelDisplay;
        private ComboBox<SelectionMode> comboBoxSelectionMode;
        private ListView<String> listViewCountries;

        public SelectionPane() {
            HBox selectionBar = generateSelectionModeBar();
            ScrollPane countrySelectPane = generateCountrySelectionPane();
            labelDisplay = new Label();
            labelDisplay.setPadding(new Insets(5));

            setActionEvents();

            setTop(selectionBar);
            setCenter(countrySelectPane);
            setBottom(labelDisplay);
        }

        private void setActionEvents() {
            comboBoxSelectionMode.setOnAction(event ->
                    listViewCountries.getSelectionModel().setSelectionMode(comboBoxSelectionMode.getValue())
            );
            listViewCountries.getSelectionModel().selectedItemProperty().addListener(
                    observable -> {
                        String message = "Selected item";
                        int size = listViewCountries.getSelectionModel().getSelectedIndices().size();
                        message += size > 1 ? "s are " : " is ";
                        for (int i = 0; i < size; i++) {
                            message += listViewCountries.getSelectionModel().getSelectedItems().get(i) + " ";
                        }
                        labelDisplay.setText(message);
                    }
            );
        }

        private ScrollPane generateCountrySelectionPane() {
            String[] countriesArray = {
                    "Canada", "China", "Denmark", "France", "Germany", "India", "Japan",
                    "Korea", "Malaysia", "Norway", "United Kingdom", "USA", "Vietnam"
            };
            ObservableList<String> countriesList = FXCollections.observableArrayList(countriesArray);
            listViewCountries = new ListView<>(countriesList);
            ScrollPane scrollPane = new ScrollPane(listViewCountries);
            scrollPane.setFitToWidth(true);
            return scrollPane;
        }

        private HBox generateSelectionModeBar() {
            ObservableList<SelectionMode> modes =
                    FXCollections.observableArrayList(SelectionMode.values());
            comboBoxSelectionMode = new ComboBox<>(modes);
            comboBoxSelectionMode.setValue(SelectionMode.SINGLE);
            HBox hBox = new HBox(new Label("Choose Selection Mode:"), comboBoxSelectionMode);
            hBox.setPadding(new Insets(5));
            hBox.setSpacing(5);
            hBox.setAlignment(Pos.CENTER_LEFT);
            return hBox;
        }
    }
}
