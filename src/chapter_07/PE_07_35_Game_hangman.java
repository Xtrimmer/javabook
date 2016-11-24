package chapter_07;

import java.util.Scanner;

/**
 * (Game: hangman) Write a hangman game that randomly generates a word and
 * prompts the user to guess one letter at a time, as shown in the sample run. Each
 * letter in the word is displayed as an asterisk. When the user makes a correct
 * guess, the actual letter is then displayed. When the user finishes a word, display
 * the number of misses and ask the user whether to continue to play with another
 * word. Declare an array to store words, as follows:
 *
 *      // Add any words you wish in this array
 *      String[] words = {"write", "that", ...};
 *
 *      (Guess) Enter a letter in word ******* > p
 *      (Guess) Enter a letter in word p****** > r
 *      (Guess) Enter a letter in word pr**r** > p
 *      p is already in the word
 *      (Guess) Enter a letter in word pr**r** > o
 *      (Guess) Enter a letter in word pro*r** > g
 *      (Guess) Enter a letter in word progr** > n
 *      n is not in the word
 *      (Guess) Enter a letter in word progr** > m
 *      (Guess) Enter a letter in word progr*m > a
 *      The word is program. You missed 1 time
 *      Do you want to guess another word? Enter y or n>
 */
public class PE_07_35_Game_hangman {
    public static void main(String[] args) {
        // Add any words you wish in this array
        String[] words = {"time", "issue", "year", "side", "people", "kind", "way", "head", "day", "house",
                "man", "service", "thing", "friend", "woman", "father", "life", "power", "child", "hour",
                "world", "game", "school", "line", "state", "end", "family", "member", "student", "law",
                "group", "car", "country", "city", "problem", "community", "hand", "name", "part", "president",
                "place", "team", "case", "minute", "week", "idea", "company", "kid", "system", "body",
                "program", "information", "question", "back", "work", "parent", "government", "face", "number", "others",
                "night", "level", "Mr", "office", "point", "door", "home", "health", "water", "person",
                "room", "art", "mother", "war", "area", "history", "money", "party", "storey", "result",
                "fact", "change", "month", "morning", "lot", "reason", "right", "research", "study", "girl",
                "book", "guy", "eye", "food", "job", "moment", "word", "air", "business", "teacher"};
        do {
            playHangman(words);
        } while (doReplay());
    }

    public static void playHangman(String[] words) {
        Scanner scanner = new Scanner(System.in);
        String word = words[(int) (Math.random() * words.length)];
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






























