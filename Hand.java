import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void pickUp(Card card) {
        hand.add(card);
    }

    public Card topCard() {
        return hand.get(hand.size() - 1);
    }

    public void play(Card choice, Card topCard) {
        if (choice.getValue() == 8) {
            System.out.println("You played 8");
            return;
        }
        if (choice.getValue() == topCard.getValue()) {
            System.out.println("You played " + choice.getValue());
            return;
        }
        if (choice.getSuit() == topCard.getSuit()) {
            System.out.println("You played " + choice.getSuit());
            return;
        }
        System.out.println("Can't play that card");
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < hand.size() - 1; i++) {
            string += (i + 1) + ": " + hand.get(i).toString() + ", ";
        }
        string += hand.size() + ": " + hand.get(hand.size() - 1).toString();
        return string;
    }
}