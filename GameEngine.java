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
        while (!winner()) {
            // increases counter and resets at player 0 once all players have played
            if (turnCounter == gamePlayer.size()) {
                turnCounter = 0;
            }
            // show current player hand, face up card, and ask for choice
            System.out.println("Player " + (turnCounter + 1) + ": 0- pick up, " + gamePlayer.get(turnCounter));
            System.out.println("Top card: " + table.topCard());
            System.out.print("Your choice: ");
            int playerChoice = scan.nextInt();
            if (playerChoice == 0) {
                deck.dealCard(gamePlayer.get(turnCounter));
                turnCounter++;
                continue;
            }
            //check card for 8, ask for suit
            if (gamePlayer.get(turnCounter).getHand().get(playerChoice - 1).getValue() == 8) {
                System.out.println("You played 8");
                System.out.print("Choose your suit: ");
                String chosenSuit = scan.nextLine();
                table.pickUp(gamePlayer.get(turnCounter).getHand().get(playerChoice - 1));
                gamePlayer.get(turnCounter).remove(gamePlayer.get(turnCounter).getHand().get(playerChoice - 1));
                turnCounter++;
                continue;
            }
            //check card for same value
            if (gamePlayer.get(turnCounter).getHand().get(playerChoice - 1).getValue() == table.topCard().getValue()) {
                System.out.println("You played " + gamePlayer.get(turnCounter).getHand().get(playerChoice - 1).getValue());
                // check if 2
                if (gamePlayer.get(turnCounter).getHand().get(playerChoice - 1).getValue() == 2) {
                    System.out.println("You played 2");
                }
                // check if Q of S
                if (gamePlayer.get(turnCounter).getHand().get(playerChoice - 1).getValue() == 12 && gamePlayer.get(turnCounter).getHand().get(playerChoice - 1).getSuit() == "Spades") {
                    System.out.println("You played Queen of Spades");
                }
                // check if J
                if (gamePlayer.get(turnCounter).getHand().get(playerChoice - 1).getValue() == 11) {
                    System.out.println("You played Jack");
                }
                table.pickUp(gamePlayer.get(turnCounter).getHand().get(playerChoice - 1));
                gamePlayer.get(turnCounter).remove(gamePlayer.get(turnCounter).getHand().get(playerChoice - 1));
                turnCounter++;
                continue;
            }
            // check card for same suit
            if (gamePlayer.get(turnCounter).getHand().get(playerChoice - 1).getSuit() == table.topCard().getSuit()) {
                System.out.println("You played " + gamePlayer.get(turnCounter).getHand().get(playerChoice - 1).getSuit());
                // check if 2
                if (gamePlayer.get(turnCounter).getHand().get(playerChoice - 1).getValue() == 2) {
                    System.out.println("You played 2");
                }
                // check if Q of S
                if (gamePlayer.get(turnCounter).getHand().get(playerChoice - 1).getValue() == 12 && gamePlayer.get(turnCounter).getHand().get(playerChoice - 1).getSuit() == "Spades") {
                    System.out.println("You played Queen of Spades");
                }
                // check if J
                if (gamePlayer.get(turnCounter).getHand().get(playerChoice - 1).getValue() == 11) {
                    System.out.println("You played Jack");
                }
                table.pickUp(gamePlayer.get(turnCounter).getHand().get(playerChoice - 1));
                gamePlayer.get(turnCounter).remove(gamePlayer.get(turnCounter).getHand().get(playerChoice - 1));
                turnCounter++;
                continue;
            }
            // wrong card
            System.out.println("You can't play that card!");
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
