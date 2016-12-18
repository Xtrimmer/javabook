package chapter_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * (Game: hangman) Rewrite Programming Exercise 7.35. The program reads the
 * words stored in a text file named hangman.txt. Words are delimited by spaces.
 */
public class PE_12_17_Game_hangman {
    public static void main(String[] args) {
        ArrayList<String> words = loadWordsFromFile("resources\\text\\hangman.txt");
        do {
            playHangman(words);
        } while (doReplay());
    }

    private static ArrayList<String> loadWordsFromFile(String filePath) {
        File file = new File(filePath);
        validateFile(file);
        ArrayList<String> words = new ArrayList<>();
        try (Scanner fileIn = new Scanner(file)){
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

    private static void validateFile(File file) {
        validateFileExists(file);
        validateFileIsFile(file);
        validateFileIsReadable(file);
    }

    private static void validateFileIsReadable(File file) {
        if (!file.canRead()) {
            System.out.println("The application cannot read the file denoted by this pathname.");
            System.exit(0);
        }
    }

    private static void validateFileIsFile(File file) {
        if (!file.isFile()) {
            System.out.println("The file denoted by this pathname is not a normal file.");
            System.exit(0);
        }
    }

    private static void validateFileExists(File file) {
        if (!file.exists()) {
            System.out.println("The file denoted by this pathname does not exist.");
            System.exit(0);
        }
    }

    public static void playHangman(ArrayList<String> words) {
        Scanner scanner = new Scanner(System.in);
        String word = words.get((int) (Math.random() * words.size()));
        boolean[] wordMask = new boolean[word.length()];
        int misses = 0;
        do {
            System.out.print("(Guess) Enter a letter in word " + maskWord(word, wordMask) + " > ");
            char guess = scanner.nextLine().charAt(0);
            misses += checkLetter(guess, word, wordMask);
        } while (!isWordGuessed(wordMask));
        System.out.print("The word is " + word + ". You missed " + misses + " time");
        System.out.println(misses > 1 ? "s" : "");
    }

    public static String maskWord(String word, boolean[] wordMask) {
        String output = "";
        for (int i = 0; i < wordMask.length; i++) {
            if (wordMask[i]) output += word.charAt(i);
            else output +="*";
        }
        return output;
    }

    public static int checkLetter(char letter, String word, boolean[] wordMask) {
        int score = 1;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                if (wordMask[i]){
                    System.out.println(letter + " is already in the word");
                    return 0;
                } else {
                    wordMask[i] = true;
                    score = 0;
                }
            }
        }
        return score;
    }

    public static boolean isWordGuessed(boolean[] mask) {
        boolean guessed = true;
        for (boolean aMask : mask) {
            guessed &= aMask;
        }
        return guessed;
    }

    public static boolean doReplay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to guess another word? Enter y or n> ");
        return scanner.nextLine().toLowerCase().charAt(0) == 'y';
    }
}






























