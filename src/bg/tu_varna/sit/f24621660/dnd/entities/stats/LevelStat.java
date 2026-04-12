package bg.tu_varna.sit.f24621660.dnd.entities.stats;

import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.Stat;

public class LevelStat implements Stat {
    private int level;

    public LevelStat(int startLevel) {
        this.level = startLevel;
    }

    @Override
    public int getValue() {
        return level;
    }

    // Има само своя специфичен метод. Няма излишен increase(amount)!
    public void levelUp() {
        this.level++;
    }
}