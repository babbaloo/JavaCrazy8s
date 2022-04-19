import java.util.*;
// try linkedHashMap?

public class GameEngine {
    private ArrayList<Hand> gamePlayer;
    private Deck deck;
    private Hand table;
    private Scanner scan;
    private String suit;

    public GameEngine(Scanner scan, Deck deck, int players) {
        this.scan = scan;
        this.deck = deck;
        // create ArrayList to store player Hands and deal 8 cards to each
        gamePlayer = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            gamePlayer.add(new Hand());
            deck.dealCards(gamePlayer.get(i), 8);
        }
        // create table for face up cards of play pile
        table = new Hand();
    }

    public void play() {
        // flip over top card
        deck.dealCard(table);
        // create changing suit variable in order to save chosen suit when an 8 is played
        suit = table.topCard().getSuit();
        // create a loop and counter for player play in order
        int turnCounter = 0;
        while (!winner()) {
            // resets counter to player 0 once all players have played
            if (turnCounter == gamePlayer.size()) {
                turnCounter = 0;
            }
            // show current player hand, face up card, suit(to keep the chosen suit in case of 8) and ask for player choice
            System.out.println("Player " + (turnCounter + 1) + ": 0- pick up, " + gamePlayer.get(turnCounter));
            System.out.println("Top card: " + table.topCard());
            System.out.println("Suit: " + suit);
            System.out.print("Your choice: ");
//            store scanner input in a String, separate at commas for multiple cards, store in String array
//            lines 42 - 80 are for multiple card playing ability
            String input = scan.next();
            if (input.contains(",")) {
                String[] choices = input.split(",");
                System.out.println(Arrays.toString(choices));
                // check that card suit matches face up suit card or value matches value
                if (gamePlayer.get(turnCounter).getHand().get(Integer.parseInt(choices[0]) - 1).getSuit().equals(suit) || gamePlayer.get(turnCounter).getHand().get(Integer.parseInt(choices[0]) - 1).getValue() == table.topCard().getValue()) {
//                    iterate through array to make sure all cards are the same value
                    boolean valuesMatchCheck = true;
                    for (String each : choices) {
                        int comparator = gamePlayer.get(turnCounter).getHand().get(Integer.parseInt(choices[0]) - 1).getValue();
                        if (gamePlayer.get(turnCounter).getHand().get(Integer.parseInt(each) - 1).getValue() != comparator) {
                            System.out.println("You can't play those cards!");
                            valuesMatchCheck = false;
                            continue;
                        }
                    }
                    // get and play each card -> add to table Hand and remove from player Hand
                    if (valuesMatchCheck) {
//                        create a register of cards to be removed. Can't remove yet cause subsequent iterations have changed indices due to deletion
                        int[] register = new int[choices.length];
                        for (int i = 0; i < choices.length; i++) {
                            int each = Integer.parseInt(choices[i]);
                            table.pickUp(gamePlayer.get(turnCounter).getHand().get(each - 1));
                            register[i] = each;
                        }
//                        create null entries where played Cards were in order to not get off by 1 errors when deleting lower indexed elements
                        for (int each : register) {
                            gamePlayer.get(turnCounter).getHand().set((each - 1), null);
                        }
//                        remove null entries from player Hand ArrayList
                        gamePlayer.get(turnCounter).getHand().removeAll(Collections.singleton(null));
                        suit = table.topCard().getSuit();
                        turnCounter++;
                    }
                } else {
                    System.out.println("Suits don't match!");
                }
                continue;
            }
            int playerChoice = Integer.parseInt(input);
//            reject choice if it's out of range
            if (playerChoice < 0 || playerChoice > gamePlayer.get(turnCounter).getHand().size()) {
                System.out.println("Invalid choice");
                continue;
            }
//            check if 0 for pick up
            if (playerChoice == 0) {
                deck.dealCard(gamePlayer.get(turnCounter));
                turnCounter++;
                continue;
            }
//            create local variable to store currently played card and reduce long method call chains
            Card cardPlayed = gamePlayer.get(turnCounter).getHand().get(playerChoice - 1);
            //check card for 8, ask for suit
            if (cardPlayed.getValue() == 8) {
                eight(cardPlayed);
                table.pickUp(cardPlayed);
                gamePlayer.get(turnCounter).remove(cardPlayed);
                turnCounter++;
                continue;
            }
            if (cardPlayed.getValue() == table.topCard().getValue() || cardPlayed.getSuit().equals(suit)) {
                suit(cardPlayed);
                table.pickUp(cardPlayed);
                gamePlayer.get(turnCounter).remove(cardPlayed);
                turnCounter++;
                continue;
            }

            // wrong card
            System.out.println("You can't play that card!");
        }
        System.out.println("Player " + turnCounter + " wins!");
    }

    public void eight(Card card) {
        System.out.println("You played " + card);
        // choose suit and define inside suit variable to save for the next player turn
        System.out.print("Choose your suit: ");
        suit = scan.next();
    }

    public void suit(Card card) {
        switcher(card);
        System.out.println("You played " + card);
        suit = card.getSuit();
    }

    public void queenOfSpades(Card card) {

    }

    public void two(Card card) {

    }

    public void jack(Card card) {

    }

    public void switcher(Card card) {
        switch(card.getValue()) {
            case 2 :
                two(card);
                break;
            case 11 :
                jack(card);
                break;
            case 12 :
                if (card.getSuit().equals("Spades")) {
                    queenOfSpades(card);
                }
        }
    }

    public boolean winner() {
        for (Hand hand : gamePlayer) {
            if (hand.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }
    
}
