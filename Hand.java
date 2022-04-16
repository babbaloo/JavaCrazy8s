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
    // this method created to follow the face up card in the table instance of Hand
    public Card topCard() {
        return hand.get(hand.size() - 1);
    }

    public void play(Card playerChoice, Hand table) {
        if (playerChoice.getValue() == 8) {
            System.out.println("You played 8");
            table.pickUp(playerChoice);
            this.remove(playerChoice);
            return;
        } else if (playerChoice.getValue() == table.topCard().getValue()) {
            System.out.println("You played " + playerChoice.getValue());
            table.pickUp(playerChoice);
            this.remove(playerChoice);
            return;
        } else if (playerChoice.getSuit() == table.topCard().getSuit()) {
            System.out.println("You played " + playerChoice.getSuit());
            table.pickUp(playerChoice);
            this.remove(playerChoice);
            return;
        }
        System.out.println("You can't play that card.");
    }

    @Override
    public String toString() {
        // check if Hand contains Card or else IndexOutOfBounds occurs when another method blindly calls
        while(!hand.isEmpty()) {
            // prints [index + 1]- [Card in Hand], | e.g. 2- Queen of Diamonds, |
            String string = "";
            for (int i = 0; i < hand.size() - 1; i++) {
                string += (i + 1) + "- " + hand.get(i).toString() + ", ";
            }
            string += hand.size() + "- " + hand.get(hand.size() - 1).toString();
            return string;
        }
        return "Your hand is empty!";
    }
}