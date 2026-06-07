import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private String name;

    private List<String> playerHand;

    public Player(String name, List<String> pHand) {
        this.name = name;
        this.playerHand = pHand;
    }

    public String getFirstCard() {
        return playerHand.remove(0);
    }

    public List<String> getPlayerHand(){
        return playerHand;
    }

    public int getNumberOfCards() {
        int count = 0;
        for (int i = 0; i < playerHand.size(); i++) {
            count++;
        }
        return count;
    }

    public String getName() {
        return name;
    }
}


