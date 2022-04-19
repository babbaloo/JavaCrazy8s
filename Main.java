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
    }
}