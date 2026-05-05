package bg.tu_varna.sit.f24621660.dnd.combat;

import java.util.Random;

public class TurnManager {
    private final Random random;
    private boolean heroTurn;

    public TurnManager() {
        this.random = new Random();

        this.heroTurn = random.nextBoolean();
    }

    public boolean isHeroTurn() {
        return heroTurn;
    }
    public void passTurn() {
        this.heroTurn = !this.heroTurn;
    }

}
