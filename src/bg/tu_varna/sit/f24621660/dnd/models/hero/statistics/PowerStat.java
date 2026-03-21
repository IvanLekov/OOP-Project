package bg.tu_varna.sit.f24621660.dnd.models.hero.statistics;

public class PowerStat extends Stat {
    private double value;

    public PowerStat(double value) {
        this.value = value;
    }

    @Override
    public void increase(double amount){
        value += amount;
    }
}
