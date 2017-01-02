package chapter_17;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * (Address book) Write a program that stores, retrieves, adds, and updates addresses
 * as shown in Figure 17.20. Use a fixed-length string for storing each attribute in the
 * address. Use random access file for reading and writing an address. Assume that
 * the size of name, street, city, state, and zip is 32, 32, 20, 2, 5 bytes, respectively.
 */
public class PE_17_09_Address_book extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        AddressBookPane pane = new AddressBookPane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise17_09");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class AddressBookPane extends VBox {
        private final AddressBook addressBook = new AddressBook();
        private final TextField textFieldName = new TextField();
        private final TextField textFieldStreet = new TextField();
        private final TextField textFieldCity = new TextField();
        private final TextField textFieldState = new TextField();
        private final TextField textFieldZip = new TextField();
        private final Button buttonAdd = new Button("Add");
        private final Button buttonFirst = new Button("First");
        private final Button buttonNext = new Button("Next");
        private final Button buttonPrevious = new Button("Previous");
        private final Button buttonLast = new Button("Last");
        private final Button buttonUpdate = new Button("Update");

        private AddressBookPane() {
            setPadding(new Insets(10));
            setSpacing(5);
            getChildren().add(createBasicPane("Name", textFieldName));
            getChildren().add(createBasicPane("Street", textFieldStreet));
            getChildren().add(createCityPane());
            getChildren().add(createControlPane());
            displayAddress(addressBook.getFirstAddress());
        }

        private HBox createBasicPane(String name, TextField textFieldName) {
            HBox hBox = new HBox();
            hBox.setSpacing(5);
            HBox.setHgrow(textFieldName, Priority.ALWAYS);
            hBox.setAlignment(Pos.CENTER_LEFT);
            Label label = new Label(name);
            label.setPrefWidth(50);
            hBox.getChildren().addAll(label, textFieldName);
            return hBox;
        }

        private HBox createCityPane() {
            HBox hBox = new HBox();
            hBox.setSpacing(5);
            HBox.setHgrow(textFieldCity, Priority.ALWAYS);
            hBox.setAlignment(Pos.CENTER_LEFT);
            Label labelCity = new Label("City");
            labelCity.setPrefWidth(50);
            Label labelState = new Label("State");
            Label labelZip = new Label("Zip");
            textFieldState.setPrefColumnCount(AddressBook.STATE_SIZE);
            textFieldZip.setPrefColumnCount(AddressBook.ZIP_SIZE);
            hBox.getChildren().addAll(labelCity, textFieldCity, labelState, textFieldState, labelZip, textFieldZip);
            return hBox;
        }

        private HBox createControlPane() {
            HBox hBox = new HBox();
            hBox.setSpacing(5);
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().addAll(buttonAdd, buttonFirst, buttonNext, buttonPrevious, buttonLast, buttonUpdate);
            setButtonActions();
            return hBox;
        }

        private void displayAddress(AddressEntry address) {
            textFieldName.setText(address.name);
            textFieldStreet.setText(address.street);
            textFieldCity.setText(address.city);
            textFieldState.setText(address.state);
            textFieldZip.setText(address.zip);
        }

        private AddressEntry getAddress() {
            AddressEntry addressEntry = new AddressEntry();
            addressEntry.name = textFieldName.getText();
            addressEntry.street = textFieldStreet.getText();
            addressEntry.city = textFieldCity.getText();
            addressEntry.state = textFieldState.getText();
            addressEntry.zip = textFieldZip.getText();
            return addressEntry;
        }

        private void setButtonActions() {
            buttonAdd.setOnAction(event -> addressBook.addAddressEntry(getAddress()));
            buttonFirst.setOnAction(event -> displayAddress(addressBook.getFirstAddress()));
            buttonNext.setOnAction(event -> displayAddress(addressBook.getNextAddress()));
            buttonPrevious.setOnAction(event -> displayAddress(addressBook.getPreviousAddress()));
            buttonLast.setOnAction(event -> displayAddress(addressBook.getLastAddress()));
            buttonUpdate.setOnAction(event -> addressBook.updateAddress(getAddress()));
        }
    }

    private class AddressBook {
        public static final int NAME_SIZE = 32;
        public static final int STREET_SIZE = 32;
        public static final int CITY_SIZE = 20;
        public static final int STATE_SIZE = 2;
        public static final int ZIP_SIZE = 5;
        private static final int ADDRESS_SIZE = NAME_SIZE + STREET_SIZE + CITY_SIZE + STATE_SIZE + ZIP_SIZE;
        private final File file;
        private RandomAccessFile addresses;
        private int currentIndex = 0;

        public AddressBook() {
            file = new File("resources/data/AddressBook.dat");
            try {
                addresses = new RandomAccessFile(file, "rw");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public void addAddressEntry(AddressEntry entry) {
            writeAddressEntry(entry, getAddressCount());
        }

        public AddressEntry getFirstAddress() {
            setCurrentIndex(0);
            return getAddress(currentIndex);
        }

        public AddressEntry getLastAddress() {
            setCurrentIndex(getAddressCount() - 1);
            return getAddress(currentIndex);
        }

        public AddressEntry getNextAddress() {
            setCurrentIndex(currentIndex + 1);
            return getAddress(currentIndex);
        }

        public AddressEntry getPreviousAddress() {
            setCurrentIndex(currentIndex - 1);
            return getAddress(currentIndex);
        }

        public void updateAddress(AddressEntry entry) {
            writeAddressEntry(entry, currentIndex);
        }

        private AddressEntry getAddress(int index) {

            AddressEntry addressEntry = new AddressEntry();
            try {
                addresses.seek(index * ADDRESS_SIZE);
                addressEntry = new AddressEntry();
                byte[] name = new byte[NAME_SIZE];
                byte[] street = new byte[STREET_SIZE];
                byte[] city = new byte[CITY_SIZE];
                byte[] state = new byte[STATE_SIZE];
                byte[] zip = new byte[ZIP_SIZE];

                addresses.read(name, 0, NAME_SIZE);
                addresses.read(street, 0, STREET_SIZE);
                addresses.read(city, 0, CITY_SIZE);
                addresses.read(state, 0, STATE_SIZE);
                addresses.read(zip, 0, ZIP_SIZE);

                addressEntry.name = new String(name);
                addressEntry.street = new String(street);
                addressEntry.city = new String(city);
                addressEntry.state = new String(state);
                addressEntry.zip = new String(zip);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addressEntry;
        }

        private int getAddressCount() {
            try {
                return (int) addresses.length() / ADDRESS_SIZE;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }

        private void setCurrentIndex(int currentIndex) {
            if (currentIndex < 0) {
                this.currentIndex = 0;
            } else if (currentIndex >= getAddressCount()) {
                this.currentIndex = getAddressCount() - 1;
            } else {
                this.currentIndex = currentIndex;
            }
        }

        private byte[] stringToFixedByteArray(String string, int size) {
            byte[] array = new byte[size];
            System.arraycopy(string.getBytes(), 0, array, 0, string.length());
            return array;
        }

        private void writeAddressEntry(AddressEntry entry, int index) {
            byte[] name = stringToFixedByteArray(entry.name, NAME_SIZE);
            byte[] street = stringToFixedByteArray(entry.street, STREET_SIZE);
            byte[] city = stringToFixedByteArray(entry.city, CITY_SIZE);
            byte[] state = stringToFixedByteArray(entry.state, STATE_SIZE);
            byte[] zip = stringToFixedByteArray(entry.zip, ZIP_SIZE);

            try {
                addresses.seek(index * ADDRESS_SIZE);
                addresses.write(name);
                addresses.write(street);
                addresses.write(city);
                addresses.write(state);
                addresses.write(zip);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class AddressEntry {
        String name;
        String street;
        String city;
        String state;
        String zip;
    }
}
