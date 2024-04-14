import java.util.ArrayList;
import java.util.List;
import java.util.Random;
class Card {
    private int value;
    private String name;
    public Card(int value, String name) {
        this.value = value;
        this.name = name;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void describe() {
        System.out.println(name);
    }
}
class Deck {
    private List<Card> cards;
    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] names = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
        for (String suit : suits) {
            for (int i = 0; i < 13; i++) {
                Card card = new Card(i + 2, names[i] + " of " + suit);
                cards.add(card);
            }
        }
    }
    public void shuffle() {
        Random rand = new Random();
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }
    public Card draw() {
        if (cards.isEmpty()) return null;
        return cards.remove(0);
    }
}
class Player {
    private List<Card> hand;
    private int score;
    private String name;
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.score = 0;
    }
    public void describe() {
        System.out.println("Player: " + name);
        System.out.println("Score: " + score);
        System.out.println("Cards in hand:");
        for (Card card : hand) {
            card.describe();
        }
    }
    public Card flip() {
        if (hand.isEmpty()) return null;
        return hand.remove(0);
    }
    public void draw(Deck deck) {
        Card card = deck.draw();
        if (card != null) {
            hand.add(card);
        }
    }
    public void incrementScore() {
        score++;
    }
    public int getScore() {
        return score;
    }
}
public class javaFinal {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        for (int i = 0; i < 52; i++) {
            player2.draw(deck);
        }
        for (int i = 0; i < 26; i++) {
            Card card1 = player1.flip();
            Card card2 = player2.flip();
            if (card1 != null && card2 != null) {
                System.out.println("Player 1 flipped: ");
                card1.describe();
                System.out.println("Player 2 flipped: ");
                card2.describe();
                if (card1.getValue() > card2.getValue()) {
                    player1.incrementScore();
                    System.out.println("Player 1 receives a point!");
                } else if (card1.getValue() < card2.getValue()) {
                    player2.incrementScore();
                    System.out.println("Player 2 receives a point!");
                } else {
                    System.out.println("It's a tie!");
                }
            }
        }
        System.out.println("Final Scores:");
        System.out.println("Player 1: " + player1.getScore());
        System.out.println("Player 2: " + player2.getScore());
        if (player1.getScore() > player2.getScore()) {
            System.out.println("Player 1 wins!");
        } else if (player1.getScore() < player2.getScore()) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}