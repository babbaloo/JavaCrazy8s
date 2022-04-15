public class Card {
    private String suit;
    private int value;
    private String number;

    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
        switch (value) {
            case 1:
            number = "Ace";
            break;
            case 11:
            number = "Jack";
            break;
            case 12:
            number = "Queen";
            break;
            case 13:
            number = "King";
            break;
            default:
            number = Integer.toString(value);
        }
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public void stringToCard(String string) {
        
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Card card = (Card) obj;
        if (this.getSuit() == card.getSuit()) {
            if (this.getValue() == card.getValue()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return number + " of " + suit;
    }
}