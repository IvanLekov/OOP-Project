package bg.tu_varna.sit.f24621660.dnd.models.hero.statistics;

public class ResourceStat extends Stat{
    private int maxValue;

    public ResourceStat(int maxValue) {
        super(maxValue);
        this.maxValue = maxValue;
    }

    public int getMaxvalue() {
        return maxValue;
    }

    @Override
    public void increase(int amount) {
        super.increase(amount);

        if (this.value > this.maxValue) {
            this.value = this.maxValue;
        }
    }

    public void decrease(int amount) {
        this.value -=amount;

        if (this.value < 0) {
            this.value = 0;
        }
    }

    public void increaseMax (int amount) {
        this.maxValue += amount;
    }
}
