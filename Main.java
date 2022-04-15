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
        // player 1 choice
        System.out.println("Your cards: " + player1);
        System.out.println("Top card: " + table.topCard());
        System.out.print("Your choice: ");
        int choice = scan.nextInt();
        player1.play(player1.getHand().get(choice - 1), table.topCard());
        // player 1 plays
        // player 2 plays
        // player 1 picks up

    }
}