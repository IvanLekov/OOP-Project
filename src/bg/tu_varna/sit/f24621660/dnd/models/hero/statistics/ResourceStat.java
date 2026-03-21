package bg.tu_varna.sit.f24621660.dnd.models.hero.statistics;

public class ResourceStat extends Stat{
    private double value;
    private double maxValue;

    public ResourceStat(double maxValue) {
        this.maxValue = maxValue;
        value = maxValue;
    }

    @Override
    public void increase(double amount) {
        value += amount;
    }

    public void decrease(double amount) {
        value -= amount;
    }

    public void increaseMax(double amount) {
        maxValue += amount;
    }

}
