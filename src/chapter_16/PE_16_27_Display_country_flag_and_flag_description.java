package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * (Display country flag and flag description) Listing 16.4, ComboBoxDemo.java,
 * gives a program that lets the user view a country’s flag image and description
 * by selecting the country from a combo box. The description is a string coded
 * in the program. Rewrite the program to read the text description from a file.
 * Suppose that the descriptions are stored in the files description0.txt, . . . , and
 * description8.txt under the text directory for the nine countries Canada, China,
 * Denmark, France, Germany, India, Norway, United Kingdom, and United States,
 * in this order.
 */
public class PE_16_27_Display_country_flag_and_flag_description extends Application {

    // Declare an array of Strings for flag titles
    private String[] flagTitles = {"Canada", "China", "Denmark",
            "France", "Germany", "India", "Norway", "United Kingdom",
            "United States of America"};

    // Declare an ImageView array for the national flags of 9 countries
    private ImageView[] flagImage = {new ImageView("image/ca.gif"),
            new ImageView("image/china.gif"),
            new ImageView("image/denmark.gif"),
            new ImageView("image/fr.gif"),
            new ImageView("image/germany.gif"),
            new ImageView("image/india.gif"),
            new ImageView("image/norway.gif"),
            new ImageView("image/uk.gif"), new ImageView("image/us.gif")};

    // Declare an array of strings for flag descriptions
    private String[] flagDescription = new String[9];

    // Declare and create a description pane
    private DescriptionPane descriptionPane = new DescriptionPane();

    // Create a combo box for selecting countries
    private ComboBox<String> cbo = new ComboBox<>(); // flagTitles);

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Set text description
        for (int i = 0; i < flagDescription.length; i++) {
            File file = new File("resources/text/description" + i + ".txt");
            try {
                Scanner scanner = new Scanner(file);
                flagDescription[i] = "";
                while (scanner.hasNext()){
                    flagDescription[i] += scanner.nextLine() + "\n";
                }
            } catch (FileNotFoundException e) {
                flagDescription[i] = "Description not found";
            }
        }

        // Set the first country (Canada) for display
        setDisplay(0);

        // Add combo box and description pane to the border pane
        BorderPane pane = new BorderPane();

        BorderPane paneForComboBox = new BorderPane();
        paneForComboBox.setLeft(new Label("Select a country: "));
        paneForComboBox.setCenter(cbo);
        pane.setTop(paneForComboBox);
        cbo.setPrefWidth(400);
        cbo.setValue("Canada");

        ObservableList<String> items =
                FXCollections.observableArrayList(flagTitles);
        cbo.getItems().addAll(items);
        pane.setCenter(descriptionPane);

        // Display the selected country
        cbo.setOnAction(e -> setDisplay(items.indexOf(cbo.getValue())));

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 450, 170);
        primaryStage.setTitle("Chapter16_28"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /** Set display information on the description pane */
    public void setDisplay(int index) {
        descriptionPane.setTitle(flagTitles[index]);
        descriptionPane.setImageView(flagImage[index]);
        descriptionPane.setDescription(flagDescription[index]);
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

    private class DescriptionPane extends BorderPane {
        /** Label for displaying an image and a title */
        private Label lblImageTitle = new Label();

        /** Text area for displaying text */
        private TextArea taDescription = new TextArea();

        public DescriptionPane() {
            // Center the icon and text and place the text under the icon
            lblImageTitle.setContentDisplay(ContentDisplay.TOP);
            lblImageTitle.setPrefSize(200, 100);

            // Set the font in the label and the text field
            lblImageTitle.setFont(new Font("SansSerif", 16));
            taDescription.setFont(new Font("Serif", 14));
            taDescription.setWrapText(true);

            //   taDescription.setWrapText(true);
            //  taDescription.setEditable(false);

            // Create a scroll pane to hold the text area
            ScrollPane scrollPane = new ScrollPane(taDescription);

            // Place label and scroll pane in the border pane
            setLeft(lblImageTitle);
            setCenter(scrollPane);
            setPadding(new Insets(5, 5, 5, 5));
        }

        /** Set the title */
        public void setTitle(String title) {
            lblImageTitle.setText(title);
        }

        /** Set the image view */
        public void setImageView(ImageView icon) {
            lblImageTitle.setGraphic(icon);
        }

        /** Set the text description */
        public void setDescription(String text) {
            taDescription.setText(text);
        }
    }
}
