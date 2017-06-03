package chapter_21;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * (Baby name popularity ranking) Use the data files from Programming
 * Exercise 12.31 to write a program that enables the user to select a year, gender,
 * and enter a name to display the ranking of the name for the selected year and
 * gender, as shown in Figure 21.9. To achieve the best efficiency, create two arrays
 * for boy’s names and girl’s names, respectively. Each array has 10 elements for
 * 10 years. Each element is a map that stores a name and its ranking in a pair
 * with the name as the key. Assume the data files are stored at www.cs.armstrong
 * .edu/liang/data/babynamesranking2001.txt, . . . , and www.cs.armstrong.edu/liang/data/
 * babynamesranking2010.txt.
 */
public class PE_21_11_Baby_name_popularity_ranking extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new BabyNamePane();
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Exercise21_11");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class BabyNamePane extends BorderPane {
        private final TextField textFieldName = new TextField();
        private final ComboBox<String> comboBoxYear = new ComboBox<>();
        private final ComboBox<String> comboBoxGender = new ComboBox<>();
        private final Label label = new Label();
        private final Button button = new Button("Find Ranking");
        private Map<String, Integer>[] boysNames;
        private Map<String, Integer>[] girlsNames;

        public BabyNamePane() {
            setCenter(createControlPane());
            setBottom(label);
            setPadding(new Insets(10));
            populateYears();
            populateGenders();
            setButtonAction();
            initializeNameArrays();
        }

        private Node createControlPane() {
            GridPane gridPane = new GridPane();
            gridPane.setHgap(5);
            gridPane.setVgap(5);
            gridPane.setPadding(new Insets(10));
            gridPane.add(new Label("Select a year:"), 0, 0);
            gridPane.add(comboBoxYear, 1, 0);
            gridPane.add(new Label("Boy or Girl?"), 0, 1);
            gridPane.add(comboBoxGender, 1, 1);
            gridPane.add(new Label("Enter a name:"), 0, 2);
            gridPane.add(textFieldName, 1, 2);
            gridPane.add(button, 1, 3);
            return gridPane;
        }

        private void initializeNameArrays() {
            boysNames = new Map[10];
            girlsNames = new Map[10];
            for (int i = 0; i < 10; i++) {
                boysNames[i] = new HashMap<>();
                girlsNames[i] = new HashMap<>();
                populateMapData(i);
            }
        }

        private void populateGenders() {
            comboBoxGender.getItems().addAll("Male", "Female");
            comboBoxGender.setValue(comboBoxGender.getItems().get(0));
        }

        private void populateMapData(int i) {
            String urlPrefix = "http://www.cs.armstrong.edu/liang/data/babynamesranking";
            String urlPostfix = ".txt";
            int year = i + 2001;
            URL url;
            try {
                url = new URL(urlPrefix + year + urlPostfix);
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    int rank = scanner.nextInt();
                    boysNames[i].put(scanner.next().toLowerCase(), rank);
                    scanner.next();
                    girlsNames[i].put(scanner.next().toLowerCase(), rank);
                    scanner.next();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void populateYears() {
            for (int year = 2001; year <= 2010; year++) {
                comboBoxYear.getItems().add(year + "");
            }
            comboBoxYear.setValue(comboBoxYear.getItems().get(0));
        }

        private void setButtonAction() {
            button.setOnAction(event -> {
                int year = Integer.valueOf(comboBoxYear.getValue());
                int arrayIndex = year - 2001;
                boolean isMale = comboBoxGender.getValue().equals(comboBoxGender.getItems().get(0));
                String name = textFieldName.getText();
                String gender;
                Integer rank;
                if (isMale) {
                    gender = "Boy";
                    rank = boysNames[arrayIndex].get(name.toLowerCase());
                } else {
                    gender = "Girl";
                    rank = girlsNames[arrayIndex].get(name.toLowerCase());
                }
                String message = String.format("%s name %s is ranked #%d in year %d", gender, name, rank, year);
                if (!(rank == null))
                    label.setText(message);
                else
                    label.setText("The name you entered is not ranked.");
            });
        }
    }
}
