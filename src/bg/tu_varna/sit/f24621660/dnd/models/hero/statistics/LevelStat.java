package bg.tu_varna.sit.f24621660.dnd.models.hero.statistics;

public class LevelStat extends Stat {
    private double value;

    public LevelStat(double value) {
        this.value = value;
    }

    @Override
    public void increase(double amount) {
        value += amount;
    }

    public double getValue() {
        return value;
    }
}
