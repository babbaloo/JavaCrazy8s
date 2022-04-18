import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
// try linkedHashMap?

public class GameEngine {
    private ArrayList<Hand> gamePlayer;
    private Deck deck;
    private Hand table;
    private Scanner scan;

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
        // create a loop and counter for player play in order
        int turnCounter = 0;
//        create changing suit variable in order to save chosen suit when an 8 is played
        String suit = table.topCard().getSuit();
        while (!winner()) {
            // increases counter and resets at player 0 once all players have played
            if (turnCounter == gamePlayer.size()) {
                turnCounter = 0;
            }

            // show current player hand, face up card, and ask for choice
            System.out.println("Player " + (turnCounter + 1) + ": 0- pick up, " + gamePlayer.get(turnCounter));
            System.out.println("Top card: " + table.topCard());
            System.out.println("Suit: " + suit);
            System.out.print("Your choice: ");
            int playerChoice = Integer.parseInt(scan.next());
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
//            create local variable to store currently played card
            Card cardPlayed = gamePlayer.get(turnCounter).getHand().get(playerChoice - 1);
            //check card for 8, ask for suit
            if (cardPlayed.getValue() == 8) {
                System.out.println("You played " + cardPlayed);
//                choose suit and define inside suit variable to save for the next player turn
                System.out.print("Choose your suit: ");
                suit = scan.next();
                table.pickUp(cardPlayed);
                gamePlayer.get(turnCounter).remove(cardPlayed);
                turnCounter++;
                continue;
            }
            //check card for same value
            if (cardPlayed.getValue() == table.topCard().getValue()) {
                System.out.println("You played " + cardPlayed);
                // check if 2
                if (cardPlayed.getValue() == 2) {
                    System.out.println("You played 2");
                }
                // check if Q of S
                if (cardPlayed.getValue() == 12 && cardPlayed.getSuit().equals("Spades")) {
                    System.out.println("You played Q of S");
                }
                // check if J
                if (cardPlayed.getValue() == 11) {
                    System.out.println("You played Jack");
                }
                table.pickUp(cardPlayed);
//                reset suit variable no 8 was played
                suit = table.topCard().getSuit();
                gamePlayer.get(turnCounter).remove(cardPlayed);
                turnCounter++;
                continue;
            }
            // check card for same suit
            if (cardPlayed.getSuit().equals(suit)) {
                System.out.println("You played " + cardPlayed);
                // check if 2
                if (cardPlayed.getValue() == 2) {
                    System.out.println("You played 2");
                }
                // check if Q of S
                if (cardPlayed.getValue() == 12 && cardPlayed.getSuit().equals("Spades")) {
                    System.out.println("You played Q of S");
                }
                // check if J
                if (cardPlayed.getValue() == 11) {
                    System.out.println("You played Jack");
                }
                table.pickUp(cardPlayed);
                // reset suit variable no 8 was played
                suit = table.topCard().getSuit();
                gamePlayer.get(turnCounter).remove(cardPlayed);
                turnCounter++;
                continue;
            }
            // wrong card
            System.out.println("You can't play that card!");
        }
        System.out.println("Player " + turnCounter + " wins!");
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
