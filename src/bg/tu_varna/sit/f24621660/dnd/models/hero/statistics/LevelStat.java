package bg.tu_varna.sit.f24621660.dnd.models.hero.statistics;

public class LevelStat extends Stat {

    public LevelStat(int value) {
        super(value);
    }

    public void levelUp() {
        super.increase(1);
    }
}