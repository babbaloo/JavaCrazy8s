import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // create deck
        Deck deck = new Deck();
        // shuffle deck
        deck.shuffle();
        // create player 1
        Hand player1 = new Hand();
        // deal player 1
        deck.dealCards(player1, 8);
        // create player 2
        Hand player2 = new Hand();
        // deal player 2
        deck.dealCards(player2, 8);
        // create table cards
        Hand table = new Hand();
        // flip top card
        deck.dealCard(table);
        // create scanner
        Scanner scan = new Scanner(System.in);
        while (true) {
            // player 1 choice
            System.out.println("Player 1: 0- pick up, " + player1);
            System.out.println("Top card: " + table.topCard());
            System.out.print("Your choice: ");
            // player 1 plays
            int choice = scan.nextInt();
            if (choice == 0) {
                deck.dealCard(player1);
            } else {
                player1.play(player1.getHand().get(choice - 1), table);
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
            choice = scan.nextInt();
            if (choice == 0) {
                deck.dealCard(player2);
            } else {
                player2.play(player2.getHand().get(choice - 1), table);
            }
            if (player2.getHand().size() == 0) {
                System.out.println("Player 2 wins!");
                break;
            }
        }
    }
}