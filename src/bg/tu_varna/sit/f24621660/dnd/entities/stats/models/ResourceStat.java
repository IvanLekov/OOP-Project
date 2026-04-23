package bg.tu_varna.sit.f24621660.dnd.entities.stats.models;

import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.types.Resource;

public class ResourceStat implements Resource {
    private int maxValue;
    private int currentValue;

    public ResourceStat(int maxValue) {
        this.maxValue = maxValue;
        this.currentValue = maxValue;
    }

    @Override
    public int getValue() {
        return currentValue;
    }

    @Override
    public int getMaxValue() {
        return maxValue;
    }

    @Override
    public void upgrade(int points) {
        this.maxValue += points;
        this.currentValue += points;
    }

    @Override
    public void deplete(int amount) {
        this.currentValue -= amount;
        if (this.currentValue < 0) {
            this.currentValue = 0;
        }
    }

    @Override
    public void restore(int amount) {
        this.currentValue += amount;
        if (this.currentValue > this.maxValue) {
            this.currentValue = this.maxValue;
        }
    }
}
