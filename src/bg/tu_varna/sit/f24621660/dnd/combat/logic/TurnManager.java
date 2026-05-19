package bg.tu_varna.sit.f24621660.dnd.combat.logic;

import java.util.Random;

public class TurnManager {
    private boolean heroTurn;

    public TurnManager(Random random) {
        this.heroTurn = random.nextBoolean();
    }

    public boolean isHeroTurn() {
        return heroTurn;
    }

    public void passTurn() {
        this.heroTurn = !this.heroTurn;
    }
}