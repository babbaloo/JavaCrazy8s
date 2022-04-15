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

    public void remove(Card card) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).equals(card)) {
                hand.remove(i);
            }
        }
    }

    public Card topCard() {
        return hand.get(hand.size() - 1);
    }

    public void play(Card choice, Hand table) {
        if (choice.getValue() == 8) {
            System.out.println("You played 8");
            table.pickUp(choice);
            this.remove(choice);
            return;
        } else if (choice.getValue() == table.topCard().getValue()) {
            System.out.println("You played " + choice.getValue());
            table.pickUp(choice);
            this.remove(choice);
            return;
        } else if (choice.getSuit() == table.topCard().getSuit()) {
            System.out.println("You played " + choice.getSuit());
            table.pickUp(choice);
            this.remove(choice);
            return;
        }
        System.out.println("You can't play that card.");
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < hand.size() - 1; i++) {
            string += (i + 1) + "- " + hand.get(i).toString() + ", ";
        }
        string += hand.size() + "- " + hand.get(hand.size() - 1).toString();
        return string;
    }
}