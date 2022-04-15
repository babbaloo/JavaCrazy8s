import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;
    private String[] suit = { "Spades", "Hearts", "Clubs", "Diamonds" };
    private int[] value = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

    public Deck() {
        // construct Deck of Cards
        deck = new ArrayList<>();
        // Loop through suits and values, create Card, add to Deck
        for (int i = 0; i < suit.length; i++) {
            for (int j = 0; j < value.length; j++) {
                Card card = new Card(suit[i], value[j]);
                deck.add(card);
            }
        }
    }

    public void shuffle() {
        Random random = new Random();

        for (int i = 0; i < deck.size(); i++) {
            int r = i + random.nextInt(deck.size() - i);
            Card temp = deck.get(r);
            deck.set(r, deck.get(i));
            deck.set(i, temp);
        }

    }

    public void dealCard(Hand player) {
        player.pickUp(deck.get(deck.size() - 1));
        deck.remove(deck.size() - 1);
    }

    public void dealCards(Hand player, int n) {
        for (int i = 0; i < n; i++) {
            dealCard(player);
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
        
}