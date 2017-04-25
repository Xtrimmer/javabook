package chapter_20;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * (Game: hangman) Programming Exercise 7.35 presents a console version of the
 * popular hangman game. Write a GUI program that lets a user play the game. The
 * user guesses a word by entering one letter at a time, as shown in Figure 20.18.
 * If the user misses seven times, a hanging man swings. Once a word is finished,
 * the user can press the Enter key to continue to guess another word.
 */
public class PE_20_07_Game_hangman extends Application {

    private static final Double WIDTH_RATIO = 18.0;
    private static final Double HEIGHT_RATIO = 14.0;
    private static final Double SCALE_FACTOR = 30.0;

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HangmanPane pane = new HangmanPane();
        Scene scene = new Scene(pane, WIDTH_RATIO * SCALE_FACTOR, HEIGHT_RATIO * SCALE_FACTOR);

        primaryStage.setTitle("Exercise20_07");
        primaryStage.setScene(scene);
        primaryStage.show();
        pane.requestFocus();
    }

    private class HangmanPane extends Pane {

        private final HangmanGame game = new HangmanGame();
        private final ArrayList<Shape> shapes = new ArrayList<>();
        private final Group hangingManGroup = new Group();
        private Text text1;
        private Text text2;
        private Timeline swingAnimation;
        private Rotate rotation;

        public HangmanPane() {
            initializeGame();
        }

        private void addShapes(int count) {
            hangingManGroup.getChildren().removeAll(shapes);
            for (int i = 0; i < count; i++) {
                hangingManGroup.getChildren().add(shapes.get(i));
            }
        }

        private void checkLetter(KeyEvent keyEvent) {
            if (keyEvent.getCode().isLetterKey()) {
                char letter = Character.toLowerCase(keyEvent.getText().charAt(0));
                if (game.checkLetter(letter)) {
                    text1.setText(game.getMaskedWord());
                } else {
                    text2.setText(game.getMissedLetters());
                    addShapes(game.getWrongGuessCount());
                }
                if (game.isGameOver()) {
                    setGameEnd();
                    if (!game.isWordGuessed()) swingAnimation.play();
                }
            }
        }

        private void initializeGame() {
            String style = "-fx-fill: transparent; -fx-stroke: black";
            Arc arc0 = new Arc();
            arc0.centerXProperty().bind(widthProperty().multiply(3 / WIDTH_RATIO));
            arc0.centerYProperty().bind(heightProperty().multiply(13 / HEIGHT_RATIO));
            arc0.radiusXProperty().bind(widthProperty().multiply(2 / WIDTH_RATIO));
            arc0.radiusYProperty().bind(heightProperty().multiply(1 / HEIGHT_RATIO));
            arc0.setStartAngle(0);
            arc0.setLength(180);
            arc0.setStyle(style);
            getChildren().add(arc0);

            Line line1 = new Line();
            line1.startXProperty().bind(widthProperty().multiply(3 / WIDTH_RATIO));
            line1.startYProperty().bind(heightProperty().multiply(12 / HEIGHT_RATIO));
            line1.endXProperty().bind(widthProperty().multiply(3 / WIDTH_RATIO));
            line1.endYProperty().bind(heightProperty().multiply(1 / HEIGHT_RATIO));
            getChildren().add(line1);

            Line line2 = new Line();
            line2.startXProperty().bind(widthProperty().multiply(3 / WIDTH_RATIO));
            line2.startYProperty().bind(heightProperty().multiply(1 / HEIGHT_RATIO));
            line2.endXProperty().bind(widthProperty().multiply(8 / WIDTH_RATIO));
            line2.endYProperty().bind(heightProperty().multiply(1 / HEIGHT_RATIO));
            getChildren().add(line2);

            Line line3 = new Line();
            line3.startXProperty().bind(widthProperty().multiply(8 / WIDTH_RATIO));
            line3.startYProperty().bind(heightProperty().multiply(1 / HEIGHT_RATIO));
            line3.endXProperty().bind(widthProperty().multiply(8 / WIDTH_RATIO));
            line3.endYProperty().bind(heightProperty().multiply(2 / HEIGHT_RATIO));
            shapes.add(line3);

            Ellipse ellipse4 = new Ellipse();
            ellipse4.centerXProperty().bind(widthProperty().multiply(8 / WIDTH_RATIO));
            ellipse4.centerYProperty().bind(heightProperty().multiply(3 / HEIGHT_RATIO));
            ellipse4.radiusXProperty().bind(widthProperty().multiply(1 / WIDTH_RATIO));
            ellipse4.radiusYProperty().bind(heightProperty().multiply(1 / HEIGHT_RATIO));
            ellipse4.setStyle(style);
            shapes.add(ellipse4);

            Line line5 = new Line();
            line5.startXProperty().bind(widthProperty().multiply(8 / WIDTH_RATIO));
            line5.startYProperty().bind(heightProperty().multiply(4 / HEIGHT_RATIO));
            line5.endXProperty().bind(widthProperty().multiply(8 / WIDTH_RATIO));
            line5.endYProperty().bind(heightProperty().multiply(8 / HEIGHT_RATIO));
            shapes.add(line5);

            Line line6 = new Line();
            line6.startXProperty().bind(widthProperty().multiply(8 / WIDTH_RATIO));
            line6.startYProperty().bind(heightProperty().multiply(4 / HEIGHT_RATIO));
            line6.endXProperty().bind(widthProperty().multiply(6 / WIDTH_RATIO));
            line6.endYProperty().bind(heightProperty().multiply(6 / HEIGHT_RATIO));
            shapes.add(line6);

            Line line7 = new Line();
            line7.startXProperty().bind(widthProperty().multiply(8 / WIDTH_RATIO));
            line7.startYProperty().bind(heightProperty().multiply(4 / HEIGHT_RATIO));
            line7.endXProperty().bind(widthProperty().multiply(10 / WIDTH_RATIO));
            line7.endYProperty().bind(heightProperty().multiply(6 / HEIGHT_RATIO));
            shapes.add(line7);

            Line line8 = new Line();
            line8.startXProperty().bind(widthProperty().multiply(8 / WIDTH_RATIO));
            line8.startYProperty().bind(heightProperty().multiply(8 / HEIGHT_RATIO));
            line8.endXProperty().bind(widthProperty().multiply(6 / WIDTH_RATIO));
            line8.endYProperty().bind(heightProperty().multiply(10 / HEIGHT_RATIO));
            shapes.add(line8);

            Line line9 = new Line();
            line9.startXProperty().bind(widthProperty().multiply(8 / WIDTH_RATIO));
            line9.startYProperty().bind(heightProperty().multiply(8 / HEIGHT_RATIO));
            line9.endXProperty().bind(widthProperty().multiply(10 / WIDTH_RATIO));
            line9.endYProperty().bind(heightProperty().multiply(10 / HEIGHT_RATIO));
            shapes.add(line9);

            text1 = new Text("");
            text1.xProperty().bind(widthProperty().multiply(6 / WIDTH_RATIO));
            text1.yProperty().bind(heightProperty().multiply(12 / HEIGHT_RATIO));

            text2 = new Text("");
            text2.xProperty().bind(widthProperty().multiply(6 / WIDTH_RATIO));
            text2.yProperty().bind(heightProperty().multiply(13 / HEIGHT_RATIO));

            rotation = new Rotate();
            rotation.pivotXProperty().bind(widthProperty().multiply(8 / WIDTH_RATIO));
            rotation.pivotYProperty().bind(heightProperty().multiply(1 / HEIGHT_RATIO));
            hangingManGroup.getTransforms().add(rotation);
            swingAnimation = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 0)),
                    new KeyFrame(Duration.seconds(0.5), new KeyValue(rotation.angleProperty(), 15)),
                    new KeyFrame(Duration.seconds(1.5), new KeyValue(rotation.angleProperty(), -15)),
                    new KeyFrame(Duration.seconds(2.0), new KeyValue(rotation.angleProperty(), 0))
            );
            swingAnimation.setCycleCount(Timeline.INDEFINITE);

            getChildren().addAll(hangingManGroup, text1, text2);
            setGameStart();
        }

        private void playAgain(KeyEvent keyEvent) {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                setGameStart();
            }
        }

        private void setGameEnd() {
            text1.setText(game.getUnmaskedWord());
            text2.setText("To Continue the game, press ENTER");
            setOnKeyPressed(this::playAgain);
        }

        private void setGameStart() {
            swingAnimation.stop();
            rotation.setAngle(0);
            text1.setText(game.getNewWord());
            text2.setText("");
            addShapes(game.getWrongGuessCount());
            setOnKeyPressed(this::checkLetter);
        }
    }

    private class HangmanGame {
        final ArrayList<String> words = loadWordsFromFile("resources\\text\\hangman.txt");
        String word;
        String missedLetters = "";
        boolean[] wordMask;
        private int wrongGuessCount = 0;

        public boolean checkLetter(char letter) {
            boolean guessed = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter) {
                    wordMask[i] = true;
                    guessed = true;
                }
            }
            if (!guessed) {
                missedLetters += letter;
                wrongGuessCount++;
            }
            return guessed;
        }

        public String getMaskedWord() {
            return "Guess a word: " + maskWord();
        }

        public String getMissedLetters() {
            return "Missed letters: " + missedLetters;
        }

        public String getNewWord() {
            word = words.get((int) (Math.random() * words.size()));
            wrongGuessCount = 0;
            wordMask = new boolean[word.length()];
            missedLetters = "";
            return "Guess a word: " + maskWord();
        }

        public String getUnmaskedWord() {
            return "The word is: " + word;
        }

        public int getWrongGuessCount() {
            return wrongGuessCount;
        }

        public boolean isGameOver() {
            return isWordGuessed() || wrongGuessCount > 6;
        }

        private boolean isWordGuessed() {
            boolean guessed = true;
            for (boolean aMask : wordMask) {
                guessed &= aMask;
            }
            return guessed;
        }

        private ArrayList<String> loadWordsFromFile(String filePath) {
            File file = new File(filePath);
            validateFile(file);
            ArrayList<String> words = new ArrayList<>();
            try (Scanner fileIn = new Scanner(file)) {
                while (fileIn.hasNext()) {
                    String line = fileIn.next();
                    words.add(line);
                }
            } catch (FileNotFoundException e) {
                System.out.println("source file is not found");
                System.exit(0);
            }
            return words;
        }

        private String maskWord() {
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < wordMask.length; i++) {
                if (wordMask[i]) output.append(word.charAt(i));
                else output.append('*');
            }
            return output.toString();
        }

        private void validateFile(File file) {
            validateFileExists(file);
            validateFileIsFile(file);
            validateFileIsReadable(file);
        }

        private void validateFileExists(File file) {
            if (!file.exists()) {
                System.out.println("The file denoted by this pathname does not exist.");
                System.exit(0);
            }
        }

        private void validateFileIsFile(File file) {
            if (!file.isFile()) {
                System.out.println("The file denoted by this pathname is not a normal file.");
                System.exit(0);
            }
        }

        private void validateFileIsReadable(File file) {
            if (!file.canRead()) {
                System.out.println("The application cannot read the file denoted by this pathname.");
                System.exit(0);
            }
        }
    }
}
