import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
    String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    List<String> deck = new ArrayList<>();
    List<String> shuffledDeck = new ArrayList<>();
    List<String> playerOneHand = new ArrayList<>();
    List<String> playerTwoHand = new ArrayList<>();
    List<String> pile = new ArrayList<>();

    // deck logic
    public void createDeck() {
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " of " + suit);
            }
        }
    }

    public void shuffleDeck(List<String> deck) {
        Collections.shuffle(deck);
        for (String card : deck){
            shuffledDeck.add(card);
        }
    }

    public void dealCards(List<String> shuffledDeck) {
        for (int i = 0; i < shuffledDeck.size(); i+=2) {
            playerOneHand.add(shuffledDeck.get(i));
        }

        for (int j = 1; j < shuffledDeck.size(); j+=2) {
            playerTwoHand.add(shuffledDeck.get(j));
        }

    }

    public List<String> getPlayerOneHand() {
        return playerOneHand;
    }

    public List<String> getPlayerTwoHand() {
        return playerTwoHand;
    }

    public String getPlayerOneCard() {
        return playerOneHand.remove(0);
    }

    public String getPlayerTwoCard() {
        return playerTwoHand.remove(0);
    }

    public int getCardValue(String card) {
        String rank = card.split(" of ")[0];
        if (rank.equals("Jack")) return 11;
        if (rank.equals("Queen")) return 12;
        if (rank.equals("King")) return 13;
        if (rank.equals("Ace")) return 14;
        return Integer.parseInt(rank);
    }
}

