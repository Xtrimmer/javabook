package chapter_03;

/**
 * (Game: pick a card) Write a program that simulates picking a card from a deck
 * of 52 cards. Your program should display the rank (Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10,
 * Jack, Queen, King) and suit (Clubs, Diamonds, Hearts, Spades) of the card.
 */
public class PE_03_24_Game_pick_a_card {
    public static void main(String args[]) {
        String suitText = null;
        String rankText = "";
        int suit = (int) (Math.random() * 4);
        int rank = (int) (Math.random() * 13) + 2;
        switch (suit) {
            case 0:
                suitText = "Clubs";
                break;
            case 1:
                suitText = "Diamonds";
                break;
            case 2:
                suitText = "Hearts";
                break;
            case 3:
                suitText = "Spades";
                break;
        }
        switch (rank) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                rankText += rank;
                break;
            case 11:
                rankText = "Jack";
                break;
            case 12:
                rankText = "Queen";
                break;
            case 13:
                rankText = "King";
                break;
            case 14:
                rankText = "Ace";
                break;
        }
        System.out.println("The card you picked is " + rankText + " of " + suitText);
    }
}
