import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Game {
    Deck deck = new Deck();
    List<String> playerOneHand = deck.getPlayerOneHand();
    List<String> playerTwoHand = deck.getPlayerTwoHand();
    List<String> cardPile = deck.pile;
    public boolean gameOver = false;

    Player playerOne = new Player("Player One", playerOneHand);
    Player playerTwo = new Player("Player Two", playerTwoHand);

    int round_counter = 0;

    public void startGame(){
        gameOver = false;
        System.out.println("This is the card game called War!");

        deck.createDeck();
        deck.shuffleDeck(deck.deck);
        deck.dealCards(deck.shuffledDeck);

        //round(cardPile);
    }

    public String round(Label card1, Label card2, Text p1Cards, Text p2Cards, Text roundResult) {

        if (gameOver) {
            return "Game Over";
        }

        if (playerOneHand.isEmpty()){
            gameOver = true;
            System.out.println(playerOne.getName() + " has no cards left. " + playerTwo.getName() + " wins!");
            return playerOne.getName() + " has no cards left. " + playerTwo.getName() + " wins!";
        }

        if (playerTwoHand.isEmpty()){
            gameOver = true;
            System.out.println(playerTwo.getName() + " has no cards left. " + playerOne.getName() + " wins!");
            return playerTwo.getName() + " has no cards left. " + playerOne.getName() + " wins!";
        }

        cardPile.clear();
        String p1Card = deck.getPlayerOneCard();
        String p2Card = deck.getPlayerTwoCard();

        card1.setText(p1Card);
        card2.setText(p2Card);

        String num1 = String.valueOf(playerOne.getNumberOfCards());
        String num2 = String.valueOf(playerTwo.getNumberOfCards());
        p1Cards.setText("Player 1 Cards: " + num1);
        p2Cards.setText("Player 2 Cards: " + num2);
        System.out.println("Player 1 Cards: " + num1);
        System.out.println("Player 2 Cards: " + num2);

        cardPile.add(p1Card);
        cardPile.add(p2Card);

        String rResult = compareCard(p1Card, p2Card);
        roundResult.setText("Round Result: " + rResult);

        return "Round: " + round_counter;
    }

    public String compareCard(String playerOneCard, String playerTwoCard) {
        String response = " ";
        int playerOneValue = deck.getCardValue(playerOneCard);
        int playerTwoValue = deck.getCardValue(playerTwoCard);

        System.out.println(playerOne.getName() + "'s" + " card value: " + deck.getCardValue(playerOneCard));
        System.out.println(playerTwo.getName() + "'s" + " card value: " + deck.getCardValue(playerTwoCard));

        if (playerOneValue == playerTwoValue) {
            response = "Going to War! " + handleWar();
        } else if (playerOneValue > playerTwoValue) {
            //System.out.println(playerOne.getName() + " wins round " + round_counter);
            response = playerOne.getName() + " wins!";
            Collections.shuffle(cardPile);
            playerOneHand.addAll(cardPile);
            cardPile.clear();
            round_counter++;
        } else {
            //System.out.println(playerTwo.getName() + " wins round " + round_counter);
            response = playerTwo.getName() + " wins!";
            Collections.shuffle(cardPile);
            playerTwoHand.addAll(cardPile);
            cardPile.clear();
            round_counter++;
        }
        return response;
    }

    public String handleWar() {
        String result = " ";
        System.out.println("WAR");

        if (playerOne.getNumberOfCards() < 4 && playerTwo.getNumberOfCards() < 4){
            gameOver = true;
            return "Not enough cards to play War!";
        }

        if (playerOne.getNumberOfCards() < 4 && playerTwo.getNumberOfCards() >=4) {
            playerTwoHand.addAll(cardPile);
            cardPile.clear();
            gameOver = true;
            return playerOne.getName() + " insufficient cards. " + playerTwo.getName() + " wins!";
        }

        if (playerOne.getNumberOfCards() >= 4 && playerTwo.getNumberOfCards() < 4) {
            playerOneHand.addAll(cardPile);
            cardPile.clear();
            gameOver = true;
            return playerTwo.getName() + " insufficient cards. " + playerOne.getName() + " wins!";
        }

        for (int i = 0; i < 3; i++) {
            cardPile.add(playerOne.getFirstCard());
            cardPile.add(playerTwo.getFirstCard());
        }
        String p1WarCard = playerOne.getFirstCard();
        String p2WarCard = playerTwo.getFirstCard();
        cardPile.add(p1WarCard);
        cardPile.add(p2WarCard);

        return compareCard(p1WarCard, p2WarCard);
}

    public boolean isGameOver() {
        return gameOver;
    }
}