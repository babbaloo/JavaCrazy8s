import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Map;
import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) {
        // create deck
        Deck deck = new Deck();
        // shuffle deck
        deck.shuffle();
        // create scanner
        Scanner scan = new Scanner(System.in);
        // ask how many players
        System.out.print("How many players? ");
        int players = scan.nextInt();
        // create gameEngine
        GameEngine engine = new GameEngine(scan, deck, players);
        engine.play();
        /*while (true) {
            // player 1 choice
            System.out.println("Player 1: 0- pick up, " + player1);
            System.out.println("Top card: " + table.topCard());
            System.out.print("Your choice: ");
            // player 1 plays
            int playerChoice = scan.nextInt();
            if (playerChoice == 0) {
                deck.dealCard(player1);
            } else {
                player1.play(player1.getHand().get(playerChoice - 1), table);
            }
            if (player1.getHand().size() == 0) {
                System.out.println("Player 1 wins!");
                break;
            }
            // player 2 choice
            System.out.println("Player 2: 0- pick up, " + player2);
            System.out.println("Top card: " + table.topCard());
            System.out.print("Your choice: ");
            // player 2 plays
            playerChoice = scan.nextInt();
            if (playerChoice == 0) {
                deck.dealCard(player2);
            } else {
                player2.play(player2.getHand().get(playerChoice - 1), table);
            }
            if (player2.getHand().size() == 0) {
                System.out.println("Player 2 wins!");
                break;
            }
        }*/
    }
}