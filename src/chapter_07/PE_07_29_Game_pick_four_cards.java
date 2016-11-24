package chapter_07;

/**
 * (Game: pick four cards) Write a program that picks four cards from a deck of 52
 * cards and computes their sum. An Ace, King, Queen, and Jack represent 1, 13,
 * 12, and 11, respectively. Your program should display the number of picks that
 * yields the sum of 24.
 */
public class PE_07_29_Game_pick_four_cards {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 49; i++) {
            for (int j = i + 1; j < 50; j++) {
                for (int k = j + 1; k < 51; k++) {
                    for (int l = k + 1; l < 52; l++) {
                        if (((i % 13) + 1) + ((j % 13) + 1) + ((k % 13) + 1) + ((l % 13) + 1) == 24) {
                            System.out.println("[" + getValue(i) + getSuit(i) + ", " +
                                    getValue(j) + getSuit(j) + ", " +
                                    getValue(k) + getSuit(k) + ", " +
                                    getValue(l) + getSuit(l) + "]");
                            count++;
                        }
                    }

                }

            }

        }
        System.out.println("There are " + count + " combinations of 4 cards that add up to 24.");
    }

    public static String getSuit(int card) {
        int s = card / 13;
        switch (s) {
            case 0:
                return "S";
            case 1:
                return "H";
            case 2:
                return "D";
            case 3:
                return "C";
            default:
                return "Unknown";
        }
    }

    public static String getValue(int card) {
        int v = card % 13;
        switch (v) {
            case 0:
                return "A";
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return (v + 1) + "";
            case 10:
                return "J";
            case 11:
                return "Q";
            case 12:
                return "K";
            default:
                return "Unknown";
        }
    }
}
