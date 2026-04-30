package bg.tu_varna.sit.f24621660.dnd.combat;

import java.util.Random;

public class TurnManager {
    private final Random random;
    private boolean isHeroTurn;

    public TurnManager() {
        random = new Random();

        isHeroTurn = random.nextBoolean();
    }

    public boolean isHeroTurn() {
        return isHeroTurn;
    }
    public boolean isMonsterTurn() {
        return !isHeroTurn;
    }
}
