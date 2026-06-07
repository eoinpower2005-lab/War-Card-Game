import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Game {
    Deck deck = new Deck();
    List<String> playerOneHand = deck.getPlayerOneHand();
    List<String> playerTwoHand = deck.getPlayerTwoHand();
    List<String> cardPile = deck.pile;

    Player playerOne = new Player("Stephen", playerOneHand);
    Player playerTwo = new Player("Eoin", playerTwoHand);

    int round_counter = 1;

    public void startGame(){
        System.out.println("This is the card game called War!");

        deck.createDeck();
        deck.shuffleDeck(deck.deck);
        deck.dealCards(deck.shuffledDeck);

        boolean playing = true;
        int playerOneHasCards = playerOneHand.size();
        int playerTwoHasCards = playerTwoHand.size();


        round(cardPile);

    }

    public void round(List<String> cardPile) {

        while (!playerOneHand.isEmpty() && !playerTwoHand.isEmpty()) {
            cardPile.clear();
            String p1Card = deck.getPlayerOneCard();
            String p2Card = deck.getPlayerTwoCard();

            cardPile.add(p1Card);
            cardPile.add(p2Card);
            compareCard(p1Card, p2Card);
        }

        if (playerOneHand.isEmpty()){
            System.out.println(playerOne.getName() + " has no cards left. " + playerTwo.getName() + " wins!");

        } else {
            System.out.println(playerTwo.getName() + " has no cards left. " + playerOne.getName() + " wins!");

        }
    }

    public void compareCard(String playerOneCard, String playerTwoCard) {
        int playerOneValue = deck.getCardValue(playerOneCard);
        int playerTwoValue = deck.getCardValue(playerTwoCard);

        System.out.println(playerOne.getName() + "'s" + " card value: " + deck.getCardValue(playerOneCard));
        System.out.println(playerTwo.getName() + "'s" + " card value: " + deck.getCardValue(playerTwoCard));

        if (playerOneValue == playerTwoValue) {
            handleWar();
        } else if (playerOneValue > playerTwoValue) {
            System.out.println(playerOne.getName() + " wins round " + round_counter);
            Collections.shuffle(cardPile);
            playerOneHand.addAll(cardPile);
            cardPile.clear();
            round_counter++;
        } else {
            System.out.println(playerTwo.getName() + " wins round " + round_counter);
            Collections.shuffle(cardPile);
            playerTwoHand.addAll(cardPile);
            cardPile.clear();
            round_counter++;
        }
    }

    public void handleWar() {
        System.out.println("WAR");

        if (playerOne.getNumberOfCards() < 4 && playerTwo.getNumberOfCards() < 4){
            System.out.println("not enough cards to play war");
            System.exit(0);
        }

        if (playerOne.getNumberOfCards() < 4 && playerTwo.getNumberOfCards() >=4) {
            System.out.println(playerOne.getName() + " has no cards. " + playerTwo.getName() + " wins!");
            // player 2 wins the pile
            playerTwoHand.addAll(cardPile);
        } else if (playerOne.getNumberOfCards() >= 4 && playerTwo.getNumberOfCards() < 4) {
            System.out.println(playerTwo.getName() + " has no cards. " + playerOne.getName() + " wins!");
            //player 1 wins the pile
            playerOneHand.addAll(cardPile);
        } else {
            for (int i = 0; i < 3; i++) {
                cardPile.add(playerOne.getFirstCard());
                cardPile.add(playerTwo.getFirstCard());
            }
            String p1WarCard = playerOne.getFirstCard();
            String p2WarCard = playerTwo.getFirstCard();
            cardPile.add(p1WarCard);
            cardPile.add(p2WarCard);
            compareCard(p1WarCard, p2WarCard);
        }

    }
}